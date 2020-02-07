<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>plugin</title>
	<link rel='stylesheet' type='text/css' href='css/style.css'>
</head>
<body><br/>

<table align=center bgcolor=#999999 cellspacing=1>
	<tr>
		<td bgcolor=#FFFFFF>

			<jsp:plugin
				code="Graph.class"
				codebase="http://java.sun.com/applets/jdk/1.4/demo/applets/GraphLayout/"
				type="applet"
				width="500"
				height="400">
				<jsp:params>
					<jsp:param name="edges"
										 value="joe-food,joe-dog,joe-tea,joe-cat,joe-table,table-plate/50,plate-food/30,food-mouse/100,food-dog/100,mouse-cat/150,table-cup/30,cup-tea/30,dog-cat/80,cup-spoon/50,plate-fork,dog-flea1,dog-flea2,flea1-flea2/20,plate-knife"/>
					<jsp:param name="center" value="joe"/>
				</jsp:params>
				<jsp:fallback>您的浏览器不支持 Java Applet</jsp:fallback>
			</jsp:plugin>

		</td>
	</tr>
</table>

</body>
</html>

