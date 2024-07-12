package com.example.devs._core.config;

import com.example.devs._core.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("인터셉터 등록됨============================================================");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("")
                .excludePathPatterns("/api/users/login")
                .excludePathPatterns("/api/users/join")
                .excludePathPatterns("/api/users/oauth/kakao")
                .excludePathPatterns("/api/users/oauth/naver");
    }

    // 외부 폴더에서 이미지파일 리소스를 가져오는 방법
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/api/user/**") // user 정보를 수정 할때 개방 폴더를 찾기
                .addResourceLocations("file:./images/")// 파일 경로 수정 및 해당 폴더 개방
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

        registry.addResourceHandler("/upload/**") // user 정보를 수정 할때 개방 폴더를 찾기
                .addResourceLocations("file:./upload/")// 파일 경로 수정 및 해당 폴더 개방
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
