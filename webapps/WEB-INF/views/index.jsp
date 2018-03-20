<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>홈페이지</title>
</head>
<body>
	<h1>홈페이지</h1>

	<p>
		<sec:authorize access="!hasRole('ADMIN')">
		<a href="<c:url value='/login.do'/>">로그인</a>
		</sec:authorize>
		
		<sec:authorize access="hasRole('ADMIN')">
		<a href="<c:url value='/logout.do'/>">로그아웃</a>
	
		<a href="<c:url value='/admin/gameList.do'/>">게임리스트</a>
		<a href="<c:url value='/admin/notice.do'/>">공지사항</a>
		</sec:authorize>
	</p>

</body>
</html>