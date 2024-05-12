package uhmami.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.MesaConReserva;
import uhmami.modelo.repository.MesaConReservaRepository;

@Repository
public class MesaConReservaServiceImpl implements MesaConReservaService{
	
	@Autowired
	private MesaConReservaRepository mesaConReservaRepository;

	@Override
	public MesaConReserva buscarPorMesa(Integer idMesa) {
		
		return mesaConReservaRepository.findByIdMesa(idMesa);
	}

	@Override
	public MesaConReserva buscarPorReserva(Integer idReserva) {
		
		return mesaConReservaRepository.findByIdMesa(idReserva);
	}

	@Override
	public List<MesaConReserva> buscarTodas() {
		return mesaConReservaRepository.findAll();
	}

	@Override
	public boolean altaMesaConReserva(MesaConReserva mesaConReserva) {
		mesaConReservaRepository.save(mesaConReserva);
		return true;
	}

	@Override
	public boolean borrarMesaConReserva(Integer idReserva) {
		if(buscarPorReserva(idReserva) != null) {
			mesaConReservaRepository.delete(buscarPorReserva(idReserva));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificarMesaConReserva(MesaConReserva mesaConReserva) {
		if(buscarPorReserva(mesaConReserva.getIdReserva().getId()) != null) {
			mesaConReservaRepository.save(mesaConReserva);
			return true;
		} else {
			return false;
		}
	}

}
