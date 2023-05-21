package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SelectBoxEntity;
import com.example.demo.entity.TargetEntity;
import com.example.demo.mapper.TargetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TargetListService extends BaseService {

	@Autowired
	private TargetMapper trgetMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * DBから目標と手段の一覧を取得して返却する。
	 * @param userId
	 * @return 
	 */
	public List<TargetEntity> inputData(String userId){
		return inputData(userId, null, null, null);
	}
	
	/**
	 * DBから目標と手段の一覧を取得して返却する。
	 * 検索条件を指定する。
	 * @param userId
	 * @param searchStr
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<TargetEntity> inputData(String userId, String searchStr, Date startDate, Date endDate){
	
		return trgetMapper.selectTarget(toInt(userId), TARGET_LIMIT, searchStr, startDate, endDate);
	}
	
	/**
	 *  検索エリアのセレクトボックスを作成する。
	 */
	public List<SelectBoxEntity> createSelectBox() {
		List<SelectBoxEntity> list = new ArrayList<SelectBoxEntity>();
		list.add(new SelectBoxEntity("0", "指定なし"));
		list.add(new SelectBoxEntity("1", "1ヶ月以内"));
		list.add(new SelectBoxEntity("2", "1週間以内"));
		list.add(new SelectBoxEntity("3", "日付を選ぶ"));
		
		return list;
	}
	
	/**
	 * セレクトボックスで選択された選択肢から
	 * 検索条件の期限を取得する。
	 * @param selectedPeriod
	 * @param startDate
	 * @param endDate
	 * @return Map<String,Date>
	 */
	public Map<String,Date> getSearchCriteriaDate(String selectedPeriod, String startDate, String endDate){
		 Map<String,Date> res = new HashMap<>();
		 
	        Calendar cal = Calendar.getInstance();
			//検索条件期限を設定する
			switch(selectedPeriod) {
				case "0":
				case "3":
					break;
				
				case "1":
			        startDate = sdf.format(cal.getTime());
					cal.add(Calendar.MONTH, 1);
			        endDate = sdf.format(cal.getTime());
			        break;
				
				case "2":
					startDate = sdf.format(cal.getTime());
			        cal.add(Calendar.DATE, 7);
			        endDate = sdf.format(cal.getTime());
			        break;
			}
			
			res.put("startDate", strToDate(startDate));
			res.put("endDate", strToDate(endDate));
			
			return res;

	}
}
