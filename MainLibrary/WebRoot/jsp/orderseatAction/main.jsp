<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
</head>
<body>
	<div class="gird2">
		<h1 class="north">N(北)</h1>
		<h1 class="west">W(西)</h1>
		<h1 class="south">S(南)</h1>
		<h1 class="east">E(东)</h1>
		<div class="area_one area">
			<a href="<%=basePath%>user/detailselect.do?zoneid=1"></a> <span class="fill"></span>
		</div>
		<div class="area_two area">
			<span class="crowd"></span>
		</div>
		<div class="area_three area">
			<span class="leisure"></span>
		</div>
		<div class="area_four area">
			<span class="leisure"></span>
		</div>
		<ul class="guide2">
			<li class="fill"><span></span>爆满</li>
			<li class="crowd"><span></span>拥挤</li>
			<li class="leisure"><span></span>空闲</li>

		</ul>
	</div>
</body>
</html>
