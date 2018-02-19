package online.dinghuiye.common.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Strangeen on 2018/01/10
 */
public class TestMd5Base64 {

    @Test
    public void testMd5() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String pwd = "123456";

        MessageDigest md = MessageDigest.getInstance("MD5");
        String md5 = new BigInteger(1, md.digest(pwd.getBytes("utf-8"))).toString(16);

        System.out.println(md5);
    }
}
