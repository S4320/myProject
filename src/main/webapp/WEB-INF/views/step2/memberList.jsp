<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
controller: /myProject/step1/step1Controller.jsp

 요청1) step1Controller.jsp?command=list
	 . memberList.jsp 뷰 제공
	 . ArrayList<MemberDO> 객체를 제공해서 전체 회원 조회 테이블에 회원 정보 출력
 요청2) step1Controller.jsp?command=insert
	 . insert 작업 후 memberList.jsp 뷰 제공
	 . insert 작업 실패시 오류 메시지 출력
 요청3) step1Controller.jsp?command=modify
	 . 회원 ID를 읽고 해당 회원 정보를 제공
	 . memberModify.jsp 뷰 제공
	 
	 요청2,3은 자바스크립스가 필요함.
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Insert &amp; List</title>
<link rel="stylesheet" href="/myProject/css/list.css" />
<script src="/myProject/step1/js/memberList.js" charset="UTF-8"></script>
</head>
<body>

<h2>회원 가입 &amp; 및 전체 회원 조회</h2>
<hr />

<h3>[회원가입]</h3>

<form method="POST" id="insertMember">
<fieldset>
<legend>회원 정보 입력</legend>

<label for="id">아이디</label>
<input type="email" name="id" id="id" placeholder="ex) example@gamil.com" required /><br />

<label for="passwd">비밀번호</label>
<input type="password" name="passwd" id="passwd" placeholder="4글자 이상" required /><br />

<label for="repasswd">비밀번호 확인</label>
<input type="password" id="repasswd" placeholder="동일 비밀번호 입력" required /><br />

<label for="name">이름</label>
<input type="text" name="name" id="name" placeholder="2글자 이상" required /> :::

<input type="submit" value="가입" />

</fieldset>
</form>

<div id="msg">${msg}</div>

<hr />
<!-- 웹페이지가 로드 완료되는 시점에 보여준다. -->
<h3>[전체 회원 조회]</h3>

<table id = "ListTable">
<tr>
<th>번호</th>
<th>아이디</th>
<th>이름</th>
<th>등록일</th>
<th>회원정보수정</th>
</tr>

<c:forEach items="${memberList}" var="member" varStatus="status">
<tr>
<td>${status.count}</td>
<td>${member.id}</td>
<td>${member.name}</td>
<td>${member.regdate}</td>
<td><button id="${member.id}">수정</button></td>
</tr>
</c:forEach>
</table>
<!-- 버튼의 개수마다 이벤트 핸들러를 만들어야 한다. 방금 회원을 한 명 등록을 하면 -->
<!-- 버튼을 하나 더 추가 해야한다. -->

<!-- 이벤트 버블링으로 해결함. 자식에게 발생한 이벤트가 부모에게 전파한다.  -->
<!-- 어느 부모가 이벤트를 처리? "tr or table" => table -->

</body>
</html>