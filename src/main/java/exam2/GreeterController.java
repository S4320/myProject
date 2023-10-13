package exam2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam1.Greeter;

// dispatcher서블릿이 요청을 받지만 실제 처리는 컨트롤러에 위임을 한다.
// 이를 위해 컨트롤러 클래스는 반드시 Controller 어노테이션이 붙어야 한다.
@Controller
public class GreeterController {

	private Greeter greeter;

	public GreeterController(Greeter greeter) {
		this.greeter = greeter;
	}

	@GetMapping("/exam2/hello")		// http://localhost:8080/myProject/exam2/hello?name=손흥민
	//이 요청은 스프링MVC가 받아서 dispatch를 통해 전달.

	public String hello(Model model, @RequestParam(value="guest", required=false) String guest) {
		//	여기의 모델 객체는 request storage임.

		String msg = greeter.greet(guest);
		model.addAttribute("msg", msg);
		
		return "hello";
		//	"hello"는 view name임
	}
}

//@PostMapping
//@RequestMapping
