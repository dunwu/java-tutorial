<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="io.github.dunwu.javaee.taglib.bean.Person" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%
	if (true) {
		List<Person> personList = new ArrayList<Person>();

		Person person = new Person();
		person.setId(1);
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
		person2.setId(2);
		person2.setName("李四");
		person2.setAge(20);
		person2.setSex("女");
		person2.setAddress("北京市东皇城根锡拉胡同");
		person2.setBirthday("2008-01-01");
		person2.setMobile("13820080808");
		person2.setTelephone("20054879");
		person2.setCity("北京");

		personList.add(person2);

		request.setAttribute("personList", personList);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
%>
