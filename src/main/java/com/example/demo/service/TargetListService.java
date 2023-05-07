package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TargetListService {
	/**
	 * 初期表示用の目標取得クエリ
	 * 最大取得件数は目標が10件
	 */
	public String SELECT001 = 
			"SELECT "
			+ " TT.target_id, "
			+ " TT.detail, "
			+ " TT.period, "
			+ " TT.result, "
			+ " TT.updated_date, "
			+ " TS.step_id, "
			+ " TS.detail, "
			+ " TS.period, "
			+ " TS.result, "
			+ " TS.updated_date "
			+ "FROM "
			+ " (SELECT "
			+ "  	* "
			+ "  FROM "
			+ "  	target_trn "
			+ "  WHERE "
			+ "  	user_id = ? "
			+ "  ORDER BY "
			+ "  	period DESC"
			+ "  LIMIT 10) "
			+ "  AS TT"
			+ "LEFT JOIN "
			+ " step_trn ST "
			+ "ON "
			+ " TT.target_id = ST.target_id "
			+ "ORDER BY "
			+ " ST.period DESC ";
	
}
