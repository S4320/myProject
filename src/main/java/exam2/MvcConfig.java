//스프링Web MVC 설정 클래스
package exam2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
// 클래스 기반의 설정은 xml(100여줄)과 다르게 어노테이션으로 한 줄로 해결.

public class MvcConfig implements WebMvcConfigurer {
//	WebMvcConfigurer가 가진 메서드는 모두 default메서드이다.
//	오버라이딩을 하지 않으면 default메서드를 그대로 사용하고
//	사용자는 필요한 기능만 추가하면 됨.
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
// 브라우저가 수많은 요청(오디오, 비디오, 이미지, 자바스크립트 등) 중에서
// 스프링MVC는 컨트롤러의 역할(동적인 것)만 제어함.
// 그 외는 DefaultServletHandlerConfigurer가 처리함
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/exam2/", ".jsp");
		// viewpath ~~ 와 동일한 역할
	}
}

// 보통은 3개의 설정클래스 사용
// MvcConfig.java
// ControllerConfig.java
// ModelConfig.java (모델을 관리하는 설정클래스)
