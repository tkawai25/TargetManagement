package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import jakarta.servlet.http.HttpSession;

public class BaseController {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/Messages","messages/HTMLMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
	@Autowired
	HttpSession session;
	
	public void setSelectedUserId(String userId) {
		session.setAttribute("user_id", userId);
	}
	
	public String getSelectedUserId() {
		return (session.getAttribute("user_id")).toString();
	}
	
	/**
	 * 文字列中の改行を削除する。
	 * @param str 削除前文字列
	 * @return str 削除後文字列
	 */
	public String removeLine(String str) {
	    str = str.replaceAll("\n", "");
	    return str;
	}
	
	/**
	 * 文字列中の記号をエスケープ後に変換する
	 * @param str 変換前文字列
	 * @return str 返還後文字列
	 */
	public String conversionStr(String str) {
	    // エスケープ文字をエスケープ
	    str = str.replaceAll("\\\\", "\\\\\\\\");
	    // %をエスケープ
	    str = str.replaceAll("%", "\\\\%");
	    // _をエスケープ
	    str = str.replaceAll("_", "\\\\_");
	    
	    return str;
	}
	
	/**
	 * 文字列を%で囲む
	 * @param str 変換前文字列
	 * @return str 返還後文字列
	 */
	public String enclosePercent(String str) {
		return "%" + str + "%";
	}
	

}
