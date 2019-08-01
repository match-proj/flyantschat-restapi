//package com.match.flyantschat.web.v1.config;
//
//import com.zqkh.appapi.context.auth.AuthHandlerInterceptor;
//import com.zqkh.appapi.context.configurtion.converter.CustomerMappingJackson2HttpMessageConverter;
//import com.zqkh.appapi.context.constant.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.List;
//
///**
// * Created by zhaofujun on 2017/8/5.
// */
//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
//
//    public static final String V1_PATH = "/" + Constants.Version.V1;
//
//    @Autowired(required = false)
//    private AuthHandlerInterceptor authHandlerInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        if (authHandlerInterceptor == null) authHandlerInterceptor = new AuthHandlerInterceptor();
//        authHandlerInterceptor.addAnonymousPaths(
//                V1_PATH + "/swagger-resources",
//                V1_PATH + "/swagger-resources/**",
//                V1_PATH + "/error",
//                V1_PATH + "/v2/api-docs*",
//                V1_PATH + "/configuration/security",
//                V1_PATH + "/configuration/ui"
//        );
//        registry.addInterceptor(authHandlerInterceptor);
//        super.addInterceptors(registry);
//    }
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
//                .maxAge(3600);
//    }
//
//    public void addAnonymousPaths(String... anonymousPaths) {
//        if (authHandlerInterceptor == null) authHandlerInterceptor = new AuthHandlerInterceptor();
//
//        this.authHandlerInterceptor.addAnonymousPaths(anonymousPaths);
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(new CustomerMappingJackson2HttpMessageConverter());
//    }
//
//
//}
