package com.dls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dls.util.WeCharUtil;

@RequestMapping("test")
@Controller
public class TestContoller {
	

	@RequestMapping("test")
	@ResponseBody
	public String test(String str) {
		String send = WeCharUtil.send("test", str);
		return send;
	}
}
