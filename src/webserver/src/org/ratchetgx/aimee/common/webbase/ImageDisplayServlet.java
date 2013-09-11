package org.ratchetgx.aimee.common.webbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageDisplayServlet extends HttpServlet {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String filename = request.getParameter("filename");
		log.debug("filename:" + filename);
		
		response.setContentType("image/jpg;charset=utf-8");
		
		String appRootPath = request.getSession().getServletContext().getRealPath("/");
		String imgFilePath = appRootPath + "attachments/" + filename;
		File imgFile = new File(imgFilePath);
		InputStream in = new FileInputStream(imgFile); 
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b, 0, 1024)) > 0) {
			out.write(b, 0, len);
		}
		out.flush();
        in.close(); 
		
	}
	
}
