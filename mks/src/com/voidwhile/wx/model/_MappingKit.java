package com.voidwhile.wx.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("wx_menu", "menu_id", Menu.class);
		arp.addMapping("wx_platform", "appid", Platform.class);
		arp.addMapping("wx_wechat_user", "uuid", WechatUser.class);
	}
}

