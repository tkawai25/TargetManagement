package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"目標ID", "目標種別", "詳細","期限","結果"})
public class TargetStepCsvBean {
	@JsonProperty("目標ID")
	private int targetId;
	
	@JsonProperty("目標種別")
	private String targetKind;

	@JsonProperty("詳細")
	private String detail;

	@JsonProperty("期限")
	private String period;

	@JsonProperty("結果")
	private String result;
}
