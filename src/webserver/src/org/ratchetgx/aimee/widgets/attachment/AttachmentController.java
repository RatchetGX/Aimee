package org.ratchetgx.aimee.widgets.attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.ratchetgx.aimee.common.webbase.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/widgets/attachment")
public class AttachmentController extends BaseController {
		
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 跳转到文件上传页
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "showFileUploadPage")
	public ModelAndView showFileUploadPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileType = request.getParameter("fileType");
		String maxSize = request.getParameter("maxSize");
		ModelAndView mav = new ModelAndView("/widgets/attachment/fileUploader");
		
		if(fileType!=null && !"".equals(fileType.trim())){
			mav.addObject("fileType", fileType);
		}
		if(maxSize!=null && !"".equals(maxSize.trim())){
			mav.addObject("maxSize", maxSize);
		}
		return mav;
	}
	
	@RequestMapping(value = "fileUploadProcess",method = RequestMethod.POST)  
	public String fileUploadProcess(MultipartHttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {  
		
			//获取并解析文件类型和支持最大值
			
			MultipartFile originalFile = request.getFile("file_upload");  
			String file_name = originalFile.getOriginalFilename();
			String file_suffix = file_name.substring(file_name.lastIndexOf("."));
			
			log.debug("file name:" + file_name);
			log.debug("file size:" + originalFile.getSize());
			
			String appRootPath = request.getSession().getServletContext().getRealPath("/");
			String attachmentFile_name = UUID.randomUUID() + file_suffix;
			String attachmentUploadPath = appRootPath + "attachments/" + attachmentFile_name;
			log.debug("attamentUploadPath:" + attachmentUploadPath);
			File attachmentFile = new File(attachmentUploadPath);
			InputStream in = originalFile.getInputStream();
			OutputStream out = new FileOutputStream(attachmentFile);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b, 0, 1024)) > 0) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
	        in.close(); 
			
	        JSONObject jsonObj = new JSONObject();
	        jsonObj.put("filename", attachmentFile_name);
	        response.getWriter().write(jsonObj.toString());
	        return null;
	} 
	
}
