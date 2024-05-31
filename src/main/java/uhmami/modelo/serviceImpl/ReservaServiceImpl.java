package uhmami.modelo.serviceImpl;

<<<<<<< HEAD:src/main/java/uhmami/modelo/service/ReservaServiceImpl.java
public class ReservaServiceImpl {
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Cliente;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.repository.ReservaRepository;
import uhmami.modelo.service.ReservaService;

/**
 * Esta clase proporciona una implementación del servicio para operaciones relacionadas con las reservas.
 * Implementa la interfaz ReservaService.
 */
@Repository
public class ReservaServiceImpl implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@Autowired
	private MesaServiceImpl mesaServiceImpl;

	@Override
	public Reserva buscarUna(String idReserva) {
		return reservaRepository.findById(idReserva).orElse(null);
	}

	@Override
	public List<Reserva> buscarTodas() {
		return reservaRepository.findAll();
	}

	@Override
	public boolean altaReserva(Reserva reserva) {
		reservaRepository.save(reserva);
		return true;
	}

	@Override
	public boolean eliminarReserva(String idReserva) {
		if(buscarUna(idReserva) != null) {
			reservaRepository.delete(buscarUna(idReserva));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificarReserva(ModificarReservasDto modificarReservasDto) {
		Reserva reserva = buscarUna(modificarReservasDto.getIdReserva());
		if(reserva != null) {
			Cliente cliente = clienteServiceImpl.buscarPorNombreYTelefono(modificarReservasDto.getNombre(), modificarReservasDto.getTelefono());
			if(cliente != null) {
				reserva.setCliente(cliente);
			} else {
				cliente.setNombre(modificarReservasDto.getNombre());
				cliente.setApellidos(modificarReservasDto.getApellidos());
				cliente.setEmail(modificarReservasDto.getEmail());
				cliente.setTelefono(modificarReservasDto.getTelefono());
				clienteServiceImpl.altaCliente(cliente);
				reserva.setCliente(clienteServiceImpl.buscarPorNombreYTelefono(modificarReservasDto.getNombre(), modificarReservasDto.getTelefono()));
			}
			reserva.setComensales(modificarReservasDto.getComensales());
			reserva.setObservaciones(modificarReservasDto.getObservaciones());
			reserva.setHora(modificarReservasDto.getHora());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaBbdd = LocalDate.parse(modificarReservasDto.getFecha(), formatter);
			reserva.setFecha(fechaBbdd);
			List<Integer> mesasOcupadas = buscarMesasOcupadas(modificarReservasDto.getFecha(), modificarReservasDto.getHora());
			List<Mesa> listaMesasLibres = mesaServiceImpl.buscarTodas();
			listaMesasLibres.removeIf(mesa -> mesasOcupadas.contains(mesa.getId()));
			for (Integer mesa : mesasOcupadas) {
				if(reserva.getMesa().getId() == mesa) {
					Optional<Mesa> mesaElegida = listaMesasLibres.stream()
                            .filter(mesaLibre -> mesaLibre.getComensales() >= reserva.getComensales())
                            .findFirst();
					reserva.setMesa(mesaElegida.get());
				}
			}
			reservaRepository.save(reserva);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Reserva> buscarPorFecha(String fecha)  {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(fecha != null) {
			//Date fechaBbdd = new java.sql.Date(formatter.parse(fecha).getTime());
			LocalDate fechaBbdd = LocalDate.parse(fecha, formatter);
			System.out.println(fechaBbdd);
			List<Reserva> lista = reservaRepository.findByFecha(fechaBbdd);
			System.out.println(lista);
			return lista;
		}
        
		return null;
	}

	@Override
	public ModificarReservasDto buscarPorIdReservaYClienteEmail(String id, String email) {
		ModificarReservasDto modificarReservasDto = new ModificarReservasDto();
		Reserva reserva = reservaRepository.findByIdAndClienteEmail(id, email);
		modificarReservasDto.setIdReserva(reserva.getId());
		modificarReservasDto.setComensales(reserva.getComensales());
		modificarReservasDto.setObservaciones(reserva.getObservaciones());
		modificarReservasDto.setFecha(reserva.getFecha().toString());
		modificarReservasDto.setHora(reserva.getHora());
		modificarReservasDto.setIdCliente(reserva.getCliente().getId());
		modificarReservasDto.setNombre(reserva.getCliente().getNombre());
		modificarReservasDto.setApellidos(reserva.getCliente().getApellidos());
		modificarReservasDto.setEmail(reserva.getCliente().getEmail());
		modificarReservasDto.setTelefono(reserva.getCliente().getTelefono());
		System.out.println(modificarReservasDto);
		return modificarReservasDto;
	}

	@Override
	public List<Integer> buscarMesasOcupadas(String fecha, String hora) {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				if(fecha != null) {
					LocalDate fechaBbdd = LocalDate.parse(fecha, formatter);
					System.out.println(fechaBbdd);
					List<Integer> lista = reservaRepository.findMesaByFechaAndHora(fechaBbdd, hora);
					System.out.println(lista);
					return lista;
				}
		        
				return null;
	}
	@Override
	public MesasBloqueadasDto mesasReservadas(MesaDto mesaDto) {
		List<Integer> mesasBloqueadas = buscarMesasOcupadas(mesaDto.getFecha(), mesaDto.getHora());
		System.out.println(mesasBloqueadas);
		MesasBloqueadasDto mesasBloqueadasDto = new MesasBloqueadasDto();
		mesasBloqueadasDto.setIdMesas(mesasBloqueadas);
		return mesasBloqueadasDto;
	}
>>>>>>> main:src/main/java/uhmami/modelo/serviceImpl/ReservaServiceImpl.java

}
