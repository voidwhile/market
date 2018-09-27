package com.voidwhile.common.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.voidwhile.common.Constant;
import com.voidwhile.common.utils.WebUtils;

@Controller
public class UploadCtrl {
	
	@ResponseBody
	@RequestMapping("/upload.do")
	public Map<String, Object> imageUpload(@RequestParam("file") MultipartFile file){
		Map<String, Object> map = new HashMap<>();
		if (!file.isEmpty()) {
			try {
				String savePath = "imgs/";
				String originalFilename = file.getOriginalFilename();
				String type = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
				String fileName = WebUtils.uuid() + type;
				File newFile = new File(Constant.FILE_PATH+ savePath);
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				file.transferTo(new File(Constant.FILE_PATH+savePath+fileName));
				map.put("error", "0000");
				map.put("msg", "上传成功");
				map.put("filePath", savePath+fileName);
				map.put("imgurl", Constant.FILE_URL+savePath+fileName);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return map;
	}

}
