<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<center>
	<div class="wrapper_second">
		<div class="error">
			<p>
				<c:if test="${error == true}">
					<b class="error">Invalid login or password.</b>
				</c:if>
			</p>
		</div>
		<form class="form1" action="<c:url value='j_spring_security_check'/>"
			method="post">

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
					<input type="password" name="j_password" id="j_password" size="30"
						maxlength="32" />
				</div>
			</div>
			<div class="buttons">
				<input class="orangebutton" type="submit" value="Login" />
			</div>
		</form>
	</div>
</center>