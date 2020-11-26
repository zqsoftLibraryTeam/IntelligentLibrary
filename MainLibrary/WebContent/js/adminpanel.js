var as = null;
var maindivs  = null;
var xmlhttp;
oFReader = new FileReader();
window.onload=function(){
	var nava = document.getElementById('nava');
//	alert(nava);
	var adddiv = document.getElementById('adddiv');
	as = nava.getElementsByTagName('a');
	maindivs = adddiv.getElementsByClassName("w3-main");
//	alert(maindivs.length);
	//为侧边栏绑定点击事件，切换div
	for(var i=1;i<as.length;i++){
		as[i].index=i-1;
		as[i].onclick = function(event){
			(eventUtil.getEvent(event)).stopPropagation();//火狐不行注释
			tabchange(this.index);
			w3_close();
			achange(this.index);
		}
	}
	
	eventUtil.addHandler(as[2],'click',function(){
				flashObjList("book");
	});
	eventUtil.addHandler(as[3],'click',function(){
				flashObjList("user");
	});
//	var 
//	eventUtil.addHandler(document.getElementById("usereditsubmit"),'click',function(){
//		submitedit
//	});
	
	
	eventUtil.addHandler(document.getElementById("btnAdduser"),'click',function(){
		Matchinputs("user","add");
	});
	eventUtil.addHandler(document.getElementById("useraddsubmit"),'click',function(){
		if(checkInputs("user","add")){
			submitadd("user");
		}
	});
	eventUtil.addHandler(document.getElementById("btnAddbook"),'click',function(){
		Matchinputs("book","add");
	});
	eventUtil.addHandler(document.getElementById("bookaddsubmit"),'click',function(){
		if(checkInputs("book","add")){
			submitadd("book");
		}
	});
	
	eventUtil.addHandler(document.getElementById("bookdeletesubmit"),'click',function(){
		submitdelete("book");
	});
	eventUtil.addHandler(document.getElementById("userdeletesubmit"),'click',function(){
		submitdelete("user");
	});
}
/**
 * 刷新面板中的列表/可以在此处处理分页
 * @param {Object} str
 */
function flashObjList(str){
	ajaxGson('GET','./get'+str+'splitnum.do',function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			createsplit(str,xmlhttp.responseText);
			
			ajaxGson('GET','./getsub'+str+'list.do?',function(){
				if (xmlhttp.readyState==4 && xmlhttp.status==200){
					//alert(JSON.parse(xmlhttp.responseText)[1].bookid);
					var objs = JSON.parse(xmlhttp.responseText);
					var tbody = document.getElementById(str+"container");
					tbody.innerHTML=null;
					for (var i=0;i<objs.length;i++) {
						tbody.appendChild(createTR(objs[i]));
					}
				}
			});
			
		}
	});
	
}
/**
 * 封装的Ajax函数  没有传递参数
 * @param {Object} method
 * @param {Object} url
 * @param {Object} fun
 */
function ajaxGson(method,url,fun){
	
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=fun;
	xmlhttp.open(method,url,true);
	xmlhttp.send();
}

/**
 * 封装的Ajax函数 有传递参数
 * @param {Object} method 	Post还是Get凡是上传
 * @param {Object} url		请求的路径
 * @param {Object} data		需要发送的数据
 * @param {Object} fun		请求返回操作函数
 */
function ajaxGsonHasData(method,url,data,fun){
	
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=fun;
	xmlhttp.open(method,url,true);
	//xmlhttp.setRequestHeader("Content-type","multipart/form-data"); 
	xmlhttp.send(data);
}
/**
 * 当点击a标签的时候进行切换标签的操作
 * @param {Object} a
 */
function achange(a){
	for(var i=1;i<as.length;i++){
		as[i].className="w3-padding";
	}
	as[a+1].className="w3-padding w3-blue";
}
/**
 * 侧边栏a标签的底色变化
 * @param a
 * @returns
 */
