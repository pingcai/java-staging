package me.pingcai.web.converter;

import me.pingcai.enums.UserType;
import org.springframework.core.convert.converter.Converter;

public class UserTypeConverter implements Converter<String,UserType> {
    @Override
    public UserType convert(String key) {
        return UserType.of(key);
    }
}
