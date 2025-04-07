package com.martin.contract.common;

import com.alibaba.fastjson2.JSON;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;

@Component
@NonNullApi
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行登录接口
        if (request.getRequestURI().contains("/auth/login")) {
            return true;
        }

        // 从Header获取Token和用户ID
        String userName = request.getHeader("UserName");
        String token = request.getHeader("Token");

        // 验证Token
        if (userName == null || token == null || !tokenUtil.validateToken(userName, token)) {
            response.setContentType("application/json;charset=UTF-8"); // 关键：设置编码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(ResultData.fail(ReturnCodeEnum.RC401)));
            return false;
        }
        return true;
    }
}
