package com.voidwhile.common.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.voidwhile.common.Constant;
import com.voidwhile.common.utils.Command;
import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;


public class Xtrabackup {
	
	static Logger log = Slf4JLogger.getLogger(Xtrabackup.class);

	/**
	 * 全量备份
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void fullBackup() throws IOException, InterruptedException {
		log.info("Xtrabackup.fullBackup()");
		String cmd = getBackupCommand(null,true);
		log.info(cmd);
		Command.exec(cmd);
	}

	/**
	 * 差异备份
	 * @param fname 基础备份文件
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void incrementBackup(String fullFileName) throws IOException, InterruptedException {
		log.info("Xtrabackup.incrementBackup("+fullFileName+")");
		String cmd = getBackupCommand(fullFileName,false);
		log.info(cmd);
		Command.exec(cmd);
	}
	
	/**
	 * 还原备份
	 * @param fullFileName 全量备份
	 * @param incFileName 差异备份
	 * @param full 是否还原全量备份
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void copyBack(String fullFileName, String incFileName, boolean full) throws IOException, InterruptedException {
		log.info("Xtrabackup.copyBack("+fullFileName+","+incFileName+")");
		InputStream is = Xtrabackup.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		String[] cmdArray = getCopyBackCommand(fullFileName,incFileName,full);
		if (full) {
			Command.exec(cmdArray[0]);
			Command.exec(cmdArray[1]);
		} else {
			Command.exec(cmdArray[0]);
			Command.exec(cmdArray[1]);
			Command.exec(cmdArray[2]);
			Command.exec(cmdArray[3]);
		}
	}

	private static String getBackupCommand(String fileName, boolean full) {
		StringBuffer command = new StringBuffer();

		// 注意哪些地方要空格，哪些不要空格
		command.append("innobackupex --defaults-file=").append(Constant.BACKUP_DEFAULTFILE).append(" --user=").append(Constant.BACKUP_USERNAME)
				.append(" --password=").append(Constant.BACKUP_PASSWORD).append(" --socket=").append(Constant.BACKUP_SOCKET);
		if (full) {
			command.append(" ").append(Constant.BACKUP_FULLBACKUPPATH).append("last");
		} else {
			command.append(" --incremental ").append(Constant.BACKUP_INCBACKUPPATH).append("last --incremental-basedir=")
					.append(Constant.BACKUP_FULLBACKUPPATH).append(fileName).append(" --parallel=2");
		}
		return command.toString();
	}

	private static String[] getCopyBackCommand(String fullFileName, String incFileName, boolean full) {
		// 第一步，准备全量备份
		String firstCommand = new StringBuffer().append("innobackupex --apply-log --redo-only ").append(Constant.BACKUP_FULLBACKUPPATH)
				.append(fullFileName).toString();
		// 第二步，还原增量备份到全量备份
		String secondCommand = new StringBuffer("innobackupex --apply-log ").append(Constant.BACKUP_FULLBACKUPPATH).append(fullFileName)
				.append(" --incremental-dir=").append(Constant.BACKUP_INCBACKUPPATH).append(incFileName).toString();
		// 第三步，完整数据再准备一次
		String thirdlyCommand = new StringBuffer().append("innobackupex --apply-log ").append(Constant.BACKUP_FULLBACKUPPATH)
				.append(fullFileName).toString();
		// 第四步，还原全量备份
		String fourthlyCommand = new StringBuffer("innobackupex --defaults-file=").append(Constant.BACKUP_DEFAULTFILE)
				.append(" --copy-back --rsync ").append(Constant.BACKUP_FULLBACKUPPATH).append(fullFileName).toString();
		// 需要返回的命令语句数组
		if (full) {
			String[] commands = new String[] {thirdlyCommand, fourthlyCommand };
			return commands;
		} else {
			String[] commands = new String[] { firstCommand, secondCommand, thirdlyCommand, fourthlyCommand };
			return commands;
		}
	}
	
}
