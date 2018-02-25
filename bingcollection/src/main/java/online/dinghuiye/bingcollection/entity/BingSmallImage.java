package online.dinghuiye.bingcollection.entity;

import java.io.File;

/**
 * 缩略图封装
 * @author Strangeen on 2018/01/11
 */
public class BingSmallImage {

    private int width;
    private int height;
    private File file;

    public BingSmallImage(int width, int height, File file) {
        this.width = width;
        this.height = height;
        this.file = file;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "BingSmallImage{" +
                "width=" + width +
                ", height=" + height +
                ", file=" + file.getPath() +
                '}';
    }
}
