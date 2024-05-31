package uhmami.modelo.serviceImpl;

<<<<<<< HEAD:src/main/java/uhmami/modelo/service/MesaServiceImpl.java
public class MesaServiceImpl {
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.entities.Mesa;
import uhmami.modelo.repository.MesaRepository;
import uhmami.modelo.service.MesaService;

/**
 * Esta clase proporciona una implementación del servicio para operaciones relacionadas con las mesas.
 * Implementa la interfaz MesaService.
 */
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
>>>>>>> main:src/main/java/uhmami/modelo/serviceImpl/MesaServiceImpl.java

	

}
