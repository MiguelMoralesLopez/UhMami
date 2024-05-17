package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Reserva;

public interface ReservaService {
	Reserva buscarUna(String idReserva);
	List<Reserva> buscarTodas();
	boolean altaReserva(Reserva reserva);
	boolean eliminarReserva(String idReserva);
	boolean modificarReserva(Reserva reserva);
}
