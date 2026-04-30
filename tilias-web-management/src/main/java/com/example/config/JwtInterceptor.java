package com.example.config;

import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("JWT拦截器: {} {}", request.getMethod(), request.getRequestURI());
        // 1.获取token
        String token = request.getHeader("token");
        // 2.判断token是否存在，否，则返回401错误
        if (token == null) {
            log.info("没有token，拒绝访问");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 3.如果token存在，则校验令牌，失败，则返回401错误
        try {
            JwtUtils.parseToken(token);
        }
            catch(Exception e) {
                log.info("token无效，拒绝访问");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        // 4.如果令牌有效，则放行
        log.info("token有效，放行");
        return true;
    }
}
