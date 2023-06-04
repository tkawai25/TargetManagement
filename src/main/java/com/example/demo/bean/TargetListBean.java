package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 画面に表示されている目標一覧を詰めるbean
 */
@Data
public class TargetListBean {
	private List<TargetBean> targetList = new ArrayList<TargetBean>();
}
