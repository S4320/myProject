// 스프링 설정 클래스
// 컨테이너에 저장될 bean을 등록해줌
package exam1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s님의 방문을 환영합니다.");
		
		return g;
	}
}
