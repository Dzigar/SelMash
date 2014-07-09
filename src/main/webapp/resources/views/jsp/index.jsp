<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	function fillstates() {
		var val = $('#state').val();
		$.ajax({
			url : 'getCities',
			method : 'get',
			ContentType : 'json',
			data : {
				countryId : val
			},
			success : function(response) {
				var options = '';
				if (response != null) {
					$(response).each(
							function(index, value) {
								options = options + '<option>' + value.name
										+ '</option>';
							});
					$('#city').html(options);
				}
			}
		});
	}
</script>
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
				<td width="80%" valign="top"><c:forEach items="${users}"
						var="user">
						<%@include file="form/user_form_extended.jsp"%>
						<br />
					</c:forEach></td>
				<td width="20%" valign="top">
					<form action="/index" method="get">
						<select id="state" onchange="fillstates();">
							<option value="Select state" />
							<c:forEach items="${stateList}" var="state">
								<option value="${state.id}">${state.name}</option>
							</c:forEach>
						</select> <select name="userCity" id="city">
							<option value="${value}" />
						</select> <br />
						<c:out value="Age from" />
						<select name="ageFrom">
							<c:forEach var="i" begin="16" end="55">
								<option><c:out value="${i}" /></option>
							</c:forEach>
						</select>
						<c:out value="Age to" />
						<select name="ageTo">
							<c:forEach var="i" begin="16" end="55">
								<option><c:out value="${i}" /></option>
							</c:forEach>
						</select> <input type="submit">
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>