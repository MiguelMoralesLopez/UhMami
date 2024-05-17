package uhmami.modelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Usuario;
import uhmami.modelo.repository.UsuarioRepository;

@Repository
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario buscarUno(String idUsuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(idUsuario).orElse(null);
	}

}
