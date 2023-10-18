package model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMember {

	private MemberDAO memberDAO;

	public JsonMember() {
	}

	public JsonMember(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

//	매개변수가 있는 생성자는 useBean액션에서 사용할 수 없으므로 아래 코드 작성
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@SuppressWarnings("unchecked")
	public String getAllMemberJson() {
		ArrayList<MemberDO> memberList = this.memberDAO.getAllMembers();
		JSONArray memberArray = new JSONArray();
		JSONObject memberJson = null;

		for (MemberDO member : memberList) {
			memberJson = new JSONObject();
//			멤버리스트에 저장된 회원 수 만큼 객체 생성

			memberJson.put("id", member.getId());
//			memberJson.put("passwd", member.getPasswd());
			memberJson.put("name", member.getName());
			memberJson.put("regdate", member.getRegdate());
//			memberJson.put("grade", member.getGrade());

			memberArray.add(memberJson);
		}

		return memberArray.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String getMemberJson(String id) {
		MemberDO member = memberDAO.getMember(id);
		JSONObject memberJson = null;
		String jsonResult = "";

		if (member != null) {
			memberJson = new JSONObject();

			memberJson.put("id", member.getId());
			memberJson.put("passwd", member.getPasswd());
			memberJson.put("name", member.getName());
			memberJson.put("regdate", member.getRegdate());
			memberJson.put("grade", member.getGrade());

			jsonResult = memberJson.toJSONString();
		}

		return jsonResult;
	}

	@SuppressWarnings("unchecked")
	public String getResult(int rowCount) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("result", rowCount);

		return jsonObject.toJSONString();
	}
//	insert작업이 성공시 처리 결과를 알려주는 메서드
	
	@SuppressWarnings("unchecked")
	public String getMessage(String msg) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("msg", msg);

		return jsonObject.toJSONString();
	}
//	실패시 메시지 반환
}