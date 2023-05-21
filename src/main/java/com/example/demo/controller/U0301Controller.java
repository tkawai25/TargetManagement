package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/targetregist")
public class U0301Controller extends BaseController {
	
	/**
	 * 画面初期表示
	 * @param model
	 * @param form
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String show(Model model) {

		return "U03-01/index";
	}

}
