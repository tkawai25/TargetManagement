package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.SelectBoxDto;
import com.example.demo.form.U0201Form;
import com.example.demo.logic.TargetListLogic;

@Controller
@RequestMapping(value = "/targetlists")
public class U0201Controller extends BaseController {

	@Autowired
	private TargetListLogic logic;
	
	/**
	 * 画面初期表示
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String show(Model model, U0201Form form) {
		//初期表時用の目標リストを取得
		logic.inputData(form);
		
		//検索エリアのリストボックスを作成
		createSelectBox(form);
		model.addAttribute("form", form);
		return "U02-01/index";
	}

	/**
	 * 検索後表示
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping("/search")
	public String search(Model model, U0201Form form) {
		//検索エリアのリストボックスを作成
		createSelectBox(form);
		model.addAttribute("form", form);
		return "U02-01/index";
	}

	/**
	 *  検索エリアのセレクトボックスを作成する。
	 */
	public void createSelectBox(U0201Form form) {
		List<SelectBoxDto> list = form.getPeriodList();
		list.add(new SelectBoxDto("0", "指定なし"));
		list.add(new SelectBoxDto("1", "1ヶ月以内"));
		list.add(new SelectBoxDto("2", "1週間以内"));
	}
}
