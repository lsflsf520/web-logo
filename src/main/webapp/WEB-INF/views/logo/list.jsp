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
          <th>设计费</th>
          <th>设计利润</th>
          <th>发票</th>
          <th title="总利润(商标利润  + 设计利润)">总利润</th>
          <th title="订单总价(商标费  + 设计费)">总价</th>
          <th>预付款</th>
          <th title="待付款(订单总价  - 预付款)">待付款</th>
          <th title="我的费用是否结清">我费</th>
         <!--  <th title="上线的费用是否结清">他费</th>  -->
          <th title="此订单是否属于加急订单">加急</th>
          <th title="订单的类型">类型</th>
          <th>备注</th>
          <th>商标名</th>
          <th title="注册商标所属的类别，多个类别用顿号“、”隔开">商标类别</th>
          <th>公司</th>
          <th>公司地址</th>
          <th>已快递</th>
          <th>微信号</th>
        </tr>
      </thead>
      <tbody id="listTbl">
       <c:forEach items="${logoList }" var="logo">
         <tr ${logo.status > 0 ? "style='color:red;'" : ""} id="ptr_${logo.id }" ondblclick="editRow(${logo.id });">
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
           <td title="设计费"><input style="width:40px;" onblur="compute('ptr_${logo.id }');" class="designFee textedit" value="${logo.designFee }"><span class="textro">${logo.designFee }</span></td>
           <td title="设计利润"><input style="width:40px;" onblur="compute('ptr_${logo.id }');" class="designProfit textedit" value="${logo.designProfit }"><span class="textro">${logo.designProfit }</span></td>
           <td title="是否需要发票"><input type="checkbox" class="textedit bill" onclick="compute('ptr_${logo.id }');" ${logo.bill == 1 ? "checked" : "" }><input type="checkbox" class="textro" ${logo.bill == 1 ? "checked disabled" : "disabled" }></td>
           <td title="总利润(商标利润  + 设计利润)"><input style="width:40px;" class="totalProfit textedit" value="${logo.totalProfit }"><span class="textro">${logo.totalProfit }</span></td>
           <td title="订单总价(商标费  + 设计费)" class="totalPrice">${logo.logoFee + (logo.designFee == null ? 0 : logo.designFee) }</td>
           <td title="预付款"><input style="width:40px;" onblur="compute('ptr_${logo.id }');" class="firstPayment textedit" value="${logo.firstPayment }"><span class="textro">${logo.firstPayment }</span></td>
           <td title="待付款(订单总价  - 预付款)" class="remainFee">${logo.logoFee + (logo.designFee == null ? 0 : logo.designFee) - logo.firstPayment}</td>
           <td title="费用是否结清"><input type="checkbox" ${logo.myFeeStatus == 1 ? "checked" : "" } class="myFeeStatus textedit"><input type="checkbox" title='${logo.myFeeTimeStr == null ? "勾选之后代表我的费用已" : logo.myFeeTimeStr }结清' id="p_my_${logo.id }" class="textro" ${logo.myFeeStatus == 1 ? "checked disabled" : "" } onclick="payRemainFee(${logo.id }, 0)"></td>
           <!-- <td title="上线的费用是否结清"><input type="checkbox"  ${logo.chenFeeStatus == 1 ? "checked" : "" } class="chenFeeStatus textedit"><input type="checkbox" title='${logo.chenFeeTimeStr == null ? "勾选之后代表陈哥的费用已" : logo.chenFeeTimeStr }结清' id="p_chen_${logo.id }" class="textro" ${logo.chenFeeStatus == 1 ? "checked disabled" : "" } onclick="payRemainFee(${logo.id }, 1)"></td>  -->
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
           <td title="是否已快递">
             <input type="checkbox" ${logo.expressNum == "Y" ? "checked" : "" } class="expressNum textedit"><input type="checkbox" title='勾选之后代表已快递' id="exp_${logo.id }" class="textro" ${logo.expressNum == "Y" ? "checked disabled" : "" } onclick="updateExpress(${logo.id })">
           </td>
           <td title="微信号"><input style="width:80px;" class="wx textedit" value="${logo.wx }"><span class="textro weixin">${logo.wx }</span></td>
         </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>
    
    <div id="opDiv">
      <a name="adMd">&nbsp;</a>
      <p id="ap" class="firstBtn"><input type="button" value="添加一行" onclick="addRow();" /></p>
      <p id="scP" class="secondBtn" style="display:none;">
         <input type="button" value="保 存" onclick="saveRow();" style="margin-right:10px" />
         <input type="button" value="取 消" onclick="cancelRow();" style="margin-right:10px" />
         <span style="color:red;" id="errorMsg"></span>
      </p>
      <p class="firstBtn"><input type="button" value="复 制" onclick="copyRow();" /></p>
      <p class="firstBtn"><input type="button" value="删 除" onclick="delRow();" /></p>
      <span class="firstBtn" id="opErrMsg" style="color:red;margin-left:3px;"></span>
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
    
    <table style="display:none;">
     <tbody id="tmpl">
      <tr>
          <td><input type="radio" class="rd" name="logoSel" disabled></td>
          <td style="width:90px;">待结款</td>
          <td><input style="width:80px;" class="currDate"></td>
          <td title="申报人">
            <select class="applyPerson">
               <c:forEach items="${partners }" var="item">
                <option value="${item }" ${item == partner ? "selected" : "" }>${item }</option>
               </c:forEach>
             </select>
          </td>
          <td title="联系人"><input style="width:50px;" class="userName"></td>
          <td title="手机号"><input style="width:90px;" class="phone"></td>
          <td title="商标数量"><input style="width:40px;" onblur="compute();" class="num" value="1"></td>
          <td title="商标费"><input style="width:40px;" onblur="compute();" class="logoFee" value="1200"></td>
          <td title="设计费"><input style="width:40px;" onblur="compute();" class="designFee"></td>
          <td title="设计利润"><input style="width:40px;" onblur="compute();" class="designProfit"></td>
          <td title="是否需要发票"><input type="checkbox" onclick="compute();" class="bill"></td>
          <td title="总利润(商标利润  + 设计利润)"><input style="width:40px;" class="totalProfit" value="800"></td>
          <td title="订单总价(商标费  + 设计费)" class="totalPrice">1200</td>
          <td title="预付款"><input style="width:40px;" onblur="compute();" class="firstPayment"></td>
          <td title="待付款(订单总价  - 预付款)" class="remainFee">1200</td>
          <td title="我的费用是否结清"><input type="checkbox" value="1" class="myFeeStatus"></td>
         <!--  <td title="上线的费用是否结清"><input type="checkbox" value="1" class="chenFeeStatus"></td>  -->
          <td title="是否加急"><input type="checkbox" value="1" class="rapid"></td>
          <td title="类型">
            <select class="orderType" onchange="compute();">
              <c:forEach items="${typeMap }" var="item">
                <option value="${item.key }">${item.value }</option>
              </c:forEach>
            </select>
          </td>
          <td title="备注"><textarea rows="3" cols="10" class="remark"></textarea></td>
          <td title="商标名"><input style="width:80px;" class="logoName"></td>
          <td title="商标注册类别，多个类别用顿号隔开"><input style="width:40px;" class="logoTypes"></td>
          <td title="公司名称"><input style="width:100px;" class="company"></td>
          <td title="公司地址"><input style="width:100px;" class="customerAddr"></td>
          <td title="快递单号"><input type="checkbox" value="Y" class="expressNum"></td>
          <td title="微信号"><input style="width:80px;" class="wx"></td>
        </tr>
       </tbody>
    </table>
    
    <script type="text/javascript" src='${base == "/" ? "" : base }/js/jquery-1.11.2.min.js'></script>
    <script type="text/javascript" src='${base == "/" ? "" : base }/plugins/laydate/laydate.js'></script>
    <script type="text/javascript">
      var index=100;
      function addRow(){
    	  if($(".inedit").length > 0){
    		  if(confirm("目前有另外一行数据尚未保存，是否放弃保存！")){
    			  var orId = getSelId();
    	    	  if(orId){
    	    		  $("#ptr_" + orId + " .textro").show();
        	    	  $("#ptr_" + orId + " .textedit").hide();
        	    	  $(".rd").removeAttr("checked");
        	    	  $(".rd").removeClass("inedit");
    	    	  }else{
    	    		  cancelRow(true);
    	    	  }
    		  }else{
    		    return;
    		  }
    	  }
    	  ++index;
    	  $("#tmpl tr").attr("id", "tr_" + index);
    	  $("#listTbl").append($("#tmpl").html());
    	  
    	  $("#tmpl tr").removeAttr("id");
    	  $("#errorMsg").text("");
    	  
    	  $("#tr_" + index + " .currDate").val(formatDate(new Date()));
    	  
    	  $(".rd").attr("disabled", "disabled");
    	  $("#tr_" + index + " .rd").attr("checked", "checked");
    	  $("#tr_" + index + " .rd").addClass("inedit");
    	  
    	  $(".firstBtn").hide();
    	  $(".secondBtn").show();
    	  
    	  location.href="#adMd";
      }
      
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
      
      function cancelRow(noConfirm){
    	  if(noConfirm || confirm("内容尚未保存，确定取消？")){
    		  var orderId = getSelId();
	    	  if(orderId){
	    		  $("#ptr_" + orderId + " .textro").show();
    	    	  $("#ptr_" + orderId + " .textedit").hide();
	    	  }else{
	    	    $("#tr_" + index).remove();
	    	  }
	    	  $(".rd").removeAttr("disabled");
	    	  $(".rd").removeClass("inedit");
	    	  $(".rd").removeAttr("checked");
	    	  
	    	  $(".firstBtn").show();
	    	  $(".secondBtn").hide();
    	  }
      }
      
      function saveRow(){
    	  $("#errorMsg").text("");
    	  
    	  var currTrId = "#tr_" + index + " ";
    	  var selId = null;
    	  
    	  var orderId = getSelId();
    	  if(orderId && $("#ptr_" + orderId).length > 0){
    		  selId = "ptr_" + orderId;
    		  currTrId = "#" + selId + " ";
    	  }
    	  
    	  var param = {};
    	  param.id=orderId;
    	  param.createTime = $(currTrId + ".currDate").val();
    	  param.applyPerson = $(currTrId + ".applyPerson").val();
    	  param.userName = $(currTrId + ".userName").val();
    	  param.phone = $(currTrId + ".phone").val();
    	  param.num = $(currTrId + ".num").val();
    	  param.logoFee = $(currTrId + ".logoFee").val();
    	  param.designFee = $(currTrId + ".designFee").val();
    	  param.designProfit = $(currTrId + ".designProfit").val();
    	  param.bill = getCheckBoxVal("bill", selId);
    	  param.firstPayment = $(currTrId + ".firstPayment").val();
    	  param.totalProfit = $(currTrId + ".totalProfit").val();
    	  param.myFeeStatus = getCheckBoxVal("myFeeStatus", selId);
    	  param.chenFeeStatus = getCheckBoxVal("chenFeeStatus", selId);
    	  param.rapid = getCheckBoxVal("rapid", selId);
    	  param.orderType = $(currTrId + ".orderType").val();
    	  param.remark = $(currTrId + ".remark").val();
    	  param.logoName = $(currTrId + ".logoName").val();
    	  param.logoTypes = $(currTrId + ".logoTypes").val();
    	  param.company = $(currTrId + ".company").val();
    	  param.customerAddr = $(currTrId + ".customerAddr").val();
    	  param.expressNum = $(currTrId + ".expressNum").val();
    	  param.wx = $(currTrId + ".wx").val();
    	  
    	  param.firstPayment = param.firstPayment ? parseInt(param.firstPayment) : 0;
    	  param.num = param.num ? parseInt(param.num) : 0;
    	  param.logoFee = param.logoFee ? parseInt(param.logoFee) : 0;
    	  param.designFee = param.designFee ? parseInt(param.designFee) : 0;
    	  param.designProfit = param.designProfit ? parseInt(param.designProfit) : 0;
    	  
    	  
    	  if(!param.userName){
    		  $("#errorMsg").text("联系人不能为空");
    		  return;
    	  }
    	  
    	  /* if(!param.phone){
    		  $("#errorMsg").text("手机号不能为空");
    		  return;
    	  } */
    	  
    	  if(param.phone && !/^1\d{10}$/.test(param.phone = param.phone.trim())){
    		  $("#errorMsg").text("手机号格式不正确");
    		  return;
    	  }
    	  
    	  if(param.num == 0){
    		  $("#errorMsg").text("数量不能小于1");
    		  return;
    	  }
    	  if(param.orderType == 0 && param.logoFee < 400){
    		  $("#errorMsg").text("商标费不能小于400");
    		  return;
    	  }
    	  
    	  if(param.orderType == 0 && !param.logoName){
    		  $("#errorMsg").text("商标名不能为空");
    		  return;
    	  }
    	  
    	  if(param.orderType == 0 && !param.logoTypes){
    		  $("#errorMsg").text("商标类别不能为空");
    		  return;
    	  }
    	  
    	  //如果是单纯的设计订单，则设计费不能为0
    	  if(param.orderType == 8 && param.designFee == 0){
    		  $("#errorMsg").text("设计费不能为0");
    		  return;
    	  }
    	  
    	  if(param.orderType != 8 && !param.company){
    		  $("#errorMsg").text("公司不能为空");
    		  return;
    	  }
    	  
    	  if(param.orderType != 8 && !param.customerAddr){
    		  $("#errorMsg").text("公司地址不能为空");
    		  return;
    	  }
    	  
    	  var totalPrice = $(currTrId + " .totalPrice").text();
    	  totalPrice = totalPrice ? parseInt(totalPrice) : 0;
    	  if(totalPrice > 0 && param.firstPayment == totalPrice){
    		  if(!confirm("检测到此单客户已全款付清，是否继续保存？")){
    			  return;
    		  }
    		  if(param.myFeeStatus != 1 && param.chenFeeStatus != 1){
    			  $("#errorMsg").text("我费 和 陈费 必须至少勾选一个，因为客户已全款付清");
    			  return;
    		  }
    	  }
    	  
    	  if(totalPrice > 0 && param.firstPayment < totalPrice && (param.myFeeStatus == 1 || param.chenFeeStatus == 1) && !param.id){
    		  $("#errorMsg").text("如果勾选了 我费 或 陈费，那么预付款必须等于总价");
			  return;
    	  }
    	  
    	  if(param.firstPayment > totalPrice){
    		  $("#errorMsg").text("预付款不能大于总价");
			  return;
    	  }
    	  
    	  
    	  $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/saveLogo.do',
    		  data:param,
    		  type:"POST",
    		  datatype:"json",
    		  success:function(data){
    			  if(data && data.resultCode == "SUCCESS"){
    			      location.reload();
    			  }else{
    				  $("#errorMsg").text("对不起，保存失败！");
    			  }
    		  }
    	  });
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
      
      var typeCostMap = ${typeCostMap};
      var billPrice = ${billPrice};
      function compute(selId){
    	  var currTrId = "#tr_" + index + " ";
    	  if(selId){
    		  currTrId = "#" + selId + " ";
    	  }
    	  
    	  var num =  $(currTrId + ".num").val();
    	  var logoFee = $(currTrId + ".logoFee").val();
    	  var designFee = $(currTrId + ".designFee").val();
    	  var designProfit = $(currTrId + ".designProfit").val();
    	  var bill = getCheckBoxVal("bill", selId);
    	  var firstPayment = $(currTrId + ".firstPayment").val();
    	  
    	  var orderType = $(currTrId + ".orderType").val();
    	  if(orderType == 8){//如果订单类型为设计，则把商标费用清零
    		  logoFee = 0;
    		  $(currTrId + ".logoFee").val(logoFee);
    	  } else if(logoFee == 0){
    		  logoFee = 1200;
    		  $(currTrId + ".logoFee").val(logoFee);
    	  }
    	  
    	  var costPrice = typeCostMap[orderType] ? typeCostMap[orderType] : 0 ;
    	  
    	  num = num ? parseInt(num) : 0;
    	  logoFee = logoFee ? parseInt(logoFee) : 0;
    	  designFee = designFee ? parseInt(designFee) : 0;
    	  designProfit = designProfit ? parseInt(designProfit) : 0;
    	  firstPayment = firstPayment ? parseInt(firstPayment) : 0;
    	  
    	  var totalPrice = logoFee + designFee;
    	  var totalProfit = logoFee - num * (costPrice + billPrice * bill) + designProfit;
    	  var remainFee = totalPrice - firstPayment;
    	  
    	  $(currTrId + ".totalPrice").text(totalPrice);
    	  $(currTrId + ".totalProfit").val(totalProfit);
    	  $(currTrId + ".remainFee").text(remainFee);
      }
      
      function updateStatus(orderId, status){
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
      }
      
      function backupData(){
    	  $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/backupData.do',
    		  type:"GET",
    		  datatype:"json",
    		  success:function(data){
    			  if(data && data.resultCode == "SUCCESS"){
    			      alert("恭喜您，数据备份成功！");
    			  }else{
    				  alert("不好意思，数据备份失败，请重试！");
    			  }
    		  }
    	  });
      }
      
      function payRemainFee(orderId, who){
    	  if(confirm("此操作代表尾款已付清，点击‘确定’继续，点击‘取消’撤销改操作。")){
    	    $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/payRemainFee.do?who=' + who + '&orderId=' + orderId,
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
    	  }else{
    		 var prefix = "p_my_";
    		 if(who != 0){
    			 prefix = "p_chen_";
    		 }
    		 
    		 $("#" + prefix + orderId).removeAttr("checked");
    	  }
      }
      
      function updateExpress(orderId){
    	  if(confirm("此操作代表快递已寄送，点击‘确定’继续，点击‘取消’撤销改操作。")){
    	    $.ajax({
    		  url:'${base == "/" ? "" : base}/logo/updateExpress.do?orderId=' + orderId,
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
    	  }else{
    		 var prefix = "exp_";
    		 $("#" + prefix + orderId).removeAttr("checked");
    	  }
      }
      
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
      
      function copyRow(){
    	  $("#opErrMsg").text("");
    	  var orderId = getSelId();
    	  if(!orderId){
    		  $("#opErrMsg").text("请先在左侧点选需要下载的商标订单按钮");
    		  return;
    	  }
    	  
    	  addRow();
    	  
    	  var currTrId = "#tr_" + index + " ";
    	  var pTrId = "#ptr_" + orderId + " ";
    	  $(currTrId + ".userName").val($(pTrId + ".un").text());
    	  $(currTrId + ".phone").val($(pTrId + ".ph").text());
    	  $(currTrId + ".company").val($(pTrId + ".cp").text());
    	  $(currTrId + ".customerAddr").val($(pTrId + ".ca").text());
    	  $(currTrId + ".wx").val($(pTrId + ".weixin").text());
      }
      
      function editRow(orderId){
    	  if($(".inedit").length > 0){
    		  if(confirm("目前有另外一行数据尚未保存，是否放弃保存！")){
    			  var orId = getSelId();
    	    	  if(orId){
    	    		  $("#ptr_" + orId + " .textro").show();
        	    	  $("#ptr_" + orId + " .textedit").hide();
        	    	  $(".rd").removeAttr("checked");
        	    	  $(".rd").removeClass("inedit");
    	    	  }else{
    	    		  cancelRow(true);
    	    	  }
    		  }else{
    		    return;
    		  }
    	  }
    	  
    	  $("#ptr_" + orderId + " .textro").hide();
    	  $("#ptr_" + orderId + " .textedit").show();
    	  $("#ptr_" + orderId + " .rd").attr("checked", "checked");
    	  $("#ptr_" + orderId + " .rd").addClass("inedit");
    	  
    	  $(".firstBtn").hide();
    	  $(".secondBtn").show();
    	  
    	  location.href="#md_" + orderId;
      }
      
      function getSelId(){
    	  var ckId = "";
    	  if($("input[name='logoSel']:checked").attr("disabled") != "disabled"){
    		  ckId = $("input[name='logoSel']:checked").val();
    	  }
    	  var inId = $("input.inedit").val();
    	  return ckId && "on" != ckId ? ckId : (inId && "on" != inId ? inId : null);
      }
      
      document.onkeydown = function(e){
    	  var ev = document.all ? window.event : e;
    	  if(ev.keyCode==13){
    	    saveRow();
    	  }
       };
      
    </script>
  </body>
</html>
