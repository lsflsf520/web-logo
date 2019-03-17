<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
    <title>动态-${site.shortName}</title>
    <link rel="stylesheet" href="/static/weizhan/css/mui.min.css"/>
    <link rel="stylesheet" href="/static/weizhan/css/index.css?v=1"/>
</head>
<body>
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
      <div class="mui-scroll">
        <div class="wo_dongt" id="content">
          <c:forEach var="item" items="${dataPage.datas}" varStatus="status"> 
		    <div class="gt_zhidt">
	            <div class="dt_time">${item.dataTimeStr }</div>
	            <div class="dt_tuwhp">
	                <p>${item.remark }</p>
	                <img src="${item.img }" alt=""/>
	            </div>
	        </div>
		  </c:forEach>
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
         NetUtil.ajaxload('/wz/dynamicByPage.do?currPage=' + currPage, function(result){
	       var html = '';
	       $(result.model.datas).each(function(i, d){
	          html +='<div class="gt_zhidt">' +
	                   '<div class="dt_time">'+d.dataTimeStr+ '</div>' +
	                   '<div class="dt_tuwhp">' +
	                     '<p>' + d.remark +'</p>' +
	                     '<img src="'+d.img +'" alt=""/>' +
	                   '</div>' +
	                 '</div>';
	       });
	       $('#content').append(html);
         });
         mui('#pullrefresh').pullRefresh().endPullupToRefresh(false);
      }
  </script>
</body>
</html>