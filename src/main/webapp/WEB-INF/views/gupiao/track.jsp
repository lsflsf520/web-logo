<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>跟踪的股票</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body style="background:#CBE1FF">
    <div><form action="${base == "/" ? "" : base }/gp/addTrackPiao.do"><input name="code"> <input type="submit" value="添加至跟踪列表"></form></div>
    <div>共<span style="font-color:red;margin-left:5px;margin-right:5px">${size }</span>只股票</div>
    <table>
       <thead>
         <tr>
           <th>股票代码</th>
           <th>股票名称</th>
           <th>首次跟踪日期</th>
           <th>最近跟踪日期</th>
           <th>跟踪次数</th>
         </tr>
       </thead>
     <c:forEach items="${piaos }" var="piao">
       <tr>
         <td><a target="_blank" href="http://stockpage.10jqka.com.cn/${piao.code }">${piao.code }</a></td>
         <td>${piao.name }</td>
         <td>${piao.createDayStr}</td>
         <td>${piao.trackDayStr}</td>
         <td>${piao.trackNum}</td>
       </tr>
     </c:forEach>
    </table>
  </body>
</html>
