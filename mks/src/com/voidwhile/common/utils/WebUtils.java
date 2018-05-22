package com.voidwhile.common.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import net.sourceforge.pinyin4j.PinyinHelper;

public final class WebUtils {
	public static String replaceStr(String str) {
		return str.replaceAll("'", "").replaceAll("\\*", "")
				.replaceAll("\\|", "").replaceAll("\\+", "")
				.replaceAll("=", "").replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(";", "");
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数个数
	 * @return
	 */
	public static String game(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}

	public static int DoubleToInt(String num) {
		int price = 0;
		if (num.contains(".")) {
			String[] z = num.split("\\.");
			int a1 = Integer.parseInt(z[0]);
			int a2 = Integer.parseInt(z[1]);
			if (z[1].length() == 1) {
				price = a1 * 100 + a2 * 10;
			} else if (z[1].length() == 2) {
				price = a1 * 100 + a2;
			} else if (z[1].length() == 0) {
				price = a1 * 100;
			}
		} else {
			price = Integer.parseInt(num) * 100;
		}
		return price;
	}
	/**
	 * 汉语转拼音 取首字母
	 */
	 // 返回中文的首字母  
    public static String getPinYinHeadChar(String str) {  
  
        		String convert = "";  
            char word = str.charAt(0);  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        return convert.toUpperCase();  
    }  
    
   
    
    /**
	 * 计算月供金额
	 * @param loanCost 贷款金额（车款减去首付款金额）
	 * @param yearRate 年利率 本系统为8
	 * @param year 贷款年份 贷款多少年
	 * @return 月供金额
	 */
	public static String loan(Double loanCost,Double yearRate,Integer year){
		//月利率
		double monthRate = yearRate/1200;
		//还款月数
		int monthNum = year*12;
		//等额本息计算公式：［贷款本金 × 月利率 × （ 1 ＋月利率）＾还款月数］ ÷ ［（ 1 ＋月利率）＾还款月数－ 1 ］
		double monthPay = (loanCost*monthRate*Math.pow((1+monthRate), monthNum))/((Math.pow((1+monthRate), monthNum)-1));
		String monthPayStr = String.format("%.2f", monthPay);
		return monthPayStr;
	}
	
	/**
	 * 计算利息
	 * @param loanCost 贷款金额（车款减去首付款金额）
	 * @param yearRate 年利率 本系统为8
	 * @param year 贷款年份 贷款多少年
	 * @return 总利息
	 */
	public static String interest(Double loanCost,Double yearRate,Integer year){
		//贷款的总金额 * 年利率 * 贷款了多少年
		double interest = loanCost*(yearRate/100)*year;
		String interestStr = String.format("%.2f", interest);
		return interestStr;
	}
	
	
	/**
	 * 发送短信 
	 */
	public static String sendMessage(String phone, String verifyCode) {
		String message = null;
		
		return message;
	}
	
	public static String getResponse(String uri, String code) throws Exception {
		URL url = new URL(uri);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(3 * 1000);
		connection.setReadTimeout(6 * 1000);
		connection.setRequestMethod("GET");
		BufferedReader in = null;
		if (code == null) {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), code));
		}

		String inputLine;
		String back = "";
		while ((inputLine = in.readLine()) != null) {
			back = back + inputLine;
		}
		in.close();
		return back;
	}
	
	/**
	 * @return 生成12位随机数
	 */
	public static String getRandom(){
		String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		int size = 12;
		StringBuffer sb = new StringBuffer();
		Random ran = new Random();
		for (int i = 1; i <= size; i++) {
			int r = ran.nextInt(chars.length);
			sb.append(chars[r]);// 将字符保存，存入Session
		}
		
		return sb.toString();
	}
	
    /**
     * 生成订单编号
     * @param travelUuid
     * @return
     */
    public static String generateOrderNum(){
    	List<Record> list = Db.find("select f_get_order_code()");
    	Record re = list.get(0);
    	Object[] cs = re.getColumnValues();
    	String orderNum = cs[0].toString();
    	return orderNum;
    }	
    
    /**
     * 格式化数字
     * @param dl
     * @param patten
     * @return
     */
    public static String doubleFormat(double dl,String patten) {
    	return new DecimalFormat(patten).format(dl);
	}
}
