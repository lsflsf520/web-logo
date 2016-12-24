<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>公司花名册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body style="background:#CBE1FF">
    <div>
      共<span style="font-color:red;margin-left:5px;margin-right:5px">${size }</span>个公司
      <span style="margin-left:10px;"><a href="${base == "/" ? "" : base }/company/toedit.do">添加公司</a></span>
    </div>
    <table>
       <thead>
         <tr>
           <th>公司名称</th>
           <th>qq</th>
           <th>微信</th>
           <th>邮箱</th>
           <th>手机号</th>
           <th>备注</th>
           <th>操作</th>
         </tr>
       </thead>
     <c:forEach items="${companyList }" var="company">
       <tr>
         <td><a target="_blank" href="${company.url}">${company.name }</a></td>
         <td>${company.qq }</td>
         <td>${company.wx }</td>
         <td>${company.email }</td>
         <td>${company.phone }</td>
         <td>${company.remark }</td>
         <td><a href="${base == "/" ? "" : base }/company/toedit.do?id=${company.id}">修改</a></td>
       </tr>
     </c:forEach>
    </table>

  </body>
</html>