function tabchange(a){
	for(var i=0;i<maindivs.length;i++){
		maindivs[i].className="w3-main zmymain";
	}
	maindivs[a].className="w3-main amymain";
}
/**
 * 创建行 RT
 * @param obj 传入每个对象
 * @returns 返回创建好的一行
 */
function createTR(obj){
	var tr = document.createElement("tr");
	var td = document.createElement('td');
	td.innerHTML="<input name='checkbox' type='checkbox'  >";
	tr.appendChild(td);
	for (var i in obj) {
		if(i == "userid"){
			td.childNodes[0].userid=obj.userid;
		}else if(i == "bookid"){
			td.childNodes[0].bookid=obj.bookid;
		}
		td = document.createElement('td');
		if(i=="status"){
			if(obj[i] ==1){
				td.innerHTML = "可借";
			}else{
				td.innerHTML = "不可借";
			}
			tr.appendChild(td);
			continue;
		}
		if(i=="picture")continue;
		if(i=="sex"){
			if(obj[i] ==1){
				td.innerHTML = "男";
			}else{
				td.innerHTML = "女";
			}
			tr.appendChild(td);
			continue;
		}
		if(i=="identify"){
			if(obj[i] ==1){
				td.innerHTML = "是";
			}else{
				td.innerHTML = "否";
			}
			tr.appendChild(td);
			continue;
		}
		td.innerHTML = obj[i];
		tr.appendChild(td);
	}
	td = document.createElement('td');
	if(obj.userid){
		td.innerHTML="<button id='btnEdit' type='button' class='btn  btn-warning' data-toggle='modal' data-target='#userchangeModal'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span>修改</button>";
	}else if(obj.bookid){
		td.innerHTML="<button id='btnEdit' type='button' class='btn  btn-warning' data-toggle='modal' data-target='#bookchangeModal'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span>修改</button>";
	}
	//td.innerHTML="<button id='btnEdit' type='button' class='btn  btn-warning' data-toggle='modal' data-target='#changeModal'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span>修改</button>";
	eventUtil.addHandler(td,'click',function(){
		if(obj.userid){
			editbutton(obj,'user');
		}else if(obj.bookid){
			editbutton(obj,'book');
		}
	});
	tr.appendChild(td);
	return tr;
}
/**
 * 每一行对应的编辑按钮
 * @param obj	当前行对象
 * @param type	传输对象的类型
 * @returns		
 */
function editbutton(obj,type){
	
	ajaxGson("GET","./gson"+type+".do?id="+obj[type+"id"],function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			var i = 0;
			var typeobj = JSON.parse(xmlhttp.responseText);
			var inputs = (document.getElementById(type+"changeform")).getElementsByTagName("input");
			for(var o in typeobj){
				for(var i=0;i<inputs.length;i++){
					if(o == "status"&&inputs[i].name == "status"){
						//console.log(typeobj[o]+"shenme"+ inputs[i].value);
						if(typeobj[o] == inputs[i].value){
							inputs[i].checked=true;
							inputs[i].parentNode.className="btn btn-default active";
							//console.log(inputs[i]);
						}else{
							inputs[i].checked=false;
							inputs[i].parentNode.className="btn btn-default ";
							//console.log(inputs[i]);
						}
						continue;
					}
					if(o == "sex"&&inputs[i].name == "sex"){
						//console.log(typeobj[o]+"shenme"+ inputs[i].value);
						if(typeobj[o] == inputs[i].value){
							inputs[i].checked=true;
							inputs[i].parentNode.className="btn btn-default active";
							//console.log(inputs[i]);
						}else{
							inputs[i].checked=false;
							inputs[i].parentNode.className="btn btn-default ";
							//console.log(inputs[i]);
						}
						continue;
					}
					if(o == "identify"&&inputs[i].name == "identify"){
						//console.log(typeobj[o]+"shenme"+ inputs[i].value);
						if(typeobj[o] == inputs[i].value){
							inputs[i].checked=true;
							inputs[i].parentNode.className="btn btn-default active";
							//console.log(inputs[i]);
						}else{
							inputs[i].checked=false;
							inputs[i].parentNode.className="btn btn-default ";
							//console.log(inputs[i]);
						}
						continue;
					}
					if(inputs[i].type == "file"){
						inputs[i].outerHTML = inputs[i].outerHTML;
						continue;
					}
					if(inputs[i].name == o){
						inputs[i].value = typeobj[o];
						if(inputs[i].name == "picture"){
							(inputs[i].nextSibling.nextSibling).src = "../img/bookimg/"+ typeobj[o];
							//(inputs[i].nextSibling.nextSibling).width=140+"px";
							//console.log(inputs[i].nextSibling.nextSibling);
						}
					}
				}
			}
		}
		Matchinputs(type,"change");
	});
	eventUtil.addHandler(document.getElementById(type+"editsubmit"),'click',function(){
		if(checkInputs(type,"change")){
			submitedit(type);
		}
	});
	
}
/**
 * 检查是否填写正确
 * @param type		什么类型的(book,user)
 * @param way		什么方式(add,edit)
 * @returns			全部填写正确的话,返回true，否则返回false
 */
