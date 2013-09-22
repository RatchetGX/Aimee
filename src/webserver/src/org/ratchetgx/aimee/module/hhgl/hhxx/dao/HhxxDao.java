package org.ratchetgx.aimee.module.hhgl.hhxx.dao;

import java.util.Map;

import org.ratchetgx.aimee.common.webbase.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class HhxxDao extends BaseDao {

	/**
	 *  获取最近受理的客服信息
	 */
	public Map getZjslKfxx(String sKh_khbh) {
		
		Map kfxxMap = null;
		
		String sql = "SELECT count(*) as cnt FROM t_hhgl_xxxx WHERE khbh=? ORDER BY fssj DESC limit 1";
		int count = this.jt.queryForInt(sql, sKh_khbh);
		if (count > 0) {
			sql = "SELECT xxbh,khbh,kfbh FROM t_hhgl_xxxx WHERE khbh=? ORDER BY fssj DESC limit 1";
			kfxxMap = this.jt.queryForMap(sql, sKh_khbh);
		}
		return kfxxMap;
		
	}
	
	/**
	 *  保存会话-消息到数据库
	 */
	public boolean recordMsgToDB(Map sMsgInfo) {
		boolean bSuccess = false;
		
		String sKh_khbh = (String)sMsgInfo.get("kh_khbh");
		String sKf_kfbh = (String)sMsgInfo.get("kf_kfbh");
		String sMsg_msgid = (String)sMsgInfo.get("msg_id");
		String sMsg_msgtype = (String)sMsgInfo.get("msg_type");
		String sMsg_content = (String)sMsgInfo.get("msg_content");
		String sMsg_picurl = (String)sMsgInfo.get("msg_picurl");
		String sMsg_mediaid = (String)sMsgInfo.get("msg_mediaid");
		String sMsg_sendtime = (String)sMsgInfo.get("msg_sendtime");
		String sMsg_sendstatus = (String)sMsgInfo.get("msg_sendstatus");
		
		/** 将信息保存到消息表 */
		String sql = "INSERT INTO t_hhgl_xxxx (xxbh,kfbh,khbh,weixin_msgid,xxnr,xxlx,weixin_picurl,weixin_mediaid,xxfx,fszt,fssj) "
				   + " VALUES(uuid(),?,?,?,?,?,?,?,'1',?,sysdate())";
		int count = this.jt.update(sql, sKf_kfbh, sKh_khbh, sMsg_msgid, sMsg_content, sMsg_msgtype, sMsg_picurl, sMsg_mediaid, sMsg_sendstatus);
		
		return bSuccess;
	}
	
}
