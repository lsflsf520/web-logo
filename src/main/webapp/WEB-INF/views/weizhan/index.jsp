<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0" />
    
    <meta http-equiv="Content-Language" content="utf-8">
    <meta name="robots" content="all">
    <meta name="author" content="${site.shortName }">
    <meta name="Copyright" content="${site.shortName }">
    <title>${site.name }</title>
    <meta name="keyword" content="${site.keyword }" >
    <meta name="description" content="${site.description }">
    
    <link rel="stylesheet" href="/static/weizhan/css/mui.min.css"/>
    <link rel="stylesheet" href="/static/weizhan/css/index.css?v=1"/>
    <style>
        .mui-slider .mui-slider-group .mui-slider-item img{height: 120px;}
    </style>
</head>
<body>
    <header class="indsy_top">
        <img src="${site.logo }" alt="${site.name }"/>
        <span>${site.name }</span>
    </header>
    <!--mui轮播开始-->
    <div class="mui_lunbot">
        <div id="slider" class="mui-slider" >
            <div class="mui-slider-group mui-slider-loop">
                <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                <div class="mui-slider-item mui-slider-item-duplicate">
                    <a href="#">
                        <img src="${site.firstBanner }">
                    </a>
                </div>
                <!-- 第一张 -->
              <c:forEach var="item" items="${site.banners}" varStatus="status"> 
                <div class="mui-slider-item">
                    <a href="#">
                        <img src="${item }">
                    </a>
                </div>
              </c:forEach> 
                <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
                <div class="mui-slider-item mui-slider-item-duplicate">
                    <a href="#">
                        <img src="${site.lastBanner }">
                    </a>
                </div>
            </div>
            <div class="mui-slider-indicator">
               <c:forEach var="item" items="${site.banners}" varStatus="status"> 
                 <div class="mui-indicator ${status.index == 0 ? 'mui-active' : '' }"></div>
               </c:forEach>
            </div>
        </div>
    </div>
    <!--mui轮播结束-->
    <div class="neiro_text">
        ${site.remark }
    </div>
    <div class="wdmx_pian">
        <div class="z_minpz">
            <div>
                <img src="${site.wxQrcode }" alt=""/>
                <p>长按识别二维码加微信</p>
            </div>
        </div>
        <div class="z_minpy">
            <div>
                <p>联系人：${site.contactName }</p>
                <p>手机号：${site.contactPhone }</p>
                <p>联系地址：${site.contactAddr }</p>
            </div>
        </div>
    </div>
    <a class="lj_zhixun" href="#">立 即 咨 询</a>
    <a class="kf_liaojie" href="#"><i></i><img src="/static/weizhan/image/xj_lxkf.jpg" alt=""/><p>点击了解更多</p></a>
    <div style="height: 8rem;"></div>
    <div class="db_daoh">
        <a class="hover" href="/">
            <div>
                <img src="/static/weizhan/image/dbdht01.png" alt=""/>
                <p>首页</p>
            </div>
        </a>
        <a href="page/environment.html">
            <div>
                <img src="/static/weizhan/image/dbdht02.png" alt=""/>
                <p>环境</p>
            </div>
        </a>
        <a href="page/particulars.html">
            <div>
                <img src="/static/weizhan/image/dbdht03.png" alt=""/>
                <p>产品</p>
            </div>
        </a>
        <a href="page/dynamic.html">
            <div>
                <img src="/static/weizhan/image/dbdht04.png" alt=""/>
                <p>动态</p>
            </div>
        </a>
    </div>

    <script src="/static/weizhan/js/jquery-1.7.2.min.js"></script>
    <script src="/static/weizhan/js/mui.min.js"></script>
    <script src="/static/weizhan/js/common.js?v=1"></script>
    <script type="text/javascript" charset="utf-8">
        mui.init({
            swipeBack:true //启用右滑关闭功能
        });
        var slider = mui("#slider");
        slider.slider({
            interval: 5000
        });
    </script>
</body>
</html>