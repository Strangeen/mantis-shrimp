package online.dinghuiye.common.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Strangeen on 2018/03/03
 */
public class TestDateUtil /*extends AbstractTest*/ {


    @Test
    public void testNow() {
        System.out.println(new Date());
        System.out.println(DateUtil.now("GMT+8", "America/New_York"));
    }

    @Test
    public void testToDate() {
        Date d = new Date();
        System.out.println(d);
        System.out.println(DateUtil.toDate(d, "GMT+8", "America/New_York"));
    }

    @Test
    public void testNowWithSysZone() {

        System.out.println(DateUtil.now());
    }





    @Test
    public void testAvailableTimeZoneId() {
        System.out.println(Arrays.asList(TimeZone.getAvailableIDs()));
    }

    /**
     * java8之前时区转换
     *
     * 时区变动，夏令时计算
     */
    @Test
    public void testJava7() throws ParseException {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        TimeZone.setDefault(TimeZone.getTimeZone("UTC+8"));
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    //        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat sdfUsa = new SimpleDateFormat(pattern);
        sdfUsa.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles")); // 必须写这种时区号，不能写GMT-8，否则夏令时有问题
        {
            Date dCn = sdf.parse("2018-03-11 17:59:59"); // +8
            System.out.println("中国时间：" + dCn);
            Date dUsa = sdf.parse(sdfUsa.format(dCn));
            System.out.println("美国时间：" + dUsa); // 没有夏令时，Sun Mar 11 01:59:59 GMT+08:00 2018
            System.out.println("---------------------------");
        }
        {
            Date dCn = sdf.parse("2018-03-11 18:00:00"); // +8
            System.out.println("中国时间：" + dCn);
            Date dUsa = sdf.parse(sdfUsa.format(dCn));
            System.out.println("美国时间：" + dUsa); // 有夏令时，会直接从2点跳到3点，Sun Mar 11 03:00:00 GMT+08:00 2018
            System.out.println("---------------------------");
        }
        {
            Date dCn = sdf.parse("2018-11-4 16:00:00"); // +8
            System.out.println("中国时间：" + dCn);
            Date dUsa = sdf.parse(sdfUsa.format(dCn));
            System.out.println("美国时间：" + dUsa); // 有夏令时，Sun Nov 04 01:00:00 GMT+08:00 2018
            System.out.println("---------------------------");
        }
        {
            Date dCn = sdf.parse("2018-11-4 16:59:59"); // +8
            System.out.println("中国时间：" + dCn);
            Date dUsa = sdf.parse(sdfUsa.format(dCn));
            System.out.println("美国时间：" + dUsa); // 没有夏令时，Sun Nov 04 01:59:59 GMT+08:00 2018
            System.out.println("---------------------------");
        }
        {
            Date dCn = sdf.parse("2018-11-4 17:00:00"); // +8
            System.out.println("中国时间：" + dCn);
            Date dUsa = sdf.parse(sdfUsa.format(dCn));
            System.out.println("美国时间：" + dUsa); // 没有夏令时，从2点跳回到1点... Sun Nov 04 01:00:00 GMT+08:00 2018
            System.out.println("---------------------------");
        }
    }

    /**
     * java8时区转换
     *
     * 时区变动，夏令时计算
     */
    @Test
    public void test8() throws ParseException {

        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        {
            TemporalAccessor ta = dtf.withZone(ZoneId.of("UTC+8")).parse("2018-11-04 17:00:00"); // +8
            ZonedDateTime ztd = ZonedDateTime.from(ta);
            System.out.println(ztd.withZoneSameInstant(ZoneId.of("UTC-8")));
            System.out.println(ztd.withZoneSameLocal(ZoneId.of("UTC-8")));
        }*/

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        {
            TemporalAccessor ta = dtf.withZone(ZoneId.of("UTC+8")).parse("2018-03-16 14:25:10"); // +8
            ZonedDateTime ztd = ZonedDateTime.from(ta);
            System.out.println(ztd.withZoneSameInstant(ZoneId.of("America/New_York"))); // 一定要写GMT-5，因为这样不知道是哪个国家，有没有执行夏令时
            System.out.println(ztd.withZoneSameLocal(ZoneId.of("America/New_York")));
        }

    }

