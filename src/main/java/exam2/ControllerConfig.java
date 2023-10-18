//컨트롤러(자바Bean)를 여기서 등록하고 사용할 수 있다.
//여기서 등록한 컨트롤러는 모두 스프링 컨테이너의 빈으로 들어간다.
package exam2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exam1.Greeter;

@Configuration // 설정 클래스를 만들기 위해 반드시 사용
public class ControllerConfig {

	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s님의 방문을 환영합니다.");
		
		return g;
	}
	
	@Bean
	public GreeterController greeterController() {
		return new GreeterController(this.greeter());
	}
}
