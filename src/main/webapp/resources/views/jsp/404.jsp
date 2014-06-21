<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<style type="text/css">
#fof {
	display: block;
	width: 100%;
	margin: 100px 0;
	line-height: 1.6em;
	text-align: center;
}

#fof .hgroup {
	text-transform: uppercase;
}

#fof .hgroup h1 {
	margin-bottom: 25px;
	font-size: 80px;
}

#fof .hgroup h1 span {
	display: inline-block;
	margin-left: 5px;
	padding: 2px;
	border: 1px solid #CCCCCC;
	overflow: hidden;
}

#fof .hgroup h1 span strong {
	display: inline-block;
	padding: 20 20px 20px;
	border: 1px solid #CCCCCC;
	font-weight: normal;
}

#fof .hgroup h2 {
	font-size: 60px;
}

#fof .hgroup h2 span {
	display: block;
	font-size: 30px;
	padding: 20 20px 20px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<!-- Head -->
	<%@include file="form/head.jsp"%>
	<!-- end head -->
	<div class="wrapper row2">
		<div id="container" class="clear">
			<section id="fof" class="clear">
				<div class="hgroup">
					<h1>
						<span><strong>4</strong></span><span><strong>0</strong></span><span><strong>4</strong></span>
					</h1>
					<h2>
						Error! <span>Page Not Found</span>
					</h2>
				</div>

			</section>
		</div>
	</div>

</body>
</html>