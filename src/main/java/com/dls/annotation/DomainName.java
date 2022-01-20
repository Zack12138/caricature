/**  
 * @Project Name:reptile  
 * @File Name:DomainName.java  
 * @Package Name:com.dls.anno  
 * @Date:2021年11月13日下午11:41:22  
 * Copyright (c) 2021, Zack All Rights Reserved.  
 *  
*/  
  
package com.dls.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DomainName {

	/**  
	 * @Description: 域名数组
	 * @return    
	 */
	String[] value();
}
 