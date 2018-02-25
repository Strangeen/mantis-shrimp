package online.dinghuiye.bingcollection.pojo;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Strangeen on 2018/02/02
 */
@Entity
@Table(name = "BING_ITEM")
public class BingItemEntity {

    private Long id;
    private Date bDate;
    private String bTitle;
    private String descLink;
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
    private String bDesc;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public BingItemEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "B_DATE")
    @Type(type = "date")
    public Date getbDate() {
        return bDate;
    }

    public BingItemEntity setbDate(Date bDate) {
        this.bDate = bDate;
        return this;
    }

    @Basic
    @Column(name = "IMG_URL")
    public String getImgUrl() {
        return imgUrl;
    }

    public BingItemEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @Basic
    @Column(name = "B_TITLE")
    public String getbTitle() {
        return bTitle;
    }

    public BingItemEntity setbTitle(String bTitle) {
        this.bTitle = bTitle;
        return this;
    }

    @Basic
    @Column(name = "IMG_COPYRIGHT")
    public String getImgCopyright() {
        return imgCopyright;
    }

    public BingItemEntity setImgCopyright(String imgCopyright) {
        this.imgCopyright = imgCopyright;
        return this;
    }

    @Basic
    @Column(name = "IMG_COPYRIGHT_LINK")
    public String getImgCopyrightLink() {
        return imgCopyrightLink;
    }

    public BingItemEntity setImgCopyrightLink(String imgCopyrightLink) {
        this.imgCopyrightLink = imgCopyrightLink;
        return this;
    }

    @Basic
    @Column(name = "DESC_LINK")
    public String getDescLink() {
        return descLink;
    }

    public BingItemEntity setDescLink(String descLink) {
        this.descLink = descLink;
        return this;
    }

    @Basic
    @Column(name = "VDO_MP4_URL")
    public String getVdoMp4Url() {
        return vdoMp4Url;
    }

    public BingItemEntity setVdoMp4Url(String vdoMp4Url) {
        this.vdoMp4Url = vdoMp4Url;
        return this;
    }

    @Basic
    @Column(name = "VDO_HD_URL")
    public String getVdoHdUrl() {
        return vdoHdUrl;
    }

    public BingItemEntity setVdoHdUrl(String vdoHdUrl) {
        this.vdoHdUrl = vdoHdUrl;
        return this;
    }

    @Basic
    @Column(name = "VDO_MOBILE_URL")
    public String getVdoMobileUrl() {
        return vdoMobileUrl;
    }

    public BingItemEntity setVdoMobileUrl(String vdoMobileUrl) {
        this.vdoMobileUrl = vdoMobileUrl;
        return this;
    }

    @Basic
    @Column(name = "VDO_IMG_LINK")
    public String getVdoImgLink() {
        return vdoImgLink;
    }

    public BingItemEntity setVdoImgLink(String vdoImgLink) {
        this.vdoImgLink = vdoImgLink;
        return this;
    }

    @Basic
    @Column(name = "VDO_IMG_MOBILE_LINK")
    public String getVdoImgMobileLink() {
        return vdoImgMobileLink;
    }

    public BingItemEntity setVdoImgMobileLink(String vdoImgMobileLink) {
        this.vdoImgMobileLink = vdoImgMobileLink;
        return this;
    }

    @Basic
    @Column(name = "VDO_TITLE")
    public String getVdoTitle() {
        return vdoTitle;
    }

    public BingItemEntity setVdoTitle(String vdoTitle) {
        this.vdoTitle = vdoTitle;
        return this;
    }

    @Basic
    @Column(name = "VDO_COPYRIGHT")
    public String getVdoCopyright() {
        return vdoCopyright;
    }

    public BingItemEntity setVdoCopyright(String vdoCopyright) {
        this.vdoCopyright = vdoCopyright;
        return this;
    }

    @Basic
    @Column(name = "B_DESC")
    @Type(type = "text")
    public String getbDesc() {
        return bDesc;
    }

    public BingItemEntity setbDesc(String bDesc) {
        this.bDesc = bDesc;
        return this;
    }

    @Basic
    @Column(name = "IMG_LOCAL_URL")
    public String getImgLocalUrl() {
        return imgLocalUrl;
    }

    public BingItemEntity setImgLocalUrl(String imgLocalUrl) {
        this.imgLocalUrl = imgLocalUrl;
        return this;
    }

    @Basic
    @Column(name = "SMALL_IMG_URL")
    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public BingItemEntity setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
        return this;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public BingItemEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "BingItemEntity{" +
                "id=" + id +
                ", bDate=" + bDate +
                ", imgUrl='" + imgUrl + '\'' +
                ", bTitle='" + bTitle + '\'' +
                ", imgCopyright='" + imgCopyright + '\'' +
                ", imgCopyrightLink='" + imgCopyrightLink + '\'' +
                ", descLink='" + descLink + '\'' +
                ", vdoMp4Url='" + vdoMp4Url + '\'' +
                ", vdoHdUrl='" + vdoHdUrl + '\'' +
                ", vdoMobileUrl='" + vdoMobileUrl + '\'' +
                ", vdoImgLink='" + vdoImgLink + '\'' +
                ", vdoImgMobileLink='" + vdoImgMobileLink + '\'' +
                ", vdoTitle='" + vdoTitle + '\'' +
                ", vdoCopyright='" + vdoCopyright + '\'' +
                ", imgLocalUrl='" + imgLocalUrl + '\'' +
                ", smallImgUrl='" + smallImgUrl + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
