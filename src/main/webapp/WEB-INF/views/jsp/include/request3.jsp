<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Визначити список користувачів(прізвище, ім’я), які проживають у
	вказаній країні, у вказаному місті.</h3>

<form:form method="POST" action="/admin/sendRequest3"
	modelAttribute="sendRequest1">
	<table width="100%">
		<th>Select start date</th>
		<th>Select end date</th>
		<tr>
			<td align="center">Enter country<input type="text"
				value="${country}" name="country" /></td>
			<td align="center">Enter city<input type="text" value="${city}"
				name="city" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit"
				value="Send" class="submit-button_no_style"></td>
		</tr>
	</table>
</form:form>
<c:if test="${not empty userlist}">
	<table id="rounded-corner">
		<thead>
			<tr>
				<th scope="col" class="rounded">Name</th>
				<th scope="col" class="rounded">Lastname</th>
				<th scope="col" class="rounded">Country</th>
				<th scope="col" class="rounded">City</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="user" items="${userlist }">
				<tr>
					<td><c:out value="${user[0]}" /></td>
					<td><c:out value="${user[1]}" /></td>
					<td><c:out value="${user[2]}" /></td>
					<td><c:out value="${user[3]}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>