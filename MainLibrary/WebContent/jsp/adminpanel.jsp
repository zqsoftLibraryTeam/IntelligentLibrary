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
<title>图书馆管理员页面</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.css" />
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/adminpanel.css" />


<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">
	<input type="hidden" id="basePath" value="<%=basePath%>"/>
	<!-- Top container -->
	<div class="w3-container w3-top w3-black w3-large w3-padding"
		style="z-index: 2">
		<button class="w3-btn w3-hide-large w3-padding-0 w3-hover-text-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i>菜单
		</button>
		<span class="w3-right"><img src="../img/white_logo.jpg"
			class="w3-circle w3-margin-right" style="width: 100px"></span>
	</div>

	<!-- Sidenav/menu -->
	<nav class="w3-sidenav w3-collapse w3-white w3-animate-left"
		style="z-index: 3; width: 300px;" id="mySidenav">
		<br>
		<div class="w3-container w3-row">
			<div class="w3-col s4">
				<img src="../img/bookimg/default.png"
					class="w3-circle w3-margin-right" style="width: 46px">
			</div>
			<div class="w3-col s8">
				<span><c:out value="${currentUser.username}" default="游客，请登陆"></c:out></span><br>
				<a href="../user/borrowinfo.do"
					class="w3-hover-none w3-hover-text-green w3-show-inline-block"><i
					class="fa fa-user"></i></a>
			</div>
		</div>
		<hr>
		<div id="nava">
			<a href="#"
				class="w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i> 关闭菜单</a> <a href="#"
				class="w3-padding w3-blue"><i class="fa fa-bank fa-fw"></i>  主页</a>
			<a href="#" class="w3-padding"><i class="fa fa-book fa-fw"></i> 
				图书管理</a> <a href="#" class="w3-padding"><i class="fa fa-users fa-fw"></i> 
				用户管理</a>
		</div>
	</nav>


	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>
	<div id="adddiv">
		<!-- !PAGE CONTENT! -->
		<div class="w3-main amymain"
			style="margin-left: 300px; margin-top: 43px;">

			<!-- Header -->
			<header class="w3-container" style="padding-top: 22px">
				<h5>
					<b><i class="fa fa-dashboard"></i>总览</b>
				</h5>
			</header>

			<div class="w3-row-padding w3-margin-bottom">
				<div class="w3-half">
					<div class="w3-container w3-red w3-padding-16">
						<div class="w3-left">
							<i class="fa fa-book w3-xxxlarge"></i>
						</div>
						<div class="w3-right">
							<h3>52</h3>
						</div>
						<div class="w3-clear"></div>
						<h4>书本数</h4>
					</div>
				</div>
				<div class="w3-half">
					<div class="w3-container w3-blue w3-padding-16">
						<div class="w3-left">
							<i class="fa fa-users w3-xxxlarge"></i>
						</div>
						<div class="w3-right">
							<h3>99</h3>
						</div>
						<div class="w3-clear"></div>
						<h4>用户数</h4>
					</div>
				</div>
			</div>

			<!-- End page content -->
		</div>
		<!--
    	作者：sean1179@sina.com
    	时间：2017-02-24
    	描述：图书展示主体页面
    -->
		<div class="w3-main zmymain"
			style="margin-left: 300px; margin-top: 43px;">

			<!-- Header -->
			<header class="w3-container" style="padding-top: 22px">
				<h5>
					<b><i class="fa fa-dashboard"></i>图书管理</b>
				</h5>
			</header>

			<div class="mainbody container">
				<div class="tab-pane active" id="a1">
					<!-- <h3>管理图书</h3> -->
					<div class="heading">

						<!-- 添加按钮-->
						<button id="btnAddbook" type="button" class="btn  btn-success"
							data-toggle="modal" data-target="#bookaddModal">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
						</button>

						<!--  删除按钮-->
						<button id="btnDelbook" type="button" class="btn  btn-danger"
							data-toggle="modal" data-target="#bookdelModal">
							<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>删除
						</button>

						<!--表格-->
						<div>
							<table id="AddFamily" class="table table-bordered table-striped"
								width="1000px" border="0" cellspacing="0" cellpadding="0"
								style="margin: 0 auto">
								<thead>
									<th class=""></th>
									<th>编号</th>
									<th>书名</th>
									<th>ISBN</th>
									<th>作者</th>
									<th>书架编号</th>
									<th>所属分类</th>
									<th>出版社</th>
									<th>状态</th>
									<th>书本数量</th>
									<th></th>
								</thead>
								<tbody id="bookcontainer">
								</tbody>
							</table>
							<!--
                                	作者：sean
                                	时间：2017-02-24
                                	描述：分页标签
                                -->
							<nav>
								<ul class="pagination pull-right" id="booksplit">
								</ul>
							</nav>
							<form id="uploadForm" enctype="multipart/form-data"></form>
							<!--添加数据模态框-->
							<div class="modal  fade" id="bookaddModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">添加图书</h4>
										</div>
										<div class="modal-body">

											<div class="container-fluid">
												<form class="form-horizontal" id="bookaddform">

													<div class="form-group ">
														<label class="col-sm-2 control-label">书名</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" name="bookname">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">ISBN</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" title=""
																data-toggle="tooltip" name="isbn"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">作者</label>
														<div class="col-sm-6 has-feedback">
															<input type="text" class="form-control" name="author">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">出版社</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" name="press">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">预览图路径</label>
														<div class="col-sm-6  has-feedback">
															<input type="file" name="picture" id="exampleInputFile">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span> <img id="adduploadPreviewImg"
																style="width: 140px;" class="img-rounded">
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">状态</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default "> <input
																type="radio" name="status" value="0" id="option1"
																autocomplete="off">不可借
															</label> <label class="btn btn-default active"> <input
																type="radio" name="status" value="1" id="option2"
																autocomplete="off" checked>可借
															</label>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">书本数量</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" name="booknum">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">RDID设备写入该书本</label>
														<div class="col-sm-6  has-feedback">
															<button onclick="rfidwrite()">写入按钮，如若已经写入请进行添加图书</button>
															<p hidden="true" id="returnresult">hhhhhhhhhhhhh</p>
														</div>
													</div>

												</form>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" type="button"
												id="bookaddsubmit">保存</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--添加数据模态框结束-->

							<!--修改数据模态框-->
							<div class="modal  fade" id="bookchangeModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">修改图书</h4>
										</div>
										<div class="modal-body">

											<div class="container-fluid">
												<form action="" method="get" class="form-horizontal"
													id="bookchangeform">

													<div class="form-group ">
														<label class="col-sm-2 control-label">书本id</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control " readonly
																name="bookid"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>


													<div class="form-group ">
														<label for="" class="col-sm-2 control-label">书名</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" name="bookname">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="id" class="col-sm-2 control-label">ISBN</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" title=""
																data-toggle="tooltip" name="isbn"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="id" class="col-sm-2 control-label">作者</label>
														<div class="col-sm-6 has-feedback">
															<input type="text" class="form-control" name="author">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="id" class="col-sm-2 control-label">出版社</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" name="press">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group ">
														<label class="col-sm-2 control-label">预览图名称</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control " readonly
																name="picture"> <img style="width: 140px;"
																class="img-rounded">
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">修改图片</label>
														<div class="col-sm-6  has-feedback">
															<input type="file" name="picture" id="exampleInputFile">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span> <img
																id="changeuploadPreviewImg" style="width: 140px;"
																class="img-rounded">
														</div>
													</div>

													<div class="form-group">
														<label for="id" class="col-sm-2 control-label">状态</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default "> <input
																type="radio" name="status" value="0" id="option0"
																autocomplete="off">不可借
															</label> <label class="btn btn-default "> <input
																type="radio" name="status" value="1" id="option1"
																autocomplete="off">可借
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">书本数量</label>
														<div class="col-sm-6  has-feedback">
															<input type="text" class="form-control" id=" "
																name="booknum"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">RFID写入书本信息</label>
														<div class="col-sm-6  has-feedback">
															<button onclick="rfidwrite()">提交按钮</button>
															<p hidden="true" id="returnresult">
														</div>
													</div>
												</form>

											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" id="bookeditsubmit"
												type="button">保存</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--修改数据模态框结束-->

							<!--删除模态框-->
							<div class="modal  fade" id="bookdelModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>

										</div>
										<div class="modal-body">
											<!-- container-fluid  流体-->
											<div class="container-fluid">你确定要删除吗</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" id="bookdeletesubmit"
												type="button" data-dismiss="modal">确定</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--删除模态框结束-->
						</div>
						<!-- 表格结束 -->
					</div>

				</div>

			</div>
			<!-- mainbody结束 -->
		</div>
		<!-- End page content 2-->
		<!-- !PAGE CONTENT!3 -->
		<div class="w3-main zmymain"
			style="margin-left: 300px; margin-top: 43px;">

			<!-- Header -->
			<header class="w3-container" style="padding-top: 22px">
				<h5>
					<b><i class="fa fa-dashboard"></i>用户管理</b>
				</h5>
			</header>

			<div class="mainbody container">

				<div class="tab-pane active" id="a1">
					<h3>用户</h3>
					<div class="heading">

						<!-- 添加按钮-->
						<button id="btnAdduser" type="button" class="btn  btn-success"
							data-toggle="modal" data-target="#useraddModal">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
						</button>

						<!--  修改按钮-->


						<!--  删除按钮-->
						<button id="btnDeluser" type="button" class="btn  btn-danger"
							data-toggle="modal" data-target="#userdelModal">
							<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>删除
						</button>

						<!--表格-->
						<div>
							<table id="AddFamily" class="table table-bordered table-striped"
								width="1000px" border="0" cellspacing="0" cellpadding="0"
								style="margin: 0 auto">
								<thead>
									<th class=""></th>
									<th>编号</th>
									<th>用户名</th>
									<th>性别</th>
									<th>描述</th>
									<th>电话</th>
									<th>名字</th>
									<th>借书期限</th>
									<th>是否为管理员</th>
									<th></th>
								</thead>
								<tbody id="usercontainer"></tbody>
							</table>
							<nav>
								<ul class="pagination pull-right" id="usersplit"></ul>
							</nav>

							<!--添加数据模态框-->
							<div class="modal  fade" id="useraddModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">添加用户</h4>
										</div>
										<div class="modal-body">

											<div class="container-fluid">
												<form action="" method="post" class="form-horizontal"
													id="useraddform">

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">用户名</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="username">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">密码</label>
														<div class="col-sm-6">
															<input type="password" class="form-control"
																name="password"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">

														<label for="" class="col-sm-2 control-label">性别</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default "> <input
																type="radio" name="sex" value="0" id="user1"
																autocomplete="off">女
															</label> <label class="btn btn-default active"> <input
																type="radio" name="sex" value="1" id="user2"
																autocomplete="off" checked>男
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">描述</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																name="describtion"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">邮箱</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="email">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">手机号</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="phone">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">名字</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="name">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">借书时间</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="timelimit">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">年龄</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="age">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">是否为管理员</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default active"> <input
																type="radio" name="identify" value="0" id="identify1"
																autocomplete="off" checked>否
															</label> <label class="btn btn-default "> <input
																type="radio" name="identify" value="1" id="identify2"
																autocomplete="off">是
															</label>
														</div>
													</div>
												</form>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" id="useraddsubmit"
												type="button">保存</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--添加数据模态框结束-->

							<!--修改数据模态框-->
							<div class="modal  fade" id="userchangeModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">修改用户</h4>
										</div>
										<div class="modal-body">
											<div class="container-fluid">
												<form action="" method="post" class="form-horizontal"
													id="userchangeform">
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">用户id</label>
														<div class="col-sm-6">
															<input type="text" readonly class="form-control"
																name="userid"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">用户名</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="username">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">密码</label>
														<div class="col-sm-6">
															<input type="password" class="form-control"
																name="password"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">性别</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default "> <input
																type="radio" name="sex" value="0" id="user1"
																autocomplete="off">女
															</label> <label class="btn btn-default active"> <input
																type="radio" name="sex" value="1" id="user2"
																autocomplete="off" checked>男
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">描述</label>
														<div class="col-sm-6">
															<input type="text" class="form-control"
																name="describtion"> <span
																class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">邮箱</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="email">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">手机号</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="phone">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">名字</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="name">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">借书时间</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="timelimit">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="" class="col-sm-2 control-label">年龄</label>
														<div class="col-sm-6">
															<input type="text" class="form-control" name="age">
															<span class="glyphicon  form-control-feedback"
																aria-hidden="true"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="" class="col-sm-2 control-label">是否为管理员</label>
														<div class="btn-group col-sm-6" data-toggle="buttons">
															<label class="btn btn-default active"> <input
																type="radio" name="identify" value="0" id="identify1"
																autocomplete="off" checked>否
															</label> <label class="btn btn-default "> <input
																type="radio" name="identify" value="1" id="identify2"
																autocomplete="off">是
															</label>
														</div>
													</div>
												</form>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" id="usereditsubmit">保存</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--修改数据模态框结束-->

							<!--删除模态框-->
							<div class="modal  fade" id="userdelModal" tabindex="-1">
								<div class="modal-dialog ">
									<div class="modal-content ">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>

										</div>
										<div class="modal-body">
											<!-- container-fluid  流体-->
											<div class="container-fluid">你确定要删除此用户吗</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-success" id="userdeletesubmit"
												type="button" data-dismiss="modal">确定</button>
											<button class="btn btn-default" type="button"
												data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
							<!--删除模态框结束-->
						</div>
						<!-- 表格结束 -->
					</div>

				</div>
			</div>

			<!-- End page content3 -->
		</div>
	</div>


</body>
<script type="text/javascript" src="../js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/adminpanel.js"></script>
<script type="text/javascript" src="../js/eventUtil.js"></script>
<script>
	// Get the Sidenav
	var mySidenav = document.getElementById("mySidenav");

	// Get the DIV with overlay effect
	var overlayBg = document.getElementById("myOverlay");

	// Toggle between showing and hiding the sidenav, and add overlay effect
	function w3_open() {
		if (mySidenav.style.display === 'block') {
			mySidenav.style.display = 'none';
			overlayBg.style.display = "none";
		} else {
			mySidenav.style.display = 'block';
			overlayBg.style.display = "block";
		}
	}

	// Close the sidenav with the close button
	function w3_close() {
		mySidenav.style.display = "none";
		overlayBg.style.display = "none";
	}

	function rfidwrite() {
		$.ajax({
			type : 'GET',
			url : './writeintoRFID.do',
			data : null,
			success : function(data) {
				$("#returnresult").hidden=false;
			},
			dataType : 'json'
		});
	}
</script>


</html>