function checkInputs(type,way){
	var inputs = (document.getElementById(type+way+"form")).getElementsByTagName("input");
	//var submitbutton = document.getElementById(type+way+"submit");
	console.log(inputs);
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].checkresult == false){
			//console.log(inputs[i]);
			//console.log(submitbutton);
			return false;
		}
	}
	$('#'+type+way+"Modal").modal('hide');
	return true;
}
/**
 * 修改提交的按钮 
 * @param type		什么类型
 * @returns
 */
function submitedit(type){
	var typeobj = new Object();
	//var i = 0;
	var fileform= null;
	var fileinput = null;
	var inputs = (document.getElementById(type+"changeform")).getElementsByTagName("input");
	//console.log(inputs);
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].type=="file"){
			if(inputs[i].files[0]!=null){
				//console.log(inputs[i]);
				typeobj[inputs[i].name] = inputs[i].files[0].name;
				fileinput = inputs[i];
				fileform = new FormData();
				fileform.append("file",inputs[i].files[0],inputs[i].files[0].name);
				//inputs[i].outerHTML = inputs[i].outerHTML;这个清除文件的地方有问题
				continue;
			}
			continue;
		}
		if(inputs[i].name =="status"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		if(inputs[i].name =="identify"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		if(inputs[i].name =="sex"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		
		typeobj[inputs[i].name]=inputs[i].value;
	}
	//console.log(typeobj);
	var string = JSON.stringify(typeobj);
	if(fileform != null){
		fileform.append("editobj",string);
		ajaxGsonHasData("POST","./adminEditHasData"+type+".do",fileform,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				alert(xmlhttp.responseText);
				//刷新面板
				flashObjList(type);
			}
		});
	}else{
		ajaxGson("POST","./adminEditNoData"+type+".do?editobj="+string,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				alert(xmlhttp.responseText);
				//刷新面板
				flashObjList(type);
			}
		});
	}
	
}
/**
 * 提交添加
 * @param type
 * @returns
 */
