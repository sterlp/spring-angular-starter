package org.sterl.education.angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringAngularStarterApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularStarterApplication.class, args);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/**
		 * We can get the resources directly from the JAR file.
		 * As so the front end JAR cannot be optional
		 */
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/frontend/0.1.0-SNAPSHOT/");
    }
}
