<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>图书详情</title>
        <link rel="stylesheet" href=<%=path+"/css/bookdetail.css"%> />
        <link rel="stylesheet" href=<%=path+"/css/bookdetail.css"%> />
        <link rel="stylesheet" href=<%=path+"/css/bootstrap.min.css"%> />
    </head>
    <body>
    	<!-- 导航条开始 -->
		<div class="headnav">
			<!-- logo开始-->
			<div class="logo">
				<a href="../index.jsp" class="logo-a">
					<p>LOGO</p>
				</a>
				<ul class="mynav nav .nav-pills" id="nav">
				    <li><a href="./findbook.do">借阅图书</a>
				    </li>
				    <li><a href="./borrowinfo.do">个人中心</a>
				    </li>
				    <li><a href="#">XXXX</a>
				    </li>
				    <li><a href="#">一级菜单四</a>
				    </li>
				</ul>
			</div>
			<!-- logo结束-->
			<div class="userinfo">
				<p>欢迎你:<c:out value="${currentUser.username}" default="游客，请登陆"></c:out></p>
			</div>
			<c:if test="${currentUser==null}" var="result">
				<div class="login">
					<!-- 登陆按钮开始 -->
					<a href=<%=path+"/jsp/userAction/userAction_login.jsp"%>><button type="button"  id="login" class="btn btn-info login" data-toggle="modal" data-target=".bs-example-modal-sm">登陆</button></a>
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
		<!--页面主体开始-->
		<div class="mainbody container">
			<div class="main-head container">
				<div class="main-head-img">
					<img src="<%=path%>/img/bookimg/${book.picture}" alt="shuben" class="img-rounded">
				</div>
				<div class="panel panel-default main-head-panel">
				  <div class="panel-heading">
				    <h3 class="panel-title">书本详情</h3>
				  </div>
				  <div class="panel-body">
				    <table class="table table-hover">
						<tr><td>书名</td><td><c:out value="${book.bookname}"></c:out></td></tr>
						<tr><td>作者</td><td><c:out value="${book.author}"></c:out></td></tr>
						<tr><td>出版社</td><td><c:out value="${book.press}"></c:out></td></tr>
						<tr><td>状态</td><td><c:out value="${book.status}"></c:out></td></tr>
						<tr><td>书架位置</td><td><c:out value="${book.ilBookshell.loc}"></c:out></td></tr>
						<tr><td>书本在库数量</td><td><c:out value="${book.booknum}"></c:out></td></tr>
						<tr><td>书本类型</td><td><c:out value="${book.ilBooksort.sortname}"></c:out></td></tr>
						<tr><td>图书标准号</td><td><c:out value="${book.isbn}"></c:out></td></tr>
						<tr><td>预定图书</td>
						<td>
							<c:if test="${book.booknum > bookborrownum}">
								<a href="<%=path%>/user/borrowBook.do?bookid=${book.bookid}"><button type="button"class="btn btn-info">预定图书</button></a>
							</c:if>
							<c:if test="${book.booknum <= bookborrownum}">
								<a href="#"><button type="button" disabled class="btn btn-info">无法预定图书</button></a>
							</c:if>
						</td></tr>
					</table>
				  </div>
				</div>
			</div>
			<div class="main-body">
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">借阅情况</h3>
				  </div>
				  <div class="panel-body">
				    <table class="table">
						<tr><td>借书流水号</td><td>借出用户</td><td>借出日期</td><td>归还日期</td></tr>
						<c:forEach var="bookdetail" items="${bookdetail}">
							<tr>
								<td><c:out value="${bookdetail.id}"></c:out></td>
								<td><c:out value="${bookdetail.ilUser.username}"></c:out>
								<td><c:out value="${bookdetail.borrowdate}"></c:out>
								<td><c:out value="${bookdetail.returndate}"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				  </div>
				</div>
			</div>
		</div>
		
		
		
		<!--页面主体结束-->
 	</body>
</html>