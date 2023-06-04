package com.example.demo.bean;

import lombok.Data;

/**
 * 画面に表示されている目標の1つを詰めるbean
 */
@Data
public class TargetBean {
	private String selectedId;
	private String updatedDate;
}
