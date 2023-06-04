package com.example.demo.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BaseForm {
	private List<String> compMessage = new ArrayList<String>();
	private List<String> errMessage = new ArrayList<String>();;
}
