<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%><%@taglib uri="http://www.springframework.org/tags"
	prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />
<script type="text/javascript" src="/js/messages_script.js"></script>
<title></title>
</head>
<body>
	<div align="center">
		<!-- Head -->
		<%@include file="form/head.jsp"%>
		<!-- end head -->

		<!-- User to form -->

		<table width="80%" border="1">
			<tr>
				<td width="20%">
					<div align="center">
						<c:choose>
							<c:when test="${not empty userSender.profilePhoto}">
								<img
									src="<c:out value="/resources/selfshots/${userSender.login}/${userSender.profilePhoto.title}" />"
									width="100" height="100" alt="" />
							</c:when>
							<c:otherwise>
								<img
									src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
									width="100" height="100" alt="" />
							</c:otherwise>
						</c:choose>
						<br />
						<c:out value="${userSender.name} ${userSender.lastname}" />
					</div>

				</td>
				<td width="60%">
					<form action="/messages/send?userId=${userReveiver.id}"
						method="post">
						<div class="messagesBlock">
							<div id="messages_list" vertical-align="bottom"
								style="vertical-align: bottom; padding-top: 5; margin: 12px; margin-right: 25px; width: 100%; height: 450px; overflow-y: scroll;">
							</div>
							<div id="messageInputBlock" style="margin: 10px;">
								<div align="center">
									<textarea style="resize: none; width: 90%; margin: 2px;"
										rows="2" id="messageInput"
										placeholder="Введіть овідомлення (Shift + Enter - надіслати)"
										onkeydown="messageInputKeyDown(event)"></textarea>

									<input type="button" onclick="sendMessage()" value="Надіслати" />
								</div>
							</div>
						</div>
					</form>
				</td>
				<td width="20%">
					<div align="center">
						<c:choose>
							<c:when test="${not empty userReceiver.profilePhoto}">
								<img
									src="<c:out value="/resources/selfshots/${userReceiver.login}/${userReceiver.profilePhoto.title}" />"
									width="100" height="100" alt="" />
							</c:when>
							<c:otherwise>
								<img
									src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
									width="100" height="100" alt="" />
							</c:otherwise>
						</c:choose>
						<br />
						<c:out value="${userReceiver.name} ${userReceiver.lastname}" />
					</div>

				</td>
			</tr>
		</table>

	</div>


	<script type="text/javascript" src="js/messages_script.js"></script>

	<script type="text/javascript">
		var dialog_id = $
		{
			dialogId
		};
		document_ready_functions.push(function() {
			loadMessagesList(true);
			$('#messages_list').scroll(function() {
				messageListScrolled();
			});
		});
	</script>




	<script type="text/template" class="template" id="messageTemplate">
    <div class="message" id="message_{{message.id}}">
        <table width="100%">
            <tr>
                <td style="width: 40px;" align="center">
                    <a class="lincToUser" href="<c:url value="/blog?id={{message.sender.id}}"/>">
                        <img src="${contextPath}/images/avatars/{{message.sender.photoName}}" height="35">
                    </a>
                </td>
                <td>
                    <a class="lincToUser" href="<c:url value="/blog?id={{message.sender.id}}"/>">
                        <b>{{message.sender.lastName}} {{message.sender.firstName}}:</b>
                    </a><br>
                    {{message.text}}
                </td>
                <td style="width: 80px;">
                    {{message.formatedCreationTime}}
                </td>
            </tr>
        </table>
    </div>
</script>
</body>
</html>