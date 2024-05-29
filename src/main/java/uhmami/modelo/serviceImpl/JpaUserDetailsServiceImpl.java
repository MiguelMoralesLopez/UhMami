package uhmami.modelo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uhmami.modelo.entities.Usuario;
import uhmami.modelo.repository.UsuarioRepository;

/**
 * Esta clase proporciona una implementación de UserDetailsService que se utiliza para cargar
 * los detalles del usuario desde una base de datos utilizando JPA. Está diseñado para integrarse
 * con Spring Security para la autenticación de usuarios.
 */
@Service
public class JpaUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Carga los detalles del usuario por su nombre de usuario.
	 *
	 * @param username El nombre de usuario del usuario.
	 * @return UserDetails que representa al usuario.
	 * @throws UsernameNotFoundException si el usuario no puede ser encontrado.
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findById(username).orElse(null);
		List<GrantedAuthority> authorities = usuario.getPerfiles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), authorities);
	}

}
