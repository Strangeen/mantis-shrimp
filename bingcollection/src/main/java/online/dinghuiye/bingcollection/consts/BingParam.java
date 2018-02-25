package online.dinghuiye.bingcollection.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Strangeen on 2018/01/09
 */
@Component("bingConsts")
@ConfigurationProperties(prefix = "ms.consts.bing")
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
        if (bing_img_save_path != null && !bing_img_save_path.endsWith("/"))
            bing_img_save_path = bing_img_save_path + "/";
        BingParam.bing_img_save_path = bing_img_save_path;
    }

    public static void setBing_img_folder_format(String bing_img_folder_format) {
        BingParam.bing_img_folder_format = bing_img_folder_format;
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

    public static void setBing_date_format(String bing_date_format) {
        BingParam.bing_date_format = bing_date_format;
    }
}
