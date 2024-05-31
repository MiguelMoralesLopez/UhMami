package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Consulta;

/**
 * Interfaz que define los servicios relacionados con la entidad Consulta.
 */
public interface ConsultaService {

    /**
     * Busca una consulta por su ID.
     * 
     * @param idConsulta El ID de la consulta a buscar.
     * @return           La consulta encontrada, o null si no se encuentra ninguna.
     */
    Consulta buscarUna(Integer idConsulta);

    /**
     * Busca todas las consultas.
     * 
     * @return Una lista de todas las consultas.
     */
    List<Consulta> buscarTodas();

    /**
     * Da de alta una nueva consulta.
     * 
     * @param consulta La consulta a dar de alta.
     * @return         true si la consulta se dio de alta correctamente, false de lo contrario.
     */
    boolean altaConsulta(Consulta consulta);

    /**
     * Borra una consulta por su ID.
     * 
     * @param idConsulta El ID de la consulta a borrar.
     * @return           true si la consulta se borró correctamente, false de lo contrario.
     */
    boolean borrarConsulta(Integer idConsulta);

    /**
     * Modifica los datos de una consulta existente.
     * 
     * @param consulta Los nuevos datos de la consulta.
     * @return         true si la consulta se modificó correctamente, false de lo contrario.
     */
    boolean modificarConsulta(Consulta consulta);
}
