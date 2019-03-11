<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="zx_xintx show_hide">
  <div class="mask"></div>
  <div class="tx_bskuank">
    <div class="nk_top">请填写信息<i></i></div>
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
NetUtil.ajaxload('/wz/loadConsultConfig.do', function(result){
	var html = '';
	$(result.model).each(function(i, d){
		if(d.optionType == 'Text') {
			html+='<div class="ot_phone ty_shur">' +
		            '<span>'+d.fieldName+'</span>' +
		            '<input class="field" key="'+d.fieldName+'" type="text" />' +
		          '</div>';
		} else if(d.optionType == 'Select'){
			var option = '';
			$(d.options).each(function(i, d){
				option += '<option value ="'+d+'">'+d+'</option>';
			})
			html+= '<div class="ot_category">' +
			    '<span>'+d.fieldName+'</span>' +
			    '<div>' +
			      '<select class="field" key="'+d.fieldName+'">' +
			      option +
			      '</select>' +
			      '<i></i>' +
			     '</div>' +
			   '</div>';
		} else if(d.optionType == 'Checkbox'){
			var option = '';
			$(d.options).each(function(i, d){
				option += '<div class="optDiv '+(i==0 ? 'hover' : '') +'">'+d+'</div>';
			});
			html += '<div class="ot_hobby">' +
			    '<span class="crfield" key="'+d.fieldName+'">'+d.fieldName+'</span>' + 
			    '<div>' +
			      option +
			    '</div>' +
			  '</div>';
		} else if(d.optionType == 'Radio'){
			var option = '';
			$(d.options).each(function(i, d){
				option += '<div class="optDiv '+(i==0 ? 'hover' : '') +'">'+d+'</div>';
			});
			html += '<div class="ot_sex">' +
			    '<span class="crfield" key="'+d.fieldName+'">'+d.fieldName+'</span>' +
			    '<div>' +
			       option +
			    '</div>' +
			  '</div>';
		} else if(d.optionType == 'Textarea') {
			html += '<div class="ot_supplement">' +
		              '<span>'+d.fieldName+'</span>' +
		              '<textarea name="" id="" class="field" key="'+d.fieldName+'" placeholder="请输入'+d.fieldName+'"></textarea>' +
		            '</div>';
		}
	});
	
	html += '<div class="ot_sbbtn"><button onclick="submit()">提交</button></div>';
	$("#zixunDiv").html(html);
})

$(".optDiv").click(function(){
	if($(this).is(".hover")){
		$(this).removeClass("hover");
	} else {
		$(this).addClass("hover");
	}
})

function submit(){
	var json = {};
	$(".field").each(function(i, d) {
		json[$(d).attr("key")] = $(d).val();
	});
	$(".crfield").each(function(i, d) {
		var val = '';
		$(d).siblings("div").find(".hover").each(function(i, d) {
			val += $(d).text();
		});
		json[$(d).attr("key")] = val;
	});
	
	NetUtil.ajaxload('/wz/doConsult.do', {'consuleInfo': JSON.stringify(json)}, function(result){
		MsgUtil.toast("操作成功！");
	});
}
</script>
        