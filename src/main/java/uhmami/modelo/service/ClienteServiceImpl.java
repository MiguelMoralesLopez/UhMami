package uhmami.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Cliente;
import uhmami.modelo.repository.ClienteRepository;

@Repository
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente buscarUno(Integer idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public boolean altaCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		return true;
	}

	@Override
	public boolean borrarCliente(Integer idCliente) {
		if(buscarUno(idCliente) != null) {
			clienteRepository.delete(buscarUno(idCliente));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		if(buscarUno(cliente.getId()) != null) {
			clienteRepository.save(cliente);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Cliente buscarPorNombreYTelefono(String nombre, Integer telefono) {
		return clienteRepository.findByNombreAndTelefono(nombre, telefono);
	}

}
