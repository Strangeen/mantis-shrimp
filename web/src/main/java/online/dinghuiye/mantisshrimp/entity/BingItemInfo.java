package online.dinghuiye.mantisshrimp.entity;

import online.dinghuiye.common.consts.MsParam;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Strangeen on 2018/02/21
 */
public class BingItemInfo {

    private String bDate;
    private String bTitle;

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
