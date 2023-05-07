package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class BaseLogic {
	@Autowired
	HttpSession session;
	
	public void setSelectedUserId(String userId) {
		session.setAttribute("user_id", userId);
	}
	
	public String getSelectedUserId() {
		return toString(session.getAttribute("user_id"));
	}
	
	/**
	 * 引数のオブジェクトをStringにキャストして返却する。
	 * 引数がnullまたは空文字の場合は空文字を返却する。
	 * @param o
	 * @return
	 */
	protected String toString(Object o) {
		if(o.equals(null) || o=="") {
			return "";
		}else {
			return (String) o;
		}
	}
	
	/**
	 * 引数がnullまたは空文字かどうか判別する。
	 * @param o
	 * @return true：nullまたは空文字 false:それ以外
	 */
	protected boolean isNullOrEmpty(Object o) {
		if(o.equals(null) || o=="") {
			return true;
		}else {
			return false;
		}
	}
}
