package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.bean.TargetStepCsvBean;
import com.example.demo.entity.StepEntity;
import com.example.demo.entity.TargetEntity;

public class CsvUtil {

	/**
	 * 目標リストをCSV用のbeanにつめて返却
	 * @param targetList
	 * @return
	 */
	public static List<TargetStepCsvBean> targetEntityToCsvBean(List<TargetEntity> targetList){
		List<TargetStepCsvBean> csvBeanList = new ArrayList<TargetStepCsvBean>();
		for(TargetEntity targetEnt : targetList) {
			TargetStepCsvBean bean = new TargetStepCsvBean();
			bean.setTargetId(targetEnt.getTargetId());
			bean.setTargetKind("目標");
			bean.setDetail(targetEnt.getDetail());
			bean.setPeriod(targetEnt.getPeriod());
			bean.setResult(targetEnt.getResult());
			csvBeanList.add(bean);
			for(StepEntity stepEnt : targetEnt.getStepList()) {
				bean = new TargetStepCsvBean();
				bean.setTargetId(targetEnt.getTargetId());
				bean.setTargetKind("手段");
				bean.setDetail(stepEnt.getDetail());
				bean.setPeriod(stepEnt.getPeriod());
				bean.setResult(stepEnt.getResult());
				csvBeanList.add(bean);
			}
		}
		return csvBeanList;
	}
	

}
