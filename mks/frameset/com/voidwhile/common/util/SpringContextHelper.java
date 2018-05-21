package com.voidwhile.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: SpringContextHelper
 * @Description: TODO(SpringContext工具类)
 * @author xiaowei
 * @date 2014年10月23日 上午10:35:57
 * 
 */
public class SpringContextHelper implements ApplicationContextAware {
	private static ApplicationContext context = null;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// 加载Spring时自动获得context
		SpringContextHelper.context = context;
	}

	public static <T> T getBean(String beanName) {
		checkApplicationContext();
		return (T) context.getBean(beanName);
	}

	public static <T> T getBean(Class<T> classType) {
		checkApplicationContext();
		return context.getBean(classType);
	}

	private static void checkApplicationContext() {
		if (context == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHelper");
		}
	}
}
