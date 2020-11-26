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
<script type="text/javascript">
	window.navigate("/booklist.do");
</script>

</head>

<body>
 <a href="booklist.do">显示所有图书</a>
	<table border="1">
		<th>书名</th>
		<th>isbn</th>
		<th>修改</th>
		<th>删除</th>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.bookname}" /></td>
				<td><c:out value="${book.isbn}" /></td>
				<td><a href="getBookInfo.do?id=${book.bookid}">修改</a></td>
				<td><a href="deleteBook.do?id=${book.bookid}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
