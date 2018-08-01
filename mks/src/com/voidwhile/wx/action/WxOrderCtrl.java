package com.voidwhile.wx.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.MbeAddress;
import com.voidwhile.market.entity.OdrCart;
import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.service.MbeAddressServcie;
import com.voidwhile.market.service.OdrCartService;
import com.voidwhile.market.service.OrderService;

@Controller
@RequestMapping("/wx/order")
public class WxOrderCtrl {
	private Logger log = Slf4JLogger.getLogger(WxOrderCtrl.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OdrCartService cartService;
	@Autowired
	private MbeAddressServcie addressService;
	
	
	@RequestMapping("/book.wx")
	public String book(Long memberId,ModelMap map) {
		try {
			orderService.book(memberId);
			MbeAddress addr = addressService.getDefault(memberId);
			map.put("addr", addr);
			map.put("rltCode", "0000");
		} catch (Exception e) {
			log.error("提交订单异常", e);
		}
		return "weixin/order/book";
	}
	
	@RequestMapping("/order.wx")
	public String order(OdrOrder order,ModelMap map) {
		try {
			orderService.order(order);
			map.put("order", order);
			map.put("rltCode", "0000");
		} catch (Exception e) {
			map.put("rltCode", "1111");
		}
		return "weixin/order/cashier_desk";
	}
	
	@RequestMapping("/pay.wx")
	public String pay(String orderId,int payType,int browser) {
		OdrOrder order = orderService.getById(orderId);
		String result = "";
		try {
			order.setPayType(payType);
			if (payType==1) {//微信
				result = "weixin/order/confirm";
			} else {//支付宝
				if (browser==1) {//微信浏览器
					result = "";
				} else {
					result = "";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
