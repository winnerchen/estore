<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的购物车</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript">
 function deleteGoodByGid(gid){
	 if (window.confirm("您确认删除该商品吗?")){
		 window.location.href="${path}/cartServlet?method=deleteGood&gid="+gid;
	 }
 }
 
 
 function changeGoodsNum(gid,num){
	 if(num==""){
   	  alert("请输入数值");
   	  return ;
     }
     //  全局函数   parseInt/float 
    var n =  parseInt(num);// 
     if(num!=n){
   	  // 字符/小数
   	  alert("请输入数值类型");
   	  return ;
     }
     if(n<=0){
   	  // 负数和0
   	  alert("请输入正整数");
   	  return ;
     }
     if(n>100){
   	  // 负数和0
   	  document.getElementById("chanagenum"+goodsid).value=100;
   	  num = 100;
   	 //  return ;
     }

	ajax({
		url:"${path}/cartServlet?method=updateCartNum&gid="+gid+"&num="+num,
		method:"get",
		callback:function(data){
			var obj=JSON.parse(data);
			//alert(obj);
			totalCount=0;
			totalMarketCount=0;
			for ( var i=0; i<obj.length;i++){
				
				//alert(obj[i].id);
				document.getElementById("subCount"+obj[i].id).innerHTML=obj[i].estoreprice*obj[i].buynum;
				
				
				totalCount=totalCount+obj[i].estoreprice*obj[i].buynum;
				
				
				
				totalMarketCount=totalMarketCount+obj[i].marketPrice*obj[i].buynum;
			}
			document.getElementById("totalCount").innerHTML=totalCount;
			document.getElementById("totalSave").innerHTML=totalMarketCount-totalCount;
			
			
			
			
		}
	});
 }
</script>


</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block table">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a><code>&gt;</code>我的购物车
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5><span>我的购物车</span></h5>
						<table width="100%" align="center" border="0" cellpadding="5"
							cellspacing="1" bgcolor="#dddddd">
							
							<c:if test="${empty cartList }">
								<font size="4">购物车空空如也...</font>
							</c:if>
							
							<c:set var="estorePriceCount" value="0.0"></c:set>
							<c:set var="marketPriceCount" value="0.0"></c:set>
							
							
							<c:if test="${not empty cartList }">
							<tr>
								<th bgcolor="#ffffff">商品名称</th>
								<th bgcolor="#ffffff">市场价</th>
								<th bgcolor="#ffffff">本店价</th>
								<th bgcolor="#ffffff">购买数量</th>
								<th bgcolor="#ffffff">小计</th>
								<th bgcolor="#ffffff" width="160px">操作</th>
							</tr>
							<c:forEach items="${cartList}" var="cartItem">
							<tr>
								<td bgcolor="#ffffff" align="center" style="width:300px;">
									<!-- 商品图片 -->
									<a href="${path}/goodsServlet?method=queryGoodsInfo&gid=${cartItem.id}" target="_blank">
										<img style="width:80px; height:80px;"
										src="${path}${cartItem.imgUrl}"
										border="0" title="${cartItem.name}" />
									</a><br />
									<!-- 商品名称 -->
									<a href="javascript:;" target="_blank" class="f6">${cartItem.name}</a>
								</td>
								<td align="center" bgcolor="#ffffff">${cartItem.marketPrice}元</td>
								<td align="center" bgcolor="#ffffff">${cartItem.estoreprice}元</td>
								<td align="center" bgcolor="#ffffff">
									<input value="${cartItem.buynum }" size="4" class="inputBg" style="text-align:center;" 
									id="${cartItem.id }"
									onblur="changeGoodsNum(${cartItem.id},this.value)" />
								</td>
								<td align="center" bgcolor="#ffffff" id="subCount${cartItem.id }">${cartItem.estoreprice*cartItem.buynum}元   </td>
								<td align="center" bgcolor="#ffffff">
									<a href="javascript:void(0);" class="f6" onclick="deleteGoodByGid(${cartItem.id})">删除</a>
								</td>
								<c:set var="marketPriceCount" value="${marketPriceCount+cartItem.marketPrice*cartItem.buynum }"></c:set>
								<c:set var="estorePriceCount" value="${estorePriceCount+cartItem.estoreprice*cartItem.buynum }"></c:set>
							</tr>
							
							</c:forEach>
							
							
							<tr>
								<td colspan="6" style="text-align:right;padding-right:10px;font-size:25px;">
									购物金额小计&nbsp;<font color="red" id="totalCount">${estorePriceCount}</font>元，
									共为您节省了&nbsp;<font color="red" id= "totalSave">${marketPriceCount-estorePriceCount}</font>元
									<a href="${path}/orderServlet?method=queryCartInfo"><input value="去结算" type="button" class="btn" /></a>
								</td>
							</tr>
							
							</c:if>
						
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<div class="blank5"></div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>
