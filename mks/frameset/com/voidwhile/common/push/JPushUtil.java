package com.voidwhile.common.push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 极光推送工具类
 * @author: xiaowei
 * @Create Date: 2014年12月22日 上午9:48:28
 *
 * @Version: v1.0
 */
public class JPushUtil {
	private static final Logger logger = LoggerFactory.getLogger(JPushUtil.class);

	private static String appKey = "070b261b2e3420650315564a";
	private static String masterSecret = "9bb56a89b2c2e4a1101fa87c";

	/**
	 * @MethodName: sendMsg
	 * @Description: 发送通知到指定的注册ID
	 * @param regids
	 * @param title
	 * @param alertContent
	 * @param extras
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月22日 上午10:07:38
	 */
	public static void sendMsg(List<String> regids, String title, String alertContent, Map<String, String> extras) throws PushException {
		try {
			JPushClient jPushClient = new JPushClient(masterSecret, appKey);

			PushPayload payload = buildPushObject(regids, title, alertContent, extras);
			PushResult result = jPushClient.sendPush(payload);
			logger.info("推送结果 - " + result);
		} catch (APIConnectionException e) {
			logger.error("连接超时，请稍候重试 - ", e);
			throw new PushException("连接超时，请稍候重试", e);
		} catch (APIRequestException e) {
			logger.error("API请求出错，请查看错误状态 - ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			throw new PushException("API请求出错，请查看错误状态", e);
		} catch (Exception e) {
			logger.error("系统错误：" + e.getMessage());
			throw new PushException("系统错误", e);
		}

	}

	/**
	 * @MethodName: sendMsgToAll
	 * @Description: 发送通知到所有人
	 * @param title
	 * @param alertContent
	 * @param extras
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月22日 上午10:08:21
	 */
	public static void sendMsgToAll(String title, String alertContent, Map<String, String> extras) throws PushException {
		try {
			JPushClient jPushClient = new JPushClient(masterSecret, appKey);
			PushPayload payload = buildPushObjectToAll(title, alertContent, extras);
			PushResult result = jPushClient.sendPush(payload);
			logger.info("推送结果 - " + result);
		} catch (APIConnectionException e) {
			logger.error("连接超时，请稍候重试 - ", e);
			throw new PushException("连接超时，请稍候重试", e);
		} catch (APIRequestException e) {
			logger.error("API请求出错，请查看错误状态 - ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			throw new PushException("API请求出错，请查看错误状态", e);
		} catch (Exception e) {
			logger.error("系统错误：" + e.getMessage());
			throw new PushException("系统错误", e);
		}

	}

	protected static PushPayload buildPushObject(List<String> regids, String title, String alertContent, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.registrationId(regids))
				.setNotification(
						Notification.newBuilder().setAlert(alertContent)
								.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extras).build())
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extras).build()).build()).build();

	}

	protected static PushPayload buildPushObjectToAll(String title, String alertContent, Map<String, String> extras) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.all())
				.setNotification(
						Notification.newBuilder().setAlert(alertContent)
								.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extras).build())
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extras).build()).build()).build();
	}

	public static void main(String[] args) throws PushException {
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("notice_id", "402880964a4c9a51014a4cb8b2950003");
		// sendMsgToAll("我是标题", "我是通知内容", extras);
		List<String> regIds = new ArrayList<String>();
		regIds.add("0a01f6cf9b6");
		sendMsg(regIds, "我是标题", "我是通知内容", extras);
	}
}
