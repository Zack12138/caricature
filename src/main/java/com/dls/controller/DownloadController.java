package com.dls.controller;

import com.dls.reptile.Reptile;
import com.dls.util.SpringContextUtil;
import com.dls.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DownloadController {

	private Reptile reptile;

	@RequestMapping("download")
	@ResponseBody
	public String download(String action, String url,String target) {
		String clazzpath = "com.dls.reptile." + action;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		if(StringUtil.isBlank(target)){
			target = "D:/迅雷下载/.和谐物/"+sdf.format(new Date())+"/"+action;
		}
		System.out.println(url);

		try {
			Class clazz = Class.forName(clazzpath);
			Reptile reptile = (Reptile)SpringContextUtil.getBeanByClass(clazz);
			reptile.download(url,target);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "seccess";
	}


	@RequestMapping("getData")
	@ResponseBody
	public String getData(){

		return null;
	}

}





