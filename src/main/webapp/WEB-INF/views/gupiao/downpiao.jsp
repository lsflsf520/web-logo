<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>连续${days}天下跌的股票</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body style="background:#CBE1FF">
    <div>共<span style="font-color:red;margin-left:5px;margin-right:5px">${size }</span>只股票</div>
    <table>
       <thead>
         <tr>
           <th>股票代码</th>
           <th>股票名称</th>
           <th>现    价</th>
           <th>今开盘价</th>
           <th>操    作</th>
         </tr>
       </thead>
     <c:forEach items="${piaos }" var="piao">
       <tr>
         <td><a target="_blank" href="http://stockpage.10jqka.com.cn/${piao.code }">${piao.code }</a></td>
         <td>${piao.name }</td>
         <td>${piao.price / 100 }</td>
         <td>${piao.openPrice / 100 }</td>
         <td><a href="javascript:addTrackPiao('${piao.code }');">添加到跟踪</a></td>
       </tr>
     </c:forEach>
    </table>

    <script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
    <script type="text/javascript">
      function addTrackPiao(code) {
        $.ajax({
          url:'${base == "/" ? "" : base}/gp/addTrackPiao.do?code=' + code,
          type:"POST",
          datatype:"text",
          success:function(data){
            if(data && data.indexOf("addTrackPiao") > 0){
              alert("添加成功!");
            }else{
              alert("添加失败!")
            }
          }
        });
      }
    </script>
  </body>
</html>
