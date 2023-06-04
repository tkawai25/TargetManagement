package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.bean.StepBean;
import com.example.demo.bean.StepListBean;
import com.example.demo.bean.TargetBean;
import com.example.demo.bean.TargetListBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BaseService {
	
	/** 目標取得件数 */
	public static final int TARGET_LIMIT = 10;
	
	/**
	 * 引数がnullまたは空文字かどうか判別する。
	 * @param o
	 * @return true：nullまたは空文字 false:それ以外
	 */
	public boolean isNullOrEmpty(Object o) {
		if(Objects.isNull(o) || o=="") {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 対象が目標か手段を判断する
	 * @param targetId
	 * @return true:目標 false:手段
	 */
	public boolean isTargetOrStep(String targetId) {
		if(targetId == null | targetId == "") {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * JSON文字列をTargetBeanに変換
	 * ※共通化できるが時間がないのでこのまま
	 * @param jsonStr
	 * @return
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public TargetListBean jsonToTargeListtBean(String jsonStr) throws JsonMappingException, IOException{
		if(jsonStr == null){
			//パラメータがnullの場合、nullを返します
			return null;
		}
 
		//Jacksonのマッパーを生成
		ObjectMapper mapper = new ObjectMapper();
 
		//Json文字列をbeanリストに変換
		List<TargetBean> beanList = mapper.readValue(jsonStr, new TypeReference<List<TargetBean>>() {});
		//リストにつめる
		TargetListBean beans = new TargetListBean();
		beans.setTargetList(beanList);
 
		return beans;
	}
	
	/**
	 * JSON文字列をStepBeanに変換
	 * ※共通化できるが時間がないのでこのまま
	 * @param jsonStr
	 * @return
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public StepListBean jsonToStepListBean(String jsonStr) throws JsonMappingException, IOException{
		if(jsonStr == null){
			//パラメータがnullの場合、nullを返します
			return null;
		}
 
		//Jacksonのマッパーを生成
		ObjectMapper mapper = new ObjectMapper();
 
		//Json文字列をbeanリストに変換
		List<StepBean> beanList = mapper.readValue(jsonStr, new TypeReference<List<StepBean>>() {});
		//リストにつめる
		StepListBean beans = new StepListBean();
		beans.setStepList(beanList);
 
		return beans;
	}
	
}
