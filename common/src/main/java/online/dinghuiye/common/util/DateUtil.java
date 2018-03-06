package online.dinghuiye.common.util;

import online.dinghuiye.common.consts.MsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Strangeen on 2018/03/03
 */
@Component
public class DateUtil {

    /**
     * 获取本地时区当前时间在指定时区时的时间
     * java8实现
     *
     * @param localZoneId 本地时区ID
     * @param toZoneId 指定时区ID
     * @return 指定时区时间Date对象
     */
    public static Date now(String localZoneId, String toZoneId) {
        return toDate(new Date(), localZoneId, toZoneId);
    }

    /**
     * 获取本地时区当前时间在指定时区时的时间
     * java8实现
     *
     * @param date 本地时区时间Date对象
     * @param localZoneId 本地时区ID
     * @param toZoneId 指定时区ID
     * @return 指定时区时间Date对象
     */
    public static Date toDate(Date date, String localZoneId, String toZoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(toZoneId));
        ZonedDateTime toZoneNow = zonedDateTime.withZoneSameLocal(ZoneId.of(localZoneId));
        return Date.from(toZoneNow.toInstant());
    }



    private static MsParam msParam;

    @Autowired
    public void setMsParam(MsParam msParam) {
        this.msParam = msParam;
    }

    public static Date now() {
        return toDate(new Date());
    }

    public static Date toDate(Date date) {
        return DateUtil.toDate(date, msParam.getLocalZoneId(), msParam.getToZoneId());
    }
}
