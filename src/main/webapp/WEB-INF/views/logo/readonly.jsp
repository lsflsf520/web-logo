<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>商标相关合作客户管理</title>
    
    <style type="text/css">
      table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
      th,td{border-right:1px solid #888;border-bottom:1px solid #888;text-align: center;}
      th{font-weight:bold;background:#ccc;} 
      div{margin-bottom:10px;}
      .data span{margin-right:5px;}
      .rlink{margin-top:70px;}
      .rlink a{margin-left:3px;}
      #opDiv p {display:inline;}
      .textedit {display:none;}
    </style>
  </head>

  <body>
    <h3 style="line-height:32px;">商标相关合作客户管理</h3>
    
    <div>
      <form action='${base == "/" ? "" : base }/logo/list.do'>
        <input type="hidden" name="timeType" value="${timeType }" id="timeType" >
       <span>
         <input type="button" onclick="switchTimeType()" value="切 换">
       </span>
       <span>
         <select id="yearMonthQ" name="yearMonth" onchange="$('#qryBtn').click()" style='${timeType==0 ? "" : "display:none"}'>
           <option value="-1" ${"-1" == yearMonth ? "selected" : ""  }>请选择月份</option>
          <c:forEach items="${timeList }" var="time">
           <option value="${time }" ${time == yearMonth ? "selected" : "" }>${time }</option>
          </c:forEach>
         </select>
       </span>
       <span id="dateCond" style='${timeType!=0 ? "" : "display:none"}'>
         <input name="startDate" value="${startDate }" onclick="laydate({format:'YYYY-MM-DD'})" />
         <input name="endDate" value="${endDate }" onclick="laydate({format:'YYYY-MM-DD'})" />
       </span>
       <span>
         <select id="statusQ" name="status" style="margin-left:3px;"  onchange="$('#qryBtn').click()">
            <option value="" ${null == status ? "selected" : ""  }>请选择状态</option>
          <c:forEach items="${statusMap }" var="item">
            <option value="${item.key }" ${item.key == status ? "selected" : "" }>${item.value }</option>
          </c:forEach>
         </select>
       </span>
       <span>
         <select id="partnerQ" name="partner" style="margin-left:3px;" onchange="$('#qryBtn').click()">
           <option value="" ${null == partner ? "selected" : ""  }>请选择申报人</option>
           <c:forEach items="${partners }" var="item">
            <option value="${item }" ${item == partner ? "selected" : "" }>${item }</option>
          </c:forEach>
         </select>
       </span>
       <input id="keywordQ" name="keyword" value="${keyword }" placeholder="请输入查询关键字" style="margin-left:3px;">
       <input type="submit" id="qryBtn" value="查 询" style="margin-left:3px;">
       <input type="button" value="备份数据" onclick="backupData()" style="margin-left:8px;">
       <input type="button" value="下载委托书" onclick="downloadDoc('delegate')" style="margin-left:3px;">
       <input type="button" value="下载协议书" onclick="downloadDoc('agent')" style="margin-left:3px;">
       <input type="button" value="下载申请书" onclick="downloadDoc('apply')" style="margin-left:3px;">
       <span style="color:red;" id="dlErrMsg" style="margin-left:3px;"></span>
      </form>
    </div>
    <div class="data">
      <span title="本页订单总金额">总流水：${totalMoney }&#65509;</span>
      <span title="本页订单总利润">总利润：${totalProfit }&#65509;</span>
      <span title="本页订单已收总金额">已收金额：${hasGetMoney }&#65509;</span>
      <span title="本页订单已收利润">已收利润：${hasGetProfit }&#65509;</span>
      <span title="本页订单总设计费">总设计费：${totalDesignFee }&#65509;</span>
      <span title="本页订单总设计利润">总设计利润：${totalDesignProfit }&#65509;</span>
      <span title="本页订单的商标总数">商标总数：${logoNum }个</span>
    </div>
    <div>
     <table>
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th style="width:90px;">状态/操作</th>
          <th>日期</th>
          <th>申报人</th>
          <th>联系人</th>
          <th>手机号</th>
          <th>数量</th>
          <th>商标费</th>
          <th>发票</th>
          <th title="我的费用是否结清">我费</th>
          <th title="此订单是否属于加急订单">加急</th>
          <th title="订单的类型">类型</th>
          <th>备注</th>
          <th>商标名</th>
          <th title="注册商标所属的类别，多个类别用顿号“、”隔开">商标类别</th>
          <th>公司</th>
          <th>公司地址</th>
          <th>快递</th>
          <th>微信号</th>
        </tr>
      </thead>
      <tbody id="listTbl">
       <c:forEach items="${logoList }" var="logo">
         <tr ${logo.status > 0 ? "style='color:red;'" : ""} id="ptr_${logo.id }">
           <td><input type="radio" class="rd" name="logoSel" value="${logo.id }"><a name="md_${logo.id }">&nbsp;</a></td>
           <td style="width:90px;">
             <c:choose>
               <c:when test="${logo.status == 1 }">
                 <a href="javascript:updateStatus(${logo.id }, 2)" ${logo.status > 0 ? "style='color:red;'" : ""} title="钱款已付清，点击后表示已提交至商标局">提局</a>
               </c:when>
               <c:when test="${logo.status == 2 }">
                 <a href="javascript:updateStatus(${logo.id }, 3)" ${logo.status > 0 ? "style='color:red;'" : ""} title="点击后表示客户已经拿到受理通知书${logo.timeTip}">受理</a><br/>
                 <a href="javascript:updateStatus(${logo.id }, 4)" ${logo.status > 0 ? "style='color:red;'" : ""} title="点击后表示商标局不受理${logo.timeTip }">不受理
               </c:when>
               <c:when test="${logo.status == 3 }">
                 <a href="javascript:updateStatus(${logo.id }, 5)" ${logo.status > 0 ? "style='color:red;'" : ""} title="点击后表示客户已经拿到商标证${logo.timeTip }">拿证</a><br/>
                 <a href="javascript:updateStatus(${logo.id }, 6)" title="点击后表示商标局已驳回${logo.timeTip }">拒证</a>
               </c:when>
               <c:otherwise>
                 ${statusMap[logo.status] }
               </c:otherwise>
             </c:choose>
           </td>
           <td style="width:80px;"><input style="width:80px;" class="currDate textedit" value="${logo.createTimeStr }"><span class="textro">${logo.createTimeStr }</span></td>
           <td title="申报人">
             <select class="applyPerson textedit">
               <c:forEach items="${partners }" var="item">
                <option value="${item }" ${item == partner ? "selected" : "" }>${item }</option>
               </c:forEach>
             </select>
             <span class="textro ap">${logo.applyPerson }</span>
           </td>
           <td title="联系人"><input style="width:50px;" class="userName textedit" value="${logo.userName }"><span class="textro un">${logo.userName }</span></td>
           <td title="手机号"><input style="width:90px;" class="phone textedit" value="${logo.phone }"><span class="textro ph">${logo.phone }</span></td>
           <td title="商标数量"><input style="width:40px;" onblur="compute('ptr_${logo.id }');" class="num textedit" value="${logo.num }"><span class="textro">${logo.num }</span></td>
           <td title="商标费"><input style="width:40px;" onblur="compute('ptr_${logo.id }');" class="logoFee textedit" value="${logo.logoFee }"><span class="textro">${logo.logoFee }</span></td>
           <td title="是否需要发票"><input type="checkbox" class="textedit bill" onclick="compute('ptr_${logo.id }');" ${logo.bill == 1 ? "checked" : "" }><input type="checkbox" class="textro" ${logo.bill == 1 ? "checked disabled" : "disabled" }></td>
           <td title="我的费用是否结清"><input type="checkbox" ${logo.myFeeStatus == 1 ? "checked" : "" } class="myFeeStatus textedit"><input type="checkbox" title='${logo.myFeeTimeStr == null ? "勾选之后代表我的费用已" : logo.myFeeTimeStr }结清' id="p_my_${logo.id }" class="textro" ${logo.myFeeStatus == 1 ? "checked disabled" : "" } onclick="payRemainFee(${logo.id }, 0)"></td>
           <td title="是否加急"><input type="checkbox" ${logo.rapid == 1 ? "checked disabled" : "disabled" }></td>
           <td><select class="orderType textedit" onchange="compute('ptr_${logo.id }');">
              <c:forEach items="${typeMap }" var="item">
                <option value="${item.key }" ${item.key == logo.orderType ? "selected" : "" }>${item.value }</option>
              </c:forEach>
            </select><span class="textro">${typeMap[logo.orderType] }</span></td>
           <td title="备注"><textarea rows="3" cols="10" class="remark textedit">${logo.remark }</textarea><span class="textro">${logo.remark }</span></td>
           <td title="商标名"><input style="width:80px;" class="logoName textedit" value="${logo.logoName }"><span class="textro">${logo.logoName }</span></td>
           <td title="商标注册类别，多个类别用顿号隔开"><input style="width:40px;" class="logoTypes textedit" value="${logo.logoTypes }"><span class="textro">${logo.logoTypes }</span></td>
           <td title="公司名称"><input style="width:100px;" class="company textedit" value="${logo.company }"><span class="textro cp">${logo.company }</span></td>
           <td title="公司地址"><input style="width:100px;" class="customerAddr textedit" value="${logo.customerAddr }"><span class="textro ca">${logo.customerAddr }</span></td>
           <td title="快递单号"><input style="width:150px;" class="expressNum textedit" value="${logo.expressNum }"><span class="textro">${logo.expressNum }</span></td>
           <td title="微信号"><input style="width:80px;" class="wx textedit" value="${logo.wx }"><span class="textro weixin">${logo.wx }</span></td>
         </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>
    
    
    <div class="rlink">
      <span>友情链接：</span>
      <a target="_blank" href="http://www.baidu.com">百 度</a>
      <a target="_blank" href="http://sbcx.saic.gov.cn:9080/tmois/wscxsy_getIndex.xhtml">商标局官网查询</a>
      <a target="_blank" href="http://zt.ipr.zbj.com/mark/jjsbcx/?pmcode=896654&fromcode=12142980&utm_source=baidu&utm_medium=SEM&_union_identify=16&_union_uid=12142980&_union_itemid=89619">八戒商标查询</a>
      <a target="_blank" href="http://www.zxripr.com/inner?bd1?bd?sbzcej?sbzccx-shangbiaochaxunzhuce">细软网商标查询</a>
      <a target="_blank" href="http://www.haotm.com/">好标网商标查询</a>
      <a target="_blank" href="http://www.sbfl.cn/">尼斯商标分类网</a>
    </div>
    
    
    <script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
    <script type="text/javascript" src='${base == "/" ? "" : base }/plugins/laydate/laydate.js'></script>
    <script type="text/javascript">
      
      function switchTimeType(){
    	  var timeType = $("#timeType").val();
    	  if("0" == timeType){
    		  $("#yearMonthQ").css("display", "none");
    		  $("#dateCond").css("display", "inline");
    		  $("#timeType").val("1");
    	  }else{
    		  $("#yearMonthQ").css("display", "inline");
    		  $("#dateCond").css("display", "none");
    		  $("#timeType").val("0");
    	  }
      }
      
      
      function formatDate(date) {
    	    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
      }
      
      function getCheckBoxVal(clazz, selId){
    	  var currTrId = "#tr_" + index + " ";
    	  if(selId){
    		  currTrId = "#" + selId + " ";
    	  }
    	  return $(currTrId+" ." + clazz).is(":checked") ? 1 : 0;
      }
      
      
     /* function updateStatus(orderId, status){
    	  $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/updateStatus.do?status=' + status + '&orderId=' + orderId,
    		  type:"GET",
    		  datatype:"json",
    		  success:function(data){
    			  if(data && data.resultCode == "SUCCESS"){
    			      location.reload();
    			  }else{
    				  $("#errorMsg").text("对不起，保存失败！");
    			  }
    		  }
    	  });
      } */
      
      
      function downloadDoc(type){
    	  $("#dlErrMsg").text("");
    	  var orderId = getSelId();
    	  if(!orderId){
    		  $("#dlErrMsg").text("请先在左侧点选需要下载的商标订单按钮");
    		  return;
    	  }
    	  window.open('${base == "/" ? "" : base}/logo/downloadDoc.do?orderId=' + orderId + '&type=' + type);
      }
      
      function delRow(){
    	  $("#opErrMsg").text("");
    	  var orderId = getSelId();
    	  if(!orderId){
    		  $("#opErrMsg").text("请先在左侧点选需要下载的商标订单按钮");
    		  return;
    	  }
    	  
    	  if(!confirm("确定删除此行吗？")){
    		  return;
    	  }
    	  
    	  $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/delOrder.do?orderId=' + orderId,
    		  type:"GET",
    		  datatype:"json",
    		  success:function(data){
    			  if(data && data.resultCode == "SUCCESS"){
    			      location.reload();
    			  }else{
    				  $("#opErrMsg").text(data.errorMsg ? data.errorMsg : "对不起，删除失败！");
    			  }
    		  }
    	  });
      }
      
      function getSelId(){
    	  var ckId = "";
    	  if($("input[name='logoSel']:checked").attr("disabled") != "disabled"){
    		  ckId = $("input[name='logoSel']:checked").val();
    	  }
    	  var inId = $("input.inedit").val();
    	  return ckId && "on" != ckId ? ckId : (inId && "on" != inId ? inId : null);
      }
      
    </script>
  </body>
</html>
