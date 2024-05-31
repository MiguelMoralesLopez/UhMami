package uhmami.modelo.service;

<<<<<<< HEAD
public interface UsuarioService {
=======
import uhmami.modelo.entities.Usuario;

/**
 * Interfaz que define los servicios relacionados con la entidad Usuario.
 */
public interface UsuarioService {
	
    /**
     * Busca un usuario por su ID.
     * 
     * @param idUsuario El ID del usuario a buscar.
     * @return          El usuario encontrado, o null si no se encuentra ninguno.
     */
	Usuario buscarUno(String idUsuario);
>>>>>>> main

}
