package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Reserva;

public interface ReservaService {
	Reserva buscarUna(Integer idReserva);
	List<Reserva> buscarTodas();
	boolean altaReserva(Reserva reserva);
	boolean eliminarReserva(Integer idReserva);
	boolean modificarReserva(Reserva reserva);
}
