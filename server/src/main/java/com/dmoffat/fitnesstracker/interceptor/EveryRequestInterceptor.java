package com.dmoffat.fitnesstracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class EveryRequestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(EveryRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(request.getMethod() + " " + request.getRequestURI());
        return true;
    }
}
