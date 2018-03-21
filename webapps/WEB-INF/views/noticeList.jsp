<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${ list }" var="notice">
			<tr>
				<td>${ notice.no }</td>
				<td><a href="<c:url value='/admin/noticedetail.do?no=${ notice.no }' />">${ notice.title }</a></td>
				<td>
				<fmt:formatDate value="${ notice.regDate }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="<c:url value='/admin/noticeAdd.do' />">글쓰기</a>
	</div>
</body>
</html>