package com.example.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class BaseController {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/Messages","messages/HTMLMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
}
