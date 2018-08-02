package com.voidwhile.wx.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.MbeAddress;
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
	public String book(Long memberId, ModelMap map) {
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
	public String order(OdrOrder order, ModelMap map) {
		try {
			orderService.order(order);
			map.put("order", order);
			map.put("rltCode", "0000");
		} catch (Exception e) {
			map.put("rltCode", "1111");
		}
		return "weixin/order/cashier_desk";
	}

	@RequestMapping("/topay.wx")
	public String topay(String orderId, int payType, int browser, Model model) {
		OdrOrder order = orderService.getById(orderId);
		String result = "";
		try {
			order.setPayType(payType);
			orderService.update(order);
			if (payType == 1) {// 微信
				model.addAttribute("fee", 30);
				model.addAttribute("openid", "");
				model.addAttribute("price", 15);
				model.addAttribute("title", "一码游湖北");
				model.addAttribute("num", 2);
				model.addAttribute("money", 39);
				model.addAttribute("browser", browser);// 1 是微信浏览器 0 否
				result = "weixin/order/confirm";
			} else {// 支付宝
				model.addAttribute("orderId", order.getOrderId());
				model.addAttribute("number", 2);
				model.addAttribute("fee", 30);
				model.addAttribute("card", "");
				model.addAttribute("outTradeNo", order.getOrderCode());
				model.addAttribute("userid", order.getMember());
				model.addAttribute("body", "旅游联票×" + 2);
				model.addAttribute("productCode", "1");
				model.addAttribute("totalAmount", 30);
				model.addAttribute("subject", "联票");
				if (browser == 1) {// 微信浏览器
					result = "alipay/post";
				} else {
					result = "redirect:/wx/order/alipay.wx";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@RequestMapping("/pay.wx")
	public String pay(String going, Model model) {
		model.addAttribute("going", going);
		return "alipay/pay";
	}

	@RequestMapping("/alipay.wx")
	public String alipay(String orderId, Model model) {
		OdrOrder order = orderService.getById(orderId);
		model.addAttribute("number", 2);
		model.addAttribute("userid", "1");
		model.addAttribute("fee", 30);
		model.addAttribute("card", "");
		model.addAttribute("outTradeNo", order.getOrderCode());
		model.addAttribute("body", "旅游联票×" + 2);
		model.addAttribute("productCode", "1");
		model.addAttribute("totalAmount", 30);
		model.addAttribute("subject", "联票");
		return "alipay/postorder";

	}
}
