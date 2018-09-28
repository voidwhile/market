package com.voidwhile.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.system.entity.SysParam;
import com.voidwhile.system.entity.SysParamItem;
import com.voidwhile.system.service.SysParamItemService;
import com.voidwhile.system.service.SysParamService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 字典编码工具 类
 * @author: zhanzheng
 * @Create Date: 2014年12月4日 下午3:08:05
 *
 * @Version: v1.0
 */
public class SysParamHelper {
	public static SysParamService paramService;
	public static SysParamItemService paramItemService;

	static {
		paramService = SpringContextHelper.getBean(SysParamService.class);
		paramItemService = SpringContextHelper.getBean(SysParamItemService.class);
	}

	/**
	 * @MethodName: getCodeList
	 * @Description: 根据字典值列表
	 * @param supplierId
	 * @param code
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月4日 下午3:39:11
	 */
	public static List<SysParamItem> getCodeList(String supplierId, String code) {
		SysParam param = paramService.getBySupplierIdAndParamCode(supplierId, code);
		if (param == null) {
			param = paramService.getBySupplierIdAndParamCode("0", code);
		}
		List<SysParamItem> items = new ArrayList<SysParamItem>();
		if (param != null) {
			items = paramItemService.findByParamId(param.getUid(), true);
		}

		return items;
	}

	/**
	 * @MethodName: getCodeOption
	 * @Description: 生成select标签选项
	 * @param supplierId 企业ID
	 * @param code 字典编码
	 * @param selected 选中的值
	 * @param single
	 * @param isShowBlank
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月4日 下午3:09:40
	 */
	public static String getCodeOption(String supplierId, String code, String selected, boolean isShowBlank) {
		List<SysParamItem> items = getCodeList(supplierId, code);
		StringBuilder sb = new StringBuilder();

		if (items != null && items.size()>0) {
			if (isShowBlank) {
				sb.append("<option value=\"\">请选择...</option>");
			}
			for (SysParamItem item : items) {
				sb.append("<option value=\"" + item.getItemCode() + "\"");
				if (selected.equals(item.getItemCode())) {
					sb.append(" selected=\"selected\"");
				}
				sb.append(">" + item.getItemName() + "</option>");
			}
		}

		return sb.toString();
	}
}
