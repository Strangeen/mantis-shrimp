package online.dinghuiye.bingcollection.service.impl;

import com.xice7.image.kit.ImageKit;
import online.dinghuiye.bingcollection.entity.BingSmallImage;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Strangeen on 2018/01/09
 */
@Service
public class BingImgSaver {

    private static final Logger logger = LoggerFactory.getLogger(BingImgSaver.class);

    /**
     * 存储图片
     * @param item bing图片对象
     * @param destImg 图片保存文件
     * @param smallWidth 缩略图宽度
     */
    public void dealImg(BingItemEntity item, File destImg, int smallWidth) {
        try {
            pullFile(item.getImgUrl(), destImg);
            BingSmallImage bingSmallImage = calcSamllImg(destImg, smallWidth);
            item.setImgLocalUrl(destImg.getPath()); // 设置本地图片地址（后面需要修正为相对路径）
            item.setSmallImgUrl(bingSmallImage.getFile().getPath()); // 设置缩略图地址（后面需要修正为相对路径）
            compressFile(destImg, bingSmallImage);

        } catch (Exception e) {
            logger.error("保存图片失败", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 拉取图片
     * @param fileUrl 图片地址
     * @param dest 保存文件
     */
    protected void pullFile(String fileUrl, File dest) {

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream is = conn.getInputStream();

            if (!dest.getParentFile().exists()) dest.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(dest);
            byte[] buf = new byte[8 * 1024];
            int len = 0;
            while ((len = is.read(buf)) > -1) {
                fos.write(buf, 0, len);
            }
        } catch (Exception e) {
            logger.error("下载文件失败", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 计算缩略图的尺寸（只是计算，生成实体）
     * @param img bing图片文件
     * @param smallWidth 缩略图宽度
     * @return 缩略图对象
     */
    protected BingSmallImage calcSamllImg(File img, int smallWidth) throws IOException {
        BufferedImage bimg = ImageIO.read(new FileInputStream(img));
        int height = bimg.getHeight();
        int width = bimg.getWidth();
        int smallHeight = (int) ((double) smallWidth * height / width);
        String imgFullName = img.getName();
        String imgSuffix = imgFullName.substring(imgFullName.lastIndexOf("."));
        String imgName = imgFullName.substring(0, imgFullName.length() - imgSuffix.length());
        int newWidth = 0;
        int newHeight = 0;
        if (smallWidth < width) {
            newWidth = smallWidth;
            newHeight = smallHeight;
        } else {
            newWidth = width;
            newHeight = height;
        }
        File smallFile = new File(img.getParentFile(), imgName + "_thumbnail_" + newWidth + "_" + newHeight + imgSuffix);
        return new BingSmallImage(newWidth, newHeight, smallFile);
    }

    /**
     * 压缩缩略图（真正的处理和存储缩略图）
     * @param img bing图片
     * @param bingSmallImage 缩略图对象
     */
    protected void compressFile(File img, BingSmallImage bingSmallImage) {
        ImageKit kit = ImageKit.read(img);
        ImageKit skit = kit.scale(bingSmallImage.getWidth(), bingSmallImage.getHeight());
        skit.transferTo(bingSmallImage.getFile());
    }
}
