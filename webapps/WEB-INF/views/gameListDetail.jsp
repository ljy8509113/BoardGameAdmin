<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게임리스트 상세화면</title>
</head>
<body>
	<h1>게임리스트 상세화면</h1>
	
	<dl>
		<dt>번호</dt><dd>${ game.gameNo }</dd>
		<dt><dd><img alt="게임대표이미지" src="${ uploadpath }/${ game.coverImage }"></dd></dt>
		<dt>제목</dt><dd>${ game.title }</dd>
		<dt>설명</dt><dd>${ game.description }</dd>
		<dt>개발상태</dt><dd>${ game.state }</dd>
		<dt>버전</dt><dd>${ game.version }</dd>
	</dl>
</body>
</html>s