<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="form"%>
<center>
	<div class="wrapper">
		<form:form class="form2" modelAtribute="userAttribute" action="/reg"
			method="post">
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