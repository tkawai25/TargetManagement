package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.BaseControllerMapper;

class BaseControllerTest {
	@Autowired
	private BaseControllerMapper baseMapper;
	
	/**
	 * 全テーブルTRUNCATE実行
	 */
	@BeforeEach
	public void truncateTable() {
		baseMapper.truncateMstUser();
		baseMapper.truncateTrnTarget();
		baseMapper.truncateTrnStep();
	}

}
