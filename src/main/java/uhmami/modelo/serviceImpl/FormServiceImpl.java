package uhmami.modelo.serviceImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Cliente;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.FormService;
import uhmami.modelo.utils.Utils;

@Repository
public class FormServiceImpl implements FormService{
	
	@Autowired
	private ConsultaServiceImpl consultaServiceImpl;
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private MesaServiceImpl mesaServiceImpl;
	
	@Autowired
	private Utils utils;

	@Override
	public boolean procesarFormContacto(String nombre, Integer telefono, String comentario) {
		if(clienteServiceImpl.buscarPorNombreYTelefono(nombre, telefono) == null) {
			Cliente cliente = new Cliente();
			cliente.setNombre(nombre);
			cliente.setEmail(" ");
			cliente.setApellidos(" ");
			cliente.setTelefono(telefono);
			if(clienteServiceImpl.altaCliente(cliente)) {
				return true;
			} else {
				return false;
			}
		}
		
		Consulta consulta = new Consulta();
		consulta.setCliente(clienteServiceImpl.buscarPorNombreYTelefono(nombre, telefono));
		consulta.setComentario(comentario);
		consulta.setFecha(new Date());
		if(consultaServiceImpl.altaConsulta(consulta)) {
			return true;
		} else {
			return false;
		}
			
	}

	@Override
	public String procesarFormReserva(ReservaDto reservaDto) throws ParseException {
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
		reserva.setId(utils.generarIdReserva(reservaDto));
		reserva.setCliente(cliente);
		reserva.setComensales(Integer.valueOf(reservaDto.getComensales()));
		reserva.setObservaciones(reservaDto.getObservaciones());
		reserva.setHora(reservaDto.getHora());
		//reserva.setUsuario(usuarioServiceImpl.buscarUno("cliente"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaBbdd = LocalDate.parse(reservaDto.getFecha(), formatter);
		reserva.setFecha(fechaBbdd);
		reserva.setMesa(mesaServiceImpl.buscarUna(Integer.valueOf(reservaDto.getMesa())));
		if(reservaServiceImpl.altaReserva(reserva)) {
			return reserva.getId();
		} else {
			return "Error al crear la reserva";
		}
	}

}
