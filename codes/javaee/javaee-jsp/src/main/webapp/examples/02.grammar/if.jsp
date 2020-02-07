<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>02.JSP语法 - if...else示例</title>
</head>
<body>
<%
	String param = request.getParameter("param");
	if ("1".equals(param)) {
%>
《破阵子·为孙同甫赋壮语以寄》<br/>
【宋】辛弃疾<br/>
醉里挑灯看剑，梦回吹角连营。<br/>
八百里分麾下炙，五十弦翻塞外声，沙场秋点兵。<br/>
马作的卢飞快，弓如霹雳弦惊。<br/>
了却君王天下事，赢得生前身后名。可怜白发生！<br/>
<%
} else if ("2".equals(param)) {
%>
《青玉案·元夕》<br/>
【宋】辛弃疾<br/>
东风夜放花千树，更吹落，星如雨。<br/>
宝马雕车香满路，凤箫声动，玉壶光转，一夜鱼龙舞。<br/>
蛾儿雪柳黄金缕，笑语盈盈暗香去。<br/>
众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。<br/>
<%
} else if ("3".equals(param)) {
%>
《丑奴儿》<br/>
【宋】辛弃疾<br/>
少年不识愁滋味，爱上层楼，爱上层楼，为赋新词强说愁。<br/>
而今识得愁滋味，欲说还休，欲说还休，却道天凉好个秋。<br/>
<%
} else if ("4".equals(param)) {
%>
《永遇乐》<br/>
【宋】辛弃疾<br/>
千古江山，英雄无觅，孙仲谋处。<br/>
舞榭歌台，风流总被、雨打风吹去。<br/>
斜阳草树，寻常巷陌，人道寄奴曾住。<br/>
想当年，金戈铁马，气吞万里如虎。<br/>
元嘉草草，封狼居胥，赢得仓皇北顾。<br/>
四十三年，望中犹记，烽火扬州路。<br/>
可堪回首，佛狸祠下，一片神鸦社鼓。<br/>
凭谁问：廉颇老矣，尚能饭否？<br/>
<%
} else if ("5".equals(param)) {
%>
《南乡子》<br/>
【宋】辛弃疾<br/>
何处望神州，满眼风光北固楼。<br/>
千古兴亡多少事，悠悠，不尽长江滚滚流。<br/>
年少万兜鍪，坐断东南战未休。<br/>
天下英雄谁敌手？曹刘，生子当如孙仲谋。<br/>
<%
} else {
%>
请使用参数 param=1,2,3,4,5 选择要显示的诗歌<br/>
<a href="if.jsp?param=1">if.jsp?param=1</a><br/>
<a href="if.jsp?param=2">if.jsp?param=2</a><br/>
<a href="if.jsp?param=3">if.jsp?param=3</a><br/>
<a href="if.jsp?param=4">if.jsp?param=4</a><br/>
<a href="if.jsp?param=5">if.jsp?param=5</a><br/>
<%
	}
%>
</body>
</html>
