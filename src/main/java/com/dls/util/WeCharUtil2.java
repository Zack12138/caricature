/**  
 * @Project Name:util  
 * @File Name:WeCharUtil.java  
 * @Package Name:com.dls.util  
 * @Date:2020年1月17日上午10:14:44  
 * Copyright (c) 2020, dongls All Rights Reserved.  
 *  
*/

package com.dls.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dls.constant.ReptileException;
import com.dls.constant.RequestMethod;

/**  
 * @Package: com.dls.util 
 * @ClassName: WeCharUtil 
 * @Description: 微信通知工具类
 * @date: 2020年7月15日 下午6:08:22  
 *  
 * @author Zack  
 * @version V2.0  
 */
@Component
@PropertySource(value= {"classpath:config/wechar.properties"})
@ConfigurationProperties("wechar")
public final class WeCharUtil2 {

	private  String url;

	private  String sckey;

	private  String suffix;

	
	public String send(String text, String desp) {
		StringBuilder sb = new StringBuilder(url).append(sckey).append(suffix);
//		sb.append("?").append("text=").append(text);
//		sb.append("&").append("desp=").append(desp);
//		
		System.out.println(sb);
		
		Map<String, String> param = new HashMap<String,String>();
		param.put("text", text);
		param.put("desp", desp);
		
			byte[] httpConnection = null;
//			try {
//				httpConnection = HttpsUtil.httpsConnection(sb.toString(), param,RequestMethod.POST);
//			} catch (ReptileException e) {
//				e.printStackTrace();
//			}
			return new String(httpConnection);
	}
	
	public static void main(String[] args) {
		new WeCharUtil2().send("test", "测试数据4");
	}

}
