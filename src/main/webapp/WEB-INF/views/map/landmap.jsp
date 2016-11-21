<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<!DOCTYPE html>  
<html>  
<head>  
<title>长沙-周末落脚点</title> 
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<meta name="description" content="一个给城里人指明周末去哪儿的网站"> 
<meta name="keyword" content="长沙落脚点,长沙好去处,周末好去处,长沙农家乐,长沙一日游,长沙周末游">  
<style type="text/css">  
html{height:100%;width:100%;}  
body{height:100%;width:100%;margin:0px;padding:0px}  
#container{height:100%;width:100%;} 
#selfCtrl{list-style:none;}
#selfCtrl li{
  cursor: pointer;
  border: 1px solid gray;
  backgroundColor: white;
  width:28%;
  float:left;
}
</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qTAvDQxZvNfv2tHcnGqzpwfsxNV9IbMG">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=qTAvDQxZvNfv2tHcnGqzpwfsxNV9IbMG"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=qTAvDQxZvNfv2tHcnGqzpwfsxNV9IbMG&callback=initialize"
</script>
<script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
</head>  
 
<body>  
<div id="container"></div> 

<div id="ctrlDiv">
  <ul id="selfCtrl">
    <!-- <li onclick="loadPoint(0)">全部</li> -->
    <li onclick="loadPoint(3)">农家乐</li>
    <li onclick="loadPoint(8)">采摘园</li>
    <li onclick="loadPoint(2)">公园</li>
    <li onclick="loadPoint(4)">古镇</li>
    <li onclick="loadPoint(5)">湖泊水库</li>
    <li onclick="loadPoint(6)">博物馆/书店</li>
    <li onclick="loadPoint(7)">故居/纪念馆</li>
    <li onclick="loadPoint(1)">风景名胜</li>
    <!-- <li onclick="loadPoint(9)">电影院</li>
    <li onclick="loadPoint(10)">购物广场</li> -->
  </ul>
</div>

<div id="infoWnd" style="display:none;">
 <h4 style='margin:0 0 5px 0;padding:0.2em 0'>msgTitle</h4> 
 <img style='float:right;margin:4px' id='imgDemo' defimg width='139' height='104' title='天安门'/> 
 <p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>shortDesc <br/> <a target="_blank" href="detailUrl">了解详情>></a></p>
</div>

<script type="text/javascript"> 
var map = new BMap.Map("container");          // 创建地图实例  
//var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
var point = new BMap.Point(112.96715,28.175466);
map.centerAndZoom(point, 11);                 // 初始化地图，设置中心点坐标和地图级别  
var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
map.addControl(top_left_control);
var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_SMALL});
//var top_left_navigation = new BMap.NavigationControl();
map.addControl(top_left_navigation);
map.enableScrollWheelZoom();

function loadPoint(type){
	map.clearOverlays();
	$.ajax({
		  url:'${base == "/" ? "" : base}/map/loadLocInfos.do?type=' + type,
		  type:"GET",
		  datatype:"json",
		  success:function(data){
			  var points = [];//定义海量点数据
			  $(data).each(function(i, d){
				  var marker = new BMap.Marker(new BMap.Point(d.lng/1000000, d.lat/1000000));
				  var currDefImg = d.defImg ? d.defImg : "http://static.qiyejieshao.cn/dl/fengjing.jpg";
				  var shortDesc = d.description ? (d.description.length > 50 ? d.description.substr(0, 47) + "..." : d.description ) : "这个地方暂无简介，敬请期待";
				  marker.cusData = {defImg:currDefImg, desc:shortDesc, locId: d.id}; 
				  map.addOverlay(marker);
				  
				  var label = new BMap.Label(d.name, {offset:new BMap.Size(20,-10)});
				  marker.setLabel(label);
				  
				  marker.addEventListener("click", showInfo);
				  //console.log(d.name + "," + (d.lng/1000000) + "," + (d.lat/1000000)); 
				  
				 /*  var p = new BMap.Point(d.lng/1000000, d.lat/1000000);  
		          p.data = {defImg:"http://app.baidu.com/map/images/tiananmen.jpg"};  
		          points.push(p);  */ 
			  });
			  
			  /* var options = {  
			            size: BMAP_POINT_SIZE_SMALL,  
			            shape: BMAP_POINT_SHAPE_STAR,  
			            color: '#d340c3'  
			        } ;
			  var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection  
		      pointCollection.addEventListener('click', showInfo);  
		      map.addOverlay(pointCollection);  // 添加Overlay  */
		  }
	});
}

