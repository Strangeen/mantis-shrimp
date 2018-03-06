package online.dinghuiye.common.util;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Strangeen on 2018/01/09
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 获取网页内容
     * @param url 网页地址
     * @return 内容字符串
     */
    public static String getContentWithGetMethod(String url) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpResponse response = httpclient.execute(httpGet);) {

            int code = response.getStatusLine().getStatusCode();
            if (code != 200) throw new RuntimeException("request error，response code：" + code);

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {

            logger.error("acquire content error，url：" + url);
            throw new RuntimeException(e);
        }
    }
}
