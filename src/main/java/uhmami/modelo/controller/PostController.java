package uhmami.modelo.controller;


import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

@Controller
public class PostController {
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
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
		
		try {
			if(utils.procesarFormReserva(reservaDto)) {
				return "redirect:/reservas";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//redirectAttributes.addFlashAttribute("mensajeError", "Parece que ha ocurrido un error, por favor intentelo de nuevo en unos minutos.");
		return "redirect:/reservas";
	}
	
	@PostMapping(value="/admin/{fecha}")
	public String mostrarReservasPorDia(@PathVariable ("fecha") String fecha, RedirectAttributes redirectAttributes) {
		List<Reserva> lista = reservaServiceImpl.buscarPorFecha(fecha);
		System.out.println("lista" + lista);
		redirectAttributes.addFlashAttribute("reservas", lista);
		return "redirect:/admin";
	}
} 
