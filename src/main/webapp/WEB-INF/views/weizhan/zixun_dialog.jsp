<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="zx_xintx show_hide">
  <div class="mask"></div>
  <div class="tx_bskuank">
    <div class="nk_top">请填写信息<i id="closeI"></i></div>
    <div class="nk_bot" id="zixunDiv">
	  
	  <!-- <div class="ot_phone ty_shur">
	    <span>手机号</span>
	    <input type="text"/>
	  </div>
	  <div class="ot_sex">
	    <span>性别</span>
	    <div>
	      <div class="hover">男</div>
	      <div>女</div>
	    </div>
	  </div>
	  <div class="ot_hobby">
	    <span>兴趣爱好</span>
	    <div>
	      <div class="hover">乒乓球</div>
	      <div>足球</div>
	    </div>
	  </div>
	  <div class="ot_qq ty_shur">
	    <span>QQ</span>
	    <input type="text"/>
	  </div>
	  <div class="ot_category">
	    <span>咨询类别</span>
	    <div>
	      <select>
	        <option value ="volvo">Volvo</option>
	        <option value ="saab">Saab</option>
	        <option value="opel">Opel</option>
	        <option value="audi">Audi</option>
	      </select>
	      <i></i>
	     </div>
	   </div>
	   <div class="ot_supplement">
	    <span>补充信息</span>
	    <textarea name="" id="" placeholder="多行输入"></textarea>
	   </div>
	   <div class="ot_sbbtn"><button>提交</button></div>
	    -->
    </div>
  </div> <!-- tx_bskuank -->
</div> <!-- zx_xintx -->

<script type="text/javascript">
var fieldIndex = 0;
NetUtil.ajaxload('/wz/loadConsultConfig.do', function(result){
	var html = '';
	$(result.model).each(function(i, d){
		
		if(d.optionType == 'Text') {
			html+='<div class="ot_phone ty_shur">' +
		            '<span>'+d.fieldName+'</span>' +
		            '<input class="field" require="'+d.required+'" key="'+d.fieldName+'" type="text" />' +
		          '</div>';
		} else if(d.optionType == 'Select'){
			var option = '';
			$(d.options).each(function(i, d){
				option += '<option value ="'+d+'">'+d+'</option>';
			})
			html+= '<div class="ot_category">' +
			    '<span>'+d.fieldName+'</span>' +
			    '<div>' +
			      '<select class="field" require="'+d.required+'" key="'+d.fieldName+'">' +
			      option +
			      '</select>' +
			      '<i></i>' +
			     '</div>' +
			   '</div>';
		} else if(d.optionType == 'Checkbox'){
			var option = '';
			$(d.options).each(function(i, d){
				var currFieldKey = "field_" + (fieldIndex + '_' + i);
				option += '<div id="'+currFieldKey+'" class="optDiv '+(i==0 ? 'hover' : '') +'" onclick="selOrDesel(\''+currFieldKey+'\');">'+d+'</div>';
			});
			html += '<div class="ot_hobby">' +
			    '<span class="crfield" require="'+d.required+'" key="'+d.fieldName+'">'+d.fieldName+'</span>' + 
			    '<div>' +
			      option +
			    '</div>' +
			  '</div>';
		} else if(d.optionType == 'Radio'){
			var option = '';
			$(d.options).each(function(i, d){
				var currFieldKey = "field_" + (fieldIndex + '_' + i);
				option += '<div id="'+currFieldKey+'" class="optDiv '+(i==0 ? 'hover' : '') +'" onclick="radioSel(\''+currFieldKey+'\');">'+d+'</div>';
			});
			html += '<div class="ot_sex">' +
			    '<span class="crfield" require="'+d.required+'" key="'+d.fieldName+'">'+d.fieldName+'</span>' +
			    '<div>' +
			       option +
			    '</div>' +
			  '</div>';
		} else if(d.optionType == 'Textarea') {
			html += '<div class="ot_supplement">' +
		              '<span>'+d.fieldName+'</span>' +
		              '<textarea class="field" require="'+d.required+'" key="'+d.fieldName+'" placeholder="请输入'+d.fieldName+'"></textarea>' +
		            '</div>';
		}
		++fieldIndex;
	});
	
	html += '<div class="ot_sbbtn"><button onclick="submit()">提交</button></div>';
	$("#zixunDiv").html(html);
})

function selOrDesel(optionId){
	if($("#" + optionId).is(".hover")){
		$("#" + optionId).removeClass("hover");
	} else {
		$("#" + optionId).addClass("hover");
	}
}

function radioSel(optionId) {
	$("#" + optionId).addClass('hover').siblings().removeClass('hover');
}

function submit(){
	var json = {};
	var needBreak = false;
	$(".field").each(function(i, d) {
		if($(d).attr("require") == "Y" && !$(d).val()) {
			MsgUtil.alert($(d).attr("key") + "不能为空");
			needBreak = true;
			return;
		}
		json[$(d).attr("key")] = $(d).val();
	});
	$(".crfield").each(function(i, d) {
		var val = '';
		$(d).siblings("div").find(".hover").each(function(i, d) {
			val += $(d).text() + ",";
		});
		if($(d).attr("require") == "Y" && !val) {
			MsgUtil.alert($(d).attr("key") + "不能为空");
			needBreak = true;
			return;
		}
		json[$(d).attr("key")] = val;
	});
	
	if(needBreak) {
		return;
	}
	
	NetUtil.ajaxload('/wz/doConsult.do', {'consuleInfo': JSON.stringify(json)}, function(result){
		MsgUtil.toast("操作成功！");
		$('.zx_xintx').hide();
	});
}

</script>
        