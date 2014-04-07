<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="" rel="icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/style.css'/>" media="all" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div align="center">
		<table width="100%">
			<tr width="20%">
				<td width="80%" align="center">
					<div class="head">
						<table width="80%">
							<tr width="80%">
								<td><a href="/index">SelfMash</a></td>

								<td width="20%" align="left"><a href="/user/${username}"><c:out
											value="Home"></c:out></a></td>
								<td align="right">
									<ul class="hr">
										<li><a href="">Settings</a></li>
										<li><a href="<c:url value="j_spring_security_logout" />">Logout</a></li>
									</ul>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>