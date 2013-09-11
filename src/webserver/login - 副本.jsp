<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Aimee服务系统客服端登录</title>

        <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/wxm2_login177db0.css">
  		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/icon176280.css">

        <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.8.2.min.js"></script>
        <!-- js框架 -->
        <script src="${ctx}/resources/js/jquery-plugins/validate/jquery.metadata.js" type="text/javascript"></script>
        <script src="${ctx}/resources/js/jquery-plugins/validate/jquery.validate.js" type="text/javascript"></script>
        <script src="${ctx}/resources/js/jquery-plugins/validate/messages_cn.js" type="text/javascript"></script>
        <script src="${ctx}/resources/js/jquery-plugins/validate/validate_ex.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
            	
                $("#loginForm").validate({
                    rules : {
                        j_username : {
                            required : true
                        },
                        j_password : {
                            required : true
                        }
                    },
                    messages : {
                        j_username : '<font color=red>&nbsp;必填项！</font>',
                        j_password : '<font color=red>&nbsp;必填项！</font>'
                    }
                });
		
                $("#captcha").click(function(){
                    $('#captcha').hide().attr('src','${ctx}/captcha.do'+ '?'+ Math.floor(Math.random() * 100)).fadeIn();  
                });  
            });
        </script>

    </head>

    <body>        
    <form action="${ctx}/login.do" method="post" id="loginForm">
		<div id="header">
		  <div class="wrapper">
		  	<h>&nbsp;&nbsp;&nbsp;&nbsp;</h>
		    <a href="#" class="dib"><img src="${ctx}/resources/image/login/logo175c39.png"></a>
		    <span class="hd_login_info">
		  </div>
		</div>
		<div id="banner">
		  <div class="wrapper">
		    <div class="login-panel">
		
		      <!--<span class="arrow"></span>-->
		      <h3>登录</h3>
        <div class="login-mod">
			<div class="login-err-panel dn" id="err_area">
		    	<span class="icon-wrapper"><i class="icon24-login err" style="margin-top:-.2em;*margin-top:0;"></i></span><span id="err_tips"></span>
		  	</div>
		  	<c:if test="${param.error != null || param.validateCodeError != null || param.errorMsg != null}">
                   <tr>
                        <td align="center"><span><font color="red">
                                    <c:if test="${param.error == 'validateCode'}">
									                        验证码错误
									</c:if>
                                    <c:if test="${param.error == 'usernameEmpty'}">
                                                                                                                                                                用户名不可为空
                                    </c:if>
                                    <c:if test="${param.error == 'username'}">
                                                                                                                                                                用户名不存在
                                    </c:if>                                                                                                                            
                                    <c:if test="${param.error == 'password'}">
                                                                                                                                                                密码错误
                                    </c:if>
                                    <c:if test="${param.errorMsg != null}">
                                ${param.errorMsg}
                                    </c:if>
                            </font></span></td>
                    </tr>
            </c:if>
			<div class="login-div" id="login-div">
				<div class="login-un">
					<span class="icon-wrapper"><i class="icon24-login un"></i></span>
			      	<input type="text" id='j_username' name='j_username' placeholder="用户名可使用Email或手机号登录">
			    </div>
			    <div class="login-pwd">
			    	<span class="icon-wrapper"><i class="icon24-login pwd"></i></span>
			      	<input type="password" id='j_password' name='j_password' placeholder="密&nbsp码">
			    </div>

			    <div class="login-va">
			    	<span class="icon-wrapper"><i class="icon24-login un"></i></span>
				    <input type="text" name="validateCode" id="validateCode" style="width:128px;" placeholder="验&nbsp证&nbsp码">
				    <img src="${ctx}/captcha.do" title="看不清，换一张" width="98" align="absmiddle"   id="captcha" />
				</div>
        	</div>
        	<div>
				<span >验证码请输入上面图片的字符，不区分大小写</span>
				<span>&nbsp</span>
			</div>
			<div class="login-btn-panel">
				<input type="submit" value="" style="border:0px;width:84px;height:30px;background:url(${ctx}/resources/image/login/dl.png);"/>
                <input type="reset" value="" style="border:0px;width:84px;height:30px;background:url(${ctx}/resources/image/login/cz.png);"/>
			</div>

        </div>	
    </form>    
    </body>
</html>
