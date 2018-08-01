package com.voidwhile.common.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class ImgUtil {
	/** 
     * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0　的List 
     * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic 
     * @param htmlCode HTML源码 
     * @return <img>标签 src 属性指向的图片地址的List集合 
     * @author Carl He 
     */  
    public static List<String> getImageSrc(String htmlCode) {  
        List<String> imageSrcList = new ArrayList<String>();  
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(htmlCode);  
        String quote = null;  
        String src = null;  
        while (m.find()) {  
            quote = m.group(1);  
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);  
            imageSrcList.add(src);  
        }  
        return imageSrcList;  
    }  
    
    public static BufferedImage createImg(String url){
		  MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	         Map hints = new HashMap();
	         hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
	         BitMatrix bitMatrix = null;
	         BufferedImage image=null;
	         try {
	             bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300,hints);
	              image = toBufferedImage(bitMatrix);
	             //输出二维码图片流
	             } catch (Exception e) {
	            	 e.printStackTrace();
	             }
	         return image;
	}
    
    public static  BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int BLACK = 0xFF000000;
		int WHITE = 0xFFFFFFFF;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
		image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
		}
		}
		return image;
	}
}
