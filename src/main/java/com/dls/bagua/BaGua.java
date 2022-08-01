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

public enum BaGua {
	kun("坤","☷","地",0),
	gen("艮","☶","山",1),
	kan("坎","☵","水",2),
	xun("巽","☴","风",3),
	zhen("震","☳","雷",4),
	li("离","☲","火",5),
	dui("兑","☱","泽",6),
	qian("乾","☰","天",7);
	public final String name;
	public final String icon;
	public final String content;
	public final int index;
	
	BaGua(String name, String icon, String content, int index){
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
 