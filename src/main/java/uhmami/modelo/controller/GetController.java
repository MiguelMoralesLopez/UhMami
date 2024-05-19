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
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.utils.Utils;

@Controller
public class GetController {
	
	@Autowired
	private Utils utils;
	
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
	
//	@GetMapping("/admin")
//	public String mostrarAdmin(Model model) {
//		List<Reserva> reservas = reservaServiceImpl.buscarTodas();
//		List<Map<String, Object>> reservasMap = reservas.stream().map(reserva -> {
//	        Map<String, Object> reservaMap = new HashMap<>();
//	        reservaMap.put("hora", reserva.getHora());
//	        reservaMap.put("nombre", reserva.getCliente().getNombre());
//	        reservaMap.put("comensales", reserva.getComensales());
//	        reservaMap.put("mesas", reserva.getMesas().stream().map(Mesa::getId));
//	        reservaMap.put("idReserva", reserva.getId());
//	        return reservaMap;
//	    }).collect(Collectors.toList());
//	    
//	    model.addAttribute("reservas", reservasMap);
//		return "listadoReservas";
//	}
	
	@GetMapping("/admin")
	public String mostrarAdmin(Model model) {
//	    List<Reserva> reservas = reservaServiceImpl.buscarTodas();
//	    List<Map<String, Object>> reservasMap = reservas.stream().map(reserva -> {
//	        Map<String, Object> reservaMap = new HashMap<>();
//	        reservaMap.put("hora", reserva.getHora());
//	        reservaMap.put("nombre", reserva.getCliente().getNombre());
//	        reservaMap.put("comensales", reserva.getComensales());
//	        
//	        String mesas = reserva.getMesas().stream()
//	                              .map(mesa -> mesa.getId().toString())
//	                              .collect(Collectors.joining(", "));
//	                              
//	        reservaMap.put("mesas", mesas);
//	        reservaMap.put("idReserva", reserva.getId());
//	        return reservaMap;
//	    }).collect(Collectors.toList());
//	    
//	    model.addAttribute("reservas", reservasMap);
	    return "listadoReservas";
	}
	
	@GetMapping("/reservas/mesasBloqueadas")
	public MesasBloqueadasDto mostrarReservas(MesaDto mesaDto) {

		return utils.mesasReservadas(mesaDto);
	}

}
