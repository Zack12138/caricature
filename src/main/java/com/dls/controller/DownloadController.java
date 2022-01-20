package com.dls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("down")
public class DownloadController {
	
	@RequestMapping("qq")
	@ResponseBody
	public String qqDownload() {
		return "qq";
	}
	
}
