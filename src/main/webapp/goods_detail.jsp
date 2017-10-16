<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品详情</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript" src="themes/ecmoban_jumei/js/action.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/mzp-packed-me.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/good_detail.js"></script>
<script type="text/javascript">
	function addToCart(){
	    if(${empty loginUser}){
            alert("请先登录然后再购物。");
            location.href = "${path}/login.jsp";
        }


		var value = document.getElementById("buynum").value;
		var num=parseInt(value);
		if(num!=value){
			alert("请输入整数");
			return;
		}
		if(num<1){
			alert("请输入大于等于1的整数");
			return;
		}
		if(num>50){
			document.getElementById("buynum").value=50;
		}
		
		location.href="${path}/cartServlet?method=addToCart&gid=${current_good.id}&buynum="+num;
	}
	function checkFormat(value) {
        var buf=parseInt(value);
        if(buf!=value||value<1){
            document.getElementById("buynum").value=1;
        }
        if(buf>100) {
            document.getElementById("buynum").value=100;
        }
    }
</script>

</head>
<body>
<%@ include file="inc/header.jsp"%>
<div class="block clearfix">
<div class="AreaR" style="width:950px;">
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href=".">首页</a>
			<code>&gt;</code>
			商品详情
		</div>
	</div>
	<div class="blank"></div>
	<!-- 商品信息开始 -->
	<div id="goodsInfo" class="clearfix">
		<!-- 商品图片信息开始 -->
		<div class="imgInfo">
			<!-- 当前显示的大图 -->
			<a href="#"
				id="zoom1" class="MagicZoom MagicThumb">
				<img src="${path}${current_good.imgUrl}"
				width="360px;" height="360px" />
			</a>
			<!-- 下方的图片列表 -->
			<div class="picture" id="imglist">
				<a href="#" rel="zoom1" 
					rev="#">
					<img src="${path}${current_good.imgUrl}" class="onbg" />
				</a>
				
			</div>
			<script type="text/javascript">mypicBg();</script>
		</div>
		<!-- 商品文字信息 -->
		<div class="textInfo">
			<h1 class="clearfix">${current_good.name }</h1>
			<ul class="ul2 clearfix">
				<li class="clearfix">
					<dd>
						<strong>市场售价：</strong>
						<font class="market">${current_good.marketPrice }元</font>
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>本店售价：</strong>
						<font class="shop">${current_good.estoreprice }元</font>
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>商品库存：</strong> ${current_good.num }件
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>商品分类：</strong> ${current_good.category }
					</dd>
				</li>
				<li class="clearfix" style="width:100%;">
					<dd>
						<strong>商品描述：</strong>
						<div style="text-indent: 2em;">
						${current_good.description }
						</div>
					</dd>
				</li>
			</ul><br/>
			<ul class="bnt_ul">
				<li class="clearfix">
					<dd>
						<strong>购买此商品可使用：</strong><font class="f4">${current_good.estoreprice }积分</font>
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>购买数量：</strong>
						<input name="buynum" id="buynum" size="4" value="1" onblur="checkFormat(value)"/>
					</dd>
				</li>
				<li class="padd">
					<a href="javascript:void(0);" onclick = "addToCart()">
						<img src="themes/ecmoban_jumei/images/goumai2.gif" />
					</a>
				</li>
			</ul>
		</div>
	</div>

</div>
</div>
<%@include file="inc/footer.jsp"%>
</body>
</html>