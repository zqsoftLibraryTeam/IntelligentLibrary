<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href=<%=path + "/css/bootstrap.min.css"%> rel="stylesheet">
<link href=<%=path + "/css/index.css"%> rel="stylesheet">
<title>闽江学院智能图书馆</title>
</head>
<body>
	<!-- navbar-fixed-top 固定在顶部-->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="#" class="navbar-brand">图书管理系统</a>
				<!-- data-toggle="collapse" data-target="#navbar-collapse"  屏幕变小 菜单收缩-->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>

				</button>
			</div>

			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="#"><span
							class="glyphicon glyphicon-home"></span> 首页</a></li>
					<li><a href="http://lib.mju.edu.cn/sub_service/search.html"><span class="glyphicon glyphicon-list"></span>
							读者指南</a></li>
					<li><a href="./common/findbook.do"><span
							class="glyphicon glyphicon-search"></span> 书刊检索</a></li>


					<li><a href="<%=basePath%>jsp/orderseatAction/main.jsp"><span class="glyphicon glyphicon-book"></span>
							图书馆占座</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-pencil"></span>
							书刊评价</a></li>
					<li><a href="./admin/adminpanel.do"><span class="glyphicon glyphicon-repeat"></span>
							图书馆管理</a></li>
					<c:if test="${currentUser==null}" var="result">
						<li data-toggle="modal" data-target="#loginDiv"><a><span
								class="glyphicon glyphicon-user"></span> 登入</a></li>
					</c:if>
					<c:if test="${currentUser!=null}" var="result">
						<li><a href=<%=path + "/user/borrowinfo.do"%>><span
								class="glyphicon glyphicon-user"></span>我的图书馆</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="item active">
				<img src=<%=path + "/img/bg1.jpg"%> alt="1">
				<div class="carousel-caption">
					<h3></h3>
					<p></p>
				</div>
			</div>
			<div class="item">
				<img src=<%=path + "/img/bg2.jpg"%> alt="2">
			</div>
			<div class="item">
				<img src=<%=path + "/img/bg3.jpg"%> alt="3">

			</div>
		</div>
		<a href="#myCarousel" data-slide="prev" class="carousel-control left">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a href="#myCarousel" data-slide="next"
			class="carousel-control right"><span
			class="glyphicon glyphicon-chevron-right"></span></a>
	</div>

	<div class="search-box">
		<div class="container">
			<form class="form-horizontal" role="form" action="./common/findbook.do" >
			  <div class="form-group">
			    <label class="col-sm-1 control-label">根据</label>
			    <div class="col-sm-3">
			    	<select class="form-control " name="bywhat">
						<option value="bookname">书名</option>
						<option value="isbn">isbn</option>
						<option value="author">作者</option>
					</select>
			    </div>
			    <label class="col-sm-1 control-label">查询</label>
			    <div class="col-sm-5">
			    	<input type="text" class="form-control" name="thevalue" />
			    </div>
			    <button type="submit" class="btn btn-default">提交</button>
			  </div>
			</form>

		</div>

	</div>
	<!--搜索框-->
	<div class="tab1">
		<div class="container">
			<h2 class="tab-h2">『新书上架』</h2>
			<p class="tab-p">给您推荐近期新上架的图书!</p>
			<div class="row " id="newBookContainer">
			</div>
			<div class="pull-right">
				<a href="#" class="btn btn-primary ">更多新书</a>
			</div>
		</div>
	</div>
	<!-- 新书推荐-->
	<div id="topBookContainer"></div>	

	<div class="tab2">
		<div class="container">
			<div class="pull-right">
				<a href="#" class="btn btn-primary ">更多图书</a>
			</div>
		</div>
	</div>


	<footer id="footer">
		<div class="container">
			<p>版权信息|版权信息|版权信息</p>
			<p>我是描述版权信息的信息我是描述版权信息的信息我是描述版权信息的信息</p>
		</div>
	</footer>

	<div class="modal fade" id="loginDiv" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog logBox">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户登入</h4>
				</div>
				<div class="modal-body ">
					<div class="container">
						<form action="common/login.do" method="post">
							<div>
								<span class="glyphicon glyphicon-user"> 账号： </span> <input
									type="text" class="form-control logInput" name="username">
							</div>
							<div style="margin: 20px auto">
								<span class="glyphicon glyphicon-lock"> 密码： </span> <input
									type="password" class="form-control  logInput" name="password">
							</div>

							<input type="submit" class="btn btn-primary " value="登入">



						</form>
					</div>

				</div>

			</div>
		</div>
	</div>
	<script src=<%=path + "/js/jquery-2.1.1.min.js"%>></script>
	<script src=<%=path + "/js/bootstrap.min.js"%>></script>
	<script src=<%=path + "/js/index.js"%>></script>
	<script>
	</script>
</body>
</html>