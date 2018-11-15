package online.dinghuiye.bingcollection.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Strangeen on 2018/01/09
 */
@Component("bingConsts")
@ConfigurationProperties(prefix = "ms.setting.bing")
public class BingParam {

    public static int log_page_size = 30;

    public static String bing_url;

    public static String bing_desc_interface_url; // 参数形式yyyyMMdd

    // json的接口地址, 0:当天图片
    public static String bing_img_interface_url;

    public static String bing_img_save_path;

    public static String bing_img_folder_format; // yyyyMM

    public static String bing_date_format; // yyyyMMdd

    public static String bing_img_prefix; // bing

    public static int bing_small_img_width; // 600

    public static int bing_photo_list_col_num; // 4

    public static String manage_mail;

    public static String to_mail;

    public static String send_when_by_hand;

    private String acquireCronExp;

    private String cronZoneId;

    private String descRegx;

    private String imgTitleRegx;

    private Integer descStart;

    private Integer descLength;

    private Integer bingDescPullTimes;

    public Integer getBingDescPullTimes() {
        return bingDescPullTimes;
    }

    public void setBingDescPullTimes(Integer bingDescPullTimes) {
        this.bingDescPullTimes = bingDescPullTimes;
    }

    public static void setLog_page_size(int log_page_size) {
        BingParam.log_page_size = log_page_size;
    }

    public static void setBing_url(String bing_url) {
        BingParam.bing_url = bing_url;
    }

    public static void setBing_desc_interface_url(String bing_desc_interface_url) {
        BingParam.bing_desc_interface_url = bing_desc_interface_url;
    }

    public static void setBing_img_interface_url(String bing_img_interface_url) {
        BingParam.bing_img_interface_url = bing_img_interface_url;
    }

    public static void setBing_img_save_path(String bing_img_save_path) {
        BingParam.bing_img_save_path = bing_img_save_path;
    }

    public static void setBing_img_folder_format(String bing_img_folder_format) {
        BingParam.bing_img_folder_format = bing_img_folder_format;
    }

    public static void setBing_date_format(String bing_date_format) {
        BingParam.bing_date_format = bing_date_format;
    }

    public static void setBing_img_prefix(String bing_img_prefix) {
        BingParam.bing_img_prefix = bing_img_prefix;
    }

    public static void setBing_small_img_width(int bing_small_img_width) {
        BingParam.bing_small_img_width = bing_small_img_width;
    }

    public static void setBing_photo_list_col_num(int bing_photo_list_col_num) {
        BingParam.bing_photo_list_col_num = bing_photo_list_col_num;
    }

    public static void setManage_mail(String manage_mail) {
        BingParam.manage_mail = manage_mail;
    }

    public static void setTo_mail(String to_mail) {
        BingParam.to_mail = to_mail;
    }

    public static void setSend_when_by_hand(String send_when_by_hand) {
        BingParam.send_when_by_hand = send_when_by_hand;
    }

    public String getAcquireCronExp() {
        return acquireCronExp;
    }

    public void setAcquireCronExp(String acquireCronExp) {
        this.acquireCronExp = acquireCronExp;
    }

    public String getCronZoneId() {
        return cronZoneId;
    }

    public void setCronZoneId(String cronZoneId) {
        this.cronZoneId = cronZoneId;
    }

    public String getDescRegx() {
        return descRegx;
    }

    public void setDescRegx(String descRegx) {
        this.descRegx = descRegx;
    }

    public Integer getDescStart() {
        return descStart;
    }

    public void setDescStart(Integer descStart) {
        this.descStart = descStart;
    }

    public Integer getDescLength() {
        return descLength;
    }

    public void setDescLength(Integer descLength) {
        this.descLength = descLength;
    }

    public String getImgTitleRegx() {
        return imgTitleRegx;
    }

    public void setImgTitleRegx(String imgTitleRegx) {
        this.imgTitleRegx = imgTitleRegx;
    }
}