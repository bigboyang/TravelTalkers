package com.example.miniproject.Config;


import com.example.miniproject.Interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static String adminHttp = "/admin/**";
    private final static String LoginHttp = "/user/login.do";
    private final static String MypageHttp = "/user/mypage/**";
    private final static String insertBoardHttp = "/board/goInsert";
    private final static String UserHttp = "/user/**";
    private final static String commentHttp = "/board/insertComment";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns(adminHttp);

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(LoginHttp);

        registry.addInterceptor(new MypageInterceptor())
                .addPathPatterns(MypageHttp)
                .addPathPatterns("/chat/**")
                .addPathPatterns(insertBoardHttp);


        registry.addInterceptor(new BoardInterceptor()).addPathPatterns(commentHttp);

        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/user/login")
                .addPathPatterns("/user/signup");

    }
}
