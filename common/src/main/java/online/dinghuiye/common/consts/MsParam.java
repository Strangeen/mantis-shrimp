package online.dinghuiye.common.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Strangeen on 2018/02/19
 */
@Component("msConsts")
@ConfigurationProperties(prefix = "ms.setting.common")
public class MsParam {

    public static final String user_session_key = "__user_session_key__";

    public static int page_size;

    public static String date_format; // yyyyMMdd

    public static String date_time_format; // yyyy-MM-dd HH:mm:ss

    public static String dev_mode;

    private String localZoneId;

    private String toZoneId;

    public static void setPage_size(int page_size) {
        MsParam.page_size = page_size;
    }

    public static void setDate_format(String date_format) {
        MsParam.date_format = date_format;
    }

    public static void setDate_time_format(String date_time_format) {
        MsParam.date_time_format = date_time_format;
    }

    public static void setDev_mode(String dev_mode) {
        MsParam.dev_mode = dev_mode;
    }

    public String getLocalZoneId() {
        return localZoneId;
    }

    public void setLocalZoneId(String localZoneId) {
        this.localZoneId = localZoneId;
    }

    public String getToZoneId() {
        return toZoneId;
    }

    public void setToZoneId(String toZoneId) {
        this.toZoneId = toZoneId;
    }
}