    /**
     * 本地时区时间转换为其他时区时间
     * @throws ParseException
     */
    @Test
    public void test8LocalZoneTimeToOtherZoneTime() throws ParseException {
        Date d = new SimpleDateFormat("yyyy-MM-dd HH").parse("2018-03-4 10");
        System.out.println(d);

        System.out.println("-----------------");

        {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(d.toInstant(), ZoneId.of("GMT-8"));
            System.out.println(localDateTime);

            // 转换成Date需要先转换成ZonedDateTime，不如下面的方法好
            Date d1 = Date.from(localDateTime.atZone(ZoneId.of("GMT-8")).withZoneSameLocal(ZoneId.of("GMT+8")).toInstant());
            System.out.println(d1);
        }

        System.out.println("-----------------");

        {
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(d.toInstant(), ZoneId.of("GMT-8"));
            System.out.println(zonedDateTime);

            // ZonedDateTime需要先设置到本地时区，再转换成对应的Date
            Date d1 = Date.from(zonedDateTime.withZoneSameLocal(ZoneId.of("GMT+8")).toInstant());
            System.out.println(d1);
        }
    }

    /**
     * 本地时区下，其他时区时间设置转换为本地时区时间
     * @throws ParseException
     */
    @Test
    public void test8OtherZoneTimeToLocalZoneTimeAtLocalZone() throws ParseException {

        // 字面量时只有时间没有时区，所以LocalDateTime会可以通过atZone直接指定时间时区
        {
            // 需要设置的时间
            LocalDateTime localDateTime = LocalDateTime.of(2018, 3, 4, 10, 0, 0);
            System.out.println(localDateTime);

            // 设置为其他时区
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("GMT-8")); // 直接指定时区
            System.out.println(zonedDateTime);

            // 计算本地时间
            ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameInstant(ZoneId.of("GMT+8"));
            System.out.println(zonedDateTime1);

            // 得到Date对象
            System.out.println(Date.from(zonedDateTime1.toInstant()));
        }

        System.out.println("------------------------");

        // 如果是从date对象转为instant，需要指定时区，会和服务器时区对比并转换为对应时区的时间...
        {
            /*LocalDateTime localDateTime = LocalDateTime.ofInstant(
                    new SimpleDateFormat("yyyy-MM-dd HH").parse("2018-03-04 10").toInstant(), ZoneId.of("GMT+8")); // 会根据当地时区进行转换，看起来就像是ZonedDateTime去掉了时区，还不如直接将date转为ZonedDateTime
            System.out.println(localDateTime);*/

            // 使用date设置时间，必须先指定为本地时区（如果直接指定其他时区会被计算）
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
                    new SimpleDateFormat("yyyy-MM-dd HH").parse("2018-03-04 10").toInstant(), ZoneId.of("GMT+8")); // date直接转为ZonedDateTime
            System.out.println(zonedDateTime);

            // 转换为其他时区时间（不计算，修改时区）
            ZonedDateTime zonedDateTime1 = zonedDateTime.withZoneSameLocal(ZoneId.of("GMT-8"));
            System.out.println(zonedDateTime1);

            // 计算本地时间
            ZonedDateTime zonedDateTime2 = zonedDateTime1.withZoneSameInstant(ZoneId.of("GMT+8"));
            System.out.println(zonedDateTime2);

            // 获取date
            System.out.println(Date.from(zonedDateTime1.toInstant()));
        }
    }


    /**
     * 相差天数计算
     * java7
     *
     * 利用相差的毫秒进行计算
     */
    @Test
    public void testIntervalJava7() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date d1 = sdf.parse("2018-11-30 11");
        Date d2 = sdf.parse("2019-12-1 5");

        // 转成日期格式才能计算（抹掉时间）
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println((sdf0.parse(sdf0.format(d2)).getTime() - (sdf0.parse(sdf0.format(d1)).getTime())) / (24 * 3600 * 1000));
    }

    /**
     * 相差天数计算
     * java8
     */
    @Test
    public void testIntervalJava8() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Date d1 = sdf.parse("2018-11-30 11");
        Date d2 = sdf.parse("2019-12-1 5");

        LocalDate ld1 = LocalDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = LocalDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        System.out.println(ld2.toEpochDay() - ld1.toEpochDay());
    }


    @Test
    public void test() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println(ldt.atZone(ZoneId.of("GMT+8")));
    }
}

