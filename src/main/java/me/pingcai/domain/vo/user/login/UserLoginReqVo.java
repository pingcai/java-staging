package me.pingcai.domain.vo.user.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author huangpingcai
 * @since 2019-05-19 17:50
 */
@Data
public class UserLoginReqVo {

    @NotEmpty(message = "用户名不能为空")
    private String name;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
