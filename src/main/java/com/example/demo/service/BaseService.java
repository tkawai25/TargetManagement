package com.example.demo.service;

import java.sql.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class BaseService {
	
	/** 目標取得件数 */
	protected static final int TARGET_LIMIT = 10;
	
	/**
	 * 引数のオブジェクトをStringにキャストして返却する。
	 * 引数がnullまたは空文字の場合は空文字を返却する。
	 * @param o
	 * @return
	 */
	protected String toString(Object o) {
		try {
			if(Objects.isNull(o) || o=="") {
				return "";
			}else {
				return (String) o;
			}
		}catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 引数がnullまたは空文字かどうか判別する。
	 * @param o
	 * @return true：nullまたは空文字 false:それ以外
	 */
	public boolean isNullOrEmpty(Object o) {
		if(Objects.isNull(o) || o=="") {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 引数のStringをintがたにキャストして返却する。
	 */
	protected int toInt(String str) {
        // 正規表現の設定
        String regex = "^-?[0-9]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m1 = p.matcher(str);
		try {
			if(m1.find()) {
				return Integer.parseInt(str);
			}else {
				return -1;
			}
			
		}catch(Exception e) {
			return -1;
		}
	}
	
	/**
	 * 文字列をsql.Date型に変換する
	 * @param str 形式：yyyy-mm-dd
	 * @return
	 */
	public Date strToDate(String str) {
		try{	
			if(str.equals(null) || str.isEmpty()) {
				return null;
			}else {
				return Date.valueOf(str);
			}
		}catch(Exception e) {
			return null;
		}
	}
}
