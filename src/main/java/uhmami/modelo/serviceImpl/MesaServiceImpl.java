package uhmami.modelo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.repository.MesaRepository;
import uhmami.modelo.service.MesaService;

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

	

}
