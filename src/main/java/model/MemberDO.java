package model;

public class MemberDO {
// data object
	// 멤버변수의 이름 = 테이블의 컬럼명 = 포멧입력양식의 파라미터명
	private String id;
	private String passwd;
	private String name;
	private String regdate;
	private int grade;
	private String newPasswd;
	
	public MemberDO() {
	}
// DO도 겟터와 세터 외의 기능 메서드를 얼마든지 만들 수 있다.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNewPasswd() {
		return this.newPasswd;
	}

	public void setNewPasswd(String NewPasswd) {
		this.newPasswd = NewPasswd;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