function submitadd(type){
	var typeobj = new Object();
	//var i = 0;
	var fileform= null;
	var fileinput = null;
	var inputs = (document.getElementById(type+"addform")).getElementsByTagName("input");
	for(var i=0;i<inputs.length;i++){
		
		if(inputs[i].type=="file"){
			if(inputs[i].files[0]!=null){
				console.log(inputs[i]);
				typeobj[inputs[i].name] = inputs[i].files[0].name;
				fileinput = inputs[i];
				fileform = new FormData();
				fileform.append("file",inputs[i].files[0],inputs[i].files[0].name);
				//inputs[i].outerHTML = inputs[i].outerHTML;这个清除文件的地方有问题
				continue;
			}
			continue;
		}
		if(inputs[i].name =="status"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		if(inputs[i].name =="identify"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		if(inputs[i].name =="sex"){
			if(inputs[i].checked == true){
				typeobj[inputs[i].name]=inputs[i].value;
			}
			continue;
		}
		typeobj[inputs[i].name]=inputs[i].value;
	}
	console.log(typeobj)
	var string = JSON.stringify(typeobj);
	//alert(string);
	if(fileform != null){
		ajaxGsonHasData("POST","./adminaddHasData"+type+".do?addobj="+string,fileform,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				alert(xmlhttp.responseText);
				//刷新面板
				flashObjList(type);
				clearElement(inputs);
			}
		});
	}else{
		ajaxGson("POST","./adminaddNoData"+type+".do?addobj="+string,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				alert(xmlhttp.responseText);
				//刷新面板
				flashObjList(type);
				clearElement(inputs);
			}
		});
	}
}
function clearElement(inputs){
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].type=="file"){
			if(inputs[i].files[0]!=null){
				inputs[i].outerHTML = inputs[i].outerHTML;
				continue;
			}
			continue;
		}
		if(inputs[i].name =="status"){
			if(inputs[i].value == "1"){
				inputs[i].checked=true;
			}
			inputs[i].checked=false;
			continue;
		}
		if(inputs[i].name =="identify"){
			if(inputs[i].value == "1"){
				inputs[i].checked=true;
			}
			inputs[i].checked=false;
			continue;
		}
		if(inputs[i].name =="sex"){
			if(inputs[i].value == "1"){
				inputs[i].checked=true;
			}
			inputs[i].checked=false;
			continue;
		}
		inputs[i].value= null;
	}
}
function submitdelete(type){
	var selectobj=new Array();
	var objboxs=new Array();
	var trs = (document.getElementById(type+"container")).getElementsByTagName("tr");
	for(var i=0;i<trs.length;i++){
		objboxs.push((trs[i].childNodes[0]).childNodes[0])
	}
	for(var i=0;i<objboxs.length;i++){
		if(objboxs[i].checked==true){
			selectobj.push(objboxs[i][type+"id"]);
		}
	}
//	console.log(selectobj);
	var string = JSON.stringify(selectobj);
//	alert(string);
	ajaxGson("POST","./admindel"+type+".do?dellist="+string,function(){
		if(xmlhttp.readyState==4 && xmlhttp.status==200){
			alert(xmlhttp.responseText);
			//刷新面板
			flashObjList(type);
			selectobj.splice(0,selectobj.length);
//			console.log(selectobj);
		}
	});
}
/**
 * 2017/2/19                Sean
 * 正则表达式函数
 * @param {Object} str      正则表达式语句
 * @param {Object} model	表达式的模式
 * @param {Object} Matched	需要匹配的字段
 */
function regExp(str,model,Matched){
	var reg = new RegExp(str, model);
	//console.log(reg.test(Matched));
	if(reg.test(Matched)){
		return true;
	}
	return false; 
}
/**
 * 正则匹配成功
 */
function regExpsuccess(obj){
	obj.parentNode.className = "col-sm-6 has-success has-feedback";
//	console.log(obj.parentNode.childNodes);
	(obj.parentNode).getElementsByTagName("span")[0].className = "glyphicon glyphicon-ok form-control-feedback";
}
/**
 * 正则匹配失败
 */
function regExperror(obj){
	obj.parentNode.className = "col-sm-6 has-error has-feedback";
	(obj.nextSibling.nextSibling).className = "glyphicon glyphicon-remove form-control-feedback";
}
/**
 * 初始化input的选框内容
 */
function regExpdefault(type,obj){
	obj.parentNode.className = "col-sm-6  has-feedback";
//	console.log(obj.parentNode.childNodes);
	(obj.parentNode).getElementsByTagName("span")[0].className = "glyphicon  form-control-feedback";
	obj.checkresult = true;//设置为false的话，在添加和修改书本没有做任何操作确定按钮会失效
	if(type == "book"){
		document.getElementById("changeuploadPreviewImg").src="";
		document.getElementById("adduploadPreviewImg").src="";
	}
}
/**
 * 对input进行验证
 */
