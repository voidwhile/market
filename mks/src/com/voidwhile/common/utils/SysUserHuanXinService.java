package com.voidwhile.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdicons.json.mapper.JSONMapper;
import com.voidwhile.common.action.AppBaseController;
import com.voidwhile.core.utils.PropertyUtils;
import com.voidwhile.core.utils.Slf4JLogger;

public class SysUserHuanXinService{
	private static String tokenurl = PropertyUtils.getPropertyValue("config.properties", "hx_tokenurl");
	private static String userurl = PropertyUtils.getPropertyValue("config.properties", "hx_userurl");
	private static String client_id = PropertyUtils.getPropertyValue("config.properties", "hx_client_id");
	private static String client_secret = PropertyUtils.getPropertyValue("config.properties", "hx_client_secret");
	private static String grant_type = PropertyUtils.getPropertyValue("config.properties", "hx_grant_type");
	private static String charset = PropertyUtils.getPropertyValue("config.properties", "charset");
	private static String msgurl = PropertyUtils.getPropertyValue("config.properties", "hx_messagesurl");
	private static String groupurl = PropertyUtils.getPropertyValue("config.properties", "hx_groupurl");
	private final static Slf4JLogger logger = Slf4JLogger.getLogger(SysUserHuanXinService.class);
	private static String friendsurl= PropertyUtils.getPropertyValue("config.properties", "hx_friendsurl");

	/**
	 * 添加所有用户带环信服务器
	 */
/*	public  void addUsers() {
		Map<String, Object> param = new HashMap<String, Object>();
		SysUserService sysuser = SpringContextHelper.getBean(SysUserService.class);
		System.out.println(sysuser);
		List<SysUser> userlist = sysuser.findByMap(param);
		System.out.println(userlist.size()+"==========");
		for (SysUser sue : userlist) {
			suhs.addHuanXinUser(sue.getUid(), sue.getPlainPassword(), sue.getRealName());
		}
		
	}*/
	
