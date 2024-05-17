package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Mesa;

public interface MesaService {
	Mesa buscarUna(Integer idMesa);
	List<Mesa> buscarTodas();
	List<Mesa> buscarOcupadas(String fecha, String hora);
}
