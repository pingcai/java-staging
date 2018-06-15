package me.pingcai.security.handler;

import me.pingcai.enums.BackCode;
import me.pingcai.util.JsonUtils;
import me.pingcai.vo.Response;
import me.pingcai.vo.ResponseFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by pingcai at 2018/6/14 12:16
 */
public class NickNameAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        Response resp = ResponseFactory.buildSuccess();
        resp.setData(authentication);
        writer.write(JsonUtils.object2Json(resp));
    }
}
