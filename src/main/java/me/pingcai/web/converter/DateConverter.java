package me.pingcai.web.converter;

import me.pingcai.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author huangpingcai
 * @since 2018/8/25 00:46
 */
public class DateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String date) {
        return DateUtils.parse(date);
    }
}
