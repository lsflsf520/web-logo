<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
    <title>${prod.viewName }-${site.shortName}</title>
    <link rel="stylesheet" href="/static/weizhan/css/mui.min.css"/>
    <link rel="stylesheet" href="/static/weizhan/css/index.css?v=1"/>
</head>
<body>
    <header class="zd_xqtoub">
        <a href="particulars.html">&lt;返回</a>
        <p>${prod.viewName }</p>
    </header>
    <div class="dh_pmianzs">
        <div class="zs_left">
            <img src="${prod.img }" alt="${prod.viewName }"/>
        </div>
        <div class="zf_right">
            <div>
                <p>产品名称：${prod.name }</p>
              <c:forEach items="${prodParams }" var="item">
                <c:if test="${item.mainParam == 'Y'}">
                  <p>${item.paramName }：${item.paramVal }</p>
                </c:if>
              </c:forEach>
                <a href="#">立即咨询</a>
            </div>
        </div>
    </div>
    <div class="cp_guige">
        <div class="ge_top">产品规格参数</div>
        <div class="ge_bot">
            <c:forEach items="${prodParams }" var="item">
              <c:if test="${item.mainParam != 'Y'}">
                <p>${item.paramName }：${item.paramVal }</p>
              </c:if>
            </c:forEach>
        </div>
    </div>
    <div class="qh_qxliuc">
        <div class="uc_qieh">
            <div class="hover">产品详情</div>
            <div>服务流程</div>
        </div>
        <div class="uc_neironx">
            ${detail.detail }
        </div>
        <div class="gz_liuchent">
            ${detail.servFlow }
        </div>
    </div>
    <div style="height: 3rem;"></div>
    <a class="lj_zhixun" href="#">立 即 咨 询</a>
    
    <%@ include file="zixun_dialog.jsp" %>
</body>
</html>