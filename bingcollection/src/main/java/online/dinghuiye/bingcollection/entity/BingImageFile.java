package online.dinghuiye.bingcollection.entity;

/**
 * @author Strangeen on 2018/02/25
 */
public class BingImageFile {

    // 文件存储位置：imgRootPath + imgFolderFormat + imgFileName

    // 存放绝对路径
    private String imgRootPath;
    // 存放文件夹名称，一般按月存
    private String imgFolderFormat;
    // 文件名
    private String imgFileName;

    public BingImageFile(String imgRootPath, String imgFolderFormat, String imgFileName) {
        this.imgFolderFormat = imgFolderFormat;
        this.imgFileName = imgFileName;
        this.imgRootPath = fixImgRootPath(imgRootPath);
    }

    private String fixImgRootPath(String path) {
        if (path == null) return null;
        path = path.replace("\\", "/");
        if (!path.endsWith("/"))
            path = path + "/";
        return path;
    }

    public String getImgRootPath() {
        return imgRootPath;
    }

    public void setImgRootPath(String imgRootPath) {
        this.imgRootPath = fixImgRootPath(imgRootPath);
    }

    public String getImgFolderFormat() {
        return imgFolderFormat;
    }

    public void setImgFolderFormat(String imgFolderFormat) {
        this.imgFolderFormat = imgFolderFormat;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }
}
