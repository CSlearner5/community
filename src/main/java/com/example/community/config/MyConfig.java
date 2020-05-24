package com.example.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //用户只有登录才可以进行其他操作
//        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
//                "/index.html", "/static/**", "/callback***", "/image/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
