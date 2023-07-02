package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface U0201ControllerMapper {
	/**
	 * ユーザマスタのINSERT
	 */
	void insertMstUser(@Param("user_id") int user_id, @Param("name") String name, @Param("mail_address") String mail_address,
			@Param("password") String password, @Param("insert_user") int insert_user, @Param("insert_date") String insert_date,
			@Param("updated_user") int updated_user, @Param("updated_date") String updated_date);
	
	/**
	 * 目標トランのINSERT
	 */
	void insertTrnTarget(@Param("target_id") int target_id, @Param("detail") String detail, @Param("period") String period,
			@Param("result") String result,@Param("user_id") int user_id, @Param("insert_user") int insert_user,
			@Param("insert_date") String insert_date, @Param("updated_user") int updated_user, @Param("updated_date") String updated_date);
	
	/**
	 * 手段トランのINSERT
	 */
	void insertTrnStep(@Param("step_id") int step_id, @Param("detail") String detail, @Param("period") String period,
			@Param("result") String result,@Param("target_id") int target_id, @Param("insert_user") int insert_user,
			@Param("insert_date") String insert_date, @Param("updated_user") int updated_user, @Param("updated_date") String updated_date);
}
