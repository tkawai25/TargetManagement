package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.entity.SelectBoxEntity;
import com.example.demo.entity.StepEntity;
import com.example.demo.entity.TargetEntity;
import com.example.demo.form.U0201Form;
import com.example.demo.mapper.U0201ControllerMapper;

@SpringBootTest
class U0201ControllerTest extends BaseControllerTest{
	private MockMvc mockMvc;
	
	@Autowired
	U0201Controller target;
	
	@Autowired
	U0201ControllerMapper mapper;
	
    @BeforeEach
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
    }
    
    @BeforeEach
    public void setData() {
    	//ユーザマスタ
    	mapper.insertMstUser(1, "テストユーザ", "testUser1@xxx.jp", "", 0, "2023-07-01 23:59:59", 0, "2023-07-01 23:59:59");
    	//目標トラン
    	mapper.insertTrnTarget(1, "目標1", "2023-08-01", "×", 1, 0, "2023-07-01 23:59:59", 0, "2023-07-01 23:59:59");
    	//手段トラン
    	mapper.insertTrnStep(1, "手段1", "2023-08-01", "×", 1, 0, "2023-07-01 23:59:59", 0, "2023-07-01 23:59:59");
    
    }
	
    /**
     * 初期表示　成功
     * @throws Exception
     */
	@Test
	void testIndexActionSuccess() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		form.setSelectedDataId(null);
		form.setKbn(null);
		form.setSearchStr(null);
		form.setSelectedPeriod(null);		
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList(null);
		form.setShowStepList(null);
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform((get("/targetlists/index"))
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        StepEntity sEnt = new StepEntity(1, "手段1", "2023-08-01", "×", "2023-07-01 23:59:59");
        stepList.add(sEnt);
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals(null,resultForm.getSelectedDataId());
        assertEquals(null,resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals(null,resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals(null,resultForm.getShowTargetList());
        assertEquals(null,resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 検索　成功
     * 文字列：未入力
     * 期限：指定なし
     * @throws Exception
     */
	@Test
	void testSearchActionSuccess1() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		form.setSelectedDataId(null);
		form.setKbn(null);
		//検索条件
		form.setSearchStr(null);
		form.setSelectedPeriod("0");
		
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList(null);
		form.setShowStepList(null);
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/search")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        StepEntity sEnt = new StepEntity(1, "手段1", "2023-08-01", "×", "2023-07-01 23:59:59");
        stepList.add(sEnt);
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals(null,resultForm.getSelectedDataId());
        assertEquals(null,resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals("0",resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals(null,resultForm.getShowTargetList());
        assertEquals(null,resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 検索　成功
     * 文字列：目標
     * 期限：1ヶ月以内
     * @throws Exception
     */
	@Test
	void testSearchActionSuccess2() throws Exception {
		//目標を登録
    	mapper.insertTrnTarget(2, "目標2", "2023-09-01", "×", 1, 0, "2023-07-01 23:59:59", 0, "2023-07-01 23:59:59");
		
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		form.setSelectedDataId(null);
		form.setKbn(null);
		//検索条件
		form.setSearchStr("目標");
		form.setSelectedPeriod("1");
		
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList(null);
		form.setShowStepList(null);
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/search")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        StepEntity sEnt = new StepEntity(1, "手段1", "2023-08-01", "×", "2023-07-01 23:59:59");
        stepList.add(sEnt);
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals(null,resultForm.getSelectedDataId());
        assertEquals(null,resultForm.getKbn());
        assertEquals("目標",resultForm.getSearchStr());
        assertEquals("1",resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals(null,resultForm.getShowTargetList());
        assertEquals(null,resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 検索　成功
     * 文字列：未入力
     * 期限：1週間以内
     * @throws Exception
     */
	@Test
	void testSearchActionSuccess3() throws Exception {
		//目標を登録
    	mapper.insertTrnTarget(2, "目標2", "2023-07-07", "×", 1, 0, "2023-07-01 23:59:59", 0, "2023-07-01 23:59:59");
		
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		form.setSelectedDataId(null);
		form.setKbn(null);
		//検索条件
		form.setSearchStr(null);
		form.setSelectedPeriod("2");
		
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList(null);
		form.setShowStepList(null);
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/search")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        TargetEntity tEnt = new TargetEntity(2, "目標2", "2023-07-07", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals(null,resultForm.getSelectedDataId());
        assertEquals(null,resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals("2",resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals(null,resultForm.getShowTargetList());
        assertEquals(null,resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 削除　成功
     * 削除対象：目標
     * @throws Exception
     */
	@Test
	void testDeleteActionSuccess1() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		//削除対象
		form.setSelectedDataId("1");
		form.setKbn("0");
		
		form.setSearchStr(null);
		form.setSelectedPeriod(null);
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setShowStepList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/delete")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals("1",resultForm.getSelectedDataId());
        assertEquals("0",resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals(null,resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowTargetList());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 削除　成功
     * 削除対象：手段
     * @throws Exception
     */
	@Test
	void testDeleteActionSuccess2() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		//削除対象
		form.setSelectedDataId("1");
		form.setKbn("1");
		
		form.setSearchStr(null);
		form.setSelectedPeriod(null);
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setShowStepList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/delete")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        assertEquals(empList,resultForm.getErrMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals("1",resultForm.getSelectedDataId());
        assertEquals("1",resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals(null,resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowTargetList());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
	}
	
    /**
     * 削除　失敗
     * 削除対象：目標
     * 目標の排他エラー
     * @throws Exception
     */
	@Test
	void testDeleteActionError1() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		//削除対象
		form.setSelectedDataId("1");
		form.setKbn("0");
		
		form.setSearchStr(null);
		form.setSelectedPeriod(null);
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-02 23:59:59\"}]");		//★排他エラーにするためにDBと異なる日付とする
		form.setShowStepList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/delete")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        StepEntity sEnt = new StepEntity(1, "手段1", "2023-08-01", "×", "2023-07-01 23:59:59");
        stepList.add(sEnt);
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals("1",resultForm.getSelectedDataId());
        assertEquals("0",resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals(null,resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-02 23:59:59\"}]",resultForm.getShowTargetList());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
        
        //エラーメッセージ
        assertEquals("データが更新されています。画面を再表示してください。",resultForm.getErrMessage().get(0));
	}
	
    /**
     * 削除　失敗
     * 削除対象：手段
     * 手段の排他エラー
     * @throws Exception
     */
	@Test
	void testDeleteActionError2() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		//削除対象
		form.setSelectedDataId("1");
		form.setKbn("1");
		
		form.setSearchStr(null);
		form.setSelectedPeriod(null);
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]");
		form.setShowStepList("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-02 23:59:59\"}]");		//★排他エラーにするためにDBと異なる日付とする
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform(post("/targetlists/delete")
                .flashAttr("u0201Form", form))
				.andExpect(MockMvcResultMatchers.view().name("U02-01/index"))
                .andReturn();
        
        // ここでmodelに詰められたformの値を取得
        U0201Form resultForm = (U0201Form) result.getModelAndView().getModel().get("form");
        
        //想定地
        //メッセージ
        List<String> empList = new ArrayList<String>();
        //検索条件セレクトボックス
        List<SelectBoxEntity> periodList = createSelectBox();
        //目標・手段
        List<TargetEntity> targetList = new ArrayList<TargetEntity>();
        List<StepEntity> stepList = new ArrayList<StepEntity>();
        StepEntity sEnt = new StepEntity(1, "手段1", "2023-08-01", "×", "2023-07-01 23:59:59");
        stepList.add(sEnt);
        TargetEntity tEnt = new TargetEntity(1, "目標1", "2023-08-01", "×", "2023-07-01 23:59:59",stepList);
        targetList.add(tEnt);
        
        //検証
        //共通
        assertEquals(empList,resultForm.getCompMessage());
        //画面項目
        assertEquals(true,compPeriodList(resultForm.getPeriodList(),periodList));
        assertEquals("1",resultForm.getSelectedDataId());
        assertEquals("1",resultForm.getKbn());
        assertEquals(null,resultForm.getSearchStr());
        assertEquals(null,resultForm.getSelectedPeriod());
        assertEquals(null,resultForm.getSearchStart());
        assertEquals(null,resultForm.getSearchEnd());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-01 23:59:59\"}]",resultForm.getShowTargetList());
        assertEquals("[{\"selectedId\":\"1\",\"updatedDate\":\"2023-07-02 23:59:59\"}]",resultForm.getShowStepList());
        //目標・手段リスト
        assertEquals(true,compTargetList(resultForm.getTargetList(),targetList));
        
        //エラーメッセージ
        assertEquals("データが更新されています。画面を再表示してください。",resultForm.getErrMessage().get(0));
	}
	
    /**
     * ダウンロード　成功
     * @throws Exception
     */
	@Test
	void testDownloadActionSuccess() throws Exception {
		//パラメータの設定
		U0201Form form = new U0201Form();
		form.setSelectedUserId(1);
		form.setSelectedDataId(null);
		form.setKbn(null);
		form.setSearchStr(null);
		form.setSelectedPeriod(null);		
		form.setSearchStart(null);
		form.setSearchEnd(null);
		form.setShowTargetList(null);
		form.setShowStepList(null);
		form.setPeriodList(null);
		form.setTargetList(null);
		
        //リクエスト送信
		MvcResult result = mockMvc.perform((get("/targetlists/download"))
                .flashAttr("u0201Form", form))
                .andReturn();
        
        //検証
		assertEquals(200,result.getResponse().getStatus());
		assertEquals("attachment; filename=\"download.csv\"; filename*=UTF-8''download.csv",result.getResponse().getHeader(HttpHeaders.CONTENT_DISPOSITION));
	}
	/**
	 * 検索条件のセレクトボックス
	 * @return
	 */
	private List<SelectBoxEntity> createSelectBox(){
		List<SelectBoxEntity> list = new ArrayList<SelectBoxEntity>();
		list.add(new SelectBoxEntity("0", "指定なし"));
		list.add(new SelectBoxEntity("1", "1ヶ月以内"));
		list.add(new SelectBoxEntity("2", "1週間以内"));
		list.add(new SelectBoxEntity("3", "日付を選ぶ"));
		return list;
	}
	
	/**
	 * 検索条件のセレクトボックス比較用
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean compPeriodList(List<SelectBoxEntity> list1, List<SelectBoxEntity> list2) {
		if(list1.size() != list2.size()) {
			return false;
		}
		
		for(int i = 0; i < list1.size(); i++) {
			if(!list1.get(i).getValue().equals(list2.get(i).getValue())) {
				return false;
			}else if(!list1.get(i).getText().equals(list2.get(i).getText())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 目標リストの比較
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean compTargetList(List<TargetEntity> list1,List<TargetEntity> list2) {
		if(list1.size() != list2.size()) {
			return false;
		}
		
		for(int i = 0; i < list1.size(); i++) {
			if(list1.get(i).getTargetId() != list2.get(i).getTargetId()) {
				return false;
			}else if(!list1.get(i).getDetail().equals(list2.get(i).getDetail())) {
				return false;
			}else if (!list1.get(i).getPeriod().equals(list2.get(i).getPeriod())) {
				return false;
			}else if (!list1.get(i).getResult().equals(list2.get(i).getResult())) {
				return false;
			}else if (!list1.get(i).getUpdatedDate().equals(list2.get(i).getUpdatedDate())) {
				return false;
			}else if (!compStepList(list1.get(i).getStepList(),list2.get(i).getStepList())) {
				return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * 手段リストの比較
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean compStepList(List<StepEntity> list1,List<StepEntity> list2) {
		if(list1.size() != list2.size()) {
			return false;
		}
		
		for(int i = 0; i < list1.size(); i++) {
			if(list1.get(i).getStepId() != list2.get(i).getStepId()) {
				return false;
			}else if(!list1.get(i).getDetail().equals(list2.get(i).getDetail())) {
				return false;
			}else if (!list1.get(i).getPeriod().equals(list2.get(i).getPeriod())) {
				return false;
			}else if (!list1.get(i).getResult().equals(list2.get(i).getResult())) {
				return false;
			}else if (!list1.get(i).getUpdatedDate().equals(list2.get(i).getUpdatedDate())) {
				return false;
			}
		}
		
		return true;
	}
	

}
