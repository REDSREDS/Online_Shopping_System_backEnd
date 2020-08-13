package com.maxChen.onlineStore.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SellerLoginInterceptor())
                .addPathPatterns("/seller/**").excludePathPatterns("/seller/login")
                .excludePathPatterns("/seller/signup");
    }
}
