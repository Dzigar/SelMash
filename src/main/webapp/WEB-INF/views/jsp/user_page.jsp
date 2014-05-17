<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<script type="text/javascript" src="/resources/js/jquery-1.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//To switch directions up/down and left/right just place a "-" in front of the top/left attribute
		//Vertical Sliding
		$('.boxgrid.slidedown').hover(function() {
			$(".cover", this).stop().animate({
				top : '-260px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				top : '0px'
			}, {
				queue : false,
				duration : 300
			});
		});
		//Horizontal Sliding
		$('.boxgrid.slideright').hover(function() {
			$(".cover", this).stop().animate({
				left : '325px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				left : '0px'
			}, {
				queue : false,
				duration : 300
			});
		});
		//Diagnal Sliding
		$('.boxgrid.thecombo').hover(function() {
			$(".cover", this).stop().animate({
				top : '260px',
				left : '325px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				top : '0px',
				left : '0px'
			}, {
				queue : false,
				duration : 300
			});
		});
		//Partial Sliding (Only show some of background)
		$('.boxgrid.peek').hover(function() {
			$(".cover", this).stop().animate({
				top : '90px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				top : '0px'
			}, {
				queue : false,
				duration : 300
			});
		});
		//Full Caption Sliding (Hidden to Visible)
		$('.boxgrid.captionfull').hover(function() {
			$(".cover", this).stop().animate({
				top : '160px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				top : '260px'
			}, {
				queue : false,
				duration : 300
			});
		});
		//Caption Sliding (Partially Hidden to Visible)
		$('.boxgrid.caption').hover(function() {
			$(".cover", this).stop().animate({
				top : '-100px'
			}, {
				queue : false,
				duration : 300
			});
		}, function() {
			$(".cover", this).stop().animate({
				top : '220px'
			}, {
				queue : false,
				duration : 300
			});
		});
	});
</script>
<title></title>

</head>
<body>


	<!-- Head -->
	<table width="100%">
		<tr width="20%">
			<td width="80%" align="center">
				<div class="head">
					<table width="80%">
						<tr width="80%">
							<td><a href="/index">SelfMash</a></td>
							<td width="20%" align="left"><a
								href="/<sec:authentication property="principal.username" />"><c:out
										value="Home"></c:out></a></td>
							<td align="right">
								<ul class="hr">
									<li><a href="">Settings</a></li>
									<li><a href="<c:url value='j_spring_security_logout' />">Logout</a></li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<!-- end head -->

	<!-- users list   -->
	<div class="user-list" id="block">
		<c:choose>
			<c:when test="${not empty friendList }">
				<c:forEach var="friend" items="${friendList}">
					<a href="/${friend.login}"> <c:out
							value="${friend.name} ${friend.lastname}" /></a>
					<br>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
	<!-- end users list -->

	<!-- Info about user -->
	<div class="info" id="block">
		<table style="width: 100%; margin-top: 5px; margin-left: 10px;">
			<tr>
				<th rowspan="2" style="width: 10%;" valign="top"><c:choose>
						<c:when test="${not empty profilePhoto}">
							<img
								src="<c:out value="/resources/selfshots/${login}/${profilePhoto}" />"
								width="100" height="100" alt="" />
						</c:when>
						<c:otherwise>
							<img
								src="<c:out value="/resources/selfshots/photo_not_found.jpg" />"
								width="100" height="100" alt="" />
						</c:otherwise>
					</c:choose></th>
				<td valign="top" height="10">
					<ul class="user-info">
						<li><c:out value="${user.name}" /></li>
						<li><c:out value="${user.lastname}" /></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table style="margin-left: 0%;">
						<tr>
							<td>
								<ul style="list-style-type: none;">
									<li>Birth date:</li>
								</ul>
							</td>
							<td>
								<ul style="list-style-type: none;">
									<li><c:out value="${user.birthDate}" /></li>
								</ul>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<!-- Add friend to friends list button --> <c:if
						test="${username ne user.login}">
						<c:if test="${!isFriends}">
							<form action="/addfriend?user=${user.id}&login=${user.login}"
								method="post">
								<input type="submit" value="Add friend"
									class="submit-button_no_style">
							</form>
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	<!-- End info about user -->

	<!-- Collection user photos -->
	<div class="collection" id="block">
		<table align="center">
			<tr>
				<td colspan="4">
					<!-- Upload photo form -->
					<div class="uploadfile_form" align="center">
						<c:choose>
							<c:when test="${username == login}">
								<form action="/photo/upload" method="post"
									encType="multipart/form-data">
									<c:out value="Upload photo" />
									<input type="file" name="image" class="button_upload"
										accept="image/jpeg,image/png"> <input type="submit"
										value="Send" class="submit-button_no_style">
								</form>
							</c:when>
						</c:choose>
					</div>
				<td>
			</tr>

			<c:forEach var="row" items="${photoRows}">
				<tr>
					<c:forEach var="photo" items="${row}">
						<td>
							<div class="boxgrid caption">
								<a href="/photo/${photo.id}?value=${login}"><img
									src="<c:out value="/resources/selfshots/${login}/${photo.title}" />"
									width="100%" height="100%" alt="" /></a>
								<div class="cover boxcaption">
									<h3>Photoname</h3>
									<p>${photo.dateUpload}<br /> <a
											href='<c:url  value="/photo/${photo.id}"/>'>Fully</a>
									</p>
								</div>
							</div>
						</td>

					</c:forEach>
				</tr>

			</c:forEach>
		</table>
	</div>
	<!-- End collection photos -->

</body>
</html>