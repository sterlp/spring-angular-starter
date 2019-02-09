package org.sterl.education.angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringAngularStarterApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularStarterApplication.class, args);
	}
	
	/**
	 * We can get the resources directly from the JAR file.
	 * As so the front end JAR cannot be optional
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/frontend/0.1.0-SNAPSHOT/");
    }
	/**
	 * This is needed in case we have no index.html in the static folder.
	 * In the JAR solution we don't even have the static folder ...
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("forward:/index.html");
	}
}
