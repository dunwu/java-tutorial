<%@ page language="java" contentType="text/html; charset=gb2312" %>
<%@ taglib uri="http://www.victorzhang.com/tags" prefix="taglib" %>
<%@page import="io.github.dunwu.javaee.taglib.bean.Person" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%
	List<Person> personList = new ArrayList<Person>();

	int i = 1;

	Person person = new Person();
	person.setId(i++);
	person.setName("张三");
	person.setAge(20);
	person.setSex("男");
	person.setAddress("北京市海淀区上地软件园");
	person.setBirthday("2008-08-08");
	person.setMobile("13820080808");
	person.setTelephone("69653234");
	person.setCity("北京");

	personList.add(person);

	Person person2 = new Person();
	person2.setId(i++);
	person2.setName("李四");
	person2.setAge(20);
	person2.setSex("男");
	person2.setAddress("北京市东皇城根锡拉胡同");
	person2.setBirthday("2008-01-01");
	person2.setMobile("13820080808");
	person2.setTelephone("20054879");
	person2.setCity("北京");

	personList.add(person2);

	Person person3 = new Person();
	person3.setId(i++);
	person3.setName("王五");
	person3.setAge(20);
	person3.setSex("男");
	person3.setAddress("北京市东皇城根锡拉胡同");
	person3.setBirthday("2008-01-01");
	person3.setMobile("13820080808");
	person3.setTelephone("20054879");
	person3.setCity("北京");

	personList.add(person3);

	Person person4 = new Person();
	person4.setId(i++);
	person4.setName("王二麻子");
	person4.setAge(20);
	person4.setSex("女");
	person4.setAddress("北京市东皇城根锡拉胡同");
	person4.setBirthday("2008-01-01");
	person4.setMobile("13820080808");
	person4.setTelephone("20054879");
	person4.setCity("北京");

	personList.add(person4);

	Person person5 = new Person();
	person5.setId(i++);
	person5.setName("王二麻子");
	person5.setAge(20);
	person5.setSex("男");
	person5.setAddress("北京市东皇城根锡拉胡同");
	person5.setBirthday("2008-01-01");
	person5.setMobile("13820080808");
	person5.setTelephone("20054879");
	person5.setCity("北京");

	personList.add(person5);

	Person person6 = new Person();
	person6.setId(i++);
	person6.setName("王二麻子");
	person6.setAge(20);
	person6.setSex("女");
	person6.setAddress("北京市东皇城根锡拉胡同");
	person6.setBirthday("2008-01-01");
	person6.setMobile("13820080808");
	person6.setTelephone("20054879");
	person6.setCity("北京");

	personList.add(person6);

	Person person7 = new Person();
	person7.setId(i++);
	person7.setName("王二麻子");
	person7.setAge(20);
	person7.setSex("男");
	person7.setAddress("北京市海淀区海淀大街甲36号六层A");
	person7.setBirthday("2008-01-01");
	person7.setMobile("13820080808");
	person7.setTelephone("20054879");
	person7.setCity("北京");

	personList.add(person7);

	request.setAttribute("personList", personList);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>Insert title here</title>
	<link href="style/style.css" rel="stylesheet" type="text/css"/>
	<style>
		.resizeDivClass {
			position: relative;
			width: 2px;
			z-index: 1;
			left: expression(this .   parentElement .   offsetWidth-1);
			cursor: e-resize;
			float: left;
		}
	</style>

	<script language=javascript>
    function MouseDownToResize(obj) {
      obj.mouseDownX = event.clientX;
      obj.pareneTdW = obj.parentElement.offsetWidth;
      obj.pareneTableW = theObjTable.offsetWidth;
      obj.setCapture();
    }

    function MouseMoveToResize(obj) {
      if (!obj.mouseDownX) return false;
      var newWidth = obj.pareneTdW * 1 + event.clientX * 1 - obj.mouseDownX;
      if (newWidth > 0) {
        obj.parentElement.style.width = newWidth;
        theObjTable.style.width = obj.pareneTableW * 1 + event.clientX * 1 - obj.mouseDownX;
      }
    }

    function MouseUpToResize(obj) {
      obj.releaseCapture();
      obj.mouseDownX = 0;
      var objId = obj.parentElement.id;
      document.cookie = objId + '_width=' + obj.parentElement.style.width;
      document.cookie = theObjTable.id + '_width=' + theObjTable.style.width;
    }

    function readCookie(name) {
      var start1 = document.cookie.indexOf(name + "=");
      if (start1 == -1) return null; else {
        start = document.cookie.indexOf("=", start1) + 1;
        var end = document.cookie.indexOf(";", start);
        if (end == -1) {
          end = document.cookie.length;
        }
        var value = unescape(document.cookie.substring(start, end));
        return value;
      }
    }
	</script>
</head>
<body>

<taglib:table items="${ personList }" url="table.jsp">
	<taglib:column property="id" label="编号"/>
	<taglib:column property="name" label="姓名"/>
	<taglib:column property="age" label="年龄"/>
	<taglib:column property="sex" label="性别"/>
	<taglib:column property="address" label="地址"/>
	<taglib:column property="telephone" label="电话"/>
	<taglib:column property="mobile" label="手机"/>
	<taglib:column property="city" label="城市"/>
	<taglib:column property="deleted" label="是否删除"/>
</taglib:table>

<script type="text/javascript">
  columns[columns.length] = 'theObjTable';
  for (var i = 0; i < columns.length; i++) {
    var width = readCookie(columns[i] + '_width');
    if (width) {
      document.getElementById(columns[i]).style.width = width;
    }
  }
</script>

</body>
</html>
