package exam1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Exam1Main {
	
	public static void main(String[] args) {
		//스프링 컨테이너를 만드는 클래스임
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext (AppContext.class);
		//설정파일의 종류 xml,그루비,자바 클래스 등
		
		// bean의 '1.이름, 2.타입'으로 컨테이너에서 가져와서 사용함.
		Greeter g = context.getBean("greeter", Greeter.class);
		
		String msg = g.greet("손흥민");
		System.out.println(msg);
		
		context.close();
	}
}