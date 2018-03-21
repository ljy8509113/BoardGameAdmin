<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<title>공지사항</title>
</head>
<body>
	<h1>공지사항</h1>
	
	<ul>
		<li>${ notice.title }</li>
		<li>${ notice.regDate }</li>
		<li>${ admin.nickname }</li>
	</ul>
	<div>
		${ notice.description }
	</div>
	<div>
		<c:if test="${ !empty notice.link }">
		<a href="${ notice.link }">바로가기</a>
		</c:if>
	</div>
	<div>
		<a href="<c:url value='/admin/noticeRemove.do?no=${ notice.no }' />">삭제</a>
		<a href="<c:url value='/admin/noticeModify.do?no=${ notice.no }' />">수정</a>
	</div>
	
</body>
</html>