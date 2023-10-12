package exam1;

public class Greeter {

	private String format;
// System.out.printf("%s님의 방문을 환영합니다.", "손흥민");

	public Greeter() {
	}

//	printf의 형식포맷을 문자열로 지정
	public void setFormat(String format) {
		this.format = format;
	}

	public String greet(String guest) {
		return String.format(this.format, guest);
	}
// System.out.printf("%s님의 방문을 환영합니다. = this.format", "손흥민 = guest");
}
