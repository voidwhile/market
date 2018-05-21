package com.voidwhile.common.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.voidwhile.common.utils.ConvertMap2Json;
import com.voidwhile.core.jdbc.sql.UUIDHexGenerator;
import com.voidwhile.core.utils.PropertyUtils;

/**
 * Servlet implementation class VideoUpload
 */
@WebServlet("/app/videoUpload.video")
public class VideoUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UUIDHexGenerator gen = UUIDHexGenerator.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(-1);
		List items = new ArrayList();
		String type = ".mp4";
		String prefix = PropertyUtils.getPropertyValue("config.properties", "file.path");
		String filepath = "/videos/";
		String fileName = gen.getNextValue() + type;
		File f = new File(prefix+filepath);
		if (!f.exists()) {
			f.mkdir();
		}
		FileItem uploadFile = null;
		try {												
			items = fileUpload.parseRequest(request);
			Iterator iterator = items.iterator();
			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();
				if (fileItem.isFormField()) {
					type = fileItem.getString("utf-8");
				} else {
					uploadFile = fileItem;
				}
			}
			uploadFile.write(new File(prefix+filepath+fileName));

			map.put("filepath", filepath+fileName);
			map.put("retcode", "0");
			map.put("message", "success");
		} catch (Exception e) {
			map.put("retcode", "1");
			map.put("message", e.getMessage());
			System.out.println(e.getMessage());
		}
		response.getWriter().append(ConvertMap2Json.buildJsonBody(map, 0, false));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
