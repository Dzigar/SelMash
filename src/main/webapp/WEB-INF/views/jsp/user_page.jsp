<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<table width="100%">
		<tr width="20%">
			<td width="80%" align="center">
				<div class="head">
					<table width="80%">
						<tr width="80%">
							<td><a href="/index">SelfMash</a></td>
							<td width="20%" align="left"><a href="/${username}"><c:out
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

	<div class="user-list" id="block">
		<c:choose>
			<c:when test="${not empty userList }">
				<c:forEach var="user" items="${userList}">
					<a href="/${user.login}"> <c:out
							value="${user.name} ${user.lastname}" /></a>
					<br>
				</c:forEach>
			</c:when>

		</c:choose>


		<c:choose>
			<c:when test="${not empty Estimations }">
				<c:forEach var="estimation" items="${Estimations}">
					<a href="/${estimation.user.login}"><c:out
							value="${estimation.user.name}" /></a>
					<c:out value=" ${estimation.estimation}"></c:out>
					<br>
				</c:forEach>
			</c:when>

		</c:choose>
	</div>
	<div class="info" id="block">
		<table style="width: 100%; margin-top: 5px; margin-left: 10px;">
			<tr>
				<th rowspan="2" style="width: 10%;" valign="top"><c:choose>
						<c:when test="${not empty profilePhoto}">
							<img
								src="<c:out value="/resources/selfshots/user${userId}/${profilePhoto}" />"
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
						<li><c:out value="${name}" /></li>
						<li><c:out value="${lastname}" /></li>
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
									<li>County:</li>
									<li>City:</li>
									<li>Language:</li>
									<li>Days online:</li>
								</ul>
							</td>
							<td>
								<ul style="list-style-type: none;">

									<li><c:out value="${birthdate}" /></li>
									<li><c:out value="${country}" /></li>
									<li><c:out value="${city}" /></li>
									<li><c:out value="${language}" /></li>
									<li><c:out value="${daysonline}" /></li>
								</ul>
							</td>
						</tr>
					</table>
		</table>
	</div>
	<div class="collection" id="block">
		<table align="center">
			<tr>
				<td colspan="4">
					<div class="uploadfile_form" align="center">
						<c:choose>
							<c:when test="${username == login}">
								<form action="/${login}/photo/upload" method="post"
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
								<a href='<c:url  value="/${login}/photo/${photo.id}"/>'><img
									src="<c:out value="/resources/selfshots/user${userId}/${photo.name}" />"
									width="100%" height="100%" alt="" /></a>
								<div class="cover boxcaption">
									<h3>Photoname</h3>
									<p>${photo.dateUpload}<br /> <a
											href='<c:url  value="/${login}/photo/${photo.id}"/>'>Fully</a>
									</p>
								</div>
							</div>
						</td>

					</c:forEach>
				</tr>

			</c:forEach>
		</table>
	</div>
</body>
</html>