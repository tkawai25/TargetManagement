package com.example.demo.dto;

public class SelectBoxDto {
	/* 値 */
	private String value;
	
	/* ラベル表示用 */
	private String text;

	public SelectBoxDto(String value, String text) {
		setValue(value);
		setText(text);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
