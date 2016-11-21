<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>根据地址查询经纬度</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
    <script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
</head>
<body style="background:#CBE1FF">
    <div style="width:730px;margin:auto;">   
        要查询的地址：<input id="text_" type="text" value="宁波天一广场" style="margin-right:100px;"/>
        查询结果(经纬度)：<input id="result_" type="text" />
        <input type="button" value="查询" onclick="searchByStationName();"/>
        
         <input type="button" value="刷新地理经纬度" onclick="refreshLngLat();"/>
        <div id="container" 
            style="position: absolute;
                margin-top:30px; 
                width: 730px; 
                height: 590px; 
                top: 50; 
                border: 1px solid gray;
                overflow:hidden;">
        </div>
    </div>

    <script>
      var map = new BMap.Map("container");
      map.centerAndZoom("宁波", 12);

      var localSearch = new BMap.LocalSearch(map);
      localSearch.enableAutoViewport(); //允许自动调节窗体大小

/*      function searchByStationName() {
    　　var keyword = document.getElementById("text_").value;
    　　localSearch.setSearchCompleteCallback(function (searchResult) {
        　　　　var poi = searchResult.getPoi(0);
        　　　　document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat; //获取经度和纬度，将结果显示在文本框中
        　　　　map.centerAndZoom(poi.point, 13);
    　　});
    　　localSearch.search(keyword);
      }
*/

   function searchByStationName() {
      map.clearOverlays();//清空原来的标注
      var keyword = document.getElementById("text_").value;
      localSearch.setSearchCompleteCallback(function (searchResult) {
        var poi = searchResult.getPoi(0);
        document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
        map.centerAndZoom(poi.point, 13);
        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度
        map.addOverlay(marker);
      });
     localSearch.search(keyword);
   }
   
   function refreshLngLat(){
	   
	   $.ajax({
 		  url:'${base == "/" ? "" : base}/map/getLocs.do',
 		  type:"GET",
 		  datatype:"json",
 		  success:function(data){
 			  var dataMap = {};
 			  $(data).each(function(i, d){
 				 dataMap[d.name] = d;
 				 localSearch.setSearchCompleteCallback(function(result){
 					var poi = result.getPoi(0);
 					
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
 			    });
 				 
 				localSearch.search(d.name);
 			  });
 			  
 			  
 		  }
 	  });
   }
  
</script>

</body>
</html>
