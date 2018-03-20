<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<title>공지사항 목록</title>
</head>
<body>
	<h1>공지사항 목록</h1>
	<table>
		<thead>
			<tr>
				<th>게임</th>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${ notice.gameNo }</td>
				<td>${ notice.no }</td>
				<td>${ notice.title }</td>
				<td>${ notice.regDate }</td>
			</tr>
		</tbody>
	</table>
</body>
</html>