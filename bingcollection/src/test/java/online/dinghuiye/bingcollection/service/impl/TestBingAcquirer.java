package online.dinghuiye.bingcollection.service.impl;


import online.dinghuiye.bingcollection.service.impl.BingAcquirer;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Strangeen on 2018/01/09
 */
public class TestBingAcquirer {

    @Test
    public void testGetBingDesc() {
        System.out.println(new BingAcquirer().getBingDesc(new Date()));
    }

    @Test
    public void testGetBingDescLastDay() {
        System.out.println(new BingAcquirer().getBingDesc(getLastDay()));
    }

    @Test
    public void testGetBingImg() {
        System.out.println(new BingAcquirer().getBingImg(null));
    }

    @Test
    public void testGetBingImgLastDay() {
        System.out.println(new BingAcquirer().getBingImg(getLastDay(), null));
    }

    private Date getLastDay() {
        LocalDate ld = LocalDate.now().minusDays(1);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = ld.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    @Test
    public void testGetLastDay() {
        System.out.println(getLastDay());
    }



}
