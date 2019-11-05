package com.match.flyantschat.web.v1.config;

import com.match.flyantschat.web.v1.V1Version;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author pc
 */
@Configuration
@ComponentScan(basePackages = V1Version.PKG)
@EnableWebMvc
public class ServletConfiguration {


}
