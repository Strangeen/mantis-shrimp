package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.AbstractTest;
import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.service.impl.Access;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/01/27
 */
public class TestAccess extends AbstractTest {

    @Autowired
    private Access access;

    @Test
    public void testBing() throws ParseException {
        //for (String d : new String[]{"20180217","20180218","20180219","20180220"}) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            System.out.println(access.bing(
                    new SimpleDateFormat("yyyyMMdd").parse("20180223"),
                    new BingImageFile(
                            "D:/test/test_mantis_shrimp/test/upload/",
                            BingParam.bing_img_folder_format,
                            BingParam.bing_img_prefix + "-" + UUID.randomUUID().toString() + ".jpg"),
                    600));
        //}
    }

    @Test
    public void testCreate() throws ParseException {
        System.out.println(access.create(
                null,
                new BingImageFile(
                        "D:/test/test_mantis_shrimp/test/upload/",
                        BingParam.bing_img_folder_format,
                        BingParam.bing_img_prefix + "-" + UUID.randomUUID().toString() + ".jpg"),
                600));
    }

}
