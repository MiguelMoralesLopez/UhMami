package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Consulta;

public interface ConsultaService {
	
	Consulta buscarUna(Integer idConsulta);
	List<Consulta> buscarTodas();
	boolean altaConsulta (Consulta consulta);
	boolean borrarConsulta (Integer idConsulta);
	boolean modificarConsulta (Consulta consulta);

}
