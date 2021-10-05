package org.sterl.education.angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SpringAngularStarterApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SpringAngularStarterApplication.class, args);
    }
}
