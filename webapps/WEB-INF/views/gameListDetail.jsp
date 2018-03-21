<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게임리스트 상세화면</title>
</head>
<body>
	<h1>게임 상세화면</h1>
	
	<table>
		<tr>
			<td><img alt="게임대표이미지" src="${ uploadpath }/${ game.coverImage }"></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>${ game.gameNo }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${ game.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${ game.description }</td>
		</tr>
		<tr>
			<td>개발상태</td>
			<td>${ game.state }</td>
		</tr>
		<tr>
			<td>버전</td>
			<td>${ game.version }</td>
		</tr>
	</table>
	<div>
	<a href="<c:url value='/admin/gameListModify.do?gameNo=${ game.gameNo }' />">수정하기</a>
	</div>
</body>
</html>