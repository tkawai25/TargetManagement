package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.SelectBoxDto;
import com.example.demo.dto.TargetDto;

import lombok.Data;

@Data
public class U0201Form {
	/* 期限リスト */
	private List<SelectBoxDto> periodList = new ArrayList<SelectBoxDto>();

	/* 目標リスト */	
	private List<TargetDto> targetList = new ArrayList<TargetDto>();
	
	/* 選択ユーザID */
	private String selectedUserId = "1";
	
	public List<SelectBoxDto> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<SelectBoxDto> periodList) {
		this.periodList = periodList;
	}
	
	public List<TargetDto> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TargetDto> targetList) {
		this.targetList = targetList;
	}
}
