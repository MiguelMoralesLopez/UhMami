package uhmami.modelo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.repository.MesaRepository;
import uhmami.modelo.utils.Utils;

@Controller
public class GetController {
	
	@Autowired
	private Utils utils;
	
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
	
	@GetMapping("/reservas/mesasBloqueadas")
	public MesasBloqueadasDto mostrarReservas(MesaDto mesaDto) {

		return utils.mesasReservadas(mesaDto);
	}

}
