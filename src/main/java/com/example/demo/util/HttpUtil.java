package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriUtils;

public class HttpUtil {
	
	private static final String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"; filename*=UTF-8''%s";
	
	public static void addContentDisposition(HttpHeaders headers, String fileName)
		       throws UnsupportedEncodingException {
		   String headerValue = String.format(CONTENT_DISPOSITION_FORMAT,
		           fileName, UriUtils.encode(fileName, StandardCharsets.UTF_8.name()));
		   headers.add(HttpHeaders.CONTENT_DISPOSITION, headerValue);
		}
}
