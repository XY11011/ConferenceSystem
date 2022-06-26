package com.example.demo.controller.interceptor;

/*
 * 使用WebMvcConfigurer来扩展springMVC的功能*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
在Spring 5.0 中，已经将 WebMvcConfigurerAdapter 抽象类加上 @Deprecated 注解 记为过时。
* extends WebMvcConfigurerAdapter  替换为 implements WebMvcConfigurer 即可
* */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login.html").setViewName("login/login");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/webjars/**",
                                "/login.html", "/",
                                "login/login",
                                // 排除检查登录页面
                                "/checkLogin",
                                // 排除首页搜索
                                "/search_allConference",
                                // 排除未登录时查看会议页面
                                "/showConference",
                                "/register",
                                "/checkRegister",
                                "/allConference",
                                "/search_allConference",
                                "/asserts/**",
                                // 排除静态资源
                                "/adminLTE/**", "/bootstrap/**", "/css/**", "/file/**", "/images/**", "/js/**", "/layui/**", "/uploadPapers/**");
            }
        };
        return adapter;
    }

}