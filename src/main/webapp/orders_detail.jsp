<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a>
					<code>&gt;</code>
					用户中心
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<c:if test="${empty order }">
					<label>您尚未有订单</label>
					</c:if>
					
					<c:if test="${not empty order }">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5>
							<span>订单状态</span>
						</h5>
						<table width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<td width="15%" align="right">订单编号：</td>
								<td align="left">${ order.id}</td>
							</tr>
							<tr>
								<td width="15%" align="right">订单状态：</td>
								<td align="left">
									<c:if test="${order.status==0 }">
									<font color="red">未支付</font>
									</c:if>
									<c:if test="${order.status==1 }">
									<font color="green">已支付</font>
									</c:if>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">下单时间：</td>
								<td align="left">${order.createTime}</td>
							</tr>
							<tr>
								<td align="right">收货人信息：</td>
								<td align="left">${order.address}</td>
							</tr>
						</table>
						<div class="blank"></div>
						<h5><span>商品列表</span></h5>
						<table width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<th width="22%" align="center">商品名称</th>
								<th width="29%" align="center">市场价格</th>
								<th width="26%" align="center">商品价格</th>
								<th width="10%" align="center">购买数量</th>
								<th width="20%" align="center">小计</th>
							</tr>
								<c:set var="totalPrice" value="0.0"></c:set>
								<c:forEach items="${order.orderItems }" var="item">
									<tr>
									
										<td>
											<a href="javascript:;" class="f6">${item.goodsName}</a>
										</td>
										<td>${item.marketPrice}元</td>
										<td>${item.estorePrice}元</td>
										<td align="center">${item.buyNum }</td>
										<td>${item.estorePrice*item.buyNum}元</td>
									</tr>
									<c:set var="totalPrice" value="${totalPrice+ item.estorePrice*item.buyNum}"></c:set>
								</c:forEach>
									<tr>
										<td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
											商品总价&nbsp;<font color="red">&yen;${totalPrice}</font>元
											<a href="${path}/pay.jsp?orderid=${order.id}&money=0.01">
											<c:if test="${order.status eq 0 }">
											<input value="确认支付" type="button" class="btn" />
											</c:if>
											
											</a>
										</td>
									</tr>
						</table>
					</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>