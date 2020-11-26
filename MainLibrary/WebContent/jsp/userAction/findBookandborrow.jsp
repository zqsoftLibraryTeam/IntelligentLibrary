<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书查询</title>
	<link rel="stylesheet" href=<%=path+"/css/bootstrap.css"%> />
    <link rel="stylesheet" href=<%=path+"/css/bootstrap.min.css"%> />
	<link rel="stylesheet" href=<%=path+"/css/findBookandborrow.css"%> />
	<link href=<%=path + "/css/index.css"%> rel="stylesheet">
</head>

<body>
	<!-- 导航条开始 -->
		<div class="headnav">
			<!-- logo开始-->
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.jsp"" class="logo-a">
					<img src="../img/white_logo.jpg" class="w3-circle w3-margin-right" style="width:120px">
				</a>
				<ul class="mynav nav .nav-pills" id="nav">
				    <li><a href="./findbook.do">借阅图书</a>
				    	<!--<div class="subNav"></div>-->
				    </li>
				    <li><a href="../user/borrowinfo.do">个人中心</a>
				    	<!--<div class="subNav"></div>-->
				    </li>
				</ul>
			</div>
			<!-- logo结束-->
			<div class="userinfo">
				<p>欢迎你:<c:out value="${currentUser.username}" default="游客，请登陆"></c:out></p>
			</div>
			<div class="login">
					<!-- 返回主界面按钮开始 -->
					<a href=<%=path+"/index.jsp"%>><button type="button" id="sign" class="btn btn-info login" >返回主页面</button></a>
					<!-- 返回主界面按钮结束 -->
				</div>
			<c:if test="${currentUser==null}" var="result">
				<div class="login">
					<!-- 登陆按钮开始 -->
					<li style='list-style: none' data-toggle="modal" data-target="#loginDiv"><a class="btn btn-info login">
							 登入</a></li>
					<!-- 登陆按钮结束 -->
				</div>
			</c:if>
			<c:if test="${currentUser!=null}" var="result">
				<div class="login">
					<!-- 退出按钮开始 -->
					<a href=<%=path+"/user/loginout.do"%>><button type="button" id="login" class="btn btn-info login" >退出</button></a>
					<!-- 退出按钮结束 -->
				</div>
				
			</c:if>
		</div>
		<!-- 导航条结束  -->
		<!-- 主体开始  -->
		<div class="mainbody container">
			<form class="form-horizontal" role="form" action="<%=basePath%>/common/findbook.do">
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
			
			<div class="searchbook">
				<table class="table table-hover">
					<th>书名</th>
					<th>ISBN</th>
					<th>作者</th>
					<th>预定</th>
					<c:forEach var="book" items="${booklist}">
						<tr>
							<td><c:out value="${book.bookname}" /></td>
							<td><c:out value="${book.isbn }"></c:out></td>
							<td><c:out value="${book.author }"></c:out></td>
							<td><a href="<%=basePath%>/user/borrowbookdetail.do?bookid=${book.bookid}">书本详情</a></td>
						</tr>
					</c:forEach>
				</table>
				
			</div>
		</div>
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
		
		<!-- 主体结束  -->
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src=<%=path+"/js/jquery-2.1.1.min.js"%>></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src=<%=path+"/js/bootstrap.min.js"%>></script>
	</body>
</html>
