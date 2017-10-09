<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="Generator" content="ECSHOP v2.7.3" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<title>提交订单</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix"><div class="AreaR">
	<div class="block box"><div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="index.jsp">首页</a><code>&gt;</code>购物流程
		</div>
	</div><div class="blank"></div><div class="box"><div class="box_1">
	<div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
	<form action="${path}/orderServlet?method=generateOrder" method="post">
		<!---------收货人信息开始---------->
		<h5><span>收货人信息</span></h5>
		<table width="100%" align="center" border="0" cellpadding="5"
			cellspacing="1" bgcolor="#dddddd">
			<tr>
				<td bgcolor="#ffffff" align="right" width="120px">区域信息：</td>
				<td bgcolor="#ffffff">
					<!-- 省 -->
					<select id="province" onchange="load(id,city);  " name="province">
						<option value="none" >-- 请选择省 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					<!-- 市 -->
					<select id="city" onchange="load(id,district);" name="city">
						<option value="none" >-- 请选择市 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					<!-- 县(区) -->
					<select id="district" name="district">
						<option value="none">-- 请选择县(区) --</option>
					</select>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">详细地址：</td>
				<td bgcolor="#ffffff">
					<input style="width:347px;" id="detailAddress" name="detailAddress"/>
				</td>
			</tr>
		
			<tr>
				<td bgcolor="#ffffff" align="right">收货人姓名：</td>
				<td bgcolor="#ffffff"><input id="receiver" name="receiver"/></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">联系电话：</td>
				<td bgcolor="#ffffff"><input id="telephone" name="telephone"/></td>
			</tr>
		</table>
		<!---------收货人信息结束---------->
		
		<!----------商品列表开始----------->
		<div class="blank"></div>
		<h5><span>商品列表</span></h5>
		<table width="100%" border="0" cellpadding="5" cellspacing="1"
			bgcolor="#dddddd">
			<c:if test="${empty cartList}">
				您的订单空空如也...
			</c:if>
			
			<c:if test="${not empty cartList}">
			<c:set var="totalPrice" value="0.0"></c:set>
			<tr>
				
				<th width="30%" align="center">商品图片</th>
				<th width="22%" align="center">商品名称</th>
				<th width="22%" align="center">商品价格</th>
				<th width="15%" align="center">购买数量</th>
				<th align="center">小计</th>
			</tr>
				<c:forEach items="${cartList}" var="item">
			<tr>
				<td>
					<a href="${path}/goodsServlet?method=queryGoodsInfo&gid=${item.id}" class="f6"><img src="${path}${item.imgUrl}"></a>
				</td>
				<td>${item.name}</td>
				<td>${item.estoreprice }元</td>
				<td align="center">${item.buynum }</td>
				<td>${item.estoreprice*item.buynum}元</td>
				<c:set var="totalPrice" value="${totalPrice+item.estoreprice*item.buynum}"></c:set>
			</tr>
				
				</c:forEach>
			</c:if>
		
			<tr>
				<td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
					商品总价&nbsp;<font color="red">&yen;${totalPrice}</font>元
					<input type="submit" value="提交订单" class="btn" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="totalPrice" value="${totalPrice}">
		<!----------商品列表结束----------->
	</form>
	</div></div></div></div></div>
	<%@include file="inc/footer.jsp"%>
	<script type="text/javascript">
		load(0,province);
		
		function load(pid,region){
			var id;
			if(pid==0){
				id = 0;
			}else{
				id = document.getElementById(pid).selectedOptions[0].id
			}
			region.length = 1; // 清空目标下拉框 (目标即下一级的区域 )
			district.length = 1; // 同时清空县数据
			if (pid=="none")return;

			ajax({
				url:"${path}/loadCityServlet?method=load&pid="+id,
				method:"get",
				callback:function(result){
					var data=JSON.parse(result);
					
					
					for (var i=0;i<data.length;i++){
					
						var opt =document.createElement("option");
						opt.id=data[i].id;
						opt.innerHTML=data[i].name;
						opt.value=data[i].name;
						region.appendChild(opt);
					}
				}
			});
		}
		
	</script>
</body>
</html>