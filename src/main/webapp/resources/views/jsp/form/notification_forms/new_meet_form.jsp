<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<div align="left" id="block">
	<form action="/meet/${meet.id}/accept">
		<c:out value="${meet.userFrom.name}" />
		<c:out value="${meet.userFrom.lastname}" />
		<input type="submit" value="Accept">
	</form>
</div>