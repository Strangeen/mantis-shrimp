package online.dinghuiye.mantisshrimp.entity;

import online.dinghuiye.mantisshrimp.consts.MsParam;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Strangeen on 2018/02/21
 */
public class BingItemInfo {

    private String bDate;
    private String bTitle;
    /*private String descLink;
    private String imgCopyright;
    private String imgCopyrightLink;
    private String imgUrl;
    private String vdoTitle;
    private String vdoCopyright;
    private String vdoHdUrl;
    private String vdoMp4Url;
    private String vdoMobileUrl;
    private String vdoImgLink;
    private String vdoImgMobileLink;
    private String imgLocalUrl;
    private String smallImgUrl;
    private Date createTime;
    private String bDesc;*/

    public Date getDate() {
        try {
            if (!StringUtils.isBlank(bDate)) {
                return new SimpleDateFormat(MsParam.date_format).parse(bDate);
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("date string error");
        }
    }

    public String getTitle() {

        if (!StringUtils.isBlank(bTitle))
            return bTitle;
        return null;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbDate() {
        return bDate;
    }

    public String getbTitle() {
        return bTitle;
    }
}
