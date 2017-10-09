<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选购中心</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="index.jsp">首页</a>
			<code>&gt;</code>
			商品列表
		</div>
	</div>
	<div class="blank"></div>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="box">
				<div class="box_1">
					<h3>
						<span>商品列表</span>
					</h3>
					<!-- 商品列表开始 -->
					<div class="clearfix goodsBox" style="border:none; ">
						<!-- 第1个商品 -->
						<c:if test="${empty goodsList }">
						<label>暂无商品</label>
						</c:if>
						<c:if test="${not empty goodsList }">
						<c:forEach items="${goodsList }" var="good">
							<div class="goodsItem"  style="padding: 10px 4px 15px 1px;display: inline;">
							<a href="${path}/goodsServlet?method=queryGoodsInfo&gid=${good.id}">
						
								<img src="${path}${good.imgUrl}"
								alt="${good.name }" class="goodsimg" />
							</a><br />
							<p style=" height:20px; overflow:hidden;">
								<a href="${path}/goodsServlet?method=queryGoodsInfo&gid=${good.id}" title="">${good.name}</a>
							</p>
							市场价：<font class="market">${good.marketPrice }元</font><br /> 本店价：<font class="f1">${good.estoreprice }元
							</font>
							</div>
						</c:forEach>
						</c:if>
						
						
					</div>
					<!-- 商品列表结束 -->
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
	<script type="text/javascript">
		window.onload = function() {
			fixpng();
		}
		
	</script>
</body>
</html>