package com.voidwhile.market.constant;

import com.voidwhile.core.utils.PropertyUtils;

public class MarketConstant {

	public static final String FILE_PATH = PropertyUtils.getPropertyValue("config.properties", "file.path");
	public static final String FILE_URL = PropertyUtils.getPropertyValue("config.properties", "imgUrl");
	public static final String BASE_PATH = PropertyUtils.getPropertyValue("config.properties", "BasePath");
	public static final String WX_MENU_PATH = PropertyUtils.getPropertyValue("config.properties", "WXMenuPath");
	public static final String CHARSET = PropertyUtils.getPropertyValue("config.properties", "charset");
}
