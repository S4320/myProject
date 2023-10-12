package model;

import java.util.ArrayList;

public class TestMember {
	public static void main(String[] args) {
		MemberDAO memberDAO = new MemberDAO();
		
		ArrayList<MemberDO> memberList = memberDAO.getAllMembers();
		
		for(MemberDO member: memberList) {
			System.out.printf("아이디: %s \n", member.getId());
			System.out.printf("이름: %s \n", member.getName());
			System.out.printf("등록일: %s \n", member.getRegdate());
			System.out.println("---------------------------------------------");
		}
	}
}
		//* [memberDAO.insertMember() 테스트]
//		MemberDO newMember = new MemberDO();
//		newMember.setId("hmson@naver.com");
//		newMember.setPasswd("1234");
//		newMember.setName("손흥민");
//		
//		try {
//			memberDAO.insertMember(newMember);
//		}
//		catch(Exception e) {
//			System.out.printf("예외 메세지: %s \n", e.getMessage());			
//		}
//		//*/
//		
//		//* [memberDAO.getMember() 테스트]
//		MemberDO member = memberDAO.getMember("hmson@naver.com");
//		
//		if(member != null) {
//			System.out.printf("아이디: %s \n", member.getId());
//			System.out.printf("패스워드: %s \n", member.getPasswd());
//			System.out.printf("이름: %s \n", member.getName());
//			System.out.printf("등록일: %s \n", member.getRegdate());
//			System.out.printf("등급: %d \n", member.getGrade());
//		}
//		//*/
//		
//		memberDAO.closeConn();
//	}
//}