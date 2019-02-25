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
    <div class="wo_dongt">
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
    
    <a class="lj_zhixun" href="#">立 即 咨 询</a>
    <div style="height: 8rem;"></div>
    
    <%@ include file="toolbar.jsp" %>
</body>
</html>