package uhmami.modelo.service;

import java.util.List;

import uhmami.modelo.entities.Cliente;

public interface ClienteService {
	
	Cliente buscarUno(Integer idCliente);
	Cliente buscarPorNombreYTelefono(String nombre, Integer telefono);
	List<Cliente> buscarTodos();
	boolean altaCliente (Cliente cliente);
	boolean borrarCliente (Integer idCliente);
	boolean modificarCliente (Cliente cliente);
}
