/**  
 * @Project Name:bean  
 * @File Name:JF.java  
 * @Package Name:com.dls.bean.swing  
 * @Date:2021年3月16日下午8:56:33  
 * Copyright (c) 2021, Zack All Rights Reserved.  
 *  
*/

package com.dls.bean.swing;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyJFrame extends javax.swing.JFrame {

	private JTextField labFilePath;
	private JTextField labUrl;
	private JButton btn;

	private static MyJFrame instance;
	
	private static final long serialVersionUID = 1L;

	private MyJFrame() throws HeadlessException {
		super();
	}

	private MyJFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	private MyJFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	private MyJFrame(String title) throws HeadlessException {
		super(title);
	}

	private void init() {

		// 设置按钮关闭
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 窗口主体
		// Container contentPane = getContentPane();

		// 设置窗口大小
		setSize(600, 600);

		setLocation(400, 400);

		// 显示窗口
		setVisible(true);
		
		Container contentPane = getContentPane();

		labFilePath = new JTextField(30);
		btn = new JButton("浏览");
		btn.addActionListener((e) -> selectDir());

		JPanel panel;
		panel = new JPanel();
		panel.add(new JLabel("保存路径: "));
		panel.add(labFilePath);
		panel.add(btn);

		panel.add(new JLabel("URL: "));
		labUrl = new JTextField(30);
		panel.add(labUrl);
		btn = new JButton("下载");
		btn.addActionListener((e) -> {
			
		});
		panel.add(btn);
		contentPane.add(panel);

	}

	private void selectDir() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("选择保存路径");
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String filepath = file.getAbsolutePath();
			labFilePath.setText(filepath);// 将文件路径设到JTextField
		}
	}

	
	
	
	public static MyJFrame getInstance() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new MyJFrame();
				}
			}
		}
		instance.init();
		return instance;
	}

	public static MyJFrame getInstance(GraphicsConfiguration gc) {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new MyJFrame(gc);
				}
			}
		}
		instance.init();
		return instance;
	}

	public static MyJFrame getInstance(String title) {
		if (instance == null) {
			synchronized (MyJFrame.class) {
				if (instance == null) {
					instance = new MyJFrame(title);
				}
			}
		}
		instance.init();
		return instance;
	}

	public static MyJFrame getInstance(String title, GraphicsConfiguration gc) {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new MyJFrame(title, gc);
				}
			}
		}
		instance.init();
		return instance;
	}

}
