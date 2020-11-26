<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>
<script src="js/jquery-2.1.1.min.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>
	<table border="1">
		<th>用户名</th>
		<th>密码</th>
		<th>修改</th>
		<th>删除</th>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.username}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><a href="getPersonInfo.do?id=${user.id}">修改</a></td>
				<td><a href="deleteUser.do?id=${user.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<table border="1">
		<th>书名</th>
		<th>ISBN</th>
		<th>作者</th>
		<th>修改</th>
		<th>删除</th>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.bookname}" /></td>
				<td><c:out value="${book.isbn }"></c:out></td>
				<td><c:out value="${book.author }"></c:out></td>
				<td><a href="getBookInfo.do?id=${book.bookid}">修改</a></td>
				<td><a href="deleteBook.do?id=${book.bookid}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
