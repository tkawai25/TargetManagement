package com.example.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * エラーハンドリングクラス
 * 全コントローラーで発生したExceptionをつかむ
 */
@ControllerAdvice
public class ExceptionHandler {
	
	/**
	 * Exceptionをつかみ、エラー画面へ遷移する
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public String handleError() {
        return "error/index";
    }
}