function showInfo(e){
	var p = e.target;
	if(p instanceof BMap.Marker){
  	  //var position = p.getPosition();
  	  //$("#lnglat").val(position.lng + "," + position.lat);
  	  //$("#keyword").val(p.zc.innerText);
  	  
  	  var currPoint = p.point;
  	  /* var opts = {
  		  width : 200,     // 信息窗口宽度
  		  height: 100,     // 信息窗口高度
  		  title : p.getLabel().content , // 信息窗口标题
  		  //enableMessage:true,//设置允许信息窗发送短息
  		  //message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
  		};
  	  var infoWindow = new BMap.InfoWindow("地址：北京市东城区王府井大街88号乐天银泰百货八层", opts); */
  	  
  	  var sContent = $("#infoWnd").html().replace("msgTitle", p.getLabel().content);
  	  sContent = sContent.replace("shortDesc", p.cusData.desc);
  	  sContent = sContent.replace(/defimg[^ ]*/, "src='" + p.cusData.defImg + "'");
  	  sContent = sContent.replace("detailUrl", '${base == "/" ? "" : base}/map/locdetail.do?locId='+ p.cusData.locId);
  	  var infoWindow = new BMap.InfoWindow(sContent);
  	  map.openInfoWindow(infoWindow,currPoint);
  	  document.getElementById('imgDemo').onload = function (){
		   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	  }
	}/* else{
		var sContent =
	  		"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+'默认标题'+"</h4>" + 
	  		"<img style='float:right;margin:4px' id='imgDemo' src='"+e.point.data.defImg+"' width='139' height='104' title='天安门'/>" + 
	  		"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
	  		"";
	  	var infoWindow = new BMap.InfoWindow(sContent);
	  	map.openInfoWindow(infoWindow,e.point);
	  	document.getElementById('imgDemo').onload = function (){
			infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		}
	} */
}

loadPoint(3);//默认初始状态加载全部地址
</script>  

<script type="text/javascript">
//自定义导航控件
function NavSelfControl(){
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_BOTTOM_LEFT;
	this.defaultOffset = new BMap.Size(10, 30);
}

//通过JavaScript的prototype属性继承于BMap.Control
NavSelfControl.prototype = new BMap.Control();

// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
NavSelfControl.prototype.initialize = function(map){
  // 创建一个DOM元素
 /*  var div = document.createElement("div");
  var ul = document.createElement("ul");
  div.appendChild(ul);
  
  var allLi = document.createElement("li");
  var allTextNode = document.createTextNode("全部");
  allLi.appendChild(allTextNode);
  
  var fengjingLi = document.createElement("li");
  var fengjingTextNode = document.createTextNode("风景名胜");
  fengjingLi.appendChild(fengjingTextNode);
  
  var parkLi = document.createElement("li");
  var parkTextNode = document.createTextNode("公园");
  parkLi.appendChild(parkTextNode);
  
  ul.appendChild(allLi);
  ul.appendChild(fengjingLi);
  ul.appendChild(parkLi);
  
  // 设置样式
  allLi.style.cursor = "pointer";
  allLi.style.border = "1px solid gray";
  allLi.style.backgroundColor = "white";
  
//设置样式
  fengjingLi.style.cursor = "pointer";
  fengjingLi.style.border = "1px solid gray";
  fengjingLi.style.backgroundColor = "white";
  
//设置样式
  parkLi.style.cursor = "pointer";
  parkLi.style.border = "1px solid gray";
  parkLi.style.backgroundColor = "white";
  
  // 绑定事件,点击一次放大两级
  allLi.onclick = function(e){
	//map.setZoom(map.getZoom() + 2);
	  loadPoint(0);
  }
  
  fengjingLi.onclick = function(e){
		//map.setZoom(map.getZoom() + 2);
		  loadPoint(1);
	  }
  
  parkLi.onclick = function(e){
		//map.setZoom(map.getZoom() + 2);
		  loadPoint(2);
	  } */
	  
  //var dom = $("#ctrlDiv").children();
  var dom = document.getElementById("ctrlDiv");
  // 添加DOM元素到地图中
  map.getContainer().appendChild(dom);
  // 将DOM元素返回
  return dom;
}
// 创建控件
var navSelfCtrl = new NavSelfControl();
// 添加到地图当中
map.addControl(navSelfCtrl);
</script>
</body>  
</html>