package ase.newportreuseandrecycle.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestRouter implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about-us").setViewName("about-us");
        registry.addViewController("/listings").setViewName("products/listings");
//        registry.addViewController("/listings/add").setViewName("products/add-listing");
    }
}
