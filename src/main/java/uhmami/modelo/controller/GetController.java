package uhmami.modelo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uhmami.modelo.entities.Consulta;

@Controller
public class GetController {
	
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}
	
	@GetMapping("/restaurante")
	public String mostrarRestaurante() {
		return "restaurante";
	}
	
	@GetMapping("/carta")
	public String mostrarCarta() {
		return "carta";
	}
	
	@GetMapping("/reservas")
	public String mostrarReservas() {
		return "reservas";
	}
	
	@GetMapping("/contacto")
	public String mostrarContacto(Model model) {
		model.addAttribute("consulta", new Consulta());
		return "contacto";
	}

}
