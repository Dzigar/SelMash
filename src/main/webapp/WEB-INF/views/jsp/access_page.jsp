<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>login</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<center>
					<div class="wrapper_second">
						<div class="error">
							<p>
								<c:if test="${error == true}">
									<b class="error">Invalid login or password.</b>
								</c:if>
							</p>
						</div>
						<form class="form1"
							action="<c:url value='j_spring_security_check'/>" method="post">

							<div class="formtitle">Sign in</div>

							<div class="input">
								<div class="inputtext">Login:</div>
								<div class="inputcontent">
									<input type="text" name="j_username" id="j_username" size="30"
										maxlength="40" />
								</div>
							</div>

							<div class="input nobottomborder">
								<div class="inputtext">Password:</div>
								<div class="inputcontent">
									<input type="password" name="j_password" id="j_password"
										size="30" maxlength="32" />
								</div>
							</div>
							<div class="buttons">
								<input class="orangebutton" type="submit" value="Login" />
							</div>
						</form>
					</div>
				</center>
			</td>
			<td>
				<center>
					<div class="wrapper">
						<form:form class="form2" modelAtribute="userAttribute"
							action="/reg" method="post">
							<div class="formtitle">Регистрация</div>

							<div class="input">
								<div class="inputtext">Имя:</div>
								<div class="inputcontent">
									<input type="text" name="name" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Фамилия:</div>
								<div class="inputcontent">
									<input name="lastname" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Дата рождения:</div>
								<div class="inputcontent">
									<input type="text" name="birthDate" placeholder="DD.MM.YYYY" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Email:</div>
								<div class="inputcontent">
									<input type="text" name="email" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Логин:</div>
								<div class="inputcontent">
									<input type="text" name="login" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Пароль:</div>
								<div class="inputcontent">
									<input type="password" name="password" />
								</div>
							</div>
							<div class="input">
								<div class="inputtext">Повторить пароль:</div>
								<div class="inputcontent">
									<input type="password" name="enterpassword" />
								</div>
							</div>
							<div class="buttons">
								<input class="orangebutton" type="submit" value="Отправить" />
							</div>
						</form:form>
					</div>
				</center>
			</td>
		</tr>
	</table>

</body>
</html>