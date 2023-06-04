package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TargetEntity {
	private int targetId;
	private String detail;
	private String period;
	private String result;
	private String updatedDate;
	private List<StepEntity> stepList = new ArrayList<StepEntity>();
}
