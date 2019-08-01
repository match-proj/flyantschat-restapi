package com.match.flyantschat.context.configuration;

import com.match.flyantschat.web.v1.V1Version;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class VersionConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBeanV1() {
        AnnotationConfigWebApplicationContext applicationContext  = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan(V1Version.PKG);
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean  = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings(V1Version.MAPPING_PATH);
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName(V1Version.VERSION_NAME);
        return registrationBean;
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBeanV2() {
//        AnnotationConfigWebApplicationContext applicationContext
//                = new AnnotationConfigWebApplicationContext();
//        //base package
//        applicationContext.scan(Constants.Version.V2_PKG);
//        //通过构造函数指定dispatcherServlet的上下文
//        DispatcherServlet rest_dispatcherServlet
//                = new DispatcherServlet(applicationContext);
//
//        //用ServletRegistrationBean包装servlet
//        ServletRegistrationBean registrationBean
//                = new ServletRegistrationBean(rest_dispatcherServlet);
//        registrationBean.setLoadOnStartup(1);
//        //指定urlmapping
//        registrationBean.addUrlMappings("/v2.0/*");
//        //指定name，如果不指定默认为dispatcherServlet
//        registrationBean.setName(Constants.Version.V2);
//        return registrationBean;
//    }
}
