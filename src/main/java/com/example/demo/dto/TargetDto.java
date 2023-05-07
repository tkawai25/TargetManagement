package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TargetDto {
	private String targetId;
	private String detail;
	private String period;
	private String updatedDate;
	private List<StepDto> stepList = new ArrayList<StepDto>();
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public List<StepDto> getStepList() {
		return stepList;
	}
	public void setStepList(List<StepDto> stepList) {
		this.stepList = stepList;
	}
}
