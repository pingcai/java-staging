package me.pingcai.config;

import me.pingcai.security.NickNameAuthenticationFilter;
import me.pingcai.security.handler.NickNameAuthenticationFailHandler;
import me.pingcai.security.handler.NickNameAuthenticationSuccessHandler;
import me.pingcai.security.provider.NickNameProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created by pingcai at 2018/5/15 21:00
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilterBefore(nickNameAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/**").authenticated()
                .and()
                .authenticationProvider(nickNameProvider())
                .formLogin()
                .loginPage("/login.htmlx")
                .permitAll()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Bean
    public NickNameProvider nickNameProvider() {
        return new NickNameProvider();
    }

    @Bean
    public NickNameAuthenticationFilter nickNameAuthenticationFilter() throws Exception {
        NickNameAuthenticationFilter filter = new NickNameAuthenticationFilter("/login");
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(nickNameAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(nickNameAuthenticationFailHandler());
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public NickNameAuthenticationFailHandler nickNameAuthenticationFailHandler() {
        return new NickNameAuthenticationFailHandler();
    }

    @Bean
    public NickNameAuthenticationSuccessHandler nickNameAuthenticationSuccessHandler() {
        return new NickNameAuthenticationSuccessHandler();
    }

}
