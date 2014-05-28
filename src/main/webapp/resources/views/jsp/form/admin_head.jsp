
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<div class="head">
	<table width="80%">
		<tr width="80%">
			<td><a href="/index">SelfMash</a></td>
			<td width="20%" align="left"><a href="/${username}"><c:out
						value="${username}"></c:out></a></td>
			<td align="right"><a
				href="<c:url value="j_spring_security_logout" />">Logout</a></td>
		</tr>
	</table>
</div>
