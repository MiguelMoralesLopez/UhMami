package uhmami.modelo.serviceImpl;

<<<<<<< HEAD:src/main/java/uhmami/modelo/service/UsuarioServiceImpl.java
public class UsuarioServiceImpl {
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Usuario;
import uhmami.modelo.repository.UsuarioRepository;
import uhmami.modelo.service.UsuarioService;

/**
 * Esta clase proporciona una implementación del servicio para operaciones relacionadas con los usuarios.
 * Implementa la interfaz UsuarioService.
 */
@Repository
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario buscarUno(String idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(idUsuario).orElse(null);
	}
>>>>>>> main:src/main/java/uhmami/modelo/serviceImpl/UsuarioServiceImpl.java

}
