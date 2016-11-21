<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%@ include file="../common/global.jsp" %>
<!DOCTYPE html>
<head>
    <title>${name }(${code }) 近况</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
    <div>
      <form action='${base == "/" ? "" : base }/gp/datachart.do' method="get">
        <input placeholder="请输入股票代码或名称" name="codeOrName" value="${kw }">
        <input type="submit" value="查  询">
        <label style="color:red;">${errorMsg }</label>
        <a target="_blank" href="http://stockpage.10jqka.com.cn/${code }">${name }</a>
      </form>
    </div>
  
    <div id="main" style="width: 80%;height:260px;"></div>
    
    <div id="mainPureIn" style="width: 80%;height:260px;"></div>
    
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
            		    title : {
            		    	text: '${name }(${code })',
            		        subtext: '主力流入流出图'
            		    },
                         tooltip : {
                             trigger: 'axis',
                             axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                 type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                             }
                         },
                         legend: {
                             data:['主力流入', '主力流出']
                         },
                         grid: {
                             left: '3%',
                             right: '4%',
                             bottom: '3%',
                             containLabel: true
                         },
                         xAxis : [
                             
                             {
                                 type : 'category',
                                 axisTick : {show: true},
                                 data : [
                                         <c:forEach items="${zijins}" var="zijin">
                                           '${zijin.dayStr}',
                                         </c:forEach>
                                         ]
                             }
                         ],
                         yAxis : [
                             {
                                 type : 'value'
                             }
                         ],
                         series : [
                             {
                                 name:'主力流入',
                                 type:'bar',
                                 stack: '主力',
                                 label: {
                                     normal: {
                                         show: true
                                     }
                                 },
                                 data:[
        							<c:forEach items="${zijins}" var="zijin">
        							 ${zijin.mainIn},
        							</c:forEach>
                                       ]
                             },
                             {
                                 name:'主力流出',
                                 type:'bar',
                                 stack: '主力',
                                 label: {
                                     normal: {
                                         show: true,
                                         //position: 'left'
                                     }
                                 },
                                 data:[
        							<c:forEach items="${zijins}" var="zijin">
        							 -${zijin.mainOut},
        							</c:forEach>
                                    ]
                             }
                         ]
                     };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
    
    <script type="text/javascript">
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
        	 var pureInChart = ec.init(document.getElementById('mainPureIn'));
        	 
        	 var pureOption = {
        		    title : {
        		        text: '${name }(${code })',
        		        subtext: '主力净流入图'
        		        //x: 'center',
        		        //align: 'right'
        		    },
                     tooltip : {
                         trigger: 'axis',
                         axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                             type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                         }
                     },
                     legend: {
                         data:['主力净流入']
                     },
                     grid: {
                         left: '3%',
                         right: '4%',
                         bottom: '3%',
                         containLabel: true
                     },
                     xAxis : [
                         
                         {
                             type : 'category',
                             axisTick : {show: false},
                             data : [
                                     <c:forEach items="${zijins}" var="zijin">
                                       '${zijin.dayStr}',
                                     </c:forEach>
                                     ]
                         }
                     ],
                     yAxis : [
                         {
                             type : 'value'
                         }
                     ],
                     series : [
                         {
                             name:'主力净流入',
                             type:'bar',
                             label: {
                                 normal: {
                                     show: true,
                                     position: 'inside'
                                 }
                             },
                             data:[<c:forEach items="${zijins}" var="zijin">
                             ${zijin.mainPureIn},
                             </c:forEach>]
                         }
                     ]
                 };
         
          pureInChart.setOption(pureOption);
        });
    </script>
</body>
</html>
