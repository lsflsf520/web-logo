<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="db_daoh">
    <a class="${currPageName == 'index' || currPageName == null ? 'hover' : '' }" title="首页-${site.shortName }" href="/">
        <div>
            <img src="/static/weizhan/image/dbdht01.png" alt="首页-${site.shortName }"/>
            <p>首页</p>
        </div>
    </a>
    <a href="/wz/environment.do?currPage=1" class="${currPageName == 'environment' ? 'hover' : '' }" title="环境-${site.shortName }">
        <div>
            <img src="/static/weizhan/image/dbdht02.png" alt="环境-${site.shortName }"/>
            <p>环境</p>
        </div>
    </a>
    <a href="/wz/prodList.do" class="${currPageName == 'prodList' ? 'hover' : '' }" title="产品-${site.shortName }">
        <div>
            <img src="/static/weizhan/image/dbdht03.png" alt="产品-${site.shortName }"/>
            <p>产品</p>
        </div>
    </a>
    <a href="/wz/dynamic.do?currPage=1" class="${currPageName == 'dynamic' ? 'hover' : '' }" title="动态-${site.shortName }">
        <div>
            <img src="/static/weizhan/image/dbdht04.png" alt="动态-${site.shortName }"/>
            <p>动态</p>
        </div>
    </a>
</div>

<%@ include file="script.jsp" %>

<%@ include file="zixun_dialog.jsp" %>