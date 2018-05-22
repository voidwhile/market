package com.voidwhile.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @MethodName: chineseToPinYin
 * @Description: 汉字换拼音
 * @param str //中文字符串
 * @return
 * 
 * 
 * @author liuxiaoyu
 * @Create Date: 2014年12月30日 上午11:18:07
 */
public class PinYinUtils {
	
	
	public static String chineseToPinYin(String str) {
		
		StringBuilder sb = new StringBuilder();
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			try {
				String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(ch, format);
				if(pinyins != null) {
					sb.append(pinyins[0]);
				}else {
					sb.append(ch);
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
