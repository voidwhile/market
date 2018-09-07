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
	private MbeAddressServcie addressService;
	@Autowired
	private OdrCartService cartService;
	

	/**
	 *  确认订单
	 * @param memberId
	 * @param map
	 * @return
	 */
	@RequestMapping("/book.wx")
	public String book(Long memberId,String addrId, ModelMap map) {
		try {
			orderService.book(memberId);
			List<OdrCart> settleList = cartService.findForSettle(memberId);
			MbeAddress addr = null;
			if (addrId!=null) {
				addr = addressService.getById(addrId);
			} else {
				addr = addressService.getDefault(memberId);
			}
			Double totalPrice = cartService.sum(memberId);
			map.put("addr", addr);
			map.put("settleList", settleList);
			map.put("totalPrice", totalPrice);
			map.put("memberId", memberId);
			map.put("rltCode", "0000");
		} catch (Exception e) {
			map.put("rltCode", "1111");
			log.error("确认订单异常", e);
		}
		return "weixin/order/book";
	}
	
	/**
	 *  放弃订单
	 * @param memberId
	 * @param map
	 * @return
	 */
	@RequestMapping("/giveUp.wx")
	public String giveUp(Long memberId, ModelMap map) {
		try {
			orderService.giveUp(memberId);
			map.put("memberId", memberId);
		} catch (Exception e) {
			map.put("rltCode", "1111");
			log.error("取消订单异常", e);
		}
		return "redirect:/wx/cart/myCart.wx";
	}
	
	/**
	 *  取消提交订单 
	 * @param memberId
	 * @param map
	 * @return
	 */
	@RequestMapping("/cancel.wx")
	public String cancel(Long memberId, ModelMap map) {
		try {
			orderService.cancel(memberId);
			map.put("memberId", memberId);
		} catch (Exception e) {
			map.put("rltCode", "1111");
			log.error("取消订单异常", e);
		}
		return "redirect:/wx/cart/myCart.wx";
	}

	/**
	 * 提交订单
	 * @param order
	 * @param map
	 * @return
	 */
	@RequestMapping("/order.wx")
	public String order(OdrOrder order, ModelMap map) {
		try {
			orderService.order(order);
			Double totalPrice = cartService.sum(order.getMemberId());
			map.put("order", order);
			map.put("totalPrice", totalPrice);
			map.put("memberId", order.getMemberId());
			map.put("rltCode", "0000");
		} catch (Exception e) {
			map.put("rltCode", "1111");
			log.error("提交订单异常", e);
		}
		return "weixin/order/cashier_desk";
	}

	
}
