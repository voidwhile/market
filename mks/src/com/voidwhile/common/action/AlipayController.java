package com.voidwhile.common.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.voidwhile.common.bean.alipay.AliTradeBean;
import com.voidwhile.common.bean.alipay.AlipayConfig;
import com.voidwhile.common.utils.HttpClientUtil;
import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.PropertyUtils;
import com.voidwhile.core.utils.Slf4JLogger;


@Controller
@RequestMapping("/alipay")
public class AlipayController extends BaseController {

	private final static Logger logger = Slf4JLogger.getLogger(AlipayController.class);
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	private static String url = PropertyUtils.getPropertyValue("config.properties", "url");
	private String basePath = PropertyUtils.getPropertyValue("config.properties", "BasePath");

	@RequestMapping("/pay.do")
	public void pay(HttpServletRequest request, HttpServletResponse response, AliTradeBean trade) {
		logger.info("支付宝支付", "");
		if (trade.getOutTradeNo() == null) {
			trade = new AliTradeBean();
			trade.setOutTradeNo(request.getParameter("outTradeNo"));
			trade.setBody(request.getParameter("body"));
			trade.setProductCode(request.getParameter("productCode"));
			trade.setSubject(request.getParameter("subject"));
			trade.setTimeoutExpress(request.getParameter("timeoutExpress"));
			trade.setTotalAmount(request.getParameter("totalAmount"));
		}
		try {

			if (trade != null) {
				AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
						AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
						AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
				AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();
				// 封装请求支付信息
				AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
				model.setOutTradeNo(trade.getOutTradeNo());
				model.setSubject(trade.getSubject());
				model.setTotalAmount(trade.getTotalAmount());
				model.setBody(trade.getBody());
				model.setTimeoutExpress(trade.getTimeoutExpress());
				model.setProductCode(trade.getProductCode());
				alipay_request.setBizModel(model);
				// 设置异步通知地址
				alipay_request.setNotifyUrl(AlipayConfig.notify_url);
				// 设置同步地址
				alipay_request.setReturnUrl(AlipayConfig.return_url);

				// form表单生产
				String form = "";
				// 调用SDK生成表单
				form = client.pageExecute(alipay_request).getBody();
				response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
				response.getWriter().write(form);// 直接将完整的表单html输出到页面
				response.getWriter().flush();
				response.getWriter().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提交订单出错", e);
		}
	}

	/**
	 * 支付宝异步支付通知
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/alipayNotify.do")
	//@Transactional(rollbackFor=Exception.class)
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级别，开启新事务，这样会比较安全些。
//		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		if(basePath.lastIndexOf("/") != (basePath.length()-1)){
			basePath = basePath + "/";
		}
		HashMap<String, String> mp = new HashMap<String,String>();
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String trade_time = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
			String buyer_pay_amount = new String(request.getParameter("buyer_pay_amount").getBytes("ISO-8859-1"),
					"UTF-8");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			// boolean AlipaySignature.rsaCheckV1(Map<String, String> params,
			// String publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,
					AlipayConfig.CHARSET, "RSA2");

			if (verify_result) {// 验证成功

				if (trade_status.equals("TRADE_FINISHED")) {

				} else if (trade_status.equals("TRADE_SUCCESS")) {
					
				} else {// 验证失败
					response.getWriter().write("fail");
				}
			}
//			transactionManager.commit(status);
		} catch (Exception e) {
//			transactionManager.rollback(status);
			e.printStackTrace();
			logger.error("支付宝支付失败", e);
			throw e;
		}
	}

	/**
	 * 支付宝同步支付通知
	 * @throws Exception 
	 */
	@RequestMapping("/alipayResult.do")
	public void alipayResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		alipayNotify(request, response);
	}
}
