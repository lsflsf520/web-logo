<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>低开缺口股票</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body style="background:#CBE1FF">
    <table>
       <thead>
         <tr>
           <th>股票代码</th>
           <th>股票名称</th>
           <th>现        价</th>
           <th>今开盘价</th>
           <th>昨收盘价</th>
           <th>日       期</th>
         </tr>
       </thead>
     <c:forEach items="${piaos }" var="piao">
       <tr>
         <td><a target="_blank" href="http://stockpage.10jqka.com.cn/${piao.code }">${piao.code }</a></td>
         <td>${piao.name }</td>
         <td>${piao.currPrice / 100 }</td>
         <td>${piao.openPrice / 100 }</td>
         <td>${piao.yesterPrice / 100 }</td>
         <td>${piao.timeStr }</td>
       </tr>
     </c:forEach>
    </table>
  </body>
</html>
