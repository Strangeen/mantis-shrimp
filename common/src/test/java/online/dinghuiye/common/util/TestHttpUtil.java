package online.dinghuiye.common.util;

import org.junit.Test;

/**
 * @author Strangeen on 2018/01/09
 */
public class TestHttpUtil {

    @Test
    public void testGetContentWithGetMethod() {
        System.out.println(HttpUtil.getContentWithGetMethod("https://cn.bing.com/HPImageArchive.aspx?format=js&n=1&video=1&idx=0"));
    }

}
