//package com.example.userapp.webConfig;
//
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (!registry.hasMappingForPattern("/assets/**")) {
//            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
//        }
//    }
//}
