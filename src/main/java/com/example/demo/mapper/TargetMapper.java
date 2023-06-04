package com.example.demo.mapper;

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
	 * @param user_id
	 * @param limit
	 * @param searchStr
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TargetEntity> selectTarget(@Param("user_id") int user_id, @Param("limit") int limit, @Param("search_str") String searchStr, @Param("start_date") String startDate, @Param("end_date") String endDate);

	/**
	 * 紐づく手段を取得
	 * 目標取得時に実行される 
	 * @param target_id
	 * @return
	 */
	List<StepEntity> selectStep(@Param("target_id")int target_id);
	
	/**
	 * 排他チェック用
	 * IDと更新日時が一致する目標を取得
	 * @param target_id
	 * @param updated_date
	 * @return
	 */
	TargetEntity selectTargetExclusion(@Param("target_id") int target_id, @Param("updated_date") String updated_date);
	
	/**
	 * 排他チェック用
	 * IDと更新日時が一致する手段を取得
	 * @param step_id
	 * @param updated_date
	 * @return
	 */
	StepEntity selectStepExclusion(@Param("step_id") int step_id, @Param("updated_date") String updated_date);
	
	/**
	 * 目標を削除
	 * 指定の目標IDと一致する目標をDBから削除する
	 * @param target_id
	 */
	void deleteTarget(@Param("target_id") int target_id);
	
	/**
	 * 手段を削除
	 * 指定の手段IDと一致する手段をDBから削除する
	 * @param step_id
	 */
	void deleteStep(@Param("step_id") int step_id);
}
