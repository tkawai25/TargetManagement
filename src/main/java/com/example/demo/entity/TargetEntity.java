package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetEntity {
	private int targetId;
	private String detail;
	private String period;
	private String result;
	private String updatedDate;
	private List<StepEntity> stepList = new ArrayList<StepEntity>();
}
