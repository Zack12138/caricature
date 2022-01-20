/**  
 * @Project Name:main  
 * @File Name:GUI.java  
 * @Package Name:com.dls.main  
 * @Date:2021年3月16日下午8:43:53  
 * Copyright (c) 2021, Zack All Rights Reserved.  
 *  
*/

package com.dls.main;


import javax.swing.SwingUtilities;

import org.springframework.stereotype.Service;

import com.dls.bean.swing.MyJFrame;

@Service
public class GUI {
	private MyJFrame jf;
	public MyJFrame createGUI() {
		jf = MyJFrame.getInstance("测试");
		return jf;
	}



	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI().createGUI();
			}
		});

	}
}
