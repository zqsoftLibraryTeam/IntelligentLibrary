window.onload = function() {
			$('.auto').eq(0).css('margin-top', ($('.tab2-img').eq(0).height() - $('.tab2-text').eq(0).height()) / 2 + "px");
			$(".auto").eq(1).css('margin-top', ($('.tab2-img').eq(0).height() - $('.tab2-text').eq(0).height()) / 2 + "px");
			getNewBook();
			
		}
		$(window).resize(function() {
			$('.auto').eq(0).css('margin-top', ($('.tab2-img').eq(0).height() - $('.tab2-text').eq(0).height()) / 2 + "px");
			$(".auto").eq(1).css('margin-top', ($('.tab2-img').eq(0).height() - $('.tab2-text').eq(0).height()) / 2 + "px");
	
		})
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
function getNewBook(){
	ajaxGson("GET",'./common/getindexnewbook.do',function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
				createShowList(xmlhttp.responseText);
				getTopBook();
		}
	});
}
function createShowList(obj){
	var objs = JSON.parse(obj);
	var sbody = document.getElementById("newBookContainer");
	sbody.innerHTML=null;
	for (var i=0;i<objs.length;i++) {
		sbody.appendChild(createshowBody(objs[i]));
	}
}

function createshowBody(obj){
	//console.log(obj);
	//外层包裹层
	var ldiv = document.createElement("div");
	ldiv.className="col-md-4 col";
	//media层
	var media = document.createElement("div");
	media.className="media";
	//media-left
	var media_left = document.createElement("div");
	media_left.className="media-left";
	var a = document.createElement("a");
	a.href="./user/borrowbookdetail.do?bookid="+obj.bookid;
	var img = document.createElement("img");
	img.src="./img/bookimg/"+obj.picture;
	img.className="showNewBooks";
	//添加进入media-left
	media_left.appendChild(a);
	media_left.appendChild(img);
	//media-body层
	var media_body = document.createElement("div");
	media_body.className="media-body";
	var h4 = document.createElement("h4");
	h4.innerHTML="《"+obj.bookname+"》";
	var p1  = document.createElement("p");
	p1.innerHTML="作者："+obj.author;
	p1.className="text-muted";
	a.className = "btn btn-default pull-right";
	a.innerHTML="详情";
	//添加进media-body
	media_body.appendChild(h4);
	media_body.appendChild(p1);
	media_body.appendChild(a);
	//将media-left和media-body加入media
	media.appendChild(media_left);
	media.appendChild(media_body);
	//将media装入外层包裹层
	ldiv.appendChild(media);
	return ldiv;
}
function getTopBook(){
	ajaxGson("GET",'./common/getindextopbook.do?num=3',function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			var objs = JSON.parse(xmlhttp.responseText);
			var tbody = document.getElementById("topBookContainer");
			tbody.innerHTML=null;
			for (var i=0;i<objs.length;i++){
				tbody.appendChild(createTopBody(objs[i],i));
			}
		}
	});
}
function createTopBody(obj,num){
	//外部包裹层
	var tab = document.createElement("div");
	if(num%2 ==0){
		tab.className="tab2";
	}else{
		tab.className="tab1";
	}
	//容器层
	var container = document.createElement("div");
	container.className="container";
	//设置排列容器
	var row = document.createElement("row");
	row.className="row";
	//img容器
	var img_container = document.createElement("div"); 
	img_container.className="col-md-6 col-sm-6 tab2-img";
	//图片
	var img = document.createElement("img");
	img.className="img-responsive center-block";
	img.src="./img/bookimg/"+obj.picture;
	//图片加入图片容器
	img_container.appendChild(img);
	//图片容器加入row
	row.appendChild(img_container);
	//创建主体容器
	var body_container = document.createElement("div");
	body_container.className="col-md-6 col-sm-6  tab2-text auto";
	//标题
	var h3 = document.createElement("h3");
	h3.className = "tab2-h3";
	//作者
	var p = document.createElement("p");
	p.className="tab2-p";
	//简介
	var pdetail = document.createElement("p");
	//链接
	var a = document.createElement("a");
	a.className="btn btn-default pull-right";
	
	h3.innerHTML = "《"+obj.bookname+"》";
	p.innerHTML = "作者："+obj.author;
	pdetail.innerHTML = "";//以后添加说明用
	a.href="./user/borrowbookdetail.do?bookid="+obj.bookid;
	a.innerHTML = "详情";
	//添加入主体容器
	body_container.appendChild(h3);
	body_container.appendChild(p);
	body_container.appendChild(pdetail);
	body_container.appendChild(a);
	//添加入排列容器
	row.appendChild(img_container);
	row.appendChild(body_container);
	//加入到容器
	container.appendChild(row);
	//加入到外部包裹层
	tab.appendChild(container);
	return tab;
	
}
