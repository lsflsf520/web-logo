<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<html>
  <head>
    <title>${locInfo.name }-详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
	    .act {  
		    width:100%;
            list-style-type:none;  
            margin:0px;  
			padding:0;
			overflow:hidden;
			zoom:1;
        } 
        .act li{
		    float:left;
			width:48%;
			padding: 5px;
		}	
        .act li img{
		    width:50%;
			height:300px;
		}		
	</style>
  </head>
  <body style="background:#CBE1FF">
    <div>
        <div align="center"><h2>鹿饮泉国际度假村</h2></div>
		<div>
			<h4>活动项目：</h4>
			<ul class="act">
			  <li><img src="http://static.qiyejieshao.cn/dl/luyinquan.jpg" /></li>
			  <li><img src="http://static.qiyejieshao.cn/dl/luyinquan.jpg" /></li>
			  <li><img src="http://static.qiyejieshao.cn/dl/luyinquan.jpg" /></li>
			  <li><img src="http://static.qiyejieshao.cn/dl/luyinquan.jpg" /></li>
			  <li><img src="http://static.qiyejieshao.cn/dl/luyinquan.jpg" /></li>
			</ul>
		</div>
    
	    <div>
		    <h4>景区介绍：</h4>
			<ul>
			    <li>
				 鹿饮泉国际生态园度假山庄，以望城水乡风情临水建筑为原型，从返璞归真、回归自然出发，营造出有层次、有情趣、有历史文化内涵的山庄。古树、老井、菜园、
	  小桥、流水、人家、原始手工作坊，凸显出中国传统的山庄文化；碧水、绿树、家禽、白鹭，构成农庄浓郁的诗情画意；羊咩、牛哞、鸡鸣、犬吠，谱就山庄自然交响乐；瓜
	  棚荷塘、水车石磨、草屋篱笆、茂林修竹、栈道凉亭，使古朴的农庄更添魅力；鹿饮泉国际度假村是一方净土，是一片悠然忘忧的家园，是一处陶然入飞心灵的世外桃源，是
	  一个回归自然、陶冶性情的人生驿站……
				</li>
				<li>
				鹿饮泉国际度假村把厚重的岭南文化演绎得淋漓尽致的同时，还将临水而建的豪华超五星级客房、古朴雅致的中餐厅、浪漫温馨的水底船餐厅、茶座、歌舞厅、舞台表演、蒸
	  汽浴、桑拿浴、水疗、游泳池、中药温泉池、足球场、网球场、乒乓球室、桌球室、羽毛球场、大型室内外游乐场、网吧等所有代表现代文明的一切元素恰到好处的表现出来。
	  在未来几年内，鹿饮泉（国际）生态园度假山庄还将兴建五星级酒店、更高级餐饮、娱乐等高端客户服务配套场所。此外，鹿饮泉国际度假村建立高科技农业种植园和养殖场
	  等生态农业设施，把鹿饮泉（国际）生态园度假山庄建设成为标准化、国际化的生态休闲山庄。鹿饮泉国际生态园度假山庄地处雷锋故里，湖南望城白箬铺龙塘村。落户于我
	  省唯一的国家级农业科技园，是一家集欧陆风情、高尚生活情趣，以吃、住、玩、赏、娱、购于一体的时尚主题度假山庄，是休闲娱乐、度假旅游、商务会议的上佳选择地。
				</li>
				<li>
				鹿饮泉国际度假村项目主体建筑为一栋三层第四代生态创新时尚休闲主题酒店，周边配套有文化特色餐饮酒楼及二十八栋风尚休闲度假别墅；山庄内含会议中心、温泉休闲会所、
	  娱乐城、鹿饮泉人文山水景点、六合古寺、山庄广场、梅花鹿基地、蒙古马训练基地、水产养殖垂钓基地、果蔬苗木种植基地。
				</li>
			</ul>
		</div>
		
		<div>
		    <h4>联系方式：</h4>
			<ul>
			    <li>电话：0731-88568888</li>
				<li>地址：<br/>
				    <a href='${base == "/" ? "" : base }/map/topos.do?locId=${locInfo.id}' target='_blank'>
				        <img style="margin:20px" width="300" height="200" src="http://api.map.baidu.com/staticimage/v2?ak=qTAvDQxZvNfv2tHcnGqzpwfsxNV9IbMG&zoom=15&center=112.788406,28.206547&markers=112.788406,28.206547&markerStyles=-1,http://static.qiyejieshao.cn/dl/position.png?v=1,-1" />
			        </a>
			    </li>
			</ul>
		</div>
	</div>
  </body>
</html>
