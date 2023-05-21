package com.example.demo.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.U0201Form;
import com.example.demo.service.TargetListService;

@Controller
@RequestMapping(value = "/targetlists")

public class U0201Controller extends BaseController {

	@Autowired
	private TargetListService service;
	

	
	/**
	 * 画面初期表示
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String show(Model model, U0201Form form) {
		setSelectedUserId(form.getSelectedUserId());
		
		//初期表時用の目標リストを取得
		form.setTargetList(service.inputData("1"));
		
		//検索エリアのリストボックスを作成
		form.setPeriodList(service.createSelectBox());
		
		model.addAttribute("form", form);
		return "U02-01/index";
	}

	/**
	 * 検索後表示
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping("/search")
	public String search(Model model, @ModelAttribute U0201Form form) {
		
		//検索文字列に入力がある場合
		String searchStr = form.getSearchStr();
		if(!service.isNullOrEmpty(searchStr)) {
			//改行の削除
			//特殊文字のエスケープ
			//%で文字列を囲む
			searchStr = enclosePercent(conversionStr(removeLine(searchStr)));
			
		}
		
		Map<String,Date> dateMap = service.getSearchCriteriaDate(form.getSelectedPeriod(),form.getSearchStart(),form.getSearchEnd());
		
		//検索条件に該当する目標リストを取得
		form.setTargetList(service.inputData("1",searchStr,dateMap.get("startDate"),dateMap.get("endDate")));
		
		//検索エリアのリストボックスを作成
		form.setPeriodList(service.createSelectBox());
		model.addAttribute("form", form);
		return "U02-01/index";
	}


}
