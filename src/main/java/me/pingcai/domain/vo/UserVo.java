package me.pingcai.domain.vo;

import lombok.Data;
import me.pingcai.domain.enums.UserSex;
import me.pingcai.domain.enums.UserStatus;
import me.pingcai.web.validator.NameCheck;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserVo {

    private Long id;

    private UserStatus status;

    @NameCheck(min = 3, max = 16, message = "用户名不合法", regex = "^[0-9a-zA-Z_]{3,}$")
    private String name;

    @NameCheck(min = 3, max = 16, message = "昵称不合法")
    private String displayName;

    @URL(message = "头像不合法")
    private String profilePicture;

    @NotEmpty(message = "联系电话不能为空")
    @Length(min = 11, max = 11, message = "联系电话不合法")
    private String phone;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱不合法")
    private String email;

    @Length(min = 8, max = 18, message = "密码不能小于{min}或大于{max}")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotNull(message = "性别不能为空")
    private UserSex sex;

    @Range(min = 0, max = 255, message = "年龄不能小于{min}或大于{max}")
    private Integer age;

    @NotNull(message = "生日不能为空")
    private Date birthday;

    @NotEmpty(message = "自我介绍不能为空")
    private String introduction;

    private Date addTime;

}