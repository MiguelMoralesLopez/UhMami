package uhmami.modelo.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ReservaDto;
import uhmami.modelo.entities.Cliente;
import uhmami.modelo.entities.Consulta;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.ClienteServiceImpl;
import uhmami.modelo.service.ConsultaServiceImpl;
import uhmami.modelo.service.MesaServiceImpl;
import uhmami.modelo.service.ReservaServiceImpl;
import uhmami.modelo.service.UsuarioServiceImpl;

@Service
@NoArgsConstructor
public class Utils {
	
	@Autowired
	private ConsultaServiceImpl consultaServiceImpl;
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@Autowired
	private MesaServiceImpl mesaServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private MesaServiceImpl mesaServiceimpl;
	
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
	
	public boolean procesarFormReserva(ReservaDto reservaDto) {
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
		reserva.setId(generarIdReserva(reservaDto));
		reserva.setCliente(cliente);
		reserva.setComensales(Integer.valueOf(reservaDto.getComensales()));
		reserva.setObservaciones(reservaDto.getObservaciones());
		reserva.setHora(reservaDto.getHora());
		reserva.setUsuario(usuarioServiceImpl.buscarUno("cliente"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formatter.parse(reservaDto.getFecha());
            reserva.setFecha(fecha);
        } catch (ParseException e) {
            // Manejar errores de formato de fecha
            e.printStackTrace();
            // Puedes agregar un mensaje de error al objeto BindingResult si es necesario
        }
		List<Mesa> mesa = new ArrayList<>();
		mesa.add(mesaServiceImpl.buscarUna(Integer.valueOf(reservaDto.getMesa())));
		reserva.setMesas(mesa);
		if(reservaServiceImpl.altaReserva(reserva)) {
			return true;
		} else {
			return false;
		}
		// TODO procesar los campos fecha y hora
	}
	
	public String generarIdReserva(ReservaDto reservaDto) {
		String iniciales = String.valueOf(reservaDto.getNombre().charAt(0)) + String.valueOf(reservaDto.getApellidos().charAt(0));
		String id = iniciales + eliminarGuionesYPuntos(reservaDto.getFecha()) + eliminarGuionesYPuntos(reservaDto.getHora());
		return id;
	}
	
	public static String eliminarGuionesYPuntos(String texto) {
        // Reemplaza las comillas dobles y los puntos por una cadena vacía
        String resultado = texto.replaceAll("-", "");
        return resultado;
    }
	

	public MesasBloqueadasDto mesasReservadas(MesaDto mesaDto) {
		List<Mesa> mesasBloqueadas = mesaServiceImpl.buscarOcupadas(mesaDto.getFecha(), mesaDto.getHora());
		MesasBloqueadasDto mesasBloqueadasDto = new MesasBloqueadasDto();
		mesasBloqueadasDto.setMesas(mesasBloqueadas);
		return mesasBloqueadasDto;
	}

}
