package uhmami.modelo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

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
	public String mostrarReservas(Model model) {
		model.addAttribute("reservaDto", new ReservaDto());
		return "reservas";
	}
	
	
	@GetMapping("/contacto")
	public String mostrarContacto(Model model) {
		model.addAttribute("consulta", new Consulta());
		return "contacto";
	}
	

	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@GetMapping("/admin")
	public String mostrarAdmin(Model model) {

	    return "listadoReservas";
	}
	
	
	@GetMapping("/gestionarReservas")
	public String gestionarReservas(Model model) {
		model.addAttribute("modificarReservasDto", new ModificarReservasDto());
		return "gestionarReservas";
	}

}
