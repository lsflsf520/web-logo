<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
    <title>产品列表-${site.shortName}</title>
    <link rel="stylesheet" href="/static/weizhan/css/mui.min.css"/>
    <link rel="stylesheet" href="/static/weizhan/css/index.css?v=1"/>
    <style>
        .mui-scroll-wrapper{bottom: 5rem;}
        .mui-content{background: #fff;}
    </style>
</head>
<body>
<!--下拉刷新容器-->
<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
    <div class="mui-scroll">
        <!--数据列表-->
        <div class="mui-table-view mui-table-view-chevron">
            <div class="sg_xianq">
                <table>
                    <tbody id="nq_sheque">
                       <c:forEach var="item" items="${dataPage.datas}" varStatus="status"> 
                       
                        <tr>
                            <td>
                                <div class="nq_toup"><img src="${item.img }" alt="${item.shortName }"/></div>
                                <div class="nq_toup2">
                                    <p>产品名称：${item.viewName }</p>
                                   <c:forEach var="paramItem" items="${item.mainParams }" varStatus="paramStat">
                                    <p style=${paramStat.last ? 'margin-bottom: 0;' : '' }>${paramItem.paramName }：${paramItem.paramVal }</p>
                                   </c:forEach>
                                </div>
                            </td>
                            <td>
                                <a href="/wz/detail.do?prodId=${item.id }"><div>查看详情&gt;&gt;&gt;</div></a>
                            </td>
                        </tr>
                        
                       </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
    
    <a class="lj_zhixun" href="#">立 即 咨 询</a>
    <div style="height: 8rem;"></div>
    
    <%@ include file="toolbar.jsp" %>
    <script>
      var totalPage = ${dataPage.totalPages};
	  var currPage = 1;
	  if(currPage < totalPage) {
	 	 mui.init({
	 	        pullRefresh: {
	 	            container: '#pullrefresh',
	 	            up: {
	 	                auto:true,
	 	                contentrefresh: '正在加载...',
	 	                callback: pullupRefresh
	 	            }
	 	        }
	 	 });
	  }
	  
      function pullupRefresh() {
         if(totalPage < ++currPage) {
           mui('#pullrefresh').pullRefresh().endPullupToRefresh(true); //参数为true代表没有更多数据了。
    	   return;
         }
         NetUtil.ajaxload('/wz/prodListByPage.do?currPage=' + currPage, function(result){
	       var html = '';
	       $(result.model.datas).each(function(i, d){
	    	  var paramHtml = '';
	    	  if(d.mainParams && d.mainParams.length > 0){
	    		  $(d.mainParams).each(function(i, d){
	    			  paramHtml += '<p '+(i == d.mainParams.length - 1 ? 'style="margin-bottom: 0;"' : '')+'>' +d.paramName + '：' + d.paramVal+ '</p>';
	    		  });
	    	  }
	          html +='<tr>'+
	                 '<td>'+
	                   '<div class="nq_toup"><img src="'+d.img+'" alt=""/></div>'+
	                   '<div class="nq_toup2">'+
	                     '<p>产品名称：'+(d.viewName)+'</p>'+
	                     paramHtml +
	                   '</div>'+
	                 '</td>'+
	                 '<td>'+
	                   '<div><a href="/weizhan/detail.do?prodId=' + d.id + '">查看详情&gt;&gt;&gt;</a></div>'+
	                 '</td>'+
	               '</tr>';
	       });
	       $('#nq_sheque').append(html)
         });
         mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);
      }

</script>
</body>
</html>