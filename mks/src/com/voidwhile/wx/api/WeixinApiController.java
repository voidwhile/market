package com.voidwhile.wx.api;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.api.JsTicket;
import com.jfinal.weixin.sdk.api.JsTicketApi;
import com.jfinal.weixin.sdk.api.JsTicketApi.JsApiType;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.voidwhile.common.utils.QrcodeUtil;
import com.voidwhile.common.utils.WebUtils;
import com.voidwhile.wx.model.Menu;
import com.voidwhile.wx.model.Platform;
import com.voidwhile.wx.model.WechatUser;

import net.sf.json.JSONObject;


public class WeixinApiController extends ApiController {

	String WXMenuPath = PropKit.use("config.properties").get("WXMenuPath");
	
	/**
	 * 如果要支持多公众账号，只需要在此返回各个公众号对应的 ApiConfig 对象即可 可以通过在请求 url 中挂参数来动态从数据库中获取
	 * ApiConfig 属性值
	 */
	public ApiConfig getApiConfig() {
		String appid = getPara("appid");
		Platform wxPlatForm = Platform.dao.findById(appid);
		ApiConfig ac = new ApiConfig();

		// 配置微信 API 相关常量
		ac.setToken(wxPlatForm.getToken());
		ac.setAppId(wxPlatForm.getAppid());
		ac.setAppSecret(wxPlatForm.getSecret());

		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(false);
		ac.setEncodingAesKey("setting it in config file");
		return ac;
	}

	/**
	 * 获取公众号菜单
	 */
	public void getMenu() {
		ApiResult apiResult = MenuApi.getMenu();
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}

	/**
	 * 获取公众号关注用户
	 */
	@SuppressWarnings("unchecked")
	public void getFollowers() {
		ApiResult apiResult = UserApi.getFollows();
		JSONObject openids = JSONObject.fromObject(apiResult.getJson());
		openids = JSONObject.fromObject(openids.get("data"));
		List<String> openidList = JsonUtils.parse(openids.get("openid").toString(), List.class);
		WechatUser user = null;
		Record savedWechatUser = null;
		for (String openid : openidList) {
			savedWechatUser = Db.findFirst("select * from wx_wechat_user where openid=?", openid);
			if (null == savedWechatUser) {
				user = JsonUtils.parse(getUserInfo(openid), WechatUser.class);
				user.setCreatetime(new Date());
				try {
					user.save();
				} catch (Exception e) {
					System.out.println(user.getNickname());
					user.setNickname("emoji");
					user.save();
					continue;
				}
			}
		}
	}

	public void createMenu() {
		deleteMenu();
		String appid = ApiConfigKit.getApiConfig().getAppId();
		List<Menu> parentList = Menu.dao.find(
				"select * from wx_menu where is_show=1 and parent_id is null and appid=? order by orders",
				appid);
		String str = "{\n" + "    \"button\": [\n";
		if (parentList.size() > 3) {
			renderText("一级菜单超过最大数量");
			return;
		}
		for (Menu menu : parentList) {
			List<Menu> list = Menu.dao.find("select * from wx_menu where" + " parent_id=? and is_show = 1 order by orders",
					menu.getMenuId());
			if (list.size() > 5) {
				renderText("二级菜单超过最大数量");
				return;
			}
			if (null != list && list.size() > 0) {
				str += "  {\n" + "            \"name\": \"" + menu.getTitle() + "\",\n"
						+ "            \"sub_button\":[\n";
				for (Menu child : list) {
					str += "        {\n" + " \"name\": \"" + child.getTitle() + "\",\n" + "            \"url\": \""
							+ WXMenuPath+ child.getMenuId() + "?appid=" + appid + "\",\n" + "            \"type\": \"" + child.getType() + "\"\n"
							+ "        },\n";
				}
				str = str.substring(0, str.lastIndexOf(","));
				str += "    ]\n" + "        },\n";
			} else {
				str += "{\n" + " \"name\": \"" + menu.getTitle() + "\",\n" + "            \"url\": \""
						+ WXMenuPath+ menu.getMenuId() + "?appid=" + appid + "\",\n" + "            \"type\": \"" + menu.getType() + "\"\n"
						+ "        },\n";
			}
		}
		str = str.substring(0, str.lastIndexOf(","));
		str += "    ]\n" + "}";
		System.out.println(str);
		ApiResult apiResult = MenuApi.createMenu(str);
		if (apiResult.isSucceed()) {
			renderText("success");
		} else {
			renderText("errorMsg:"+apiResult.getErrorMsg());
		}
	}

