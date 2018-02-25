package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.dao.BingItemDao;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.entity.BingPullException;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.common.entity.MailAttachment;
import online.dinghuiye.common.entity.MailImage;
import online.dinghuiye.common.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/01/27
 *
 * bing图片收集功能入口
 */
@Service
public class Access {

    private static final Logger logger = LoggerFactory.getLogger(Access.class);

    @Autowired
    private BingAcquirer acquirer;
    @Autowired
    private BingImgSaver saver;
    @Autowired
    private BingItemDao itemDao;
    @Autowired
    private BingLogOper logOper;
    @Autowired
    private MailUtil mailUtil;


    @Value("${ms.setting.mngMail}")
    private String mngMial;
    @Value("${ms.setting.toMail}")
    private String toMial;


    /**
     * 创建一次收集，包括拉取文件，记录操作日志，以及发送提示邮件
     *
     * @param date 拉取文件所在日期，null表示当日
     * @param imgFile 存放img相关路径信息
     * @param smallWidth 缩略图的宽度
     * @param byHand 是否手动创建，用于log记录
     * @return bing条目对象
     */
    public BingItemEntity create(Date date, BingImageFile imgFile, int smallWidth, boolean byHand) {
        BingItemEntity item;
        try {
            // 拉取文件
            item = bing(date, imgFile, smallWidth);
            // 记录日志
            logOper.create("success", item.getId() + " | " + item.getbTitle(), byHand);
            if (!byHand)
                // 发送邮件
                // TODO 邮件内容改为模板生成
                mailUtil.sendMail(toMial.split(","), item.getbTitle(), concatMailContent(item), getAttachment(item, imgFile.getImgRootPath()));
        } catch (Exception e) {
            if (e instanceof BingPullException) {
                // 记录日志
                logOper.create("fail", e.getMessage(), byHand);
                if (!byHand)
                    // 发送邮件
                    // TODO 邮件内容改为模板生成
                    mailUtil.sendMail(new String[]{mngMial}, "Bing收集失败", e.getMessage());
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

    private String concatMailContent(BingItemEntity item) {
        return "<html><p>" + item.getbTitle() + "</p>" +
                    "<p>#[mail-attach]</p>" +
                    item.getbDesc() + "</html>";
    }

    private MailAttachment getAttachment(BingItemEntity item, String imgRootPath) {
        return new MailImage(UUID.randomUUID().toString(), new File(imgRootPath + item.getSmallImgUrl()));
    }

    /**
     * 拉取文件功能总成
     *
     * @param date 拉取文件所在日期，null表示当日
     * @param imgFile 存放img相关路径信息
     * @param smallWidth 缩略图的宽度
     * @return bing条目对象
     */
    protected BingItemEntity bing(Date date, BingImageFile imgFile, int smallWidth) {

        try {
            Date today = new Date();
            if (date == null) date = today;

            BingItemEntity item = acquirer.getBingImg(date);
            String desc = acquirer.getBingDesc(date);

            String imgRootPath = imgFile.getImgRootPath();
            File img = new File(imgRootPath + new SimpleDateFormat(imgFile.getImgFolderFormat()).format(date) + "/" + imgFile.getImgFileName());
            saver.dealImg(item, img, smallWidth);

            item.setImgLocalUrl(fixImgLocalUrl(item.getImgLocalUrl(), imgRootPath))
                    .setSmallImgUrl(fixImgLocalUrl(item.getSmallImgUrl(), imgRootPath))
                    .setbDesc(desc)
                    .setCreateTime(new Date());
            itemDao.save(item);
            return item;

        } catch (Exception e) {
            logger.error("pull image error", e);
            throw new BingPullException(e);
        }
    }

    private String fixImgLocalUrl(String imgLocalUrl, String imgRootPath) {
        String imgRelativePath = imgLocalUrl.replace("\\", "/").substring(imgRootPath.length());
        if (imgRelativePath.indexOf(":") <= -1 && !imgRelativePath.startsWith("/"))
            imgRelativePath = "/" + imgRelativePath;
        return imgRelativePath;
    }
}
