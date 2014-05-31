
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<div class="head">
	<table width="100%" border="0">
		<tr width="20%">
			<td width="80%" align="center">

				<table width="80%">
					<tr width="80%">
						<td width="26%"><a href="/index"><spring:message
									code="service.name" /> </a></td>
						<td align="left"><a
							href="/<sec:authentication property="principal.username" />">
								<spring:message code="lable.home" />
						</a></td>
						<td><div class="message-text">
								<a href="/notifications"><img alt=""
									src="<c:url value='/resources/images/bell.png'/>" /></a>
								<c:if test="${notificationCount ne 0}">
									<c:out value="${notificationCount}" />
								</c:if>
								<a href=""><img alt=""
									src="<c:url value='/resources/images/message.png'/>" /></a>
								<c:out value="${messageCount}" />
							</div></td>
						<td align="right"><span style="float: right"> <a
								href="?lang=en">en</a> | <a href="?lang=ru">ru</a>
						</span>
							<ul class="hr">
								<li><a href="<c:url value="j_spring_security_logout" />"><spring:message
											code="lable.logout" /> </a></li>
							</ul></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

