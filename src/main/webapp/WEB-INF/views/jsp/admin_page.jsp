<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>IN ADMIN PANEL | Powered by INDEZINER</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/admin_style.css'/>" media="all" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/resources/css/niceforms-default.css'/>"
	media="all" />
<script type="text/javascript" src="/resources/js/clockp.js"></script>
<script type="text/javascript" src="/resources/js/clockh.js"></script>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion
			.init({
				headerclass : "submenuheader", //Shared CSS class name of headers group
				contentclass : "submenu", //Shared CSS class name of contents group
				revealtype : "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
				mouseoverdelay : 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
				collapseprev : true, //Collapse previous content (so only one open at any time)? true/false 
				defaultexpanded : [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
				onemustopen : false, //Specify whether at least one header should be open always (so never all headers closed)
				animatedefault : false, //Should contents open by default be animated into view?
				persiststate : true, //persist state of opened contents within browser session?
				toggleclass : [ "", "" ], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
				togglehtml : [
						"suffix",
						"<img src='/resources/images/plus.gif' class='statusicon' />",
						"<img src='/resources/images/minus.gif' class='statusicon' />" ], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
				animatespeed : "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
				oninit : function(headers, expandedindices) { //custom code to run when headers have initalized
					//do nothing
				},
				onopenclose : function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
					//do nothing
				}
			})
</script>

<script type="text/javascript"
	src="/resources/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
</script>

<script language="javascript" type="text/javascript"
	src="/resources/js/niceforms.js"></script>


</head>
<body>
	<div id="main_container">

		<div class="header">
			<div class="logo">
				<a href="#"><img src="/resources/images/logo.gif" alt=""
					title="" border="0" /></a>
			</div>

			<div class="right_header">
				Welcome Admin, <a href="#">Visit site</a> | <a href="#"
					class="messages">(3) Messages</a> | <a
					href="<c:url value="j_spring_security_logout" />" class="logout">Logout</a>
			</div>
			<div id="clock_a"></div>
		</div>

		<div class="main_content">

			<div class="menu">
				<ul>
					<li><a class="current" href="index.html">Admin Home</a></li>
					<li><a href="list.html">Manage Categories<!--[if IE 7]><!--></a>
						<!--<![endif]--> <!--[if lte IE 6]><table><tr><td><![endif]-->
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul></li>
					<li><a href="login.html">Manage Users</a>
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2</a>
								<ul>
									<li><a class="sub2" href="#nogo">sublevel3</a>
										<ul>
											<li><a href="#nogo">Third level-1</a></li>
										</ul></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul></li>
					<li><a href="">Manage Orders</a>
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2</a>
								<ul>
									<li><a href="" title="">sulevel link</a></li>
									<li><a class="sub2" href="">sublevel3</a>
										<ul>
											<li><a href="">Third level-1</a></li>
										</ul></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul></li>

							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul></li>
					<li><a href="">Settings</a>
						<ul>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
							<li><a class="sub1" href="" title="">sublevel2</a>
								<ul>
									<li><a href="" title="">sublevel link</a></li>
									<li><a class="sub2" href="">sublevel3</a>
										<ul>
											<li><a href="#nogo">Third level-4</a></li>
										</ul></li>
									<li><a href="" title="">sulevel link</a></li>
								</ul></li>
							<li><a href="" title="">Lorem ipsum dolor sit amet</a></li>
						</ul></li>
					<li><a href="">Templates</a></li>
					<li><a href="">Custom details</a></li>
					<li><a href="">Contact</a></li>
				</ul>
			</div>
			<div class="center_content">
				<div class="left_content">
					<div class="sidebarmenu">
						<a class="menuitem submenuheader" href="">Requests</a>
						<div class="submenu">
							<ul>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="1" /></c:url>">Request
										№1 </a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="2" /></c:url>">Request
										№2</a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="3" /></c:url>">Request
										№3</a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="4" /></c:url>">Request
										№4</a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="5" /></c:url>">Request
										№5</a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="6" /></c:url>">Request
										№6</a></li>
								<li><a
									href="<c:url value="/admin" ><c:param name="requestPage" value="7" /></c:url>">Request
										№7</a></li>
							</ul>
						</div>
						<a class="menuitem submenuheader" href="">Sidebar Settings</a>
						<div class="submenu">
							<ul>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
							</ul>
						</div>
						<a class="menuitem submenuheader" href="">Add new products</a>
						<div class="submenu">
							<ul>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
								<li><a href="">Sidebar submenu</a></li>
							</ul>
						</div>
						<a class="menuitem" href="">User Reference</a> <a class="menuitem"
							href="">Blue button</a> <a class="menuitem_green" href="">Green
							button</a> <a class="menuitem_red" href="">Red button</a>
					</div>
				</div>
				<div class="right_content">
					<jsp:include page="include/request${requestPage}.jsp" />
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>