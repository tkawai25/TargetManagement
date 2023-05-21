package com.example.demo.entity;

import lombok.Data;

@Data
public class SelectBoxEntity {
	/* 値 */
	private String value;
	
	/* ラベル表示用 */
	private String text;
	
	public SelectBoxEntity(String value, String text) {
		setValue(value);
		setText(text);
	}
}
