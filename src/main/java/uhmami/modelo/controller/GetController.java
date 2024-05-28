package uhmami.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.serviceImpl.ReservaServiceImpl;

@Controller
public class GetController {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
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
	
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@GetMapping("/listadoReservas")
	public String listado() {

	    return "listadoReservas";
	}
	
	
	@GetMapping("/gestionarReservas/{idReserva}/{email}")
	public String gestionarReservas(@PathVariable("idReserva") String idReserva, @PathVariable("email") String email, Model model) {
		ModificarReservasDto modificarReservasDto = reservaServiceImpl.buscarPorIdReservaYClienteEmail(idReserva, email);
	    model.addAttribute("modificarReservasDto", modificarReservasDto);
		
		return "gestionarReservas";
	}

}
