package uhmami.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Reserva;
import uhmami.modelo.repository.ReservaRepository;

@Repository
public class ReservaServiceImpl implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;

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
	public boolean modificarReserva(Reserva reserva) {
		if(buscarUna(reserva.getId()) != null) {
			reservaRepository.save(reserva);
			return true;
		} else {
			return false;
		}
	}

}