function Matchinputs(type,way){
	var inputs = (document.getElementById(type+way+"form")).getElementsByTagName("input");
	//console.log(inputs);
	for(var i=0;i<inputs.length;i++){
		
		
		if(inputs[i].name =="bookname"){
			regExpdefault(type,inputs[i]);
			$('[name="bookname"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\w+$|^[\u4e00-\u9fa5]+$','i',this.value)){
					this.title="";
					$('[name="bookname"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="书本名不能为空";
  					$('[name="bookname"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="author"){
			regExpdefault(type,inputs[i]);
			$('[name="author"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\w+$|^[\u4e00-\u9fa5]+$','i',this.value)){
					this.title="";
					$('[name="author"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="作者名不能为空";
  					$('[name="author"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="press"){
			regExpdefault(type,inputs[i]);
			$('[name="press"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\w+$|^[\u4e00-\u9fa5]+$','i',this.value)){
					this.title="";
					$('[name="press"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="出版社不能为空";
  					$('[name="press"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		//进行isbn长度的匹配
		if(inputs[i].name =="isbn"){
			regExpdefault(type,inputs[i]);
			$('[name="isbn"]').tooltip('destroy');
//			console.log(this.value == null)
			inputs[i].onblur = function(){
				if(regExp('^\\d{10}$|^\\d{13}$','i',this.value)){
					this.title="";
					regExpsuccess(this);
					$('[name="isbn"]').tooltip('destroy');
					this.checkresult = true;
				}else{
					this.title="长度应是10位或是13位纯数字";
  					$('[name="isbn"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
			
		}
		if(inputs[i].type =="file"){
			regExpdefault(type,inputs[i]);
			$('[type="file"]').tooltip('destroy');
			inputs[i].onchange = function(){
//				console.log(this.value)
//				console.log(this.files[0].name);
				if(regExp('^\\w+\.jpg$|^\\w+\.png$|^\\w+\.gif$','i',this.files[0].name)){
					oFReader.readAsDataURL(this.files[0]);
					oFReader.onload = function (oFREvent) {
						 document.getElementById(way+"uploadPreviewImg").src = oFREvent.target.result;
					};
					this.title="";
					regExpsuccess(this);
					$('[type="file"]').tooltip('destroy');
					this.checkresult = true;
				}else{
					this.title="请选择.jpg文件.png文件或者.gif均可,但是文件名请不要带符号!!";
  					$('[type="file"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="booknum"){
			regExpdefault(type,inputs[i]);
			$('[name="booknum"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\d{1,9}$','i',this.value)){
					this.title="";
					$('[name="booknum"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="书本数量应为数字,且小于999999999";
  					$('[name="booknum"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="username"){
			regExpdefault(type,inputs[i]);
			$('[name="username"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\.{1,10}$','i',this.value)){
					this.title="";
					$('[name="username"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="用户名长度不能太长";
  					$('[name="username"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="password"){
			regExpdefault(type,inputs[i]);
			$('[name="password"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\b\\w{6,11}$','i',this.value)){
					this.title="";
					$('[name="password"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="密码为字母开头，长度介于8~12位";
  					$('[name="password"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="password"){
			regExpdefault(type,inputs[i]);
			$('[name="password"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\b\\w{6,11}$','i',this.value)){
					this.title="";
					$('[name="password"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="密码为字母开头，长度介于8~12位";
  					$('[name="password"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="email"){
			regExpdefault(type,inputs[i]);
			$('[name="email"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$','i',this.value)){
					this.title="";
					$('[name="email"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="邮箱有误！！";
  					$('[name="email"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="phone"){
			regExpdefault(type,inputs[i]);
			$('[name="phone"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^1\\d{10}$','i',this.value)){
					this.title="";
					$('[name="phone"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="手机号有误";
  					$('[name="phone"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="name"){
			regExpdefault(type,inputs[i]);
			$('[name="name"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^[\u4e00-\u9fa5]{1,6}$|^w{1,12}$','i',this.value)){
					this.title="";
					$('[name="name"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="名字长度在1-6之间";
  					$('[name="name"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="timelimit"){
			regExpdefault(type,inputs[i]);
			$('[name="timelimit"]').tooltip('destroy');
			inputs[i].onblur = function(){
				if(regExp('^\\d{1,10}$','i',this.value)){
					this.title="";
					$('[name="timelimit"]').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title="应为数字,长度小于10";
  					$('[name="timelimit"]').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
		}
		if(inputs[i].name =="age"){
			checkElement(inputs[i],type,"age",'^\\d{1,10}$',"年龄应为数字！")
		}
	}
	
	
}
function checkElement(obj,type,attribute,regExpstr,message){
			regExpdefault(type,obj);
			$('[name='+attribute+']').tooltip('destroy');
			obj.onblur = function(){
				if(regExp(regExpstr,'i',this.value)){
					this.title="";
					$('[name='+attribute+']').tooltip('destroy');
					regExpsuccess(this);
					this.checkresult = true;
				}else{
					this.title=message;
  					$('[name='+attribute+']').tooltip();
					regExperror(this);
					this.checkresult = false;
				}
			}
}
function createsplit(type,num){
	//console.log(type+"sss"+num);
	var split = document.getElementById(type+"split");
	split.innerHTML=null;
	var li = document.createElement('li');
	var a = document.createElement('a');
	a.onclick = function(){
		//pre按钮执行方法
		splitchange(type,true,-1);
	}
	a.innerHTML="&laquo";
	a.index=0;
	a.href="#";
	li.appendChild(a);
	li.index=0;
	split.appendChild(li);
	for(var i =1;i<=num;i++){
		li = document.createElement('li');
		li.index=i;
		a = document.createElement('a');
		a.index=i;
		a.href="#";
		a.onclick = function(){
			//里面的按钮进行的动作
			splitchange(type,false,this.index);
		}
		a.innerHTML=i;
		li.appendChild(a);
		split.appendChild(li);
	}
	li = document.createElement('li');
	a = document.createElement('a');
	a.onclick = function(){
		//next按钮执行方法
		splitchange(type,true,1);
	}
	a.innerHTML="&raquo";
	a.index=num+1;
	a.href="#";
	li.appendChild(a);
	li.index=num+1;
	split.appendChild(li);
	split.getElementsByTagName("li")[1].className="active";
}
/**
 * 分页标签的切换
 * @param type   	book还是user
 * @param model		切换的模式是什么，向前、向后翻页(true)，还是直接切换(false)
 * @param index		(model模式-1 前一页，1后一页)直接切换的话切换到哪一页
 * @returns
 */
function splitchange(type,model,index){
	var lis = (document.getElementById(type+"split")).getElementsByTagName("li");
	var aindex;
	if(model){
		for(var i=1;i<lis.length-1;i++){
			if(lis[i].className=="active"&&i==1&&index==-1){
				aindex=(lis[i].index+lis.length-3)%(lis.length);
			}else if(lis[i].className=="active"&&i==(lis.length-2)&&index==1){
				aindex=(lis[i].index+lis.length+3)%(lis.length);
			}else if(lis[i].className=="active"){
				aindex=(lis[i].index+index+lis.length)%(lis.length);
			}
			lis[i].className="";
		}
		lis[aindex].className="active";
		//console.log(lis);
		ajaxGson('GET','./getsub'+type+'list.do?currentpage='+aindex,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				//alert(JSON.parse(xmlhttp.responseText)[1].bookid);
				var objs = JSON.parse(xmlhttp.responseText);
				var tbody = document.getElementById(type+"container");
				tbody.innerHTML=null;
				for (var i=0;i<objs.length;i++) {
					tbody.appendChild(createTR(objs[i]));
				}
			}
		});
	}else{
		for(var i=1;i<lis.length-1;i++){
			lis[i].className="";
		}
		lis[index].className="active";
		ajaxGson('GET','./getsub'+type+'list.do?currentpage='+index,function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				//alert(JSON.parse(xmlhttp.responseText)[1].bookid);
				var objs = JSON.parse(xmlhttp.responseText);
				var tbody = document.getElementById(type+"container");
				tbody.innerHTML=null;
				for (var i=0;i<objs.length;i++) {
					tbody.appendChild(createTR(objs[i]));
				}
			}
		});
	}
}