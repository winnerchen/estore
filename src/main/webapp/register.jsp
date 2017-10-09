<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<%@include file="inc/common_head.jsp"%>
	<script type="text/javascript">
	//验证码ajax校验,决定表单是否可以提交
	var code_flag=false;
	var email_flag=false;
	//邮箱唯一性校验,决定表单是否可以提交
	//离焦事件触发时调用该方法
	function checkUniqueEmail(email){
		ajax({
			url:"${path}/userServlet?method=validEmail&email="+email,
			method:"get",
			callback:function(data){
				if(data=="true"){
					username_notice.style.color="green";
					username_notice.innerHTML="√"
					email_flag=true;
					
				}
				else{
					username_notice.style.color="red";
					username_notice.innerHTML="用户已存在×"
					email_flag=false;
				}
				
			}
		});
		
	}
	
	 function check_captcha(code){
		ajax({
			url:"${path}/userServlet?method=checkCode&code="+code,
			method:"get",
			callback:function(data){
				if(data=="true"){
					captcha_notice.style.color="green";
					captcha_notice.innerHTML="√"
					code_flag=true;
				}
				else{
					captcha_notice.style.color="red";
					captcha_notice.innerHTML="×"
					code_flag=false;
				}
				
			}
		})
	}        
	 
	 
	 function validForm(){
		
		 var flag=code_flag&&email_flag&&register();
		 return flag;
	 }
	  
	</script>
</head>
<body>
	<%@include file="inc/header.jsp"%>

	<div class="block block1">
		<div class="blank"></div>
		<div class="usBox">
			<div class="usBox_1">
				<div class="login_tab">
					<ul>
						<li><div style="color:red">${login_error_msg }</div></li>
						<li onclick="location.href='login.jsp';">
							<a href="javascript:;">用户登录</a>
						</li>
						<li class="active">用户注册</li>
					</ul>
				</div>
				<form action="${path}/userServlet?method=save" method="post" name="formUser"
					onsubmit="return validForm();">
					<table width="100%" border="0" align="left" cellpadding="5"
						cellspacing="3">
						<tr>
							<td width="25%" align="right">账号</td>
							<td width="65%"><input name="email" type="text"
								id="username" onblur="if(is_registered(this.value)) {checkUniqueEmail(value);};"
								class="inputBg" /> <span id="username_notice"
								style="color:#FF0000"> *</span></td>
						</tr>
						<tr>
							<td align="right">昵称</td>
							<td><input name="nickname" type="text"
								id="nickname" onblur="check_nickname(this.value);"
								class="inputBg" /> <span id="nickname_notice"
								style="color:#FF0000"> *</span></td>
						</tr>
						<tr>
							<td align="right">密码</td>
							<td><input name="password" type="password" id="password1"
								onblur="check_password(this.value);"
								onkeyup="checkIntensity(this.value)" class="inputBg" />
								<span style="color:#FF0000"
								id="password_notice"> *</span></td>
						</tr>
						<tr>
							<td align="right">密码强度</td>
							<td>
								<table width="145" border="0" cellspacing="0" cellpadding="1">
									<tr align="center">
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_lower">弱</td>
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_middle">中</td>
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_high">强</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="right">确认密码</td>
							<td><input name="confirm_password" type="password" 
								id="conform_password"
								onblur="check_conform_password(this.value);" class="inputBg" />
								<span style="color:#FF0000"
								id="conform_password_notice"> *</span></td>
						</tr>
						<tr>
							<td align="right">验证码</td>
							<td><input type="text" size="8" name="captcha" id="captcha"
								class="inputBg" onblur="check_captcha(this.value);" /> <span style="color:#FF0000"
								id="captcha_notice"> *</span></td>
						</tr>
						<tr>
							<td align="right"></td>
							<td><img src="validatecode.jsp"
								style="vertical-align:middle;cursor:pointer;width:130px;height:35px;margin-top:-2px;"
								onClick="src='validatecode.jsp?'+Math.random()" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><label> <input name="agreement" type="checkbox"
									value="1" checked="checked" /> 我已看过并接受《<a
									href="javascript:;" style="color:blue" target="_blank">用户协议</a>》
							</label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="left">
								<input type="submit" value="" class="us_Submit_reg">
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
				</form>
				<div class="blank"></div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>