	/**
	 * 
	 * @author hekang
	 * @return strtoken
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getToken() throws ClientProtocolException, IOException{
		Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("client_id", client_id);  
        createMap.put("client_secret", client_secret);  
        createMap.put("grant_type", grant_type); 
        HttpPost httpPost = new HttpPost(tokenurl); 
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
        for(NameValuePair nameValuePair:nvps){
       	 	httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        try {
			httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(createMap), charset));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        JSONObject obj = new JSONObject(retSrc); 
        String strtoken = obj.get("access_token").toString();
		return strtoken;
	}
	
	/**
	 * addHuanXinUser 
	 * 描述：添加环信用户信息
	 * @author hekang
	 * @param uid
	 * @param PlainPassword
	 * @param realName
	 */
	public void addHuanXinUser(String uid, String PlainPassword, String realName){
        Map<String,String> params = new HashMap<String,String>();
        params.put("username", uid);  
        params.put("password", PlainPassword);  
        params.put("nickname", realName); 
        HttpPost httpPost = new HttpPost(userurl);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
        String token;
		try {
			token = getToken();
			System.out.println("---token----"+token);
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			System.out.println("--ClientProtocolException--"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("--IOException--"+e.getMessage());
		}
		for(NameValuePair nameValuePair:nvps){
       	 	httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
		
        try {
        	httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(params),"utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
			if(httpResponse != null){
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == 200){
					System.out.println("--add--");
				}
			}
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			System.out.println("---success---"+retSrc);
/*			JSONObject json = new JSONObject(retSrc);
	    	if("duplicate_unique_property_exists".equals(json.get("error").toString())){
	    		System.out.println("---该用户已经存在---不允许再次添加-");
	    	}*/
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("-----add---end---success---");
	}
	
	/**
	 * deleteByuid
	 * 描述：删除环信用户
	 * @author hekang
	 * @param uid
	 */
	public void deleteByuid(String uid){
		String token;
		HttpResponse httpResponse;

		HttpDelete httpdelete = new HttpDelete(userurl+"/"+uid);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for(NameValuePair nameValuePair:nvps){
        	httpdelete.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        try {
			httpResponse = new DefaultHttpClient().execute(httpdelete);
			if(httpResponse != null){
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == 200){
					System.out.println("--del--");
				}
			}
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			System.out.println("----success----"+retSrc);
/*			JSONObject json = new JSONObject(retSrc);
			if("service_resource_not_found".equals(json.get("error").toString())){
				System.out.println("--环信服务器不存在该用户--");
			}*/
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("---del---end---success---");
	}
	
	/**
	 * put 
	 * 描述:更新环信昵称
	 * @author hekang
	 * @param uid
	 */
	public void putHuanXinUser(String uid,String password,String nickname){
		System.out.println("--11uid--"+uid+"--password--"+password+"--nickname--"+nickname);
		String token;
		HttpPut httpput = new HttpPut(userurl+"/"+uid);
        Map<String,String> params = new HashMap<String,String>();
        //params.put("username", uid);
        if(!"".equals(password) && password != null){
        	params.put("password", password);  
        }
        if(!"".equals(nickname)){
        	params.put("nickname", nickname); 
        }
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			System.out.println("--ClientProtocolException--"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("--IOException--"+e.getMessage());
		}
		for(NameValuePair nameValuePair:nvps){
			httpput.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
		DefaultHttpClient client = new DefaultHttpClient();
		
		try {
			httpput.setEntity(new StringEntity(JsonUtils.pojo2json(params),"utf-8"));
			HttpResponse response = client.execute(httpput);
			if(response != null){
				int statusCode = response.getStatusLine().getStatusCode();
				if(statusCode == 200){
					System.out.println("--put--");
				}
			}
			String retSrc = EntityUtils.toString(response.getEntity());
			System.out.println("---success---"+retSrc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get 
	 * 描述：获取单个环信用户信息
	 * @author hekang
	 * @param uid
	 * 获取单个用户信息
	 */
	public void getHuanXinUser(String uid){
		String token;
		HttpResponse httpResponse;
		HttpGet httpGet = new HttpGet(userurl+"/"+uid);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for(NameValuePair nameValuePair:nvps){
        	httpGet.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        try {
			httpResponse = new DefaultHttpClient().execute(httpGet);
			if(httpResponse != null){
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == 200){
					System.out.println("--get--");
				}
			}
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			System.out.println("----success----"+retSrc);
/*			JSONObject json = new JSONObject(retSrc);
			if("service_resource_not_found".equals(json.get("error").toString())){
				System.out.println("--环信服务器不存在该用户--");
			}*/
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author hekang
	 * 获取该app下的所有环信用户
	 */
	public String getAllHuanXinUsers(){
		String token;
		HttpResponse httpResponse;
		BufferedReader in = null;  
		String content = null; 
		HttpGet httpGet = new HttpGet(userurl);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for(NameValuePair nameValuePair:nvps){
        	httpGet.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        try {
			httpResponse = new DefaultHttpClient().execute(httpGet);
			in = new BufferedReader(new InputStreamReader(httpResponse.getEntity()  
					.getContent()));
			StringBuffer sb = new StringBuffer("");  
			String line = "";  
			String NL = System.getProperty("line.separator");  
			while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            } 
			in.close();
			content = sb.toString();  
			System.out.println("---allusers---"+content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
            if (in != null) {  
                try {  
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }
		return content;  
	}
	
	/**
	 * 
	    * @Title: addMessages
	    * @Description: 给用户发送消息
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	public static void addMessages(String msg, String[] newUserIds, String userid,Map<String,Object> ext){
		Map<String, Object> param = new HashMap<String, Object>();
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		//创建httpPost
		HttpPost httpPost = new HttpPost(msgurl);
		try {
			String[] userArr = new String[]{"4028803557ad88a10157ada6bdb4001f","4028803557ad88a10157ada73ac40020","4028803557ad88a10157ad9d44130013"};
			Map<String, Object> msgmap = new HashMap<String, Object>();
			msgmap.put("type", "txt");
			msgmap.put("msg", msg);
			//msgmap.put("msg", "hello from rest! 道路比较滑，注意安全！");
			param.put("target_type", "users");//users 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
			param.put("from", userid);//表示消息发送者。无此字段Server会默认设置为"from":"admin"，有from字段但值为空串("")时请求失败
			param.put("target", newUserIds);//接收人数组,数组长度建议不大于20
			param.put("msg", msgmap);//消息类型及文本
			param.put("ext",ext);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", "Bearer "+getToken().toString());
			httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(param), charset));
			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			try {
				if(httpResponse.getStatusLine().getStatusCode() == 200){
					System.out.println("--addmessges--success");
				}
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					result = EntityUtils.toString(entity, charset);
					logger.debug(result);
				}
			} finally {
				httpResponse.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}

		System.out.println("---result---"+result);


	}
	
	/**
	 * 新建群组
	 * @param groupname 群组名称
	 * @param desc	群组描述
	 * @param isPublic	是否公开	
	 * @param owner	群主
	 * @param members	群成员
	 */
	public static Map<String, String> addGroup(String groupname,String desc,boolean isPublic,String owner,String[] members){
		logger.debug("----环信建群开始----");
		Map<String,String> rlt = new HashMap<String,String>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("groupname", groupname);
		params.put("desc", desc);
		params.put("public", isPublic);
		params.put("approval", false);
		params.put("owner", owner);
		params.put("members", members);
		HttpPost httpPost = new HttpPost(groupurl);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
        String token;
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
			for(NameValuePair nameValuePair:nvps){
	       	 	httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
	        }
        	httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(params),"utf-8"));
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
			if(httpResponse != null){
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				String retSrc = EntityUtils.toString(httpResponse.getEntity());
				rlt.put("status", String.valueOf(statusCode));
				rlt.put("desc", retSrc);
				if(statusCode == 200){
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = mapper.readTree(retSrc);
					String groupid = node.get("data").get("groupid").asText();
					if(groupid != null){
						String[] newUserIds = new String[]{groupid};
						Map<String, String> map = sendMessages("chatgroups", "大家好,欢迎进群！", newUserIds, owner);
						if(map.get("status").toString()=="200"){
							System.out.println("---环信建群完成------");
						}
					}
					logger.debug(retSrc);
					logger.debug("--环信建群完成-");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			rlt.put("status", "1");
			rlt.put("desc", "系统异常");
		}
		return rlt;
	}
	
	/**
	 * @desc 发送群消息
	 * @param msgType
	 * @param msg
	 * @param newUserIds
	 * @param userid
	 * @author hykang
	 * @return
	 */
	public static Map<String, String> sendMessages(String msgType,String msg, String[] newUserIds, String userid){
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		//创建httpPost
		HttpPost httpPost = new HttpPost(msgurl);
		try {
			Map<String, Object> msgmap = new HashMap<String, Object>();
			msgmap.put("type", "txt");
			msgmap.put("msg", msg);
			//msgmap.put("msg", "hello from rest! 道路比较滑，注意安全！");
			param.put("target_type", msgType);//users 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
			param.put("from", userid);//表示消息发送者。无此字段Server会默认设置为"from":"admin"，有from字段但值为空串("")时请求失败
			param.put("target", newUserIds);//接收人数组,数组长度建议不大于20
			param.put("msg", msgmap);//消息类型及文本
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("Authorization", "Bearer "+getToken().toString());
			httpPost.setEntity(new StringEntity(JsonUtils.pojo2json(param), charset));
			CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
			try {
				if(httpResponse.getStatusLine().getStatusCode() == 200){
					map.put("status", "200");
					logger.debug("--addmessges--success");
				}
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					result = EntityUtils.toString(entity, charset);
					logger.debug(result);
				}
			} finally {
				httpResponse.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
			}
		}
		return map;
	}
	public static  String getfriends(String hxid){
		String token;
		HttpResponse httpResponse;
		BufferedReader in = null;  
		String content = null; 
		HttpGet httpGet = new HttpGet(friendsurl+"/"+hxid+"/contacts/users");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Content-Type", "application/json"));
		try {
			token = getToken();
			nvps.add(new BasicNameValuePair("Authorization", "Bearer "+token));
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for(NameValuePair nameValuePair:nvps){
        	httpGet.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        try {
			httpResponse = new DefaultHttpClient().execute(httpGet);
			in = new BufferedReader(new InputStreamReader(httpResponse.getEntity()  
					.getContent()));
			StringBuffer sb = new StringBuffer("");  
			String line = "";  
			String NL = System.getProperty("line.separator");  
			while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            } 
			in.close();
			content = sb.toString();  
			System.out.println("---allusers---"+content);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
            if (in != null) {  
                try {  
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }
		return content;  
	}
	public static void main(String[] args) {
		SysUserHuanXinService sys = new SysUserHuanXinService();
		String[] member =new String[2]; 
		member[0] = "4028803557ad88a10157ada73ac40020";
		member[1] = "4028803557ad88a10157ada6bdb4001f";
		String[] userArr = new String[]{"4028803557ad88a10157ada6bdb4001f","4028803557ad88a10157ada73ac40020","4028803557ad88a10157ad9d44130013"};
		addGroup("测试群6", "群组描述暂无", true, "4028803557ad88a10157ad9c74930012", userArr);
		//sys.putHuanXinUser("12", "1234567890", "admin");
		//sys.addMessages("hello ", args, "12");
	}
	
}
