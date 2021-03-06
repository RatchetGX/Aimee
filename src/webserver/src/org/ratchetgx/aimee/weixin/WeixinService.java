package org.ratchetgx.aimee.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.ratchetgx.aimee.common.webbase.HttpMultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinService {
	
	private static Logger log = LoggerFactory.getLogger(WeixinService.class);
	
	/** 微信AppId */
	private final static String WEIXIN_APPID = "wxcc7310ca575f22e8";
	
	/** 微信AppSecret */
	private final static String WEIXIN_APPSECRET = "ff288899be8f2978fdc7fdb93c512028";
	
	/** 微信AccessToken */
	private static String WEIXIN_ACCESSTOKEN;

	/** 获取access_token */
	public static String getAccessToken() throws IOException {
		
		if (WEIXIN_ACCESSTOKEN != null) {
			return WEIXIN_ACCESSTOKEN;
		}
		
		String sAccessToken = null;
		
		String urlStr =  "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
				      +  "&appid=" + WEIXIN_APPID
				      +  "&secret=" + WEIXIN_APPSECRET;
		URL url = new URL(urlStr); 
        URLConnection uc = url.openConnection(); 
        InputStream in = uc.getInputStream(); 
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b, 0, 1024)) > 0) {
			String str = new String(b, 0, len);
			sb.append(str);
		}
        in.close(); 
        
        String urlRetStr = sb.toString();
        log.info("get access_token:url return：" + urlRetStr);
        
        JSONObject sJsonObj;
		try {
			sJsonObj = new JSONObject(urlRetStr);
			if (sJsonObj.has("access_token")) {
				sAccessToken = sJsonObj.getString("access_token");
	        	log.info("get access_token success:access_token：" + sAccessToken);
	        } else {
	        	String errcode = sJsonObj.getString("errcode");
	        	log.info("get access_token:errcode：" + errcode);
	        }
		} catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        
        return sAccessToken;

	}
	
	/** 获取用户信息 */
	public static Map getUserInfo(String openid) throws IOException {
		
		Map sUserInfoMap = null;
		
		String sAccessToken = getAccessToken();
		if (sAccessToken == null) {
			return sUserInfoMap;
		}
		
		log.info("getUserInfo:openid:" + openid);
		String urlStr =  "https://api.weixin.qq.com/cgi-bin/user/info?"
				      +  "access_token=" + sAccessToken
				      +  "&openid=" + openid;
		URL url = new URL(urlStr); 
        URLConnection uc = url.openConnection(); 
        InputStreamReader inr = new InputStreamReader(uc.getInputStream(), "UTF-8"); 
        StringBuffer sb = new StringBuffer();
        char[] b = new char[1024];
		int len = 0;
		while ((len = inr.read(b, 0, 1024)) > 0) {
			String str = new String(b, 0, len);
			sb.append(str);
		}
        inr.close(); 
        
        String urlRetStr = sb.toString();
        log.info("get user info:url return：" + urlRetStr);
        
        JSONObject sJsonObj;
		try {
			sJsonObj = new JSONObject(urlRetStr);
			if (sJsonObj.has("openid")) {
				sUserInfoMap = new HashMap();
				sUserInfoMap.put("nickname", sJsonObj.getString("nickname"));
				sUserInfoMap.put("sex", sJsonObj.getString("sex"));
				sUserInfoMap.put("city", sJsonObj.getString("city"));
				sUserInfoMap.put("language", sJsonObj.getString("language"));
	        	log.info("get access_token success!");
	        } else {
	        	String errcode = sJsonObj.getString("errcode");
	        	log.info("get access_token:errcode：" + errcode);
	        }
		} catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        
        return sUserInfoMap;

	}
	
	/** 向用户发文本消息 */
	public static boolean sendTextMsgToUser(String openid, String content) throws IOException {
		
		boolean bSuccess = false;
		
		String sAccessToken = getAccessToken();
		if (sAccessToken == null) {
			return bSuccess;
		}
		
		log.info("sendTextMsgToUser:openid:" + openid);
		String urlStr =  "https://api.weixin.qq.com/cgi-bin/message/custom/send?"
				      +  "access_token=" + sAccessToken;
		URL url = new URL(urlStr); 
        URLConnection conn = url.openConnection(); 
        
        /** 发送消息 */
        conn.setDoOutput(true);   
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");   
        JSONObject sJsonSendMsg = new JSONObject();
        try {
        	sJsonSendMsg.put("touser", openid);
        	sJsonSendMsg.put("msgtype", "text");
    		JSONObject sJsonMsgContent = new JSONObject();
    		sJsonMsgContent.put("content", content);
    		sJsonSendMsg.put("text", sJsonMsgContent);
        } catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        writer.write(sJsonSendMsg.toString());   
        writer.flush();   
        writer.close();  
        log.info("send msg：" + sJsonSendMsg.toString());
        
        /** 获取返回的信息 */
        InputStream in = conn.getInputStream(); 
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b, 0, 1024)) > 0) {
			String str = new String(b, 0, len);
			sb.append(str);
		}
        in.close(); 
        String urlRetStr = sb.toString();
        log.info("send msg return：" + urlRetStr);
        
        JSONObject sJsonObj;
		try {
			sJsonObj = new JSONObject(urlRetStr);
			String errcode = sJsonObj.getString("errcode");
			if ("0".equals(errcode)) {
	        	bSuccess = true;
	        	log.info("send msg success!");
	        } else {
	        	
	        	log.info("send msg failed:errcode：" + errcode);
	        }
		} catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        
        return bSuccess;

	}
	
	/** 向用户发图片消息 */
	public static boolean sendImgMsgToUser(String openid, String sFileName, String sFilePath) throws IOException {
		
		boolean bSuccess = false;
		
		String sAccessToken = getAccessToken();
		if (sAccessToken == null) {
			return bSuccess;
		}
		
		
		log.debug("sFileName:" + sFileName);
		log.debug("sFilePath:" + sFilePath);
		
		/** 上传图片 */
		String urlStr = "http://api.weixin.qq.com/cgi-bin/media/upload?"
			      + "access_token=" + sAccessToken + "&type=image";  
        Map<String, String> textMap = new HashMap<String, String>();  
        textMap.put("name", "testname");  
        Map<String, String> fileMap = new HashMap<String, String>();  
        fileMap.put("media", sFilePath);  
        String retStr = HttpMultipartRequest.postForm(urlStr, textMap, fileMap);  
        log.info("upload image url return:" + retStr);  
        
        String sMedia_id = "";
        JSONObject sJsonObj;
		try {
			sJsonObj = new JSONObject(retStr);
			sMedia_id = sJsonObj.getString("media_id");
		} catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
			log.info("upload image failed:" + retStr);
			return false;
		}
        
        
		/** 发送图片 */
		log.info("sendImgMsgToUser:openid:" + openid);
		urlStr =  "https://api.weixin.qq.com/cgi-bin/message/custom/send?"
				      +  "access_token=" + sAccessToken;
		URL url = new URL(urlStr); 
		URLConnection conn = url.openConnection(); 
        
        /** 发送消息 */
        conn.setDoOutput(true);   
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");   
        JSONObject sJsonSendMsg = new JSONObject();
        try {
        	sJsonSendMsg.put("touser", openid);
        	sJsonSendMsg.put("msgtype", "image");
    		JSONObject sJsonMsgContent = new JSONObject();
    		sJsonMsgContent.put("media_id", sMedia_id);
    		sJsonSendMsg.put("image", sJsonMsgContent);
        } catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        writer.write(sJsonSendMsg.toString());   
        writer.flush();   
        writer.close();  
        log.info("send msg：" + sJsonSendMsg.toString());
        
        /** 获取返回的信息 */
        InputStream in = conn.getInputStream(); 
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b, 0, 1024)) > 0) {
			String str = new String(b, 0, len);
			sb.append(str);
		}
        in.close(); 
        String urlRetStr = sb.toString();
        log.info("send msg return：" + urlRetStr);
        
		try {
			sJsonObj = new JSONObject(urlRetStr);
			String errcode = sJsonObj.getString("errcode");
			if ("0".equals(errcode)) {
	        	bSuccess = true;
	        	log.info("send msg success!");
	        } else {
	        	
	        	log.info("send msg failed:errcode：" + errcode);
	        }
		} catch (JSONException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
        
        return bSuccess;

	}
	
}
