package uhmami.modelo.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.MesaConReserva;

@Repository
public class MesaConReservaServiceImpl implements MesaConReservaService{

	@Override
	public MesaConReserva buscarPorMesa(Integer idMesa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MesaConReserva buscarPorReserva(Integer idReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MesaConReserva> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean altaMesaConReserva(Integer idReserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrarMesaConReserva(Integer idReserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarMesaConReserva(MesaConReserva mesaConReserva) {
		// TODO Auto-generated method stub
		return false;
	}

}
