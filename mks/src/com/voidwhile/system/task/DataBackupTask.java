package com.voidwhile.system.task;

import java.util.Date;

import com.voidwhile.common.jdbc.Xtrabackup;
import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.core.utils.DateUtils;
import com.voidwhile.system.entity.DataBackup;
import com.voidwhile.system.service.DataBackupService;



public class DataBackupTask implements Task {
	
	/**
	 * 数据备份
	 */
	@Override
	public void run() {
		try {
			DataBackupService service = SpringContextHelper.getBean(DataBackupService.class);
			int dayOfWeek = DateUtils.getDayOfWeek(new Date());
			if (dayOfWeek==2) {
				//执行全量备份
				Xtrabackup.fullBackup();
				service.save(0l,null,1,true);
			} else if (dayOfWeek==4||dayOfWeek==6) {
				DataBackup backup = service.getLastFullBackup();
				//执行差异备份
				Xtrabackup.incrementBackup(backup.getTitle());
				service.save(0l,backup.getBackupId(),1, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
