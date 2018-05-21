package com.voidwhile.market.app.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.AppBaseController;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.core.jdbc.sql.UUIDHexGenerator;
import com.voidwhile.core.utils.PropertyUtils;
import com.voidwhile.market.entity.AppSession;
import com.voidwhile.market.entity.PubImage;
import com.voidwhile.market.service.PubImageService;

import sun.misc.BASE64Decoder;



/**
 * 
 * @author zhangujing
 *2016年11月4日
 */
@Controller
@RequestMapping("/app/upload")
public class AppUploadController extends  AppBaseController {
	UUIDHexGenerator gen = UUIDHexGenerator.getInstance();
	
	@Autowired
	private PubImageService mkPubImageService;
	/**
	 * 
	 * @param formFile
	 * @param sessionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.json")
	@ResponseBody
	public Map<String, Object> imageUpload(String formFile,String sessionId,HttpServletRequest request) {
		Map<String, Object> rlt = new HashMap<String, Object>();
			try {
				AppSession session = validateSession(sessionId);
				if (session == null) {
					rlt.put("retcode", "-1");
					rlt.put("message", "未登陆");
					return rlt;
				}
				//System.out.println(request.getSession().getServletContext().getRealPath("/"));
				String imgPath=PropertyUtils.getPropertyValue("config.properties", "file.path")+"imgs";
				File f = new File(imgPath);
				if(!f.exists()){
					f.mkdir();
				}
				
				String filename = MarketUtils.uuid();
				String img1="imgs/"+filename+".jpg";
				imgPath=imgPath +"/"+filename+".jpg";
				System.out.println("2-------------"+imgPath);
				f = new File(imgPath);
				System.out.println("3-------------"+f);
				BASE64Decoder decoder = new BASE64Decoder();
				//通过Base64解密，将图片数据解密成字节数组
				byte[] bytes = decoder.decodeBuffer(formFile);
				//构造字节数组输入流
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				//读取输入流的数据
				BufferedImage bi = ImageIO.read(bais);
				//将数据信息写进图片文件中
				ImageIO.write(bi, "jpg", f);// 不管输出什么格式图片，此处不需改动
				bais.close();
				rlt.put("retcode", "0");
				rlt.put("message", "保存成功!");
				return rlt;
			} catch (IOException e) {
				rlt.put("message", "出错啦!" + e.getMessage());
				return rlt;
			}
		}
	

	/**
	 * 
	    * @Title: imageUploads
	    * @Description: 多张图片上传
	    * @param formFile 字符串
	    * @param sessionId 
	    * @param bizid	对应文档id
	    * @param biztype 图片类型
	    * @param request
	    * @return    参数
	    * @return Map<String,Object>    返回类型
	    * @throws
	 */
	@RequestMapping("/imgs_save.do")
	public Map<String, Object> imageUploads(String formFile, String sessionId,String mainImgType,Integer imgType, Long bizid, String biztype, HttpServletRequest request) {
		Map<String, Object> rlt = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			AppSession session = validateSession(sessionId);
			if (session == null) {
				rlt.put("retcode", "-1");
				rlt.put("message", "未登陆");
				return rlt;
			}
			String[]  list=formFile.split(",");
			for (int i = 0; i < list.length; i++) {
				String imgPath=PropertyUtils.getPropertyValue("config.properties", "file.path")+"imgs/";
				File targetDir = new File(imgPath);
				String filename = gen.getNextValue() + ".jpg";
				targetDir.renameTo(new File(imgPath + filename));
				
				BASE64Decoder decoder = new BASE64Decoder();
				//通过Base64解密，将图片数据解密成字节数组
				byte[] bytes = decoder.decodeBuffer(list[i]);
				//构造字节数组输入流
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				//读取输入流的数据
				BufferedImage bi = ImageIO.read(bais);
				//将数据信息写进图片文件中
				ImageIO.write(bi, "jpg", targetDir);// 不管输出什么格式图片，此处不需改动
				bais.close();
				
				String img = "imgs/"+ filename;
				param.put("biztype", biztype);
				param.put("bizid", bizid);
				PubImage entity = (PubImage) mkPubImageService.findByMap(param);
				if(entity != null){
					entity.setPath(img);
					entity.setImgname(filename);
					mkPubImageService.update(entity);
				}else{
					PubImage record = new PubImage();
					record.setImgType(imgType);
					record.setBiztype(Integer.valueOf(biztype));
					record.setBizid(bizid);
					record.setPath(img);
					record.setImgname(filename);
					mkPubImageService.save(record);
				}
			}
			
			rlt.put("retcode", "0");
			rlt.put("message", "保存成功!");
			return rlt;
		} catch (Exception e) {
			e.printStackTrace();
			rlt.put("retcode", "2");
			rlt.put("message", "出错啦!" + e.getMessage());
			return rlt;
		}
	}
}
