package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.SelectBoxEntity;
import com.example.demo.entity.TargetEntity;

import lombok.Data;

@Data
public class U0201Form {
	/* 期限リスト */
	private List<SelectBoxEntity> periodList = new ArrayList<SelectBoxEntity>();

	/* 目標リスト */	
	private List<TargetEntity> targetList = new ArrayList<TargetEntity>();
	
	/* 選択ユーザID */
	private String selectedUserId = "1";
	
	/* 選択目標ID */
	private String selectedTargetId;
	
	/* 選択手段ID */
	private String slectedStepID;
	
	/* 検索文字列 */
	private String searchStr;
	
	/* 選択検索期限 */
	private String selectedPeriod;
	
	/* 期限検索：始まり */
	private String searchStart;
	
	/* 期限検索：終わり */
	private String searchEnd;
	
}
