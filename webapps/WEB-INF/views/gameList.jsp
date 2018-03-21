<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Game List</title>
</head>
<body>
	<h1>Game List</h1>
	
	<c:forEach items="${ list }" var="game">
	<table>
		<tr>
			<th>Image</th>
			<th>Title</th>
			<th>Version</th>
		</tr>
		<tr>
			<td><img alt="게임대표이미지" src="${ uploadpath }/${ game.coverImage }"><td>
			<td rowspan="2"><a href="<c:url value='/admin/gameListDetail.do?gameNo=${ game.gameNo }' />">${ game.title }</a></td>
			<td rowspan="2">${ game.version }</td>
		<tr>
		<tr>
			<td><a href="<c:url value='/admin/subimage.do' />">이미지 등록</a></td>
		</tr>
	</table>
	</c:forEach>
	
	<div>
	<a href="<c:url value='/admin/gameListAdd.do' />">게임등록</a>
	</div>
</body>
</html>