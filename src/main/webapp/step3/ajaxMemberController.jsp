<%@ page contentType="application/json; charset=UTF-8" %>

<jsp:useBean id="memberDAO" class="model.MemberDAO" />
<jsp:useBean id="jsonMember" class="model.JsonMember" />
<!-- 매개변수가 없는 생성자로 만들었으므로 DO가 없음  -->
<jsp:useBean id="memberDO" class="model.MemberDO" />
<jsp:setProperty name="memberDO" property="*" />

<%
jsonMember.setMemberDAO(memberDAO);
String command = request.getParameter("command");

if (command != null && command.equals("list")) {
	out.println(jsonMember.getAllMemberJson());
	out.flush();
} else if (command != null && command.equals("insert")) {

	try {
		int rowCount = memberDAO.insertMember(memberDO);
		out.println(jsonMember.getResult(rowCount));
	} catch (Exception e) {
		out.println(jsonMember.getMessage(e.getMessage()));
	}
	
	out.flush();
}
%>
