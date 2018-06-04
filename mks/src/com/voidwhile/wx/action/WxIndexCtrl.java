package com.voidwhile.wx.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.common.utils.DateUtils;
import com.voidwhile.market.entity.AdAdvert;
import com.voidwhile.market.entity.RunRecommend;
import com.voidwhile.market.entity.RunSale;
import com.voidwhile.market.service.AdAdvertService;
import com.voidwhile.market.service.RunRecomService;
import com.voidwhile.market.service.RunSaleService;
import com.voidwhile.system.constant.SysConstant;

@Controller
@RequestMapping("/wx/index")
public class WxIndexCtrl {
	
	@Autowired
	private AdAdvertService advertService;
	@Autowired
	private RunRecomService recomService;
	@Autowired
	private RunSaleService saleService;
	
	/**
	 * 微信首页
	 * @param map
	 * @return
	 */
	@RequestMapping("/index.wx")
	public String index(ModelMap map,String memberId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("today", DateUtils.dateToString(new Date()));
		param.put("adAdvertType", "1");
		List<AdAdvert> banner = advertService.findByMap(param);//轮播广告
		param.put("adAdvertType", "2");
		List<AdAdvert> adAdverts = advertService.findByMap(param);//普通广告
		RunSale runSale = saleService.getTodaySale();//今日特价
		RunRecommend runRecommend = recomService.getTodayRecom();//今日推荐
		param.put("saleType", 2);
		List<RunSale> sales = saleService.findByMap(param);//长期特价
		map.put("sales", sales);
		map.put("runSale", runSale);
		map.put("runRecommend", runRecommend);
		map.put("banner", banner);
		if (banner.size()>2) {
			map.put("l", banner.get(banner.size()-1));
		}
		map.put("adAdverts", adAdverts);
		map.put("imgUrl", SysConstant.IMG_URL);
		map.put("memberId", memberId);
		return "weixin/index";
	}
	
	@RequestMapping("/advert.wx")
	public String advert(String advertId,ModelMap map){
		AdAdvert advert = advertService.getById(advertId);
		map.put("advert", advert);
		return "weixin/advert/advert";
	}
}
