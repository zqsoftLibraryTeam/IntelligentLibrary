<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户管理页面</title>
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href=<%=path + "/css/index.css"%> rel="stylesheet">
<style>
.nav-tabs>li {
	float: none;
}

.nav-tabs {
	border: none
}

.m_navbar {
	height: 70px;
}

.m_nav li {
	margin-top: 10px;
}

.navbar-header>a {
	position: relative;
}

.navbar-header>a>img {
	top: 0;
	position: absolute;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top m_navbar">
		<div class="container">
			<div class="navbar-header">
				<a href="<%=basePath%>index.jsp" class="navbar-brand"> <img
					src="<%=basePath%>/img/white_logo.jpg" style=""></a>
				<!-- data-toggle="collapse" data-target="#navbar-collapse"  屏幕变小 菜单收缩-->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav navbar-right m_nav">
					<li><a href="<%=basePath%>index.jsp"><span
							class="glyphicon glyphicon-home"></span> 首页</a></li>

					<c:if test="${currentUser==null}">

						<li data-toggle="modal" data-target="#loginDiv"><a><span
								class="glyphicon glyphicon-user"></span> 登入</a></li>
					</c:if>
					<c:if test="${currentUser!=null}">
						<li class="active"><a href=<%=path + "/user/loginout.do"%>><span
								class="glyphicon glyphicon-question-sign"></span> 登出</a></li>
					</c:if>
			</div>

			</ul>

		</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="container">
			<hgroup>
				<h1>
					欢迎您:
					<c:out value="${currentUser.username}" default="游客，请登陆"></c:out>
				</h1>
				<h4>在这里您可以查看您的一部分信息...</h4>
				<h4>
					<c:out value="${message}"></c:out>
					<%
						session.removeAttribute("message");
					%>
				</h4>
			</hgroup>
		</div>
	</div>

	<div id="about">
		<div class="container">
			<div class="row">
				<div class="col-md-3 hidden-sm hidden-xs">
					<div class="list-group">

						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#borrowinfo"
								aria-controls="home" role="tab" data-toggle="tab">借阅信息</a></li>
							<li role="presentation"><a href="#changepwd"
								aria-controls="profile" role="tab" data-toggle="tab">修改密码</a></li>
							<li role="presentation"><a href="#userinfo"
								aria-controls="messages" role="tab" data-toggle="tab">用户信息</a></li>
							<li role="presentation"><a href="#occupyinfo"
								aria-controls="settings" role="tab" data-toggle="tab">占座信息</a></li>
							<li role="presentation"><a href="#recommandinfo"
								aria-controls="settings" role="tab" data-toggle="tab">推荐书籍</a></li>
						</ul>


					</div>
				</div>


				<div class="col-md-7 about" style="padding-top: 20px">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="borrowinfo">

							<div class="panel panel-default">


								<div class="panel-heading">借阅信息如下</div>
								<div class="panel-body">
									<table class="table  table-hover  ">
										<thead>
											<tr>
												<th>借书流水号</th>
												<th>借出用户</th>
												<th>书本名</th>
												<th>借出日期</th>
												<th>归还日期</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="userbook" items="${userborrowinfo}">
												<tr>
													<td><c:out value="${userbook.id}"></c:out></td>
													<td><c:out value="${userbook.ilUser.username}"></c:out>
													<td><c:out value="${userbook.ilBook.bookname}"></c:out>
													<td><c:out value="${userbook.borrowdate}"></c:out>
													<td><c:out value="${userbook.returndate}"></c:out></td>
												</tr>
											</c:forEach>

										</tbody>

									</table>


								</div>

							</div>

						</div>

						<div role="tabpanel" class="tab-pane" id="changepwd">
							<form id="changepass">
								<c:out value="${changepassmsg}"></c:out>
								<div class="form-group">
									<label for="exampleInputEmail1">Old pass</label> <input
										type="password" name="oldpass" class="form-control"
										id="exampleInputEmail1" placeholder="Enter Old password">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">New Pass</label> <input
										type="password" name="newpass" class="form-control"
										id="exampleInputPassword1" placeholder="Enter New Password">
								</div>
								<button type="button" id="passsubmit" class="btn btn-default">提交</button>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane" id="userinfo">
							<table class="table">
								<tr>
									<td>用户ID</td>
									<td><c:out value="${currentUser.userid}"></c:out></td>
								</tr>
								<tr>
									<td>用户名</td>
									<td><c:out value="${currentUser.username}"></c:out></td>
								</tr>
								<tr>
									<td>用户密码</td>
									<td><c:out value="${currentUser.password}"></c:out></td>
								</tr>
								<tr>
									<td>用户性别</td>
									<td><c:if test="${currentUser.sex==0}">
											<c:out value="男"></c:out>
										</c:if> <c:if test="${currentUser.sex==1}">
											<c:out value="女"></c:out>
										</c:if></td>
								</tr>
								<tr>
									<td>用户邮箱</td>
									<td><c:out value="${currentUser.email}"></c:out></td>
								</tr>
								<tr>
									<td>用户名字</td>
									<td><c:out value="${currentUser.name}"></c:out></td>
								</tr>
								<tr>
									<td>用户电话</td>
									<td><c:out value="${currentUser.phone}"></c:out></td>
								</tr>
								<tr>
									<td>用户年龄</td>
									<td><c:out value="${currentUser.age}"></c:out></td>
								</tr>

							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="occupyinfo">
							<div class="panel panel-default">


								<div class="panel-heading">占座信息如下</div>
								<div class="panel-body">
									<table class="table  table-hover  ">
										<thead>
											<tr>
												<th>id号</th>
												<th>位置所在区域</th>
												<th>位置所在桌子</th>
												<th>位置所在角落</th>
												<th>预定时间</th>
												<th>取消占座</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="occupyinfo" items="${occupyinfos}">
												<tr>
													<td><c:out value="${occupyinfo.id}"></c:out></td>
													<td><c:out value="${occupyinfo.seat.desk.floor}"></c:out>
													<td><c:out value="${occupyinfo.seat.desk.desknum}"></c:out>号桌子



													
													<td><c:out value="${occupyinfo.seat.position}"></c:out>
													<td><c:out value="${occupyinfo.ordertime}"></c:out></td>
													<td><a
														href="<%=basePath%>user/reverseorder.do?userid=${currentUser.userid}">取消占座</a>
												</tr>
											</c:forEach>

										</tbody>

									</table>


								</div>

							</div>
						
						</div>
						<div role="tabpanel" class="tab-pane" id="recommandinfo">
						<div class="panel panel-default">


							<div class="panel-heading">推荐图书如下</div>
							<div class="panel-body">
								<table class="table  table-hover  ">
									<thead>
										<tr>
											<th>id号</th>
											<th>书名</th>
											<th>作者</th>
											<th>ISBN</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="recommandinfo" items="${recommandinfos}">
											<tr>
												<td><c:out value="${recommandinfo.book.bookid}"></c:out></td>
												<td><c:out value="${recommandinfo.book.bookname}"></c:out></td>
												<td><c:out value="${recommandinfo.book.author}"></c:out></td>
												<td><c:out value="${recommandinfo.book.isbn}"></c:out></td>
											</tr>
										</c:forEach>

									</tbody>

								</table>


							</div>

						</div>

					</div>
				</div>
					</div>
					
				</div>
				
			</div>



		</div>


	</div>


	</div>



	<!-- 模态框 -->
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
						<form action="<%=basePath%>common/login.do" method="post">
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
</body>
<script src="<%=basePath%>js/jquery-2.1.1.min.js">
	
</script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript" src=<%=path + "/js/userpanel.js"%>></script>
</html>
