package com.voidwhile.common.bean.baidu;

import java.util.HashMap;
import java.util.Map;

import com.voidwhile.common.mapper.JsonMapper;
import com.voidwhile.core.utils.HttpXmlClient;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: Baidu Map API 调用
 * @author: zhanzheng
 * @Create Date: 2014年11月25日 下午3:49:39
 *
 * @Version: v1.0
 */
public class BaiduMapRequest {
	private static final String BASE_URL = "http://api.map.baidu.com/geocoder/v2/";
	private static final Map<String, String> params = new HashMap<String, String>();

	public BaiduMapRequest() {
		params.put("ak", "D48aa9cba758da347dcab0c38286bec0");
		params.put("callback", "renderReverse");
		params.put("output", "json");
		params.put("pois", "0");
	}

	/**
	 * @MethodName: getAddress
	 * @Description: 根据经纬度查询地址
	 * @param lat 经度
	 * @param lng 纬度
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月25日 下午3:50:15
	 */
	public String getAddress(String lat, String lng) {
		String address = "";
		try {
			params.put("location", lat + "," + lng);
			String xml = HttpXmlClient.post(BASE_URL, params);
			JsonMapper jsonMapper = new JsonMapper();
			BaiduMessage baiduMessage = jsonMapper.fromJson(xml, BaiduMessage.class);
			address = baiduMessage.getResult().getFormatted_address();
		} catch (Exception e) {
			address = "位置数据出错";
		}
		return address;
	}

	public static void main(String[] args) {
		BaiduMapRequest baiduRequest = new BaiduMapRequest();
		String address = baiduRequest.getAddress("34.78961", "113.698586");
		System.out.println(address);
	}
}
