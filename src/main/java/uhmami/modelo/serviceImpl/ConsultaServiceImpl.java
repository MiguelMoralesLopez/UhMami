package uhmami.modelo.serviceImpl;

<<<<<<< HEAD:src/main/java/uhmami/modelo/service/ConsultaServiceImpl.java
public class ConsultaServiceImpl {
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Consulta;
import uhmami.modelo.repository.ConsultaRepository;
import uhmami.modelo.service.ConsultaService;

/**
 * Implementación de la interfaz ConsultaService que proporciona servicios relacionados con la entidad Consulta.
 */
@Repository
public class ConsultaServiceImpl implements ConsultaService{
	
	@Autowired
	private ConsultaRepository consultaRepository;

	@Override
	public Consulta buscarUna(Integer idConsulta) {
		return consultaRepository.findById(idConsulta).orElse(null);
	}

	@Override
	public List<Consulta> buscarTodas() {
		return consultaRepository.findAll();
	}

	@Override
	public boolean altaConsulta(Consulta consulta) {
		consultaRepository.save(consulta);
		return true;
		
	}

	@Override
	public boolean borrarConsulta(Integer idConsulta) {
		if(buscarUna(idConsulta) != null) {
			consultaRepository.delete(buscarUna(idConsulta));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificarConsulta(Consulta consulta) {
		if(buscarUna(consulta.getId()) != null) {
			consultaRepository.save(consulta);
			return true;
		} else {
			return false;
		}
	}
>>>>>>> main:src/main/java/uhmami/modelo/serviceImpl/ConsultaServiceImpl.java

}
