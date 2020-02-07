<%
	// Set response content type
	response.setContentType("text/html");
	// Set spanish language code.
	response.setHeader("Content-Language", "es");
	String title = "En Espa?ol";

%>
<html>
<head>
	<title><% out.print(title); %></title>
</head>
<body>
<h1 align="center"><% out.print(title); %></h1>
<div align="center">
	<p>En Espa?ol</p>
	<p>?Hola Mundo!</p>
</div>
</body>
</html>
