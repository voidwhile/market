package com.voidwhile.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String UTF8 = "UTF-8";

	public static String httpPost(String url, Map<String, String> params, String encoding) throws Exception {
		log.debug("收到HTTP POST请求");

		String result = "";
		// 创建默认的httpClient实例.    
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost    
		HttpPost httppost = new HttpPost(url);

		//参数
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (params != null) {
			log.debug("发送post参数");
			Set<String> keys = params.keySet();

			for (String key : keys) {
				log.debug("param:" + key);
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}

		}

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, encoding);
			httppost.setEntity(uefEntity);

			log.debug("executing request " + httppost.getURI());

			CloseableHttpResponse response = httpclient.execute(httppost);

			try {
				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------");

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					log.debug("--------------------------------------");
					log.debug("Response content: " + result);
					log.debug("--------------------------------------");
				}

			} finally {
				response.close();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭连接,释放资源    
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return result;
	}

	public static String httpGet(String url, String encoding) throws Exception {

		log.debug("收到HTTP GET请求");

		String result = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.    
			HttpGet httpget = new HttpGet(url);
			log.debug("executing request " + httpget.getURI());
			// 执行get请求.    
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {

				log.debug("返回HTTP状态:" + response.getStatusLine());

				Header[] headers = response.getAllHeaders();

				log.debug("返回HTTP头");
				log.debug("--------------------------------------");
				for (Header header : headers) {
					log.debug(header.getName() + "-->" + header.getValue());
				}
				log.debug("--------------------------------------"); // 获取响应实体    
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, encoding);
					// 打印响应内容    
					log.debug("Response content: " + result);
				}
				log.debug("------------------------------------");
				//System.out.println(result+"结果为");
				JSONObject json = new JSONObject(result);
				//System.out.println(json.get("message"));
			} finally {
				response.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭连接,释放资源    
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return result;

	}
	 public static void main(String[] args) {
		 String url="http://api.map.baidu.com/trace/v2/fence/list?ak=HzyswMQuTrz23pMAePBqWnpox9RneEKf&service_id=128098&creator=沪A2K153&";
		 String encoding="UTF-8";
		 try {
			httpGet(url, encoding);
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
