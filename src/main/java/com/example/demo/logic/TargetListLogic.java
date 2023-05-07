package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StepDto;
import com.example.demo.dto.TargetDto;
import com.example.demo.form.U0201Form;
import com.example.demo.service.TargetListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TargetListLogic extends BaseLogic {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private TargetListService service;

	public void inputData(U0201Form form){
		//DBから目標を取得
		List<Map<String, Object>> resultAll = jdbcTemplate.queryForList(service.SELECT001, getSelectedUserId());
		List<TargetDto> targetList = form.getTargetList();
		TargetDto dto = null;
		String preTargetId = "";
		for (Map<String, Object> result : resultAll) {
			//ターゲットIDが前回と異なる場合にターゲットdtoを作成し、目標リストへ追加する。
			if(!preTargetId.equals(toString(result.get("TT.target_id")))) {
				//初回の目標リストへの追加を防ぐ。
				if(!isNullOrEmpty(dto)) {
					targetList.add(dto);
				}
				dto = new TargetDto();
				dto.setTargetId(toString(result.get("TT.target_id")));
				dto.setDetail(toString(result.get("TT.detail")));
				dto.setPeriod(toString(result.get("TT.period")));
				dto.setUpdatedDate(toString(result.get("updated_date")));
				preTargetId = toString(result.get("TT.target_id"));
			}
			//手段が取れていたらDTOを作成して手段リストへ追加する。
			if(!isNullOrEmpty(result.get("TS.step_id"))){
				StepDto sDto = new StepDto();
				sDto.setStepId(toString(result.get("TS.step_id")));
				sDto.setDetail(toString(result.get("TS.detail")));
				sDto.setPeriod(toString(result.get("TS.period")));
				sDto.setUpdatedDate(toString(result.get("TS.updated_date")));
				dto.getStepList().add(sDto);
			}
		}
		//最後のdtoを追加する
		if(!isNullOrEmpty(dto)) {
			targetList.add(dto);
		}
	}
}
