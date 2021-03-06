<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="mm" uri="/mytaglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${options.title}</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/style.css" />
	<link rel="icon" href="<%=basePath%>static/favicon.ico" type="image/x-icon" />
	<base href="<%=basePath%>" />
</head>
<body id="section-homepage">
	<div id="Container">
		<div id="ContainerTop"></div>
		<!-- start header -->
		<%@ include file="header.jsp" %>
		<%@ include file="mainmenu.jsp" %>
		<!-- start content items -->
		<div id="MainBody">
			<div id="MainBodyTop"></div>
			<div id="content">
				<div id="breadcrumb">
					Nothing found for  404
				</div>
				<div class="error_page">
    				<img alt="Kidmondo_face_sad" src="static/img/kidmondo_face_sad.gif" />
    				<h1>We're sorry...</h1>
    				<p>The page or journal you are looking for cannot be found.</p>
    				<p><a href="${options.url}">Return to the homepage</a></p>
				</div>
			</div>
			<div id="SideBar">
				<jsp:include page="seach.jsp" />
				<jsp:include page="sidebar.jsp" />
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>