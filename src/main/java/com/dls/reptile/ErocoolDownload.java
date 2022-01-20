/**  
 * @Project Name:reptile  
 * @File Name:ErocoolDownload.java  
 * @Package Name:com.dls.reptile  
 * @Date:2020年8月16日上午1:26:46  
 * Copyright (c) 2020, Zack All Rights Reserved.  
 *  
*/

package com.dls.reptile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dls.util.NumberUtil;

public  class ErocoolDownload extends Reptile{
	
	/**  
	 * @Description: TODO
	 * @param args    
	 */
	/**  
	 * @Description: TODO
	 * @param args    
	 */
	public static void main(String[] args) {
		Scanner sc = null;
		while(true) {
			 sc = new Scanner(System.in);
			 System.out.println("请输入需要下载的URL");
			String url = sc.nextLine();
			
			if("exit".equals(url))
				break;

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					ErocoolDownload erocoolDownload = new ErocoolDownload();
					erocoolDownload.url = url;
				}
			});
			thread.start();
		}
		if(sc != null) {
			sc.close();
		}
	}
	
	public void download(String url, String target) throws Exception {
		Document document = Jsoup.connect(url).get();
		Element mainDiv = document.selectFirst("#comicdetail");

		String name = mainDiv.select("h1").text();
		name = name.replaceAll("\\?|:", "");
		Elements imgDiv = mainDiv.select("div>img");
		System.out.println("URL:"+url);
		System.out.println("需要下载的漫画名:" + name);
		int size = imgDiv.size();
		System.out.println("漫画页数:" + imgDiv.size());

		List<String> eachAttr = imgDiv.eachAttr("data-src");

		List<String[]> timeoutList = new ArrayList<>();

		for (int i = 0; i < eachAttr.size(); i++) {
			String path = eachAttr.get(i);
			System.out.println("正在下载:" + path);
			String fileName = NumberUtil.getNumberString(i, String.valueOf(size).length()) + ".jpg";
			try {
				downloadFile(path, target + File.separator + name + File.separator, fileName, "");
			} catch (java.net.SocketTimeoutException e) {
				System.out.println("读取超时" + path);
				timeoutList.add(new String[] { String.valueOf(i), path });
			} catch (Exception e) {
				System.out.println("下载失败 失败原因"+e.getMessage());
			}
		}

		// 将连接超时的重新下载三次
		for (int index = 0; index < 3; index++) {
			System.out.println("下载完成。" + "下载失败" + timeoutList.size() + "个");
			if (timeoutList.isEmpty()) {
				break;
			}
			System.out.println("正在尝试第" + (index + 1) + "次重新下载超时的文件");
			List<String[]> timeoutListTmp = new ArrayList<>();
			for (int i = 0; i < timeoutList.size(); i++) {
				String[] arr = timeoutList.get(i);
				int j = Integer.valueOf(arr[0]);
				String path = arr[1];
				System.out.println("正在下载:" + path);
				String fileName = NumberUtil.getNumberString(j, String.valueOf(size).length()) + ".jpg";
				try {
					downloadFile(path, target + File.separator + name + File.separator, fileName, "");
				} catch (java.net.SocketTimeoutException e) {
					System.out.println("读取超时" + path);
					timeoutListTmp.add(new String[] { String.valueOf(j), path });
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("下载失败 失败原因"+e.getMessage());
				}
			}

			timeoutList = timeoutListTmp;
		}

	}

	/**
	 * TODO 下载文件到本地
	 *
	 * @param fileUrl
	 *            远程地址
	 * @param fileLocal
	 *            本地路径
	 * @throws Exception
	 */
	public void downloadFile(String fileUrl, String fileLocal, String filename, String callId) throws Exception {
		SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
		sslcontext.init(null, new TrustManager[] { new X509TrustUtiil() }, new java.security.SecureRandom());
		URL url = new URL(fileUrl);
		HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
			public boolean verify(String s, SSLSession sslsession) {
				System.out.println("WARNING: Hostname is not matched for cert.");
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
		HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
		HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
		urlCon.setConnectTimeout(10000);
		urlCon.setReadTimeout(10000);
		int code = urlCon.getResponseCode();
		if (code != HttpURLConnection.HTTP_OK) {
			throw new Exception("文件读取失败 "+code);
		}
		File file = new File(fileLocal);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 读文件流
		DataInputStream in = new DataInputStream(urlCon.getInputStream());
		DataOutputStream out = new DataOutputStream(new FileOutputStream(fileLocal + File.separator + filename));
		byte[] buffer = new byte[2048];
		int count = 0;
		while ((count = in.read(buffer)) > 0) {
			out.write(buffer, 0, count);
		}
		out.close();
		in.close();
	}

}
