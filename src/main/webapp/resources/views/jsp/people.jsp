<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="javax.swing.text.StyledEditorKit.ItalicAction"%>
<%@page import="com.selfmash.service.EstimationService"%>
<%@page import="org.hibernate.Hibernate"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div align="center">
		<!-- Head -->
		<%@include file="form/head.jsp"%>
		<!-- end head -->
		<table width="80%">
			<tr>
				<td width="25%" valign="top"></td>
				<td width="50%" valign="top">
					<!-- Posts -->
					<div align="left">
						<c:forEach var="user" items="${people }">
							<div id="block"><%@include
									file="form/user_form_extended.jsp"%></div>
						</c:forEach>
						<!--  -->
					</div>
				</td>
				<td width="25%" valign="top"></td>
			</tr>
		</table>
	</div>
</body>
</html>




