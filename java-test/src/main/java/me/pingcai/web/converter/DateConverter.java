package me.pingcai.web.converter;

import me.pingcai.enums.BackCode;
import me.pingcai.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String,Date> {

    private String DEFAULT_FORMATTER = "yyyy-MM-dd hh:mm:ss";

    private String formatter = DEFAULT_FORMATTER;

    public DateConverter() {
    }

    public DateConverter(String formatter) {
        this.formatter = formatter;
    }

    @Override
    public Date convert(String s) {
        Date date = null;
        if(StringUtils.isNotBlank(s)){
            try {
                date = new SimpleDateFormat(formatter).parse(s);
            } catch (ParseException e) {
                throw new CustomException(BackCode.DATETIME_FORMAT_ERROR);
            }
        }
        return date;
    }
}
