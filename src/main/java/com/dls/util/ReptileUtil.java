package com.dls.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Package: com.dls.reptile.util
 * @ClassName: ReptileUtil
 * @Description: 爬虫工具类
 * @date: 2019年12月22日 下午3:31:51
 * 
 * @author dongls
 * @version V2.0
 */
public class ReptileUtil {

	private ReptileUtil() {
	}

	/** 请求编码 */
	private static final String DEFAULT_CHARSET = "iso8859-1";

	/**
	 * 执行HTTP POST请求
	 * 
	 * @param url
	 *            url
	 * @param param
	 *            参数
	 * @return
	 */
	public static String httpPostWithJSON(String url, Map<String, ?> params) {
		CloseableHttpClient client = null;
		try {
			if (url == null || url.trim().length() == 0) {
				throw new Exception("URL is null");
			}
			// 配置连接超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
					.setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();
			HttpPost httpPost = new HttpPost(url);
			// 设置超时时间
			httpPost.setConfig(requestConfig);
			client = HttpClients.createDefault();

			if (params != null) {
				// 装配post请求参数
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				for (String key : params.keySet()) {
					list.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
				}
				// 将参数进行编码为合适的格式,如将键值对编码为param1=value1&param2=value2
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
				httpPost.setEntity(urlEncodedFormEntity);
			}
			// 执行 post请求
			HttpResponse resp = client.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == 200) {
				// return EntityUtils.toByteArray(resp.getEntity());
				return EntityUtils.toString(resp.getEntity(), DEFAULT_CHARSET);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(client);
		}
		return null;
	}

	/**
	 * 执行HTTP GET请求
	 * 
	 * @param url
	 *            url
	 * @param param
	 *            参数
	 * @return
	 */
	public static String httpGetWithJSON(String url, Map<String, ?> param) {
		CloseableHttpClient client = null;
		try {
			if (url == null || url.trim().length() == 0) {
				throw new Exception("URL is null");
			}
			client = HttpClients.createDefault();
			if (param != null) {
				StringBuffer sb = new StringBuffer("?");
				for (String key : param.keySet()) {
					sb.append(key).append("=").append(param.get(key)).append("&");
				}
				url = url.concat(sb.toString());
				url = url.substring(0, url.length() - 1);
			}
			HttpGet httpGet = new HttpGet(url);
			HttpResponse resp = client.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(resp.getEntity(), DEFAULT_CHARSET);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(client);
		}
		return null;
	}

	/**
	 * 关闭HTTP请求
	 * 
	 * @param client
	 */
	private static void close(CloseableHttpClient client) {
		if (client == null) {
			return;
		}
		try {
			client.close();
		} catch (Exception e) {
		}
	}

	public static String post(String path, Map<String, String> parameters) throws IOException {
		String resStr = "";

		URL url = new URL(path);
		if (parameters != null) {
			url = new URL(url.toString() + buildGetParameterString(parameters));
		}
		InputStream inputStream = url.openStream();
		int nextByteOfData = 0;
		StringBuffer apiResponseBuffer = new StringBuffer();
		while ((nextByteOfData = inputStream.read()) != -1) {
			apiResponseBuffer.append((char) nextByteOfData);
		}
		resStr = apiResponseBuffer.toString();
		return resStr;
	}

	private static String buildGetParameterString(Map<String, String> parameters) {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> param : parameters.entrySet()) {
			if (param.getValue() == null) {
				continue;
			}
			result.append(result.length() < 1 ? "?" : "&");
			result.append(param.getKey()).append("=").append(param.getValue());
		}

		return result.toString();
	}
}
