/*通用工具*/

function addEvent(obj,evt,fn) {                                           
    var saved;  
    if (typeof obj["on"+evt] == "function") {  
        saved = obj["on"+evt];  
    }  
    obj["on"+evt] = function () {  
        if (saved) saved();       
        fn();                 
    }                     
}  

function windowReady(fn){
    addEvent("window", "load", fn);
}

var serializeJson = function(elemSel){
    var serializeObj={};  
    var array=$(elemSel).serializeArray();  
   // var str=$(elemSel).serialize();  
    $(array).each(function(){  
        if(serializeObj[this.name]){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
};

var NetUtil = {
		
  /* 参数形式 url [, data, success, faiure, extOptions]
   * 
   * 例如：NetUtil.ajaxload("http://www.csaimall.com/index.do")
   *     NetUtil.ajaxload("http://www.csaimall.com/index.do", function(result){alert(result)})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", {name1:value1,name2:value2})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", "#formId")
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", "#formId", function(result){alert(result)})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", {name1:value1,name2:value2}, function(result){alert(result)}, function(result){alert(result)})
   * 
   *  指定 extOptions参数：
   *     NetUtil.ajaxload("http://www.csaimall.com/index.do", {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *     NetUtil.ajaxload("http://www.csaimall.com/index.do", function(result){alert(result)}, {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", {name1:value1,name2:value2}, {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", "#formId", {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", "#formId", function(result){alert(result)}, {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *     NetUtil.ajaxload("http://www.csaimall.com/upload.do", {name1:value1,name2:value2}, function(result){alert(result)}, function(result){alert(result)}, {_elemSel:'#submit', _denyClass:'deny', _checker: formChecker})
   *  注意：
   *    extOptions 目前只支持一下参数：
   *       _elemSel ： 这是用来给jquery选择点击按钮的选择器，如果_elemSel没有指定，那么整个extOptions将失去意义 
   *       _denyClass ： 作用就是在点击按钮发出ajax请求之前，在指定的_elemSel按钮上添加一个_denyClass的禁用样式类，在该请求没有返回之前，不能再次点击；样式类默认为 deny
   *       _checker ： 用来指定表单数据校验器的，可以参考 csai-layui-checker.js 文件中的formChecker的写法;
   *       _locInput ：true|false，是否在表单参数校验失败的情况下，自动定位到出错表单域的锚点。默认为false
   * 注意：如果ajax请求返回的ResultModel对象中的redirectUrl字段不为空，那么优先处理这个重定向，其它callback逻辑将不会被执行
   * 下边举一个例子（注意input标签上的两个自定义属性“v-label”、“v-checkers”， 另外还要注意校验器是优先根据元素的id来选取元素，如果没有将会根据 input[name="xxx"] 的属性选择器来获取form表单中的元素的，如果某些特效表单，不是用input来作为输入源的，那么一定要在对应的元素上添加 id="xxx" 属性）
   *  另外，如果没有指定“v-label”，校验器将会尝试获取元素的placeholder来作为错误提示标签名：
   *   <form id="loginForm">
   *     <input type="text" name="phone" id="phone" v-label="手机号" v-checkers="phone" placeholder="请输入手机号">
   *     <input type="password" name="password" v-label="密码" v-checkers="lenMin6" placeholder="请输入密码"/> 
   *     <input type="button" name="btn_submit" id="btn_login" value="提交" class="tj_an">
   *   </form>
   *   
   *   <script>
   *     $("#btn_login").click(function(){
			NetUtil.ajaxload("${BASE_URI}/p2p/port/dologon.do", "#loginForm", function(result){
				window.location.href = "${BASE_URI}"+result.model;
			}, {
				_elemSel: "#btn_login",
				_locInput: true,  
				_checker: formChecker
			});
		});
   *   </script>
   */
  ajaxload: function(url){
	  if(!url){
		  MsgUtil.warn("url不能为空！");
		  return;
	  }  
	  
	  if(arguments.length > 5){
		  MsgUtil.warn("最多允许指定4个参数，形如 url [, data, success, faiure, extOptions]，请检查！");
		  return;
	  }
  
	  var options = {url: url, type: "GET"};
	  if(/^(https?:?)?\/\/.+/.test(url) && window.location.host != url.match(/^(https?:?)?\/\/([^\/]+)\/?.*/)[2]){
		  //如果不同域，则增加jsonp请求参数
		  options.dataType = 'jsonp';
	  }
	  
	  var successCallback = null; //寻找请求成功的回调函数
	  var finallyCallback = null; //寻找请求失败的回调函数
	  var extOptions = null; //其它扩展参数
	  if(arguments.length >= 2){ 
		  //第二个参数如果是一个方法，则用作success
		  if(typeof arguments[1] == "function"){
			  successCallback = arguments[1];
		  } else if(typeof arguments[1] == "object"){
			  if(arguments[1]._elemSel){
				  extOptions = arguments[1];
			  } else {
				  options.data = arguments[1];
				  options.type = "POST";
			  }
		  } else if (typeof arguments[1] == "string"){
			  if($(arguments[1]).length <= 0 ||  ElemUtil.getTagName(arguments[1]) != 'FORM'){
				  MsgUtil.warn("没有找到" + arguments[1] + "对应的form元素，请检查！");
				  return;
			  }
			  //options.data = $(arguments[1]).serialize();
			  options.data = serializeJson(arguments[1]);
			  options.type = "POST";
			  options.traditional = true;//这里设置为true，防止checkbox数组在传递时在属性后自动加上 [] 后缀
		  }
	  }
	  
	  if(arguments.length >= 3){
		  if(typeof arguments[1] == "function"){
			  if(typeof arguments[2] == "object"){
				  extOptions = arguments[2];
			  } else {
				  finallyCallback = arguments[2];
			  }
		  } else {
			  successCallback = arguments[2];
		  }
	  }
	  
	  if(arguments.length >= 4){
		  if(typeof arguments[1] == "function"){
			  if(typeof arguments[3] == "object"){
				  extOptions = arguments[3];
			  } else if(typeof arguments[3] == "function"){
				  finallyCallback = arguments[3];
			  } else {
				  MsgUtil.warn("参数错误，参数定义形如 url [, data, success, faiure, extOptions]，请检查。");
				  return ;
			  }
		  } else if (typeof arguments[3] == "object"){
			  extOptions = arguments[3];
		  } else if(typeof arguments[3] == "function"){
			  finallyCallback = arguments[3];
		  } else {
			  MsgUtil.warn("参数错误，参数定义形如 url [, data, success, faiure, extOptions]，请检查。");
			  return ;
		  }
	  }
	  
	  if(arguments.length == 5){
		  if(typeof arguments[4] == "object"){
			 extOptions = arguments[4]; 
		  } else {
			  MsgUtil.warn("参数错误，参数定义形如 url [, data, success, faiure, extOptions]，请检查。");
			  return ;
		  }
	  }
	  
	  var denyClass = "";
	  var elemSel = "";
	  if(extOptions){
		  //如果extOptions中存在_checker 对象，并且 options.data 不为空
		  if(typeof extOptions._checker == "object" && options.data){
			  var currFormSel = "";
			  if(typeof arguments[1] == "string"){
				  currFormSel = arguments[1];
			  }
			  
			  for(var key in options.data){
				  var currElem = $("#" + key);
				  if(currElem.length <= 0){
					  currElem = $(currFormSel + " input[name='"+key+"']");
				  }
				  var validators = currElem.attr("v-checkers");
				  validators = ElemUtil.clearSpace(validators);
				  if(validators){
					  var validatorArr = validators.split("|");
					  for(var i in validatorArr){
						  var validItem = validatorArr[i];
						  if(extOptions._checker[validItem] || "required" == validItem){
							  var label = currElem.attr("v-label");
							  if(!label){
								  label = currElem.attr("placeholder");
							  }
							  label = label ? label : key;
							  var result = null;
							  if("required" == validItem){
								  if(/^\s*$/.test(options.data[key])){
									  result = "必填项“"+label+"”不能为空";
								  }
							  } else if(!/^\s*$/.test(options.data[key])){
								  if($.isArray(extOptions._checker[validItem])){
									  if(extOptions._checker[validItem].length != 2){
										  result = "不支持的验证表达式(" + validItem + ")";
									  } else {
										  if(!extOptions._checker[validItem][0].test(options.data[key])){
											  result = extOptions._checker[validItem][1];
										  }
									  }
								  } else if(typeof extOptions._checker[validItem] == "function"){
									  result = extOptions._checker[validItem](options.data[key], label, options.data); //传递三个参数，数据域的值，数据域的标签，整个表单数据对象
								  }
							  }
							  //如果返回的result存在，并且是一个字符串，则说明校验失败
							  if(result && typeof result == "string"){
								  if(typeof extOptions._locInput != "undefined" && extOptions._locInput){
									  $("<a name='"+key+"'></a>").insertBefore(currElem);
									  location.href = "#" + key;
								  }
								  MsgUtil.warn(result);
								  return;
							  }
						  }
					  }
				  }
			  }
		  }
		  
		  denyClass = extOptions._denyClass ? extOptions._denyClass : "deny";
		  if(extOptions._elemSel && $(extOptions._elemSel).length > 0){
			  elemSel = extOptions._elemSel;
			  if($(elemSel).is("." + denyClass)){
				  MsgUtil.warn("请求正在处理，请勿重复点击");
				  return;
			  } else {
				  $(elemSel).addClass(denyClass);
			  }
		  }/* else {
			  console.log("not found element with selector '" + extOptions.elemSel + "'");
		  }*/
	  }
  
	  options.success = function(result) {
		                    if(result.redirectUrl){ // 如果返回参数中的redirectUrl不为空，那么优先处理这个重定向，其它逻辑不会被执行
		                    	location.href = result.redirectUrl;
		                    	return;
		                    }
				  			if(result.resultCode == "SUCCESS"){
				  				if(successCallback != null) {
				  					successCallback(result);
				  				} else {
				  					MsgUtil.success("操作成功！");
				  				}
				  			} else {
				  				if(finallyCallback == null){
				  					MsgUtil.error(result.resultMsg ? result.resultMsg : "操作失败！");
				  				}
				  			}
				  			
				  			if(finallyCallback != null && typeof finallyCallback == "function"){
				  				finallyCallback(result.resultCode == "SUCCESS", result);
				  			}
				  			
				  			if(elemSel){
				  				$(elemSel).removeClass(denyClass);
				  			}
				  		};
	  options.error = function(xhr){
		  if(elemSel){
			  $(elemSel).removeClass(denyClass);
		  }
		  if(typeof netErr == "function"){
			  var ret = netErr(xhr); //可以在页面中定义一个 netErr 的function，来自定义网络故障处理，如果该方法返回true，则将终止默认的错误处理行为，否则还会继续执行默认的错误处理
			  if(ret){
				  return;
			  }
		  }
		  
		  if(finallyCallback != null && typeof finallyCallback == "function"){
			  var ret = finallyCallback(false, {
				                                 "resultCode": xhr ? xhr.status : "UNKNOWN", 
				                                 "resultMsg": xhr && xhr.statusText ? xhr.statusText : (xhr.responseText ? xhr.responseText : "未知网络错误")
				                               }, 
				                               xhr);
			  if(ret){
				  return;
			  }
		  }
		  
		  if(!xhr){
			  MsgUtil.error("发生未知错误，请检查！");
		  } else if(xhr.status == 404){
			  MsgUtil.error("对不起，未找到您请求的网页(404)");
		  } else if(xhr.status == 500){
			  MsgUtil.error("对不起，服务器发生未知错误(500)");
		  } else if(xhr.status == 400){
			  MsgUtil.error("错误的请求参数(400)");
		  } else if(xhr.status == 401){
			  MsgUtil.error("未授权的访问(400)");
		  } else {
			  MsgUtil.error((xhr.statusText ? xhr.statusText : xhr.responseText) + "("+xhr.status+")");
		  }
	  };
	  
	  $.ajax(options);
  },
  
  _showMsg:function(elemId, msg){
		if($("#msg_" + elemId).length > 0){
			 if(ElemUtil.getTagName("#msg_" + elemId) == "INPUT"){
				 $("#msg_" + elemId).val(msg);
			 }else{
				 $("#msg_" + elemId).text(msg);
			 }
		}
  },
  uploadFile: function(elemId, module, success){
	  var currElem = $("#"+elemId);
  	  var fileName = elemId;
  	  var formId = "form_"+elemId+ "_"+new Date().getTime();
  	  var formstr = '<form id="'+formId+'" enctype="multipart/form-data" style="display:none">' +
  	                 //'<input name="'+fileName+'" type="file" value="'+value+'" />' + 
  	               // '<input type="hidden" name="upfileElemName" value="'+fileName+'">' +
  	                '<input type="hidden" name="module" value="'+module+'">' +
  	                (typeof _img_prefix_ != "undefined" ? '<input type="hidden" name="prefix" value="'+_img_prefix_+'">' : '') +
  	                '</form>';
  	  $("body").append(formstr);
  	  currElem.after(currElem.clone(true));
  	  currElem.attr("name", elemId);
  	  $("#" + formId).append(currElem);
	  var formData = new FormData($("#"+formId)[0]);
	  
	  NetUtil._showMsg(elemId, "正在上传...");
	  
	  $.ajax({
	      url: UPFILE_DOMAIN + '/file/upload.do',  //Server script to process data
	      type: 'POST',
	      //Ajax events
	      //beforeSend: beforeSendHandler,
	      success: function(data){
	    	  //alert(JSON.stringify(data));
	    	 if(data.resultCode == "SUCCESS"){
	    		 if($("#val_" + elemId).length > 0){
	    			 if(typeof useAccessUrl == "boolean" && useAccessUrl){
		    	    	$("#val_" + elemId).val(data.model.accessUrl);
	    	    	 } else{
	    	    		$("#val_" + elemId).val(data.model.accessUri);
	    	    	 }
	    		 }
	    		 if($("#prev_" + elemId).length > 0){
	    			 $("#prev_" + elemId).attr("href", data.model.accessUrl);
	    			 if($("#prev_" + elemId)[0].tagName != "IMG"){
	    				 $("#prev_" + elemId).text(data.model.originName ? data.model.originName : data.model.accessUri);
	    			 }
	    			 NetUtil._showMsg(elemId, "");
	    		 } else if($("#msg_" + elemId).length > 0){
	    			 NetUtil._showMsg(elemId, "文件上传成功");
	    		 } else if(!success){
	    			 MsgUtil.success("文件上传成功");
	    		 }
	    		 
	    		 if(typeof success == 'function'){
	    			 success(data.model);
	    		 }
	    	 } else {
	    		 var msg = data.resultMsg ? data.resultMsg : "文件上传失败";
	    		 if($("#msg_" + elemId).length > 0){
	    			 NetUtil._showMsg(elemId, msg);
	    		 } else {
	    			 MsgUtil.error(msg);
	    		 }
	    	 }
	      },
	      error: NetUtil._neterror,
	      // Form data
	      data: formData,
	      //Options to tell jQuery not to process data or worry about content-type.
	      cache: false,
	      contentType: false,
	      processData: false
	  });
  },
  uploadImg: function(elemId, module, success){
	  var currElem = $("#"+elemId);
  	  var fileName = elemId;
  	  var formId = "form_"+elemId+ "_"+new Date().getTime();
  	  var formstr = '<form id="'+formId+'" enctype="multipart/form-data" style="display:none">' +
  	                 //'<input name="'+fileName+'" type="file" value="'+value+'" />' + 
  	                //'<input type="hidden" name="upfileElemName" value="'+fileName+'">' +
  	                '<input type="hidden" name="module" value="'+module+'">' +
  	                (typeof _img_prefix_ != "undefined" ? '<input type="hidden" name="prefix" value="'+_img_prefix_+'">' : '') +
  	                '</form>';
  	  $("body").append(formstr);
  	  currElem.after(currElem.clone(true));
  	  currElem.attr("name", elemId);
  	  $("#" + formId).append(currElem);
	  var formData = new FormData($("#"+formId)[0]);
	  
	  NetUtil._showMsg(elemId, "正在上传...");
	  
	  $.ajax({
	      url: UPFILE_DOMAIN + '/image/upload.do',  //Server script to process data
	      type: 'POST',
	      //Ajax events
	      //beforeSend: beforeSendHandler,
	      success: function(data){
	    	  //alert(JSON.stringify(data));
	    	  if(data.resultCode == "SUCCESS"){
	    	    if($("#val_" + elemId).length > 0){
	    	    	if(typeof useAccessUrl == "boolean" && useAccessUrl){
	    	    		$("#val_" + elemId).val(data.model.accessUrl);
	    	    	} else{
	    	    		$("#val_" + elemId).val(data.model.accessUri);
	    	    	}
  	            }
  	            if($("#prev_" + elemId).length > 0){
      	            $("#prev_" + elemId).attr("src", data.model.accessUrl);
      	            NetUtil._showMsg(elemId, "");
      	        } else if($("#msg_" + elemId).length > 0){
      	        	NetUtil._showMsg(elemId, "文件上传成功");
      	        } else if(!success){
	    			 MsgUtil.success("图片上传成功");
	    		}
  	            
  	            if(ElemUtil.getTagName("#prevA_" + elemId) == "A"){
  	            	$("#prevA_" + elemId).attr("href", data.model.accessUrl);
  	            }
  	            
  	            if(typeof success == 'function'){
  	        	  success(data.model);
  	            }
	         } else {
	        	 var msg = data.resultMsg ? data.resultMsg : "图片上传失败";
	    		 if($("#msg_" + elemId).length > 0){
	    			 NetUtil._showMsg(elemId, msg);
	    		 } else {
	    			 MsgUtil.error(msg);
	    		 }
	    	 }
	      },
	      error: NetUtil._neterror,
	      // Form data
	      data: formData,
	      //Options to tell jQuery not to process data or worry about content-type.
	      cache: false,
	      contentType: false,
	      processData: false
	  });
  },
  
  _saveSuccess: function(result, toTabId){
	  var tabId = TabUtil.exists(toTabId);
	  if(tabId != -1){
		  var currTabId = TabUtil.getCurrTabId();
		  TabUtil.refreshTab(tabId);
		  TabUtil.removeTab(currTabId);
	  }else{
		  MsgUtil.success(result.resultMsg ? result.resultMsg : "操作成功");
	  }
  },
  doSave: function(url, toTabId, formId, success){
	  if(!formId){
		  if($("#_submit_div_").length > 0){
			  formId = "#" + $("#_submit_div_").closest("form").attr("id");
		  }
	  }
	  this.ajaxload(url, formId, function(result){
		  if(success && typeof success == "function"){
			  success(result, toTabId);
		  }else{
			  NetUtil._saveSuccess(result, toTabId);
		  }
	  });
  },
  doDel: function(url, tip, success){
	  MsgUtil.confirm("确定要" + tip + "吗？", function(index){
		  NetUtil.ajaxload(url, function(result){
			  MsgUtil.closeAll();
			  if(success && typeof success == "function"){
				  success(result);
			  }else{
				  if(typeof refreshCurrPage == "function"){
					  refreshCurrPage();
				  } else {
					  var currTabId = TabUtil.getCurrTabId();
					  TabUtil.refreshTab(currTabId);
				  }
			  }
		  });
	  });
  },
  /**
   * 
   * NetUtil.confirmBeforeRequest("/sys/depart/pass.do?pks=1");
   * NetUtil.confirmBeforeRequest("/sys/depart/pass.do?pks=1", "确定要关闭吗？");
   * NetUtil.confirmBeforeRequest("/sys/depart/pass.do?pks=1", "确定要关闭吗？", function(result){});
   * NetUtil.confirmBeforeRequest("/sys/depart/pass.do", "确定要关闭吗？", function(result){}, {"pks": 1});
   * NetUtil.confirmBeforeRequest("/sys/depart/pass.do", "确定要关闭吗？", function(result){}, "#dataForm");
   * 
   */
  confirmBeforeRequest: function(uri, title, success, data){
	  MsgUtil.confirm(title ? title : "确认执行此操作？", function(){
		  if(!success){
			  success = function(){
				  if($("#search").length > 0){
					  $("#search").trigger("click");
				  }else{
					  location.reload();
				  }
			  }
		  }
		  NetUtil.ajaxload(uri, data, success);
	  });
  },
  /**
   * 弹出3个按钮（比如：通过、不通过、取消）
   * NetUtil.decisionBeforeRequest("/sys/depart/pass.do?pks=1", "${BASE_URI!}/sys/depart/reject.do?pks=2");
   * NetUtil.decisionBeforeRequest("/sys/depart/pass.do?pks=1", "${BASE_URI!}/sys/depart/reject.do?pks=2", "请选择处理意见");
   * NetUtil.decisionBeforeRequest("/sys/depart/pass.do?pks=1", "${BASE_URI!}/sys/depart/reject.do", "请选择处理意见", {"pks": 2});
   * NetUtil.decisionBeforeRequest("/sys/depart/pass.do?pks=1", "${BASE_URI!}/sys/depart/reject.do?pks=2", "请选择处理意见", null, "同意", "不同意");
   * NetUtil.decisionBeforeRequest("/sys/depart/pass.do?pks=1", "${BASE_URI!}/sys/depart/reject.do?pks=2", "请选择处理意见", null, "同意", "不同意", function(result){}, function(result){});
   */
  decisionBeforeRequest: function(allowUri, denyUri, title, data, allowLabel, denyLabel, allowed, denied){
	  MsgUtil.decision(title ? title : "请选择要执行的操作", function(){
		  if(!allowed){
			  allowed = function(){
				  if($("#search").length > 0){
					  $("#search").trigger("click");
				  }else{
					  location.reload();
				  }
			  }
		  }
		  NetUtil.ajaxload(allowUri, data, allowed);
	  }, function(){
		  if(!denied){
			  denied = function(){
				  if($("#search").length > 0){
					  $("#search").trigger("click");
				  }else{
					  location.reload();
				  }
			  }
		  }
		  NetUtil.ajaxload(denyUri, data, denied);
	  }, allowLabel, denyLabel);
  }
}
/**
 *js方法的日期格式化 
 */
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}