/*通用工具*/

// msg中的方法是参照layer官网 http://www.layui.com/doc/modules/layer.html 设计的，具体用法也可以参考此网页
var MsgUtil = {
	/*
	 * msg.alert('只想简单的提示'); msg.alert('加了个图标', {icon: 1});
	 * //这时如果你也还想执行yes回调，可以放在第三个参数中。 msg.alert('有了回调', function(index){
	 * layer.close(index); });
	 */
	alert : function(content, options, yes) {
		mui.alert(content, options, yes);
	},

	error : function(content) {
		mui.toast(content, {
			icon : 2
		});
	},

	success : function(content) {
		mui.toast(content, {
			icon : 1
		});
	},

	warn : function(content) {
		mui.toast(content, {
			icon : 0
		});
	},

	/**
	 * //eg1 layer.confirm('is not?', {icon: 3, title:'提示'}, function(index){
	 * //do something
	 * 
	 * layer.close(index); }); //eg2 layer.confirm('is not?', function(index){
	 * //do something
	 * 
	 * layer.close(index); });
	 * 更多可以参考：http://www.layui.com/doc/modules/layer.html
	 */
	confirm : function(content, options, yes, cancel) {
		layer.confirm(content, options, yes, cancel);
	},

	/*
	 * 该方法用于弹出3个按钮，是、否、取消，并对应后边3个参数的回调方法(其中取消的回调函数是默认的)
	 * MsgUtil.decision("确认是否通过审核？", function(){ //是的逻辑 }, function(){ //否的逻辑 } );
	 * 
	 * 
	 */
	decision : function(content, yes, no, yesLabel, noLabel) {
		if (typeof yes != 'function' || typeof no != 'function') {
			this.warn('参数格式不正确，请参考方法说明');
			return;
		}
		this.confirm(content,
				{
					btn : [ yesLabel ? yesLabel : '通过',
							noLabel ? noLabel : '不通过', '取消' ], // 可以无限个按钮
					btn3 : function() {
					},
				}, yes, no);
	},

	// 消息提示框，会自动消失
	toast : function(content, options, end) {
		mui.msg(content, options, end);
	},

	// 弹出加载层
	loading : function(icon, options) {
		return mui.load(icon, options);
	},

	/*
	 * 用来关闭方法 loading 弹出的加载层 @param index 弹出层的索引，改值由方法 loading 返回的
	 */
	close : function(index) {
		layer.close(index);
	},

	/*
	 * layer.closeAll(); //疯狂模式，关闭所有层 layer.closeAll('dialog'); //关闭信息框
	 * layer.closeAll('page'); //关闭所有页面层 layer.closeAll('iframe');
	 * //关闭所有的iframe层 layer.closeAll('loading'); //关闭加载层d
	 * layer.closeAll('tips'); //关闭所有的tips层
	 */
	closeAll : function(type) {
		layer.closeAll(type);
	},

	/*
	 * layer.tips('只想提示地精准些', '#id'); $('#id').on('click', function(){ var that =
	 * this; layer.tips('只想提示地精准些', that); //在元素的事件回调体中，follow直接赋予this即可 });
	 */
	tips : function(content, follow, options) {
		layer.tips(content, follow, options);
	},

	// 弹出带输入框的层
	prompt : function(options, yes) {
		layer.prompt(options, yes);
	},

	/**
	 * 使用方法： MsgUtil.openDiv("#myDivId"); //弹出一个DIV MsgUtil.openDiv("#myDivId",
	 * "我的弹层"); //弹出一个指定标题的DIV MsgUtil.openDiv("#myDivId", "我的弹层", 400);
	 * //弹出一个指定标题和指定宽度的DIV MsgUtil.openDiv("#myDivId", "我的弹层", 400, 300);
	 * //弹出一个指定标题、指定宽度和指定高度的DIV
	 */
	openDiv : function(divSel, title, width, height) {
		var jqElem = $(divSel);
		if (!jqElem || jqElem.length <= 0) {
			MsgUtil.warn("找不到选择器" + divSel + "对应的元素");
			return;
		}

		if (jqElem.length > 1) {
			MsgUtil.warn("找到了" + jqElem.length + "个与" + divSel + "匹配的元素，请先排重！");
			return;
		}

		if (!title) {
			title = "消息弹层提示";
		}

		if (!width) {
			width = "auto";
		}

		if (!height) {
			height = "auto";
		}

		if (RegexUtil.isInt(width)) {
			width += "px";
		}

		if (RegexUtil.isInt(height)) {
			height += "px";
		}

		var dimension = [ width, height ];
		if ("auto" == width && "auto" == height) {
			dimension = "auto";
		}

		// alert(window.screen.availWidth +',' + window.screen.availHeight +"\n"
		// + window.screen.width + "," + window.screen.height);

		var index = layer.open({
			type : 1,
			offset : 't',
			title : title,
			skin : 'layui-layer-rim', // 加上边框
			area : dimension,
			maxWidth : window.screen.availWidth * 0.7,
			maxHeight : window.screen.availHeight * 0.6,
			content : jqElem
		});

		return index;
	},
	/**
	 * 全屏弹框
	 */
	fullOpenDiv : function(divSel, title) {
		var index = this.openDiv(divSel, title);

		layer.full(index);
	}
}

var ElemUtil = {
	getTagName : function(sel) {
		try {
			return $(sel)[0].tagName;
		} catch (err) {
			return $(sel).tagName;
		}
	},
	ltrim : function(val) {
		return val ? val.replace(/^\s+/g, "") : val;
	},
	rtrim : function(val) {
		return val ? val.replace(/\s+$/g, "") : val;
	},
	trim : function(val) {
		return this.rtrim(this.ltrim(val));
	},
	clearSpace : function(val) {
		return val ? val.replace(/\s+/g, "") : val;
	}
}
