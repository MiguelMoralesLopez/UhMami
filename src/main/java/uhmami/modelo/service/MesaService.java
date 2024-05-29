package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Mesa;

/**
 * Interfaz que define los servicios relacionados con la entidad Mesa.
 */
public interface MesaService {

    /**
     * Busca una mesa por su ID.
     * 
     * @param idMesa El ID de la mesa a buscar.
     * @return       La mesa encontrada, o null si no se encuentra ninguna.
     */
    Mesa buscarUna(Integer idMesa);

    /**
     * Busca todas las mesas disponibles.
     * 
     * @return Una lista de todas las mesas disponibles.
     */
    List<Mesa> buscarTodas();
}
