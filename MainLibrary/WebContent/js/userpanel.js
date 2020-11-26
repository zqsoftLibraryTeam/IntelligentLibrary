window.onload = function(){
	var btn = document.getElementById("passsubmit");
	btn.onclick = function(){
		clickCpass();
	}
}
function getsubnav(a){
	var subnav = [];
	var subNav = 'borrowinfo';
	var reg = new RegExp('\.' + subNav, 'i');
	var allclass = document.getElementsByTagName('*');
	for (var i=0 ; i<allclass.length ; i++){
		if(reg.test(allclass[i].className)){
			allclass[i].className="aborrowinfo";
			subnav.push(allclass[i]);
		}
	}
	return subnav[a];
}
function shownav(){
	var nav = document.getElementById("mainnav");
	var nava = nav.getElementsByTagName("a");
	
	for(var i =0;i<nava.length;i++){
		nava[i].index=i;
		nava[i].onclick = function(){
		getsubnav(this.index).className="zborrowinfo";
		}
	}
}
function ajaxGson(method,url,data,fun){
	
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
	xmlhttp.send(data);
}
function clickCpass(){
	var inputs = document.getElementById("changepass").getElementsByTagName("input");
	var data = "";
		data =data+inputs[0].name+"="+inputs[0].value+"&"+inputs[1].name+"="+inputs[1].value;
	console.log(data);
	ajaxGson("POST",'./changepass.do?'+data,data,function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			alert(xmlhttp.responseText);
			for(var i=0 ;i<inputs.length;i++){
				inputs[i].value = "";
			}
			location.reload(true);
		}
	});
}
