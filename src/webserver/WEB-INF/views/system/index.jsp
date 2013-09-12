<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Aimee服务系统客服端</title>
    <!--
    <script type="text/javascript" src="${ctx}/resources/js/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jplayer/jquery.jplayer.min.js"></script>
    <link rel="stylesheet" href="${ctx}/resources/js/jquery-ui-1.10.3.custom/css/smoothness/jquery-ui-1.10.3.custom.min.css" />
    <link type="text/css" href="${ctx}/resources/js/jplayer/skin/blue.monday/jplayer.blue.monday.css" rel="stylesheet" />
	-->
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.8.2.min.js"></script>
        
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/themes/icon.css">
	<!--<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/demo.css">-->
	<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-plugins/jquery.form.js"></script>
	
    <script type="text/javascript" src="${ctx}/js/system/index.js"></script>
    <script type="text/javascript" src="${ctx}/js/system/index_websocket.js"></script>
    <script type="text/javascript" src="${ctx}/js/system/index_tab.js"></script>
    <script type="text/javascript" src="${ctx}/js/system/emojimap.js"></script>
    <script type="text/javascript">
		 $(document).ready(function() {
		 	var $Items1 = $(".faceBox a");
         	$Items1.each(function(){
         		$(this).bind("click",function(){
         			//alert($(this).attr("title"));
         			chooseEmoji($(this));
         		});	
		 	});
			
		});
	</script>
    
    <style type="text/css">
        #table_kcxx td {
            text-align: center;
        }

        #table_kcxx th {
            text-align: center;
        }
        
        body {
        	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
        	font-size: 62.5%;
        }
    </style>
    
    <style>
    #dialog label, #dialog input { display:block; }
    #dialog label { margin-top: 0.5em; }
    #dialog input, #dialog textarea { width: 95%; }
    #tabs { margin-top: 1em; }
    #tabs li .ui-icon-close { float: left; margin: 0.4em 0.2em 0 0; cursor: pointer; }
    #add_tab { cursor: pointer; }
    </style>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/index.css">
    
    
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}">
	<input type="hidden" id="username" name="username" value="${sessionScope.kfInfo.username}">
	<input type="hidden" id="kf_kfbh" name="kf_kfbh" value="${sessionScope.kfInfo.kfbh}">
	<input type="hidden" id="kf_kfhm" name="kf_kfhm" value="${sessionScope.kfInfo.kfhm}">
	
	<form id="inputWordsFormTamplate" style="display:none" method="post" enctype="multipart/form-data">
	    	<a href="javascript:;" id="sendEmojiIcon" 
				style="display:block; width:34px; height:34px; margin-right:2px; float:left; background:url(${ctx}/resources/image/index/button_chat13dfb3.png) no-repeat 0 0; text-decoration:none; "
				onclick="showEmojiPanel()" title="选择表情" style=""></a>
	    	<a href="javascript:;" id="screenSnapIcon"
	    		style="display:block; width:34px; height:34px; margin-right:2px; float:left; background:url(${ctx}/resources/image/index/button_chat13dfb3.png) no-repeat -32px -98px; text-decoration:none; "
	    		click="screenSnap" title="发送截屏" style=""></a>
			<a href="javascript:;" id="screenSnapIcon"
	    		style="display:block; width:34px; height:34px; margin-right:2px; float:left; background:url(${ctx}/resources/image/index/button_chat13dfb3.png) no-repeat -31px -34px; text-decoration:none; "
	    		onclick="hideEmojiPanel()" title="发送截屏" style=""></a>

	              <textarea rows="2" cols="68" style="overflow-x:hidden;overflow-y:auto"></textarea>
	              <input type="file" name="file_upload"/>
	             <a class="easyui-linkbutton" href="#" onclick="sendImgMsgToCustomer2()" style="margin-right:32px;float:right;">发图片 </a>
	             <a class="easyui-linkbutton" href="#" onclick="sendTextMsgToCustomer2()" style="margin-right:32px;float:right;"> 发 送 </a>
	</form>
	
	  <div id="jquery_jplayer_1" class="jp-jplayer" style="display:none"></div>
	  <div id="jp_container_1" class="jp-audio" style="display:none">
	    <div class="jp-type-single">
	      <div class="jp-gui jp-interface">
	        <ul class="jp-controls">
	          <li><a href="javascript:;" class="jp-play" tabindex="1">play</a></li>
	          <li><a href="javascript:;" class="jp-pause" tabindex="1">pause</a></li>
	          <li><a href="javascript:;" class="jp-stop" tabindex="1">stop</a></li>
	          <li><a href="javascript:;" class="jp-mute" tabindex="1" title="mute">mute</a></li>
	          <li><a href="javascript:;" class="jp-unmute" tabindex="1" title="unmute">unmute</a></li>
	          <li><a href="javascript:;" class="jp-volume-max" tabindex="1" title="max volume">max volume</a></li>
	        </ul>
	        <div class="jp-progress">
	          <div class="jp-seek-bar">
	            <div class="jp-play-bar"></div>
	          </div>
	        </div>
	        <div class="jp-volume-bar">
	          <div class="jp-volume-bar-value"></div>
	        </div>
	        <div class="jp-time-holder">
	          <div class="jp-current-time"></div>
	          <div class="jp-duration"></div>
	          <ul class="jp-toggles">
	            <li><a href="javascript:;" class="jp-repeat" tabindex="1" title="repeat">repeat</a></li>
	            <li><a href="javascript:;" class="jp-repeat-off" tabindex="1" title="repeat off">repeat off</a></li>
	          </ul>
	        </div>
	      </div>
	      <div class="jp-title">
	        <ul>
	          <li>Bubble</li>
	        </ul>
	      </div>
	      <div class="jp-no-solution">
	        <span>Update Required</span>
	        To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
	      </div>
	    </div>
	  </div>

    <table border='0' width="98%">
    	<div class="menu-connStatus">
    		<div class="connStatus">
    			<a><span>正在连接服务器!</span></a>
    		</div>
    	</div>	
    </table>
    
    <table id="mainTable" border='0' width="98%">
    	<div id="chat" class="chatPanel normalPanel" ctrl="1" style="">
    		<div class="chatLeftPanel">
			<div id="chat_leftpanel" class="list" ctrl="1">
				<div class="top">
					<div id="profile" class="title"> 
						<div class="myProfile"> <img src="${ctx}/resources/image/index/webwxgeticon.png" un="avatar_wxid_axo9myavqv8e22"> 
							<div class="avatar" id="accountAvatarWrapper" click="popupModifyAvatarWin" style="cursor:pointer;"></div> 
							<a class="unreadTotalCount" id="unreadTotalCount" href="javascript:;" style="display:none;">0</a>     
							<div class="info">  <span class="nickName">${sessionScope.kfInfo.kfhm}</span> </div> 
							
						</div> 
						<div class="clr"></div> 
					</div>
				</div>
				<!--search-->
				<div class="chatListSearch">
					<div class="chatListSearchPanel">
						
						<div class="chatListSearchInput left">
							<span class="chatListSearchIcon left"></span>
							<input class="left" type="text" name="search" value="" keydown="preSearch" keyup="search@.chatListSearchInput" placeholder="搜索" id="conv_search">
							<span class="searchClean left" click="cleanSearchWord@.chatListSearchInput" style="display:none;" id="conv_search_clean"></span>
						</div>
						<div class="clr"></div>
						<div class="userlist"></div>
					</div>
				</div>
			</div>
			</div>
				
			<div class="chatMainPanel normalPanel" id="chatMainPanel" ctrl="1" style="">
				<div class="chatTitle1">
					<div id="tabs" class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:810px;height:495px">
					</div>
					
				</div>
			</div>	
			
			<!--<div id="emojiPanel" style="display: ;z-index:100;background:#164C3F;left:50px ;top:10px;width:150px;height:100px;position:absolute;">-->
			<div id="emojiPanel" class="emojiPanel"  style="display:none;z-index:999;background:#164C3F;left:239px ;top:381px;width:100px;height:100px;position:absolute;"> 
			<ul class="faceTab"><li><a class="chooseFaceTab"
