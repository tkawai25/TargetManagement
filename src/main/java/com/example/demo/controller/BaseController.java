package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import jakarta.servlet.http.HttpSession;

public class BaseController {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    @Bean
    public ResourceBundleMessageSource messageSource() {
        messageSource.setBasenames("messages/Messages","messages/HTMLMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    public String getMsg(String key) {
        String msg = messageSource.getMessage(key, null, Locale.JAPAN);
        return msg;
      }
    
	@Autowired
	HttpSession session;
	
	public void setSelectedUserId(String userId) {
		session.setAttribute("user_id", userId);
	}
	
	public String getSelectedUserId() {
		return (session.getAttribute("user_id")).toString();
	}
	
}
