package uhmami.modelo.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Cliente;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.entities.MesaConReserva;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ClienteServiceImpl;
import uhmami.modelo.service.ConsultaServiceImpl;
import uhmami.modelo.service.MesaConReservaServiceImpl;
import uhmami.modelo.service.MesaServiceImpl;
import uhmami.modelo.service.ReservaServiceImpl;

@Controller
public class PostController {
	
	@Autowired
	private ConsultaServiceImpl consultaServiceImpl;
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private MesaServiceImpl mesaServiceImpl;
	
	@Autowired
	private MesaConReservaServiceImpl mesaConReservaServiceImpl;
	
	@PostMapping(value="/contacto/alta", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String procesarFormContacto(@RequestParam(name="nombre")String nombre,
			@RequestParam(name="telefono")Integer telefono,
			@RequestParam(name="comentario")String comentario) {
		if(clienteServiceImpl.buscarPorNombreYTelefono(nombre, telefono) == null) {
			Cliente cliente = new Cliente();
			cliente.setNombre(nombre);
			cliente.setEmail(" ");
			cliente.setApellidos(" ");
			cliente.setTelefono(telefono);
			clienteServiceImpl.altaCliente(cliente);
		}
		
		Consulta consulta = new Consulta();
		consulta.setCliente(clienteServiceImpl.buscarPorNombreYTelefono(nombre, telefono));
		consulta.setComentario(comentario);
		consulta.setFecha(new Date());
		consultaServiceImpl.altaConsulta(consulta);
		return "redirect:/contacto";
	}
	
	@PostMapping(value="/reservas/reservar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String procesarFormReserva(@ModelAttribute("reservaDto") ReservaDto reservaDto) {
		Cliente cliente = new Cliente();
		if(clienteServiceImpl.buscarPorNombreYTelefono(reservaDto.getNombre(), Integer.valueOf(reservaDto.getTelefono())) == null) {
			cliente.setNombre(reservaDto.getNombre());
			cliente.setApellidos(reservaDto.getApellidos());
			cliente.setEmail(reservaDto.getEmail());
			cliente.setTelefono(Integer.valueOf(reservaDto.getTelefono()));
			clienteServiceImpl.altaCliente(cliente);
		} else {
			cliente = clienteServiceImpl.buscarPorNombreYTelefono(reservaDto.getNombre(), Integer.valueOf(reservaDto.getTelefono()));
			cliente.setApellidos(reservaDto.getApellidos());
			cliente.setEmail(reservaDto.getEmail());
			clienteServiceImpl.modificarCliente(cliente);
		}
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setComensales(Integer.valueOf(reservaDto.getComensales()));
		reserva.setObservaciones(reservaDto.getObservaciones());
		List<Mesa> mesa = new ArrayList<>();
		mesa.add(mesaServiceImpl.buscarUna(Integer.valueOf(reservaDto.getMesa())));
		reserva.setMesa(mesa);
		reservaServiceImpl.altaReserva(reserva);
		
		
		return "redirect:/reservas";
	}
}
