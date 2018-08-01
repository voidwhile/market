package com.voidwhile.common.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.PaymentApi;
import com.jfinal.weixin.sdk.api.PaymentApi.TradeType;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.voidwhile.common.utils.ImgUtil;
import com.voidwhile.core.utils.PropertyUtils;
 
@Controller
@RequestMapping("/weixinpay")
public class WeixinPayController {
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	private static String appid = PropertyUtils.getPropertyValue("shangyu.properties", "weixin.appid");// wx0c4b0b47e088488d
	private static String partner = PropertyUtils.getPropertyValue("shangyu.properties", "weixin.partner");// 1384277002
	private static String paternerKey = PropertyUtils.getPropertyValue("shangyu.properties", "weixin.paternerKey");// 3knWnTKJWaz7vYhpsnrEi2fPOHzpwP9R
	private static String notify_url = PropertyUtils.getPropertyValue("shangyu.properties", "weixin.notify_url")
			+ "/weixinpay/pay_notify.do";
	private static String url = PropertyUtils.getPropertyValue("shangyu.properties", "url");
	private String basePath = PropertyUtils.getPropertyValue("shangyu.properties", "BasePath");
	

	@RequestMapping("/index.do")	 
	public String index(HttpServletRequest request, String num, String title, String orderNum,String openid,
			String fee,String ids,String userid,Model model) {
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			params.put("appid", appid);
			params.put("mch_id", partner);
			params.put("body", "旅游"+"--"+ "X" +num);
			long nonce_str = System.currentTimeMillis() / 1000;
			params.put("out_trade_no", orderNum + "AAA" + System.currentTimeMillis() / 1000);
			params.put("total_fee", fee);
			String ip = IpKit.getRealIp(request);
			if (StrKit.isBlank(ip)) {
				ip = "115.28.220.220";
			}
			
			params.put("openid", openid);
			params.put("product_id",ids);
			params.put("spbill_create_ip", ip);
			params.put("trade_type", TradeType.JSAPI.name());
			params.put("nonce_str", nonce_str+"");
			params.put("notify_url", notify_url);

			String sign = PaymentKit.createSign(params, paternerKey);
			params.put("sign", sign);
			String xmlResult = PaymentApi.pushOrder(params);

			Map<String, String> result = PaymentKit.xmlToMap(xmlResult);
             System.out.println(result);
			String return_code = result.get("return_code");

			String return_msg = result.get("return_msg");
			Map<String, String> map = new HashMap<String, String>();

			if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
				map.put("msg", return_msg);
				 
				return "";
			}
			String result_code = result.get("result_code");
			if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
				map.put("msg", return_msg);
				return "";
			}
			Map<String, String> param =new HashMap<>();
			String timeStamp=System.currentTimeMillis() / 1000+"";
			String nonceStr=System.currentTimeMillis()+"";
			param.put("appId", appid);
			param.put("nonceStr", nonceStr);
			param.put("timeStamp",timeStamp );
			param.put("package", "prepay_id="+result.get("prepay_id"));
			param.put("signType", "MD5");
			String paySign=PaymentKit.createSign(param,paternerKey);
			model.addAttribute("appId", appid);
			model.addAttribute("timeStamp", timeStamp);
			model.addAttribute("nonceStr",   nonceStr);
			model.addAttribute("perpayid","prepay_id="+result.get("prepay_id"));
			model.addAttribute("paySign",  paySign);
			model.addAttribute("url", result.get("code_url"));
			model.addAttribute("userid",userid);
			model.addAttribute("openid", openid);
			model.addAttribute("orderNum", orderNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		 
		return "wxjsputil/nativepay";
	}
	@RequestMapping("/browser_index.do")	 
	public String browserindex(HttpServletRequest request, String num, String title, String orderNum,String openid,
			String fee,String ids,String userid,Model model) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("appid", appid);
			params.put("mch_id", partner);
			params.put("body", "旅游联票×" + num);
			// params.put("product_id", getPara("id"));
			params.put("out_trade_no", orderNum + "AAA" + System.currentTimeMillis() / 1000);// 商户订单号
			params.put("total_fee", fee);

			//String ip = IpKit.getRealIp(request);
			 String ip="";
			System.out.println(ip + "----ddd---ip");
			if (StrKit.isBlank(ip)) {
				ip = "127.0.0.1";
			}

			params.put("spbill_create_ip", ip);
			params.put("trade_type", TradeType.NATIVE.name());
			params.put("nonce_str", System.currentTimeMillis() / 1000 + "");
			params.put("notify_url", notify_url);

			String sign = PaymentKit.createSign(params, paternerKey);
			params.put("sign", sign);
			String xmlResult = PaymentApi.pushOrder(params);

			Map<String, String> result = PaymentKit.xmlToMap(xmlResult);

			String return_code = result.get("return_code");
           System.out.println(result);
			String return_msg = result.get("return_msg");
			Map<String, String> map = new HashMap<String, String>();

			if (StrKit.isBlank(return_code) || !"SUCCESS".equals(return_code)) {
				map.put("msg", return_msg);
				// renderText(return_msg);
				return "";
			}
			String result_code = result.get("result_code");
			if (StrKit.isBlank(result_code) || !"SUCCESS".equals(result_code)) {
				map.put("msg", return_msg);
				return "";
			}
			map.put("msg", result.get("code_url"));
			model.addAttribute("url",  result.get("code_url"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return "wxjsputil/browsernativepay";
		// renderText(result.get("code_url"));
	}

	/**
	 * 
	 */
	@RequestMapping("/createQrcode.do")
	public void createQrcode(String url, HttpServletResponse response) {
		BufferedImage image = ImgUtil.createImg(url);
		try {
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/pay_notify.do")
//	@Transactional(rollbackFor=Exception.class) 
	public void pay_notify(HttpServletRequest request, Model model,HttpSession session) throws Exception {
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级别，开启新事务，这样会比较安全些。
//		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		if(basePath.lastIndexOf("/") != (basePath.length()-1)){
			basePath = basePath + "/";
		}
		HashMap<String, String> mp = new HashMap<String,String>();
		try {
			 System.out.println("123456");
				String xmlMsg = HttpKit.readData(request);
				Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);

				String result_code = params.get("result_code");

				String totalFee = params.get("total_fee");
				
				String orderId =  params.get("out_trade_no");
				String openid   = params.get("openid");
				
				String transId = params.get("transaction_id");
				
				String timeEnd = params.get("time_end");
				SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
				Date d = null;
				try {
					d = sim.parse(timeEnd);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		 		orderId = orderId.split("AAA")[0];
		 		if (PaymentKit.verifyNotify(params, paternerKey)) {
					if (("SUCCESS").equals(result_code)) {
						
					} else {
						Map<String, Object> map = new HashMap<>();
						map.put("ordercode", orderId);
						map.put("status", 3);
						/*orderservice.updateOrderStatus(map);*/
					}
				}
//		 		transactionManager.commit(status);
		} catch (Exception e) {
//			transactionManager.rollback(status);
			e.printStackTrace();
			throw e;
		}
       
		 
	}


}
