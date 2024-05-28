package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Reserva;

public interface ReservaService {
	Reserva buscarUna(String idReserva);
	ModificarReservasDto buscarPorIdReservaYClienteEmail(String id, String email);
	List<Reserva> buscarTodas();
	List<Reserva> buscarPorFecha(String fecha);
	List<Integer> buscarMesasOcupadas(String fecha, String hora);
	boolean altaReserva(Reserva reserva);
	boolean eliminarReserva(String idReserva);
	boolean modificarReserva(ModificarReservasDto ModificarReservasDto);
	MesasBloqueadasDto mesasReservadas(MesaDto mesaDto);
}
