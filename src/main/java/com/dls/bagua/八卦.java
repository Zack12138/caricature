/**  
 * @Project Name:test  
 * @File Name:Bagua.java  
 * @Package Name:bagua  
 * @Date:2022年7月19日下午5:52:43  
 * Copyright (c) 2022, Zack All Rights Reserved.  
 *  
*/  
/**  
 * @Project Name:test  
 * @File Name:Bagua.java  
 * @Package Name:bagua  
 * @Date:2022年7月19日下午5:52:43  
 * Copyright (c) 2022, Zack All Rights Reserved.  
 *  
 */  
  
package com.dls.bagua;

import com.dls.util.StringUtil;

/**
 * @Package: bagua 
 * @ClassName: Bagua 
 * @Description: TODO
 * @date: 2022年7月19日 下午5:52:43  
 *  
 * @author Zack  
 * @version V2.0  
 */

public enum 八卦 {
	坤("坤","☷","地",0),
	艮("艮","☶","山",1),
	坎("坎","☵","水",2),
	巽("巽","☴","风",3),
	震("震","☳","雷",4),
	离("离","☲","火",5),
	兑("兑","☱","泽",6),
	乾("乾","☰","天",7);
	public final String name;
	public final String icon;
	public final String content;
	public final int index;
	
	八卦(String name, String icon,String content, int index){
		this.name = name;
		this.icon = icon;
		this.content = content;
		this.index = index;
		
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public int getIndex() {
		return index;
	}

	public String toBinaryString(){
		return StringUtil.setLength(Integer.toBinaryString(index),3,'0',false);
	}

}
 