	public void deleteMenu() {
		String deleteMenu = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
		HttpUtils.get(deleteMenu + AccessTokenApi.getAccessTokenStr());
	}

	public String getUserInfo(String openid) {
		ApiResult apiResult = UserApi.getUserInfo(openid);
		return apiResult.getJson();
	}

	public void getJsApiInfo() {
		String nonceStr = WebUtils.uuid();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String basePath = PropKit.use("shangyu.properties").get("BasePath");
		JsTicket jsapi_ticket = JsTicketApi.getTicket(JsApiType.jsapi);

		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket.getTicket(), "timestamp=" + timestamp,
				"noncestr=" + nonceStr, "url=" + PropKit.use("config.properties").get("url")
						+ "/api/getJsApiInfo?openid=" + getPara("openid") + "&issub=" + getPara("issub") };
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		System.out.println("拼接之后的content为:" + content);
		String signature = HashKit.sha1(content);
		setAttr("appId", ApiConfigKit.getApiConfig().getAppId());
		setAttr("timestamp", timestamp);
		setAttr("nonceStr", nonceStr);
		setAttr("signature", signature);
		setAttr("isSub", getPara("issub"));
		setAttr("openid", getPara("openid"));
		setAttr("url", basePath);
		render("/weixin/yhq.jsp");
	}

	

	/**
	 * 获取网页授权， state标示要跳转的链接 1.代表优惠券，2.代表个人中心，3.代表留言 4.预订购票 5.首页
	 */
	@SuppressWarnings("deprecation")
	public void webScope() {
		String appId = ApiConfigKit.getApiConfig().getAppId();
		String state = getPara(0);
		String redirectUrl = PropKit.use("config.properties").get("url") + "/api/getCode?appid=" + appId;
		String redirectUri = java.net.URLEncoder.encode(redirectUrl);
		String url = SnsAccessTokenApi.getAuthorizeURL(appId, redirectUri, state, true);
		redirect(url);
	}

	public void getCode() {
		String appId = ApiConfigKit.getApiConfig().getAppId();
		String secret = ApiConfigKit.getApiConfig().getAppSecret();
		String openId = "";
		WechatUser user =  null;
		if (StrKit.notBlank(getPara("code"))) {
			String code = getPara("code");
			SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);
			openId = snsAccessToken.getOpenid();
			user = WechatUser.dao.findFirst("select * from wx_wechat_user where openid=?", openId);
		}
		int menuid = 0;
		menuid = getParaToInt("state");
		Menu menu = Menu.dao.findById(menuid);
		if (user!=null) {
			if (menu.getBackpath()!=null) {
				redirect(menu.getPath()+"?openid=" + user.getOpenid()+"&"+menu.getBackpath());
			} else {
				redirect(menu.getPath()+"?openid=" + user.getOpenid());
			}
		}

	}

	/**
	 * 客服接口 发送消息
	 */
	public void pushText() {
		String openId = getPara("openid");
		String content = "";
		CustomServiceApi.sendText(openId, content);
		redirect("/api/getSuccess?openid=" + openId);
	}

	/**
	 * 为代理商创造推广二维码
	 */
	public void createQrcode() {
		String openid = getPara("openid");
		String useruuid = getPara("useruuid");
		setAttr("openid", openid);
		setAttr("Code", getPara("Code"));
		System.out.println(getPara("Code"));
		setAttr("Num", getPara("Num"));
		Record user = Db.findFirst("select*From we_wechat_user where openid=?", openid);
		if (StrKit.isBlank(user.getStr("qrcode"))) {
			ApiResult apiResult = QrcodeApi.createPermanent(useruuid);
			if (apiResult.isSucceed()) {
				String url = null;
				url = apiResult.getStr("url");
				System.out.println("url::::::::::====" + url);
				String filename = "qrCodeImg/" + WebUtils.uuid() + ".png";
				// 生成二维码
				String pngFile = PropKit.use("config.properties").get("file.path") + filename;
				File qrFile = new File(pngFile);
				// 二维码内容
				try {
					QrcodeUtil.qrCodeEncode(url, qrFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Db.update("update wx_wechat_user set qrcode=? where openid=?", filename, openid);
				setAttr("qrcode", filename);
			}
		} else {
			setAttr("qrcode", user.get("qrcode"));
		}
		setAttr("useruuid", useruuid);
		render("/web/proxy/qrcode.jsp");
	}

	/**
	 * 订单提醒
	 */
	public void orderRemind() {
		int type = getParaToInt("type");
		String openId = getPara("openid");
		System.out.println("我是订单openid" + openId);
		String content = "";
		if (type == 1) {// success
			content = "恭喜您购票成功,订单号为" + getPara("orderno") + ",祝您玩的开心！😄";
		}

		CustomServiceApi.sendText(openId, content);
		renderNull();
	}

	/**
	 * pay notify
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void payNotify() throws UnsupportedEncodingException {
		String openId = getPara("openid");
		String content = URLDecoder.decode(getPara("content"), "utf-8");
		CustomServiceApi.sendText(openId, content);
		renderNull();
	}

	/**
	 * 充值提醒
	 */
	public void chargeRemind() {
		String openId = getPara("openid");
		String content = "";
		content = "恭喜您充值成功,请访问个人中心－我的账户查看余额。";
		CustomServiceApi.sendText(openId, content);
		renderNull();
	}

	/**
	 * 充值提醒
	 */
	public void prizeRemind() {
		String openId = getPara("openid");
		String prizename = getPara("prizename");
		String level = getPara("level");
		String content = "";
		content = "恭喜您在大转盘抽奖活动中,获得" + level + "等奖，奖品为" + prizename + "。请尽快到景区联系工作人员领取奖品。";
		CustomServiceApi.sendText(openId, content);
		renderNull();
	}

	/**
	 * 用户同步
	 */
	@SuppressWarnings("unchecked")
	public void sycn() {
		int total = 0;
		int count = 0;
		String next_openid = "";
		ApiResult apiResult = UserApi.getFollows();
		total = apiResult.getInt("total");
		count = apiResult.getInt("count");
		next_openid = apiResult.getStr("next_openid");
		JSONObject openids = JSONObject.fromObject(apiResult.getJson());
		openids = JSONObject.fromObject(openids.get("data"));
		List<String> openidList = JsonUtils.parse(openids.get("openid").toString(), List.class);
		// 每次最多垃取1万条，如超过一万，则分批次垃取
		while (count < total) {
			apiResult = UserApi.getFollowers(next_openid);
			openids = JSONObject.fromObject(apiResult.getJson());
			openids = JSONObject.fromObject(openids.get("data"));
			openidList.addAll(JsonUtils.parse(openids.get("openid").toString(), List.class));
			count += apiResult.getInt("count");
			next_openid = apiResult.getStr("next_openid");
		}
		WechatUser user = new WechatUser();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WechatUser userCheckExist = null;
		String appid = ApiConfigKit.getApiConfig().getAppId();
		for (String openid : openidList) {
			userCheckExist = WechatUser.dao.findFirst("select * from wx_wechat_user where openid=?", openid);

			JSONObject json = JSONObject.fromObject(getUserInfo(openid));
			if (null == userCheckExist) {
				try {

					user.set("nickname", json.getString("nickname"));
					user.set("subscribe", json.getInt("subscribe"));
					user.set("openid", json.getString("openid"));
					user.set("headimgurl", json.getString("headimgurl"));
					user.set("sex", json.getInt("sex"));
					user.set("language", json.getString("language"));
					user.set("city", json.getString("city"));
					user.set("province", json.getString("province"));
					user.set("country", json.getString("country"));
					user.set("subscribe_time", json.getString("subscribe_time"));
					user.set("remark", json.getString("remark"));
					user.set("groupid", json.getString("groupid"));
	
					user.setAppid(appid);
					user.setCreatetime(new Date());
					user.save();
				} catch (Exception e) {
					System.out.println("数据库中没有该用户异常" + e.getMessage());
					user.setNickname("Can't store emoji.");
					user.save();
					continue;
				}
			} else {
				try {
					String date = sim.format(new Date());
					userCheckExist.setAppid(appid);
					userCheckExist.set("createtime", date);
					userCheckExist.setHeadimgurl(json.getString("headimgurl"));
					userCheckExist.setNickname(json.getString("nickname"));
					userCheckExist.setCity(json.getString("city"));
					userCheckExist.setCountry(json.getString("country"));
					userCheckExist.setProvince(json.getString("province"));
					userCheckExist.update();
				} catch (Exception e) {
					System.out.println("数据库中存在该用户异常" + e.getMessage());
					userCheckExist.setNickname("Can't store emoji.");
					userCheckExist.update();
					continue;
				}
			}

		}
		renderJson(1);
	}
}