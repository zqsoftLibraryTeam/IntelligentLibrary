<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>选取座位</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/style.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
	window.onload=function(){
		var chair=document.getElementsByClassName('chair');
		var zoneid = $("#zoneid").val();
		//copy class operater
		function hasClass(elem, cls) {
		  cls = cls || '';
		  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
		  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
		}
		 
		function addClass(elem, cls) {
		  if (!hasClass(elem, cls)) {
		    elem.className = elem.className == '' ? cls : elem.className + ' ' + cls;
		  }
		}
		function removeClass(elem, cls) {
		  if (hasClass(elem, cls)) {
		    var newClass = ' ' + elem.className.replace(/[\t\r\n]/g, '') + ' ';
		    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
		      newClass = newClass.replace(' ' + cls + ' ', ' ');
		    };
		    elem.className = newClass.replace(/^\s+|\s+$/g, '');
		  }
		}
		function init(elem,cls){
			for (var i=0,len=elem.length;i<len;i++) {
				removeClass(elem[i],cls); 
			}
		}
		//编码
		function encodeU(data) {   
		    var str='';
		    for (var i in data) {
		        str+=encodeURIComponent(i)+'='+encodeURIComponent(data[i])+'&';
		    }
		    return str.substr(0,str.length-1);
		}
		
		
		
		function sentdata(chairindex){
			//判断桌椅位置 
			var tempchairindex=chairindex;
			 deskindex=parseInt(chairindex/4)+1;
			var temp=((chairindex+1)/4).toString().substr(1)||'0';
			switch (temp){
				case '.25':
					chairindex='1';
					break;
				case '.5':
					chairindex='2';
					break;
				case '0':
					chairindex='4';
					break;
				case '.75':
					chairindex='3';
					break;
				default: 
					chairindex='error';
					break;
			}

		
		
	    if($('#cuid').val()===''){
	        alert('请登录');
	        chair[tempchairindex].classList.remove('selected');
	        chair[tempchairindex].classList.add('allow');
	        }
	    else{
			$.ajax({
				  url: "<%=basePath%>user/orderseat.do",
						data : {
							seatid : chairindex,
							deskid : deskindex,
							userid : $('#cuid').val(),
							zoneid : zoneid
						},
						success : function(data) {
							if(data==="预定失败"){
							 chair[tempchairindex].classList.remove('selected');
						     chair[tempchairindex].classList.add('allow');
						     alert(data+" 可能的原因是您已经预定过了");
						     location.href("<%=basePath%>index.jsp");
							}
							else{
							 alert(data);
							 location.href("<%=basePath%>index.jsp");
							}
						}

					});
				}
			

			}

			//事件绑定
			for (var i = 0, len = chair.length; i < len; i++) {
				chair[i].index = i;
				chair[i].addEventListener('click', function() {
					var nowelem = this;
					if (!hasClass(this, 'selected')&&!hasClass(this, 'nallow')) {
						addClass(this, 'select');
						setTimeout(function() {
							if (window.confirm('你确定要选中该座位吗？')) {
								init(chair, 'selected');
								removeClass(nowelem, 'select');
								addClass(nowelem, 'selected');
								sentdata(nowelem.index);
							} else {
								removeClass(nowelem, 'select');
							}
						}, 100);
					} else {
						removeClass(nowelem, 'selected');
					}
				});

			}
		}
	
	
	</script>


</head>

<body>

	<input type="hidden" value="1" id="zoneid"/>
	<div class="title">
		<h1>区1</h1>
	</div>
	<div class="guide">
		<span><span class="allow"></span>&nbsp;&nbsp;可选</span> <span><span
			class="nallow"></span>&nbsp;&nbsp;不可选</span> <span><span
			class="allowed"></span>&nbsp;&nbsp;已选</span>
	</div>
	<div class="gird">
		<div class="topchair">
			<div>
				<div class="desk" id="desk1">
					<h1>
						桌<br />&nbsp;1
					</h1>
					<span class="onechair chair"></span>
					 <span class="twochair chair"></span>
					<span class="threechair chair"></span>
					 <span class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk2">
					<h1>
						桌<br />&nbsp;2
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk3">
					<h1>
						桌<br />&nbsp;3
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk4">
					<h1>
						桌<br />&nbsp;4
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk5">
					<h1>
						桌<br />&nbsp;5
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="topdoor"></div>
			</div>
		</div>
		<div class="firstspace"></div>
		<div class="centerchair">
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="secondspace"></div>
		<div class="bottomchair">
			<div>
				<div class="bottomdoor"></div>
			</div>
			<div>
				<div class="desk" id="desk6">
					<h1>
						桌<br />&nbsp;6
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk7">
					<h1>
						桌<br />&nbsp;7
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk8">
					<h1>
						桌<br />&nbsp;8
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk9">
					<h1>
						桌<br />&nbsp;9
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>
			<div>
				<div class="desk" id="desk10">
					<h1>
						桌<br />&nbsp;10
					</h1>
					<span class="onechair chair"></span> <span class="twochair chair"></span>
					<span class="threechair chair"></span> <span
						class="fourchair chair"></span>
				</div>
			</div>

		</div>
	</div>
	<input type="hidden" value="${currentUser.userid}" id="cuid" />
	<c:forEach items="${seatsinfo}" var="seat">
		<script type="text/javascript">
		var chair=document.getElementsByClassName('chair');
			if("${seat.position}"=='左上'&&"${seat.occupied}"=='1'){
				var index=(parseInt("${seat.desk.desknum}")-1)*4;
				chair[index].classList.add('nallow');
				
				}
			if("${seat.position}"=='左下'&&"${seat.occupied}"=='1'){
				var index=(parseInt("${seat.desk.desknum}")-1)*4+1;
				chair[index].classList.add('nallow');
				
				}
			if("${seat.position}"=='右下'&&"${seat.occupied}"=='1'){
				var index=(parseInt("${seat.desk.desknum}")-1)*4+2;
				chair[index].classList.add('nallow');
				
				}
			if("${seat.position}"=='右上'&&"${seat.occupied}"=='1'){
				var index=(parseInt("${seat.desk.desknum}")-1)*4+3;
				chair[index].classList.add('nallow');
				
				}
		</script>
	</c:forEach>
</body>
</html>