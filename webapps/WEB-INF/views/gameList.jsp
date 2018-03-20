<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Game List</title>
</head>
<body>
	<h1>게임리스트</h1>
	
	<c:forEach items="${ list }" var="game">
	<div><img alt="게임대표이미지" src="${ uploadpath }/${ game.coverImage }"></div>
	<ul>
		<li><a href="<c:url value='/admin/gameListDetail.do?gameNo=${ game.gameNo }' />">${ game.title }</a>
		<li>${ game.version }
		<li><a href="<c:url value='/admin/subimage.do' />">이미지 등록</a>
	</ul>
	</c:forEach>
	
	<a href="<c:url value='/admin/gameListAdd.do' />">게임등록</a>
	
</body>
</html>