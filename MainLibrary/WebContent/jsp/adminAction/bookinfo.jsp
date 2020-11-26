<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bookinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <form action="editBook.do" method="post">
   			<input type="hidden" value="${bookInfo.bookid}" name="bookid" />
   			书名:<input type="text" name="bookname" value="${bookInfo.bookname}"/><br>
   			ISBN:<input type="text" name="isbn" value="${bookInfo.isbn}"/><br>
   			作者:<input type="text" name="author" value="${bookInfo.author}"/>
   			<input type="submit" value="提交"/>
   		</form>
  </body>
</html>
