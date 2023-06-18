package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.SearchDateBean;
import com.example.demo.bean.StepListBean;
import com.example.demo.bean.TargetListBean;
import com.example.demo.bean.TargetStepCsvBean;
import com.example.demo.entity.TargetEntity;
import com.example.demo.form.U0201Form;
import com.example.demo.service.TargetListService;
import com.example.demo.util.CsvUtil;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

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
		//初期表時用の目標リストを取得
		form.setTargetList(service.inputData(form.getSelectedUserId()));
		
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
			searchStr = StrUtil.removeLine(searchStr);
			//特殊文字のエスケープ
			searchStr = StrUtil.conversionStr(searchStr);
			//%で文字列を囲む
			searchStr = StrUtil.enclosePercent(searchStr);
		}
		
		SearchDateBean dateBean = new SearchDateBean();
		dateBean = service.getSearchCriteriaDate(form.getSelectedPeriod(),form.getSearchStart(),form.getSearchEnd());
		
		//検索条件に該当する目標リストを取得
		form.setTargetList(service.inputData(form.getSelectedUserId(),searchStr,dateBean.getStartDate(),dateBean.getEndDate()));
		
		//検索エリアのリストボックスを作成
		form.setPeriodList(service.createSelectBox());
		model.addAttribute("form", form);
		return "U02-01/index";
	}
	
	/**
	 * 目標・手段削除処理
	 * @param model
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delete")
	public String delete(Model model, @ModelAttribute U0201Form form)  throws Exception{

			//画面に表示されている目標・手段リストを取得
			TargetListBean targetListBean = service.jsonToTargeListtBean(form.getShowTargetList());
			StepListBean stepListBean = service.jsonToStepListBean(form.getShowStepList());

			//排他チェック
			if(!service.checkExclusion(targetListBean.getTargetList(),stepListBean.getStepList())) {
				form.getErrMessage().add(getMsg("G0201.ERR001"));
				return show(model,form);
			}
			
			//削除実行
			service.delete(form.getKbn(), form.getSelectedDataId());
			
			//初期表示
			return show(model,form);
	}
	
	/**
	 * CSVダウンロード
	 * @param model
	 * @param form
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping(value="/download")
	public ResponseEntity<byte[]> download(@ModelAttribute U0201Form form) throws JsonProcessingException, UnsupportedEncodingException {
		//Listを取得
		List<TargetEntity> targetList = service.inputData(form.getSelectedUserId(),0);
		//CsvBeanListにつめる
		List<TargetStepCsvBean> csvBeanList = CsvUtil.targetEntityToCsvBean(targetList);
        //csvBeanクラスでマッピングし、csv出力する
		CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(TargetStepCsvBean.class).withHeader();
        String csvStr = mapper.writer(schema).writeValueAsString(csvBeanList);
        //レスポンスヘッダーの設定
        HttpHeaders headers = new HttpHeaders();
        HttpUtil.addContentDisposition(headers, "download.csv");
		return new ResponseEntity<>(csvStr.getBytes("UTF-8"), headers, HttpStatus.OK);
	}
}
