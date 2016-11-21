<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%
    pageContext.setAttribute("base", request.getContextPath());
%>

<script type="text/javascript">
var WEB_ROOT = "${base}";
var PASSPORT_DOMAIN = "${passportDomain}";
var PROJECT_RES_DOMAIN = "${projectResDomain}";
var COMMON_RES_DOMAIN = "${commonResDomain}";
</script>