package online.dinghuiye.bingcollection.service.impl;

import com.alibaba.fastjson.JSONObject;
import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.entity.BingImageUrlWrapper;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.common.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Strangeen on 2018/01/09
 */
@Service
public class BingAcquirer {

    private static final Logger logger = LoggerFactory.getLogger(BingAcquirer.class);

    public String getBingDesc(Date date) {
        return getBingDesc(date, null);
    }

    /**
     * 获取bing图片边栏描述html
     *
     * @param date 该日期的图片边栏描述，null表示当天
     * @param descInterfaceUrl 边栏描述获取接口url，null使用默认，一般保持null
     * @return 描述html字符串
     */
    private String getBingDesc(Date date, String descInterfaceUrl) {

        try {

            if (date == null) date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            if(StringUtils.isBlank(descInterfaceUrl))
                descInterfaceUrl = BingParam.bing_desc_interface_url + sdf.format(date);

            String desc = HttpUtil.getContentWithGetMethod(descInterfaceUrl);
            String s1 = "/Images?q";
            String s2 = "/Search?q=";
            String s3 = "/dreammap/?mkt";

            // TODO 替换一些html和css样式

            return StringUtils.replaceEach(desc, new String[]{s1, s2, s3},
                    new String[]{BingParam.bing_url + s1, BingParam.bing_url + s2, BingParam.bing_url + s3});

        } catch (Exception e) {

            logger.error("获取图片边栏描述内容失败");
            throw new RuntimeException(e);
        }
    }


    public BingItemEntity getBingImg(Date date) {
        return getBingImg(date, null);
    }

    /**
     * 解析bing图片json
     *
     * @param date 图片日期，bing只支持最多获取当天前7天的图片，null默认当天，一般保持null
     * @param imgInterfaceUrl 图片json获取接口url，null使用默认，一般保持null
     * @return 图片主要信息对象
     */
    public BingItemEntity getBingImg(Date date, String imgInterfaceUrl) {

        try {

            //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            /*
                处理date：
                bing仅提供当天前7天的图片回顾，用参数 index（天数）表示，即 index 为：
                0 当天
                1 前一天
                2 前2天
                以此类推，到前7天

                index = 当天的日期 - date
             */

            int index = 0;
            Date today = new Date();
            if (date == null) date = today;
            if (date.getTime() < today.getTime()) {
                LocalDate todayLd = LocalDate.now();
                LocalDate dateLd = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
                index = (int) (todayLd.toEpochDay() - dateLd.toEpochDay());
            }

            if (StringUtils.isBlank(imgInterfaceUrl))
                imgInterfaceUrl = BingParam.bing_img_interface_url + index;

            String json = HttpUtil.getContentWithGetMethod(imgInterfaceUrl);
            BingImageUrlWrapper waper = JSONObject.parseObject(json, BingImageUrlWrapper.class);

            BingImageUrlWrapper.Image img = waper.getImages()[0];
            Pattern pattern = Pattern.compile("^(.*)(\\(©.*)$");

            // img
            Matcher matcher = pattern.matcher(img.getCopyright());
            matcher.find();
            String imgTitle = matcher.group(1);
            String imgCopyright = matcher.group(2);

            BingItemEntity item = new BingItemEntity();
            item.setbDate(date)
                .setbTitle(imgTitle)
                .setDescLink(img.getQuiz())
                .setImgCopyright(imgCopyright)
                .setImgCopyrightLink(img.getCopyrightlink())
                .setImgUrl(BingParam.bing_url + img.getUrl());

            BingImageUrlWrapper.VideoUrlWrapper vdo = img.getVid();
            if (vdo != null) {
                matcher = pattern.matcher(vdo.getCaption());
                matcher.find();
                String vdoTitle = matcher.group(1);
                String vdoCopyright = matcher.group(2);

                item.setVdoTitle(vdoTitle)
                    .setVdoCopyright(vdoCopyright)
                    .setVdoHdUrl(vdo.getSources()[1][2])
                    .setVdoMp4Url(vdo.getSources()[0][2])
                    .setVdoMobileUrl(vdo.getSources()[2][2])
                    .setVdoImgLink(vdo.getImage())
                    .setVdoImgMobileLink(vdo.getMobileimage());
            }
            return item;

        } catch (Exception e) {

            logger.error("解析图片json失败");
            throw new RuntimeException(e);
        }
    }

}
