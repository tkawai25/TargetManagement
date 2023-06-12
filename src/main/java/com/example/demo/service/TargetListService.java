package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.SearchDateBean;
import com.example.demo.bean.StepBean;
import com.example.demo.bean.TargetBean;
import com.example.demo.entity.SelectBoxEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.entity.TargetEntity;
import com.example.demo.mapper.TargetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TargetListService extends BaseService {

	@Autowired
	private TargetMapper targetMapper;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	/**
	 * DBから目標と手段の一覧を取得して返却する。
	 * @param userId
	 * @return 
	 */
	public List<TargetEntity> inputData(int userId){
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
	public List<TargetEntity> inputData(int userId, String searchStr, String startDate, String endDate){
	
		return inputData(userId, TARGET_LIMIT, searchStr, startDate, endDate);
	}
	
	/**
	 * DBから目標と手段の一覧を取得して返却する。
	 * 件数指定。(limitが0の場合は全件取得)
	 * @param userId
	 * @param limit
	 * @return
	 */
	public List<TargetEntity> inputData(int userId, int limit){
		
		return inputData(userId, limit, null, null, null);
	}
	
	/**
	 * マッパーからSQLを実行する
	 * @param userId
	 * @param limit
	 * @param searchStr
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<TargetEntity> inputData(int userId, int limit, String searchStr, String startDate, String endDate){
	
		return targetMapper.selectTarget(userId, limit, searchStr, startDate, endDate);
	}
	

	
	/**
	 * 排他チェック用
	 * DBから目標IDと更新日時が一致するデータを取得して返却する。
	 * @param targetId
	 * @param updatedDate
	 * @return
	 */
	private TargetEntity inpuDataExclusionTarget(int targetId, String updatedDate) {
		return targetMapper.selectTargetExclusion(targetId, updatedDate);
	}
	
	/**
	 * 排他チェック用
	 * DBから目標IDと更新日時が一致するデータを取得して返却する。
	 * @param targetId
	 * @param updatedDate
	 * @return
	 */
	private StepEntity inpuDataExclusionStep(int stepId, String updatedDate) {
		return targetMapper.selectStepExclusion(stepId, updatedDate);
	}
	
	private void deleteTarget(int selectedId) {
		targetMapper.deleteTarget(selectedId);
	}
	
	private void deleteStep(int selectedId) {
		targetMapper.deleteStep(selectedId);
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
	public SearchDateBean getSearchCriteriaDate(String selectedPeriod, String startDate, String endDate){
		SearchDateBean dateBean = new SearchDateBean();
		 
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
			
			dateBean.setStartDate(startDate);
			dateBean.setEndDate(endDate);
			
			return dateBean;

	}
	
	/**
	 * 排他チェック
	 * 画面に表示されている目標・手段が取得できるか確認
	 * @param targetList
	 * @param stepList
	 * @return true：変更なし false：変更あり
	 * @throws NumberFormatException
	 */
	public boolean checkExclusion(List<TargetBean> targetList, List<StepBean> stepList) {
		//目標が取得できるか確認
		for(TargetBean bean : targetList) {
			TargetEntity targerEnt = new TargetEntity();
			targerEnt = inpuDataExclusionTarget(Integer.parseInt(bean.getSelectedId()),bean.getUpdatedDate());
			
			//取得できなければ更新されているため排他エラー
			if(isNullOrEmpty(targerEnt)) {
				return false;
			}
		}
		
		//手段が取得できるか確認
		for(StepBean bean : stepList) {
			StepEntity stepEnt = new StepEntity();
			stepEnt = inpuDataExclusionStep(Integer.parseInt(bean.getSelectedId()),bean.getUpdatedDate());
			
			//取得できなければ更新されているため排他エラー
			if(isNullOrEmpty(stepEnt)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void delete(String kbn, String selectedId) {
		//選択されたデータが目標か手段で分岐
		if(kbn.equals("0")) {
			deleteTarget(selectedId);
		}else {
			deleteStep(selectedId);
		}
	}
	
	public void deleteTarget(String selectedId) {
		deleteTarget(Integer.parseInt(selectedId));
	}
	
	public void deleteStep(String selectedId) {
		deleteStep(Integer.parseInt(selectedId));
	}
}
