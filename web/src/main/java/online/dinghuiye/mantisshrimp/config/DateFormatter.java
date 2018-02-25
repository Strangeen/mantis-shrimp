package online.dinghuiye.mantisshrimp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Strangeen on 2018/02/25
 */
public class DateFormatter implements Formatter<Date> {

    @Value("${ms.consts.common.date_time_format}")
    private String format;

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return new SimpleDateFormat(format).parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return new SimpleDateFormat(format).format(date);
    }
}
