package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.MesaConReserva;

public interface MesaConReservaService {
	
	MesaConReserva buscarPorMesa(Integer idMesa);
	MesaConReserva buscarPorReserva(Integer idReserva);
	List<MesaConReserva> buscarTodas();
	boolean altaMesaConReserva(MesaConReserva mesaConReserva);
	boolean borrarMesaConReserva (Integer idReserva);
	boolean modificarMesaConReserva (MesaConReserva mesaConReserva);

}
