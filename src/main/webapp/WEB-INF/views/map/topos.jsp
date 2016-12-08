<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#posmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qTAvDQxZvNfv2tHcnGqzpwfsxNV9IbMG"></script>
	<title>添加动画标注点</title>
</head>
<body>
	<div id="posmap"></div>
	
	<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("posmap");
	var point = new BMap.Point(${locInfo.lng / 1000000.0} , ${locInfo.lat / 1000000.0});
	map.centerAndZoom(point, 15);
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    </script>
</body>
</html>