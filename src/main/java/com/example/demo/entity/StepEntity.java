package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepEntity {
	private int stepId;
	private String detail;
	private String period;
	private String result;
	private String updatedDate;
}
