package exam2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import exam1.Greeter;

public class GreeterController {

	private Greeter greeter;

	public GreeterController(Greeter greeter) {
		this.greeter = greeter;
	}

	@GetMapping("/exam2/hello")
	public String hello(Model model) {

		return "hello";
	}
}
