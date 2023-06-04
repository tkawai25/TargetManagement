package com.example.demo.util;

public class StrUtil {
	/**
	 * 文字列中の改行を削除する。
	 * @param str 削除前文字列
	 * @return str 削除後文字列
	 */
	public static String removeLine(String str) {
	    str = str.replaceAll("\n", "");
	    return str;
	}
	
	/**
	 * 文字列中の記号をエスケープ後に変換する
	 * @param str 変換前文字列
	 * @return str 返還後文字列
	 */
	public static String conversionStr(String str) {
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
	public static String enclosePercent(String str) {
		return "%" + str + "%";
	}
	
}
