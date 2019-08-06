package com.match.flyantschat.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangchao
 * @Date 2019/7/31 18:02
 * @Version v1.0
 */
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.match.*.client")
@EnableHystrix                // 开启断路器
@EnableHystrixDashboard
@SpringBootApplication
@ComponentScan({"com.match.flyantschat.context","com.match.*.client.fallback","com.match.common"})
@EnableEurekaClient
//@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
