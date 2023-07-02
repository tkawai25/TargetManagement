package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectBoxEntity {
	/* 値 */
	private String value;
	
	/* ラベル表示用 */
	private String text;
}
