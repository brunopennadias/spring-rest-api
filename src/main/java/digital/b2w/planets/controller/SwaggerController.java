package digital.b2w.planets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

	@GetMapping	("/swagger")
	public String swagger() {
		return "redirect:/swagger-ui.html";
	}
	
	@GetMapping	("/")
	public String inicial() {
		return "redirect:/swagger-ui.html";
	}
}