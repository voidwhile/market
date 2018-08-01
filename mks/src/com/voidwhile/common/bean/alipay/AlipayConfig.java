package com.voidwhile.common.bean.alipay;

import com.jfinal.kit.PropKit;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2017092608932022";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPYrhBS2GCqGFbfg61hBQKXGMjyr0JRXk1/tq5GwWxE+CC2nHX/CD9VYkchQfSlfqHvj+9CIOtOUJ4s/v9l9fPtuu5aXV5IC+18VAKSoBL9KrYpgpZxdITKpA9qWcOYpuB5b/smhb5OHocQO5FrJ8Urwq5K2YvTvTG+fa38eUriXiHWi/v3zqEYg4qpZJiIGzUIhxtQXaOJl88a2Sc/ksRAZD9FX0mhxTfOQaY6P8fX3/5+jce7grb2PZ5ufyTC5AJsR66EYnKfAeBGETm6ne6uSWQhOdpuHJM7zW9SlKaoVhbSMzCoMYNxjvXxoANwXEaOwdGaj72gUYtJtXP2jIJAgMBAAECggEAff+sFoSdPWmlk/akClH0Il6pJ+yiTSFlsFMx9POP/tmOsq4u2DNQ8g1WHV+xThZJPzupWv2+aFwm01amG2RxWrBNWHMZrcTg6CsTwNdTAmwQLa1K7zRstDb05ZqVWwi/ylgQXWPSyFIey/Eq8MMOT+i5rZo7g1sIskk5ys6gKa6YiE43dS2yXnyBc5upDPjaeWl1VhfGefLNVnqKZ6x6jfHpq2LU79wWYredkXnoIoVsCGFceKiATcWU81pA6zQzRKAh/Vsl7D/TAW12tZvAQzz3Tt5f+xx+Ej47pM+ifgvMA+drNn6wWVsM0kTV/2pvOosEeVIrtYLmqWxDYuDsAQKBgQD7vJAcuSTLkpUI1EeXKZsl2wMA+tV3OEcGy02sg6ydcustsgyCTPRMLoI2OJ6pudOETN6aRaRrAhgYwZQiQXiDFFABziz9lNTI9beHzmHajeN0bZwFywBsU8ySEWRvsuZXsLXN4LrXPc+y1e6Qq4jNYkIhwGIBAIsvrkl8EfsiQQKBgQCR0GMSUYuprsx2BemjYWh7zIS9Q4LRDZZpWsrx/hDiOq5zl6aNbqlPq47C5LTww5LxHYDe9TwbT5HlDL5t4CUyxlV2udoz0tZXGmezAmKyZGdC7mnA1ZjtoL7KmRkSWJEKG/lkAu96ORhV+GK2q6U5cmvar0NeIv0VeML+5y8NyQKBgQCdYgVDP+kmMM1mSLk9Y3CxGRzmpuuAbrnRIJe+LFBl22v3FKLHb+8oFceouHtRp3D6/MgK8VFa6WNBTv0a8Y2zfaaLimgMXdkujpigmkIJTr4Ok1yBLqpkD3wNMjx1rQZfeoYfYcnOOcG5kBBneCa+KRkpWsqqz7k1JSSiIee9QQKBgBvwwGssMQTst8UN0P07lxfR0sWpgBGq9t1tRmIkevu+ntsLfiCvycW4a/ywcnOIJkoJiz4t47yf/KbVUFAhmT8nfgzoBAXWKrg6GRDWLi9cyeSrZpxK0akHGkgcGg/H5NL5FUqyvUzAUAd5NwEl1t0xLbWTy+bn8XMB1uudRQjxAoGAWUNdvsCDE2kXiYJhrshcLwWVysASGoYON2GHaz+8q1RO1nqMzerUczd6/D0kuf27UyDcEMUnTfi8bssSPdYc4MvUXyNKLgE1eUzwnurZU0iMyZwFYOngcb+HxrLg9dZDvx/t18jT9mmz7n6ZD8WwLOWtT1+9bE4F4dOpktbGF3I=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = PropKit.use("shangyu.properties").get("alipayNotify");
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = PropKit.use("shangyu.properties").get("alipayResult");
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyxEhp3NqlcmzRq6s7DOFbtIrJSrhf92mwwzPTyMZM9CmV047H5DenBDH9QnjSNl1O3Kcl7tLDEWFsdzcjc9CtZNHPI5TCpLLTEfLb2utDxzurLYuhKgPrCip5gREfnqwV70o3HqSDo+YpL7ib5wFtmX134LXS53iKNLoRwMIov7uvEti6+U9FlFjhAWKMlYEBYYhAO9LMXigiSLJMR2aJFGHxVi4sPcJi0WDjwZzMsMOaURuTEt4R/7MhoNNx0QJ1G3W0qKlXTM3dsB6eXUZoqDa5yMizo1nHHwbmHJiVQt29smF+CHT33l2RjZdJuQYamgE/ppCSElgs946Hyea4QIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}