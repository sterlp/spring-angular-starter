package org.sterl.education.angular;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Controller
// just scan this app
@ComponentScan("org.sterl.education.angular")
@EnableSpringDataWebSupport
public class SpringAngularStarterApplication implements WebMvcConfigurer {
    /**
     * This requires to be enabled build-info
     * https://docs.spring.io/spring-boot/docs/current/maven-plugin/examples/build-info.html
     */
    @Autowired private BuildProperties buildProperties;

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularStarterApplication.class, args);
    }
    
    /**
     * We can get the resources directly from the JAR file.
     * As so the front end JAR cannot be optional
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String jarResourcePath = "classpath:/META-INF/resources/webjars/frontend/" + buildProperties.getVersion() + "/";
        // no caching for HTML pages
        registry.addResourceHandler("/ui/*.html")
                .addResourceLocations(jarResourcePath);

        // the remaining stuff for 365 days
        // apply custom config as needed
        registry.addResourceHandler("/ui/**")
                .addResourceLocations(jarResourcePath)
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(true);
    }
    /**
     * Route the base path to the UI
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/ui/index.html");
    }
    /**
     * Angular UI is hosted behind /ui/* so we route everything to the index.html
     
    @Bean
    public OncePerRequestFilter angularForwardFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                final String requestURI = request.getRequestURI();
                if (requestURI.startsWith("/ui") && !requestURI.endsWith("/ui/index.html") && !requestURI.contains(".")) {
                    request.getRequestDispatcher("/ui/index.html").forward(request, response);
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        };
    }
    */

    /**
     * Taking care of any angular HTML5 routes -- we just forward them to the index.html
     * https://spring.io/blog/2015/05/13/modularizing-the-client-angular-js-and-spring-security-part-vii#using-ldquo-natural-rdquo-routes
    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
    */
}