package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.entities.Mesa;

public interface MesaService {
	Mesa buscarUna(Integer idMesa);
	List<Mesa> buscarTodas();
}
