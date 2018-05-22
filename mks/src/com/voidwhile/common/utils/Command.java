package com.voidwhile.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;


public class Command {
	
	private static Logger log = Slf4JLogger.getLogger(Command.class);

	public static void exec(String command) throws IOException, InterruptedException{
		log.info(command);
		Runtime run = Runtime.getRuntime();
		Process p = run.exec(command);
		InputStream is = p.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s;
		while ((s = br.readLine())!=null) {
			log.info(s);
		}
		p.waitFor();
		is.close();
	}
	
	public static void exec(String[] cmdArray) throws IOException, InterruptedException{
		log.info(cmdArray.toString());
		Runtime run = Runtime.getRuntime();
		Process p = run.exec(cmdArray);
		InputStream is = p.getErrorStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s;
		while ((s = br.readLine())!=null) {
			log.info(s);
		}
		p.waitFor();
		is.close();
	}
}
