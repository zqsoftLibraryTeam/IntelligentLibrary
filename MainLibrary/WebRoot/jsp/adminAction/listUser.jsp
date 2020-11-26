<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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

<meta charset="UTF-8">
<title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="http://www.jeasyui.com/easyui/demo/demo.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.6.min.js"></script>
<script type="text/javascript"
	src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>

</head>

<body>
	<table id="dg" title="My Users" class="easyui-datagrid"
		style="width:100%;height:100%;margin:0 auto;" 
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th	field="id" width="25">编号</th>
				<th field="username" width="50">用户名</th>
				<th field="password" width="50">密码</th>
				<th field="age" width="50">年龄</th>
				<th field="describtion" width="50">描述</th>
				<th field="name" width="50">姓名</th>
				<th field="phone" width="50">电话</th>
				<th field="sex" width="50">性别</th>
				<th field="timelimit" width="50">时限</th>
				<th field="email" width="50">Email</th>
			</tr>
		</thead>
		<c:forEach var="user" items="${users}">
				
			<tr>
				<td><c:out value="${user.id }"/></td>
				<td><c:out value="${user.username}" /></td>
				<td><c:out value="${user.password}" /></td>
				<td><c:out value="${user.age}" /></td>
				<td><c:out value="${user.describtion}" /></td>
				<td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.phone}" /></td>
				<td><c:out value="${user.sex}" /></td>
				<td><c:out value="${user.timelimit}" /></td>
				<td><c:out value="${user.email}" /></td>
			</tr>
		</c:forEach>
	</table>
	<div id="toolbar">
		<a  href="listUsers.do" class="easyui-linkbutton" iconCls="icon-add"
			plain="true"">显示所有用户</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">New User</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editUser()">Edit User</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">Remove User</a>

	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post" novalidate style="margin:0">
			<div
				style="margin-bottom:20px;font-size:14px;border-bottom:1px solid #ccc">User
				Information</div>
			<div style="margin-bottom:10px">
				<input name="username" class="easyui-textbox" required="true"
					label="用户名:" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="password" class="easyui-textbox" required="true"
					label="密码:" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="age" class="easyui-textbox" required="true" label="年龄:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="describtion" class="easyui-textbox" required="true" label="描述:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="name" class="easyui-textbox" required="true" label="姓名:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="phone" class="easyui-textbox" required="true" label="电话:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="sex" class="easyui-textbox" required="true" label="性别:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="timelimit" class="easyui-textbox" required="true" label="时限:"
					style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input name="email" class="easyui-textbox" required="true"
					validType="email" label="Email:" style="width:100%">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width:90px">Cancel</a>
	</div>
	<script type="text/javascript">
		var url;
		

		
		function newUser() {
			$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'New User');
			$('#fm').form('clear');
			url = 'addUser.do';
		}
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('center').dialog('setTitle', 'Edit User');
				$('#fm').form('load', row);
				url = 'editUser.do?id=' + row.id;
			
			}
			
		}
		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function() {
					$('#dlg').dialog('close');
					$('#dg').datagrid('reload'); // reload the user data
					window.open('listUsers.do','_self');
				}
			});
			$('#fm').datagrid('reload'); // reload the user data
		}
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm', 'Are you sure you want to destroy this user?', function(r) {
					if (r) {
						$.post('deleteUser.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload'); 
							
							// reload the user data
								
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
						window.open('listUsers.do','_self')
					}
				});
			}
		}
	</script>
</body>
</html>
