<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="form"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Визначити список користувачів (прізвище, ім’я, логін), які
	зареєструвалися в мережі у визначений період часу, та ті, для яких ще
	не було підтверджено реєстрацію.</h3>

<form:form method="POST" action="/admin/sendRequest1"
	modelAttribute="sendRequest1">
	<table width="100%">
		<th>Select start date</th>
		<th>Select end date</th>
		<tr>
			<td align="center"><select name="monthFrom">
					<option>- Month -</option>
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
			</select> <select name="dayFrom">
					<option>- Day -</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
			</select> <select name="yearFrom">
					<option>- Year -</option>
					<option value="2014">2014</option>
					<option value="2013">2013</option>
			</select></td>
			<td align="center"><select name="monthTo">
					<option>- Month -</option>
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
			</select> <select name="dayTo">
					<option>- Day -</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
			</select> <select name="yearTo">
					<option>- Year -</option>
					<option value="2014">2014</option>
					<option value="2013">2013</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit"
				value="sendRequest1" class="submit-button_no_style"></td>
		</tr>
	</table>
</form:form>
<c:if test="${not empty userlist}">
	<table id="rounded-corner">
		<thead>
			<tr>
				<th scope="col" class="rounded">Name</th>
				<th scope="col" class="rounded">Lastname</th>
				<th scope="col" class="rounded">Date reg</th>
				<th scope="col" class="rounded">Confim reg</th>
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