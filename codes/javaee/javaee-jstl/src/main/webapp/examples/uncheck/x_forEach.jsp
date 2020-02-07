<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		body, td, div {
			font-size: 12px;
		}

		.source {
			white-space: pre;
			width: 100%;
			height: 200px;
			overflow: scroll;
			border: 1px solid #999999;
			padding: 2px;
			margin-bottom: 20px;
		}
	</style>
</head>
<body>

<c:import var="file" url="/sina.xml" charEncoding="UTF-8"/>

<div class="source">
	<c:out value="${ file }"/>
</div>

<x:parse var="doc" doc="${ file }"></x:parse>

<x:forEach var="node" select="$doc/rss/channel/item" varStatus="status">
	${ status.count }.
	<a href="<x:out select="$node/link"/>" target="_blank"><x:out select="$node/title"/></a>&nbsp;
	<x:out select="$node/pubDate"/> <br/>
	<x:out select="$node/description"/> <br/><br/>

</x:forEach>

</body>
</html>
