package online.dinghuiye.mantisshrimp.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Strangeen on 2018/02/19
 */
@Component("msConsts")
@ConfigurationProperties(prefix = "ms.consts.common")
public class MsParam {

    public static final String user_session_key = "__user_session_key__";
    public static int page_size;
    public static String date_format; // yyyyMMdd

    public static void setPage_size(int page_size) {
        MsParam.page_size = page_size;
    }

    public static void setDate_format(String date_format) {
        MsParam.date_format = date_format;
    }
}
