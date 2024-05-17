package uhmami.modelo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.utils.Utils;

@Controller
public class PostController {
	
	@Autowired
	private Utils utils;
	
	@PostMapping(value="/contacto/alta", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String procesarFormContacto(@RequestParam(name="nombre")String nombre,
			@RequestParam(name="telefono")Integer telefono,
			@RequestParam(name="comentario")String comentario,
			RedirectAttributes redirectAttributes) {
		
		if(utils.procesarFormContacto(nombre, telefono, comentario)) {
			return "redirect:/contacto";
		} else {
			//redirectAttributes.addFlashAttribute("mensajeError", "Parece que ha ocurrido un error, por favor intentelo de nuevo en unos minutos.");
			return "redirect:/contacto";
		}
		
	}
	
	@PostMapping(value="/reservas/reservar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String procesarFormReserva(@ModelAttribute("reservaDto") ReservaDto reservaDto,
			RedirectAttributes redirectAttributes) {
		
		if(utils.procesarFormReserva(reservaDto)) {
			return "redirect:/reservas";
		} else {
			//redirectAttributes.addFlashAttribute("mensajeError", "Parece que ha ocurrido un error, por favor intentelo de nuevo en unos minutos.");
			return "redirect:/reservas";
		}
	}
}
