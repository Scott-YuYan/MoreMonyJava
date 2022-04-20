package com.example.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class DemoInterceptor extends HandlerInterceptorAdapter {

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long currentTime = System.currentTimeMillis();
        request.setAttribute("startTime", currentTime);
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        long currentTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        log.info(String.format("本次请求处理时间：%s", currentTime - startTime));
        request.removeAttribute("startTime");
    }
}
