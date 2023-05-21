package com.example.demo.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.StepEntity;
import com.example.demo.entity.TargetEntity;

@Mapper
public interface TargetMapper {
	/**
	 * 目標を取得
	 * 最新10件
	 * @return 目標の情報
	 * 
	 */
	List<TargetEntity> selectTarget(@Param("user_id") int user_id, @Param("limit") int limit, @Param("search_str") String searchStr, @Param("start_date") Date startDate, @Param("end_date") Date endDate);

	/**
	 * 紐づく手段を取得
	 * 目標取得時に実行される
	 * @return 手段の情報
	 */
	List<StepEntity> selectStep(int target_id);
}