href="javascript:;" >QQ表情</a></li><li><a href="javascript:;" click="chooseEmojiPanel@.faceTab"
un="emojiBox">符号表情</a></li><li><a href="javascript:;" click="chooseEmojiPanel@.faceTab" un="rabbitBox">动画表情</a></li></ul>
			<div class="faceWrap"
style="zoom:1;outline:none;" tabindex="0" hidefocus="true"><div class="faceBox emojiArea" style=""><a title="微笑" class="f14"
href="javascript:;"></a><a title="撇嘴" class="f1" href="javascript:;"></a><a title="色" class="f2" href="javascript:;"></a><a title="发呆" class="f3"
href="javascript:;"></a><a title="得意" class="f4" href="javascript:;"></a><a title="流泪" class="f5" href="javascript:;"></a><a title="害羞" class="f6"
href="javascript:;"></a><a title="闭嘴" class="f7" href="javascript:;"></a><a title="睡" class="f8" href="javascript:;"></a><a title="大哭" class="f9"
href="javascript:;"></a><a title="尴尬" class="f10" href="javascript:;"></a><a title="发怒" class="f11" href="javascript:;"></a><a title="调皮" class="f12"
href="javascript:;"></a><a title="呲牙" class="f13" href="javascript:;"></a><a title="惊讶" class="f0 borderRightNone" href="javascript:;"></a><a title="难过"
class="f15" href="javascript:;"></a><a title="酷" class="f16" href="javascript:;"></a><a title="冷汗" class="f96" href="javascript:;"></a><a title="抓狂" class="f18"
href="javascript:;"></a><a title="吐" class="f19" href="javascript:;"></a><a title="偷笑" class="f20" href="javascript:;"></a><a title="愉快" class="f21"
href="javascript:;"></a><a title="白眼" class="f22" href="javascript:;"></a><a title="傲慢" class="f23" href="javascript:;"></a><a title="饥饿" class="f24"
href="javascript:;"></a><a title="困" class="f25" href="javascript:;"></a><a title="惊恐" class="f26" href="javascript:;"></a><a title="流汗" class="f27"
href="javascript:;"></a><a title="憨笑" class="f28" href="javascript:;"></a><a title="悠闲" class="f29 borderRightNone" href="javascript:;"></a><a title="奋斗"
class="f30" href="javascript:;"></a><a title="咒骂" class="f31" href="javascript:;"></a><a title="疑问" class="f32" href="javascript:;"></a><a title="嘘" class="f33"
href="javascript:;"></a><a title="晕" class="f34" href="javascript:;"></a><a title="疯了" class="f35" href="javascript:;"></a><a title="衰" class="f36"
href="javascript:;"></a><a title="骷髅" class="f37" href="javascript:;"></a><a title="敲打" class="f38" href="javascript:;"></a><a title="再见" class="f39"
href="javascript:;"></a><a title="擦汗" class="f97" href="javascript:;"></a><a title="抠鼻" class="f98" href="javascript:;"></a><a title="鼓掌" class="f99"
href="javascript:;"></a><a title="糗大了" class="f100" href="javascript:;"></a><a title="坏笑" class="f101 borderRightNone" href="javascript:;"></a><a title="左哼哼"
class="f102" href="javascript:;"></a><a title="右哼哼" class="f103" href="javascript:;"></a><a title="哈欠" class="f104" href="javascript:;"></a><a title="鄙视"
class="f105" href="javascript:;"></a><a title="委屈" class="f106" href="javascript:;"></a><a title="快哭了" class="f107" href="javascript:;"></a><a title="阴险"
class="f108" href="javascript:;"></a><a title="亲亲" class="f109" href="javascript:;"></a><a title="吓" class="f110" href="javascript:;"></a><a title="可怜"
class="f111" href="javascript:;"></a><a title="菜刀" class="f112" href="javascript:;"></a><a title="西瓜" class="f89" href="javascript:;"></a><a title="啤酒"
class="f113" href="javascript:;"></a><a title="篮球" class="f114" href="javascript:;"></a><a title="乒乓" class="f115 borderRightNone" href="javascript:;"></a><a
title="咖啡" class="f60" href="javascript:;"></a><a title="饭" class="f61" href="javascript:;"></a><a title="猪头" class="f46" href="javascript:;"></a><a title="玫瑰"
class="f63" href="javascript:;"></a><a title="凋谢" class="f64" href="javascript:;"></a><a title="嘴唇" class="f116" href="javascript:;"></a><a title="爱心" class="f66"
href="javascript:;"></a><a title="心碎" class="f67" href="javascript:;"></a><a title="蛋糕" class="f53" href="javascript:;"></a><a title="闪电" class="f54"
href="javascript:;"></a><a title="炸弹" class="f55" href="javascript:;"></a><a title="刀" class="f56" href="javascript:;"></a><a title="足球" class="f57"
href="javascript:;"></a><a title="瓢虫" class="f117" href="javascript:;"></a><a title="便便" class="f59 borderRightNone" href="javascript:;"></a><a title="月亮"
class="f75" href="javascript:;"></a><a title="太阳" class="f74" href="javascript:;"></a><a title="礼物" class="f69" href="javascript:;"></a><a title="拥抱" class="f49"
href="javascript:;"></a><a title="强" class="f76" href="javascript:;"></a><a title="弱" class="f77" href="javascript:;"></a><a title="握手" class="f78"
href="javascript:;"></a><a title="胜利" class="f79" href="javascript:;"></a><a title="抱拳" class="f118" href="javascript:;"></a><a title="勾引" class="f119"
href="javascript:;"></a><a title="拳头" class="f120" href="javascript:;"></a><a title="差劲" class="f121" href="javascript:;"></a><a title="爱你" class="f122"
href="javascript:;"></a><a title="NO" class="f123" href="javascript:;"></a><a title="OK" class="f124 borderRightNone" href="javascript:;"></a><a title="爱情" class="f42
borderBottomNone" href="javascript:;"></a><a title="飞吻" class="f85 borderBottomNone" href="javascript:;"></a><a title="跳跳" class="f43 borderBottomNone"
href="javascript:;"></a><a title="发抖" class="f41 borderBottomNone" href="javascript:;"></a><a title="怄火" class="f86 borderBottomNone" href="javascript:;"></a><a
title="转圈" class="f125 borderBottomNone" href="javascript:;"></a><a title="磕头" class="f126 borderBottomNone" href="javascript:;"></a><a title="回头" class="f127
borderBottomNone" href="javascript:;"></a><a title="跳绳" class="f128 borderBottomNone" href="javascript:;"></a><a title="挥手" class="f129 borderBottomNone"
href="javascript:;"></a><a title="激动" class="f130 borderBottomNone" href="javascript:;"></a><a title="街舞" class="f131 borderBottomNone" href="javascript:;"></a><a
title="献吻" class="f132 borderBottomNone" href="javascript:;"></a><a title="左太极" class="f133 borderBottomNone" href="javascript:;"></a><a title="右太极" class="f134
borderBottomNone borderRightNone" href="javascript:;"></a></div>
			</div>
			</div>
		</div>	
    </table>
    
</body>
</html>