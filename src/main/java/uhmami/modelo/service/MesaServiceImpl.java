package uhmami.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;
import uhmami.modelo.repository.MesaRepository;

@Repository
public class MesaServiceImpl implements MesaService{
	
	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public Mesa buscarUna(Integer idMesa) {
		// TODO Auto-generated method stub
		return mesaRepository.findById(idMesa).orElse(null);
	}

	@Override
	public List<Mesa> buscarTodas() {
		// TODO Auto-generated method stub
		return mesaRepository.findAll();
	}

	@Override
	public List<Mesa> buscarOcupadas(String fecha, String hora) {
		
		return mesaRepository.findMesasReservadasByFechaAndHora(fecha, hora);
	}

}
