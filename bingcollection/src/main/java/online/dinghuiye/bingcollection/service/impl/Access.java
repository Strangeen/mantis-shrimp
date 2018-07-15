package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.dao.BingItemDao;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.entity.BingPullException;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.common.entity.MailAttachment;
import online.dinghuiye.common.entity.MailImage;
import online.dinghuiye.common.util.DateUtil;
import online.dinghuiye.common.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/01/27
 * <p>
 * bing图片收集功能入口
 */
@Service
public class Access {

    private static final Logger logger = LoggerFactory.getLogger(Access.class);

    private final BingAcquirer acquirer;
    private final BingImgSaver saver;
    private final BingItemDao itemDao;
    private final BingLogOper logOper;
    private final MailUtil mailUtil;
    private final BingParam bingParam;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    public Access(BingAcquirer acquirer, BingImgSaver saver, BingItemDao itemDao,
                  BingLogOper logOper, MailUtil mailUtil, BingParam bingParam) {
        this.acquirer = acquirer;
        this.saver = saver;
        this.itemDao = itemDao;
        this.logOper = logOper;
        this.mailUtil = mailUtil;
        this.bingParam = bingParam;
    }

    /**
     * 创建一次收集，包括拉取文件，记录操作日志，以及发送提示邮件
     *
     * @param date       拉取文件所在日期
     * @param imgFile    存放img相关路径信息
     * @param smallWidth 缩略图的宽度
     * @param byHand     是否手动创建，用于log记录
     * @return bing条目对象
     */
    public BingItemEntity create(Date date, BingImageFile imgFile, int smallWidth, boolean byHand) {

        Assert.notNull(date, "date must not null");
        BingItemEntity item;
        try {
            // 拉取文件
            item = bing(date, imgFile, smallWidth);

            // 记录日志
            StringBuilder msg = new StringBuilder()
                    .append(item.getId()).append(" | ")
                    .append(new SimpleDateFormat(BingParam.bing_date_format).format(item.getbDate()))
                    .append(" | ").append(item.getbTitle())
                    .append(" | ").append("desc获取次数：" + descPullCount.get());
            logOper.create("success", msg.toString(), byHand);

            // 发送邮件
            if (!byHand || "true".equals(BingParam.send_when_by_hand))
                // TODO 邮件内容改为模板生成
                mailUtil.sendMail(
                        fromMail, BingParam.to_mail.split(","),
                        item.getbTitle(), concatMailContent(item),
                        getAttachment(item, imgFile.getImgRootPath()));

        } catch (Exception e) {

            if (e instanceof BingPullException) {
                // 记录日志
                logOper.create("fail", e.getMessage(), byHand);

                // 发送邮件
                if (!byHand || "true".equals(BingParam.send_when_by_hand))
                    // TODO 邮件内容改为模板生成
                    mailUtil.sendMail(
                            fromMail, new String[]{BingParam.manage_mail},
                            "Bing收集失败", e.getMessage());
            }
            throw new RuntimeException(e);
        }
        return item;
    }

    /**
     * 默认auto
     */
    public BingItemEntity create(Date date, BingImageFile imgFile, int smallWidth) {
        return create(date, imgFile, smallWidth, false);
    }

    /**
     * 发送邮件
     * @param id item的id
     * @param imgRootPath 图片存储目录
     */
    public void sendMail(Long id, String imgRootPath) {
        BingItemEntity item = itemDao.findOne(id);
        if (item == null) throw new RuntimeException("不存在条目");
        mailUtil.sendMail(
                fromMail, BingParam.to_mail.split(","),
                item.getbTitle(), concatMailContent(item),
                getAttachment(item, imgRootPath));
    }


    /**
     * 获取图片信息(DESC)
     * @param id
     */
    @Transactional
    public String reacquireInfo(Long id) {

        try {

            BingItemEntity item = itemDao.findOne(id);
            Date date = item.getbDate();
            String desc = acquirer.getBingDesc(date);
            item.setbDesc(desc);
            itemDao.save(item);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            String title = item.getbTitle();

            // 记录日志
            logOper.create("success", id + " | " + dateStr + " | " + title + " | 图片信息获取", true);

            return desc;

        } catch (Exception e) {

            logger.error("pull image info error", e);

            // 记录日志
//            if (e instanceof BingPullException)
            logOper.create("fail", e.getMessage(), true);
            throw new RuntimeException(e);
        }
    }


    private String concatMailContent(BingItemEntity item) {
        return new StringBuilder()
                .append("<html><p>").append(item.getbTitle()).append("</p>")
                .append("<p>#[mail-attach]</p>").append(item.getbDesc())
                .append("<style>#hpla,#hpla img{width:inherit!important;")
                .append("color:#333!important}#hplaT{width:354px}</style></html>")
                .toString();
    }

    private MailAttachment getAttachment(BingItemEntity item, String imgRootPath) {
        return new MailImage(
                UUID.randomUUID().toString(),
                new File(imgRootPath + item.getSmallImgUrl()));
    }

    /**
     * 拉取文件功能总成
     *
     * @param date       拉取文件所在日期
     * @param imgFile    存放img相关路径信息
     * @param smallWidth 缩略图的宽度
     * @return bing条目对象
     */
    protected BingItemEntity bing(Date date, BingImageFile imgFile, int smallWidth) {

        Assert.notNull(date, "date must not null");
        try {
//            Date today = DateUtil.now();
//            if (date == null) date = today;

            BingItemEntity item = acquirer.getBingImg(date);
            String desc = "";
            // TODO: 由于bing有时无法获取desc，多尝试几次
            //       可能是由于某些地区的bing没有desc功能，网站转发请求又正好转发到这些地区的服务器上造成的
            int counts = 1;
            for (; counts <= bingParam.getBingDescPullTimes(); counts ++) {
                desc = acquirer.getBingDesc(date);
                if (desc.length() > 50) break;
            }

            if (desc.length() < 50) throw new BingPullException("pull desc error, too short: " + desc.length());

            // 记录获取desc次数
            descPullCount.set(counts);

            String imgRootPath = imgFile.getImgRootPath();
            File img = new File(
                    imgRootPath +
                            new SimpleDateFormat(imgFile.getImgFolderFormat()).format(date) + "/" +
                            imgFile.getImgFileName());
            saver.dealImg(item, img, smallWidth);

            item.setImgLocalUrl(fixImgLocalUrl(item.getImgLocalUrl(), imgRootPath))
                    .setSmallImgUrl(fixImgLocalUrl(item.getSmallImgUrl(), imgRootPath))
                    .setbDesc(desc)
                    .setCreateTime(DateUtil.now());
            itemDao.save(item);
            return item;

        } catch (Exception e) {
            if (e instanceof BingPullException) {
                logger.error(e.getMessage(), e);
                throw e;
            }
            logger.error("pull image error", e);
            throw new BingPullException(e);
        }
    }

    // 用于向上层传播数据
    // TODO: 这类问题该如何解决(line204)，方法需要传递2个或以上个数的不相关的结果，并且需要更改已经写好的方法
    private static final ThreadLocal<Integer> descPullCount = new ThreadLocal<>();


    private String fixImgLocalUrl(String imgLocalUrl, String imgRootPath) {
        String imgRelativePath = imgLocalUrl.replace("\\", "/").substring(imgRootPath.length());
        if (!imgRelativePath.contains(":") && !imgRelativePath.startsWith("/"))
            imgRelativePath = "/" + imgRelativePath;
        return imgRelativePath;
    }
}
