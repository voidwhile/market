package com.voidwhile.common;

import com.voidwhile.core.utils.PropertyUtils;

public class Constant {

	public static final String FILE_PATH = PropertyUtils.getPropertyValue("config.properties", "file.path");
	public static final String FILE_URL = PropertyUtils.getPropertyValue("config.properties", "imgUrl");
	public static final String BASE_PATH = PropertyUtils.getPropertyValue("config.properties", "BasePath");
	public static final String WX_MENU_PATH = PropertyUtils.getPropertyValue("config.properties", "WXMenuPath");
	public static final String CHARSET = PropertyUtils.getPropertyValue("config.properties", "charset");
	
	
	
	/**
	 * ***********************************************
	 * backup
	 */
	public static final String BACKUP_USERNAME = PropertyUtils.getPropertyValue("jdbc.properties", "backup.username");
	public static final String BACKUP_PASSWORD = PropertyUtils.getPropertyValue("jdbc.properties", "backup.password");
	public static final String BACKUP_FULLBACKUPPATH = PropertyUtils.getPropertyValue("jdbc.properties", "backup.fullBackupPath");
	public static final String BACKUP_INCBACKUPPATH = PropertyUtils.getPropertyValue("jdbc.properties", "backup.incBackupPath");
	public static final String BACKUP_DEFAULTFILE = PropertyUtils.getPropertyValue("jdbc.properties", "backup.defaultFile");
	public static final String BACKUP_SOCKET = PropertyUtils.getPropertyValue("jdbc.properties", "backup.socket");
}
