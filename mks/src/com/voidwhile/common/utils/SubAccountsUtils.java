package com.voidwhile.common.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.voidwhile.core.utils.PropertyUtils;
import com.voidwhile.system.bean.PushExtraBean;
import com.voidwhile.system.bean.PushMsg;



/**
 * 云通讯 子账号创建获取
 * 
 * @author CAI
 *
 */
public class SubAccountsUtils {
	private static String baseUrl=PropertyUtils.getPropertyValue("config.properties","rl_baserUrl");
	private static	String accountsid = PropertyUtils.getPropertyValue("config.properties", "account_sid");
	private static String authtoken = PropertyUtils.getPropertyValue("config.properties", "auth_token");
	private static String acountappid = PropertyUtils.getPropertyValue("config.properties", "appId");
    

	/**
	 * 创建子账号
	 * 
	 * @param userName
	 * @return
	 */
	public static int createSub(String userName, String appid) {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(PropertyUtils.getPropertyValue("config.properties", "account_sid"),
				PropertyUtils.getPropertyValue("config.properties", "auth_token"));// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(appid);// 初始化应用ID
		result = restAPI.createSubAccount(userName);

		System.out.println("SDKTestCreateSubAccount result=" + result);

		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
		     Object data=result.get("data");
		/*	Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			 
			}*/
			return 1;
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			return 0;
		}
	}

	public static Object querySub(String userName, String appid) {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(PropertyUtils.getPropertyValue("config.properties", "account_sid"),
				PropertyUtils.getPropertyValue("config.properties", "auth_token"));// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(appid);// 初始化应用ID
		result = restAPI.querySubAccount(userName);
		// System.out.println("SDKTestCreateSubAccount result=" + result);
		Object returnData = "";
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				// System.out.println(key +" = "+object);
			}
			returnData = data.get("SubAccount");
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
		}
		return returnData;
	}

	/**
	 * IM Push
	 * 
	 * @param args
	 */
	public static int imPush(PushMsg pm, String appid, int type, PushExtraBean peb) {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
	
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(PropertyUtils.getPropertyValue("config.properties", "account_sid"),
				PropertyUtils.getPropertyValue("config.properties", "auth_token"));// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(appid);// 初始化应用ID
		Map<String, Object> extraField = new HashMap<String, Object>();
		if (type <= 5) {
			extraField.put("type", type + "");
			extraField.put("userData", peb);
		} else {
			extraField.put("type", type + "");
		}
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(extraField);
		result = restAPI.push(pm.getSender(), pm.getReceiver(), pm.getMsgType(), pm.getPushType(), pm.getMsgContent(),
				json.toString(), pm.getMsgFileName(), pm.getMsgFileUrl());

		System.out.println("SDK=" + result);

		if ("000000".equals(result.get("statusCode"))) {
			return 1;
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
			return 0;
		}
	}

	public static HashMap<String, Object> getChatList(String appid) {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(PropertyUtils.getPropertyValue("config.properties", "account.sid"),
				PropertyUtils.getPropertyValue("config.properties", "auth.token"));// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(appid);// 初始化应用ID
		result = restAPI.getChatList("day");
		return result;
	}

	public static void main(String[] args) {
		 //System.out.println(imPush("18039678870", "18538206256", 1, 1, "你好","", "", ""));
	    Map<String, Object> extraField = new HashMap<String, Object>();
		PushExtraBean peb = new PushExtraBean();
	    peb.setName("test");
		peb.setPhoto("test");
		peb.setUuid("test");
		extraField.put("type", "1");
		extraField.put("userData", peb);
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(extraField);//
		PushMsg pm = new PushMsg("5a92873686e211e782257cd30ac333b2", "388a8b7d86e211e782257cd30ac333b2", 1, 1,
				"医生已经成为您的随访专家。", json.toString(), null, null);
	imPush(pm, PropertyUtils.getPropertyValue("config.properties", "doctor.appid"), 1, null);
		
		
//		 System.out.println(createSub("17796651957",PropertyUtils.getPropertyValue("config.properties","doctor.appid")));
		/*System.out.println(querySub("17796651957",PropertyUtils.getPropertyValue("config.properties","doctor.appid")));*/
	}
	/**
	 * 主账号推送消息
	 * @param sender
	 * @param receiver
	 * @param appid
	 * @return
	 */
	public static Map<String, Object> PushMsg(String sender,String[] receiver,String appid,String content,String ext){
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		//创建httpPost
		String SigParameter=accountsid+authtoken+new Date().getTime();
		String  Authorization=accountsid+new Date().getTime();
		HttpPost httpPost = new HttpPost(baseUrl+"/Accounts/"+accountsid+"/?sig="+SigParameter);
		
		Map<String, Object> msgmap = new HashMap<String, Object>();
		msgmap.put("pushType", "1");
		msgmap.put("appId", appid);	 
		param.put("sender", sender);//表示消息发送者。无此字段Server会默认设置为"from":"admin"，有from字段但值为空串("")时请求失败
		param.put("receiver", receiver);//接收人数组,数组长度建议不大于20
		param.put("msgType", "1");//消息类型及文本
		param.put("msgContent",content);
		param.put("msgDomain", ext);
		//Accept:application/json;
		httpPost.addHeader("Accept", ":application/json");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Authorization",Authorization);
		httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(param), "utf-8"));
		try {
			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == 000000){
				map.put("status", "0");
			   System.out.println("----success------");
			}
			HttpEntity entity = httpResponse.getEntity();
			if(entity != null){
				result = EntityUtils.toString(entity, "utf-8");
			     System.err.println(result);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
