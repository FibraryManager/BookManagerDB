package com.bookmanagerdb.bookmanagerdb.filter;


import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilter {

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 放行策略配置
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasAuthority(RoleConstants.ROLE_USER)
                .antMatchers("/api/admin/**").hasAuthority(RoleConstants.ROLE_ADMIN)
                .antMatchers("/api/admin/login", "/api/user/login", "/api/user/register", "/auth/**").permitAll();

        // 添加授权过滤器
        httpSecurity.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加认证失败过滤器
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

        return httpSecurity.build();
    }
}
