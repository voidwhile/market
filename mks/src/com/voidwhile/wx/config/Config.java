package com.voidwhile.wx.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.json.JFinalJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.voidwhile.wx.api.WeixinApiController;
import com.voidwhile.wx.api.WeixinMsgController;
import com.voidwhile.wx.model._MappingKit;

 
 

  
 


/**
 * API引导式配置
 */
public class Config extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("jdbc.properties"); // 加载少量必要配置，随后可用PropKit.get(...)获取值
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
		me.setBaseUploadPath(PropKit.use("config.properties").get("file.path"));
		me.setJsonFactory(new JFinalJsonFactory());
		me.setError404View("/common/error_404.jsp");
		me.setError500View("/common/error_500.jsp");
		ApiConfigKit.setDevMode(me.getDevMode());
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/wx/api", WeixinApiController.class);
		me.add("/wx/msg", WeixinMsgController.class);
		
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("database.url")+"/"+PropKit.get("database.databaseName")+"?characterEncoding=utf8", PropKit.get("database.username"),
				PropKit.get("database.password").trim());
		me.add(c3p0Plugin);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}

	public void afterJFinalStart() {
		String imgUrl = PropKit.use("config.properties").get("imgUrl");
		JFinal.me().getServletContext().setAttribute("imgUrl", imgUrl);
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}
}
