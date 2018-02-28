package online.dinghuiye.mantisshrimp.config;

import online.dinghuiye.mantisshrimp.consts.MsParam;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Strangeen on 2018/02/25
 */
public class DateFormatter implements Formatter<Date> {

//    @Value("${ms.consts.common.date_time_format}")
//    private String format;

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return new SimpleDateFormat(MsParam.date_time_format).parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return new SimpleDateFormat(MsParam.date_time_format).format(date);
    }
}
