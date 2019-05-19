package me.pingcai.domain.vo;

import lombok.Data;
import me.pingcai.domain.enums.UserSex;
import me.pingcai.domain.enums.UserStatus;
import me.pingcai.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author huangpingcai
 * @since 2019-05-19 17:50
 */
@Data
public class UserLoginVo {

    private Long id;

    private UserStatus status;

    @NotEmpty(message = "用户名不能为空")
    private String name;

    private String displayName;

    private String profilePicture;

    private String phone;

    private String email;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private UserSex sex;

    private Integer age;

    private Date birthday;

    private String introduction;

    private Date addTime;

    private Date updateTime;


}
