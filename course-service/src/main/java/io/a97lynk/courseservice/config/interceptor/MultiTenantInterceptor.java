package io.a97lynk.courseservice.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.a97lynk.courseservice.config.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Component
public class MultiTenantInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("In preHandle we are Intercepting the Request");
        System.out.println("____________________________________________");
        String requestURI = request.getRequestURI();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        String tenantID = new ObjectMapper().readTree(JwtHelper.decode(token).getClaims()).get("tenantId").asText();
        System.out.println("RequestURI::" + requestURI + " || Search for X-TenantID  :: " + tenantID);
        System.out.println("____________________________________________");

        if (tenantID == null) {
            response.getWriter().write("X-TenantID not present in the Request Header");
            response.setStatus(400);
            return false;
        }

        TenantContext.setCurrentTenant(tenantID);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Clear tenant");
        TenantContext.clear();
    }
}
