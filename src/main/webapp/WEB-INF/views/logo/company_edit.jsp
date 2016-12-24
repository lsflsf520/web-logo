<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>添加公司</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body style="background:#CBE1FF">
    <form action="${base == "/" ? "" : base }/company/save.do" method="post">
      <input type="hidden" name="id" value="${company.id}">
      <table>
        <tbody>
          <tr>
            <td>公司名称:</td>
            <td><input name="name" value="${company.name}"></td>
          </tr>
        <tr>
            <td>公司官网:</td>
            <td><input name="url" value="${company.url}"></td>
          </tr>
        <tr>
            <td>QQ:</td>
            <td><input name="qq" value="${company.qq}"></td>
          </tr>
        <tr>
            <td>微信:</td>
            <td><input name="wx" value="${company.wx}"></td>
          </tr>
        <tr>
            <td>邮箱:</td>
            <td><input name="email" value="${company.email}"></td>
          </tr>
        <tr>
            <td>手机:</td>
            <td><input name="phone" value="${company.phone}"></td>
          </tr>
        <tr>
            <td>备注:</td>
            <td><textarea cols="30" rows="3" name="remark">${company.remark}</textarea></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="保 存"></td>
          </tr>
        </tbody>
      </table>

    </form>

  </body>
</html>
