package com.voidwhile.common.push;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 推磅异常
 * @author: zhanzheng
 * @Create Date: 2014年12月22日 上午10:20:11
 *
 * @Version: v1.0
 */
public class PushException extends Exception {

	public PushException() {
		super();
	}

	public PushException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PushException(String message, Throwable cause) {
		super(message, cause);
	}

	public PushException(String message) {
		super(message);
	}

	public PushException(Throwable cause) {
		super(cause);
	}

}
