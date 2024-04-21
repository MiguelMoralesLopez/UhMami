package uhmami.modelo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}

}
