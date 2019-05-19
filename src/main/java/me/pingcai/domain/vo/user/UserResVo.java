package me.pingcai.domain.vo.user;

import lombok.Data;
import me.pingcai.domain.enums.UserSex;
import me.pingcai.domain.enums.UserStatus;

import java.util.Date;

/**
 * @author huangpingcai
 * @since 2019-05-19 17:50
 */
@Data
public class UserResVo {

    private Long id;

    private UserStatus status;

    private String name;

    private String displayName;

    private String profilePicture;

    private String phone;

    private String email;

    private UserSex sex;

    private Integer age;

    private Date birthday;

    private String introduction;

    private Date addTime;

    private Date updateTime;


}
