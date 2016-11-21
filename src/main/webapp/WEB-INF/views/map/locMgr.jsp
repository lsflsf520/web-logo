<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<!DOCTYPE html>  
<html>  
<head>  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>Hello, World</title>  
<style type="text/css">  
html{height:100%;width:100%;}  
body{height:50%;width:50%;margin:0px;padding:0px}  
#container{height:100%;width:100%;} 
#selfCtrl{list-style:none;}
#selfCtrl li{
  cursor: pointer;
  border: 1px solid gray;
  backgroundColor: white;
  width:15%;
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
<div style="width:730px;margin:auto;">   
        要查询的地址：<input id="keyword" type="text" value="橘子洲" style="margin-right:20px;"/>
        查询结果(经纬度)：<input id="lnglat" type="text" />
        <input type="button" value="查询" onclick="searchByStationName();"/>
        <input type="button" value="刷新地理经纬度" onclick="refreshLngLat();"/>
</div>
<div id="container"></div> 

<div id="ctrlDiv">
  <ul id="selfCtrl">
    <li onclick="loadPoint(0)">全部</li>
    <li onclick="loadPoint(1)">风景名胜</li>
    <li onclick="loadPoint(2)">公园</li>
    <li onclick="loadPoint(3)">农家乐</li>
    <li onclick="loadPoint(4)">古镇</li>
    <li onclick="loadPoint(5)">湖泊水库</li>
    <li onclick="loadPoint(6)">博物馆/书店</li>
    <li onclick="loadPoint(7)">故居/纪念馆</li>
    <li onclick="loadPoint(8)">采摘园</li>
    <li onclick="loadPoint(9)">电影院</li>
    <li onclick="loadPoint(10)">购物广场</li>
  </ul>
</div>

<script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
<script type="text/javascript"> 
var map = new BMap.Map("container");          // 创建地图实例  
//var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
var point = new BMap.Point(112.96715,28.175466);
map.centerAndZoom(point, 11);                 // 初始化地图，设置中心点坐标和地图级别  
var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
map.addControl(top_left_control);
var top_left_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_SMALL});
map.addControl(top_left_navigation);

function loadPoint(type){
	map.clearOverlays();
	$.ajax({
		  url:'${base == "/" ? "" : base}/map/loadLocInfos.do?type=' + type,
		  type:"GET",
		  datatype:"json",
		  success:function(data){
			  $(data).each(function(i, d){
				  var marker = new BMap.Marker(new BMap.Point(d.lng/1000000, d.lat/1000000));
				  map.addOverlay(marker);
				  
				  marker.enableDragging();
				  
				  var label = new BMap.Label(d.name, {offset:new BMap.Size(20,-10)});
				  marker.setLabel(label);
				  
				  marker.addEventListener("click",getPosition);
				  console.log(d.name + "," + (d.lng/1000000) + "," + (d.lat/1000000));
			  });
		  }
	});
}

function getPosition(e){
	var p = e.target;
	if(p instanceof BMap.Marker){
  	  var position = p.getPosition();
  	  $("#lnglat").val(position.lng + "," + position.lat);
  	  $("#keyword").val(p.zc.innerText);
	}
}

loadPoint(0);//默认初始状态加载全部地址
</script>  

<script type="text/javascript">
var localSearch = new BMap.LocalSearch(map);
localSearch.enableAutoViewport(); //允许自动调节窗体大小

function searchByStationName() {
    map.clearOverlays();//清空原来的标注
    var keyword = document.getElementById("keyword").value;
    localSearch.setSearchCompleteCallback(function (searchResult) {
      var poi = searchResult.getPoi(0);
      document.getElementById("lnglat").value = poi.point.lng + "," + poi.point.lat;
      map.centerAndZoom(poi.point, 13);
      var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度
      map.addOverlay(marker);
    });
    localSearch.search(keyword);
 }
</script>

<script type="text/javascript">
function refreshLngLat(){
	   
	   $.ajax({
		  url:'${base == "/" ? "" : base}/map/getLocs.do',
		  type:"GET",
		  datatype:"json",
		  success:function(data){
			  var dataMap = {};
			 // var d = {city:"长沙", name:"橘子洲", baseType: 1};
			  $(data).each(function(i, d){
				  var kw = d.name;
				  if(d.city != null && d.name.indexOf(d.city) < 0){
					kw = d.city + d.name;
				  }
					
				 dataMap[kw] = d;
				 localSearch.setSearchCompleteCallback(function(result){
					var poi = result.getPoi(0);
					if(poi){
						var dt = dataMap[result.keyword];
						dt.lng = poi.point.lng * 1000000;
						dt.lat = poi.point.lat * 1000000;
						
						$.ajax({
					 		  url:'${base == "/" ? "" : base}/map/save.do',
					 		  type:"POST",
					 		  data:dt,
					 		  datatype:"text",
					 		  success:function(data){
					 			  console.log(data);
					 		  }
						});
					}
			    });
				
				localSearch.search(kw);
			  });
		  }
	  });
}
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
	  
  var dom = $("#ctrlDiv");
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