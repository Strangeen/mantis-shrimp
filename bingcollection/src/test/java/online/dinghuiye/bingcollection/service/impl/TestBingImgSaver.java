package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.entity.BingSmallImage;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.service.impl.BingImgSaver;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Strangeen on 2018/01/27
 */
public class TestBingImgSaver {

    @Test
    public void testPullFile() {
        new BingImgSaver().pullFile(
                "https://images2015.cnblogs.com/blog/690292/201609/690292-20160923095944481-1758567758.png",
                new File("D:\\test\\test_mantis_shrimp\\pullImg-" + UUID.randomUUID() + ".png"));
    }

    @Test
    public void testCalcSmallImgAndCompressFile() throws IOException {
        BingImgSaver s = new BingImgSaver();
        File img = new File("D:\\test\\test_mantis_shrimp\\villa.jpg");
        BingSmallImage si = s.calcSamllImg(img, 100);
        System.out.println(si);
        s.compressFile(img, si);
    }


    @Test
    public void testDealImg() {
        BingItemEntity item = new BingItemEntity();
        item.setImgUrl("https://static.leiphone.com/uploads/new/article/740_740/201609/57ce9265aae3f.png");
        new BingImgSaver().dealImg(item, new File("D:\\test\\test_mantis_shrimp\\dealImage.png"), 300);
        System.out.println(item);
    }
}
