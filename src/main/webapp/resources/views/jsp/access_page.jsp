<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@ taglib
	prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
.error {
	color: #ff0000;
	font-size: 10px;
}

.errorblock {
	color: #ff0000;
	font-size: 12px;
}
</style>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/login-style.css'/>" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<div id="wrapper">
		<table width="100%">
			<tr>
				<td>
					<div class="header">
						<h1>
							<a href="/"><spring:message code="service.name" /></a>
						</h1>
					</div>
				</td>
				<td align="right"><div class="login-form" align="right">
						<form id="login-form" name="login-form"
							action="<c:url value='j_spring_security_check'/>" method="post">
							<input type="text" class="input username" name="j_username"
								id="j_username"
								placeholder="<spring:message code="lable.form.login" />" /> <input
								type="password" class="input password"
								placeholder="<spring:message code="lable.form.pass" />"
								name="j_password" id="j_password" /><input type="submit"
								name="login" value="Login" class="button" />
						</form>
					</div></td>
			</tr>
		</table>

		<div class="reg-form">
			<div class="content" align="right">
				<table width="100%">
					<tr>
						<td><div align="left">
								<img alt="" src="<c:url value='/resources/images/faces.png'/>">
							</div></td>
						<td><form:form action="/register" commandName="user"
								name="userForm" method="POST">
								<ul>
									<li><form:errors path="name" cssClass="error" /></li>
									<li><input type="text" name="name" class="input name"
										placeholder="<spring:message code="lable.form.name" />"
										required /></li>
									<li><form:errors path="lastname" cssClass="error" /></li>
									<li><input type="text" name="lastname"
										class="input surname"
										placeholder="<spring:message code="lable.form.lastname" />"
										required /></li>
									<li><form:errors path="birthDate" cssClass="error" /></li>
									<li><input type="text" name="birthDate"
										placeholder="DD.MM.YYYY" class="input birthDate" required /></li>
									<li><form:errors path="email" cssClass="error" /></li>
									<li><input type="email" name="email" class="input email"
										placeholder="<spring:message code="lable.form.email" /> "
										required /></li>
									<li><form:errors path="login" cssClass="error" /></li>
									<li><input type="text" name="login" type="text"
										class="input login"
										placeholder="<spring:message code="lable.form.login" />"
										required /></li>
									<li><form:errors path="password" cssClass="error" /></li>
									<li><input type="password" name="password" type="password"
										class="input password"
										placeholder="<spring:message code="lable.form.pass" />"
										required /></li>

								</ul>
								<br>
								<input type="submit" value="Register" class="button" />
							</form:form></td>
					</tr>
				</table>
			</div>
			<div class="footer" align="center"><spring:message code="lable.footer" /></div>
		</div>
	</div>

</body>
</html>