package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseControllerMapper {
	/**
	 * ユーザマスタのTRUNCATE
	 */
	void truncateMstUser();
	
	/**
	 * 目標トランのTRUNCATE
	 */
	void truncateTrnTarget();
	
	/**
	 * 手段トランのTRUNCATE
	 */
	void truncateTrnStep();
}
