<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


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
				duration : 200
			});
		});
	});
</script>

<div id="block">
	<table align="center">
		<tr>
			<td colspan="4">
				<!-- Upload photo form -->
				<div class="uploadfile_form" align="center">
					<c:choose>
						<c:when test="${authentication == login}">
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
							<a href="/photo/${photo.id}"><img
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
