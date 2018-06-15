package me.pingcai.security.handler;

import me.pingcai.enums.BackCode;
import me.pingcai.util.JsonUtils;
import me.pingcai.vo.Response;
import me.pingcai.vo.ResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by pingcai at 2018/6/14 12:32
 */
public class NickNameAuthenticationFailHandler implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        Response resp = ResponseFactory.buildError(BackCode.LOGIN_FAIL);
        writer.write(JsonUtils.object2Json(resp));
    }
}
