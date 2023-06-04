package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 画面に表示されている手段一覧を詰めるbean
 */
@Data
public class StepListBean {
	private List<StepBean> stepList = new ArrayList<StepBean>();
}
