
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:choose>
	<c:when test="${authentication ne user.login and !isSubscribed}">
		<form action="/subscribe?userId=${user.id}&userLogin=${user.login}"
			method="post">
			<input type="submit" value='<spring:message code="lable.follow" />'
				class="submit-button_no_style">
		</form>
	</c:when>
	<c:otherwise>
		<c:if test="${authentication ne user.login}">
			<form action="/unsubscribe?userId=${user.id}&userLogin=${user.login}"
				method="post">
				<input type="submit"
					value='<spring:message code="lable.unsubscribe" />'>
			</form>
		</c:if>
	</c:otherwise>
</c:choose>