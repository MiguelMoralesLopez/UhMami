package uhmami.modelo.service;

<<<<<<< HEAD
public interface ClienteService {

=======
import java.util.List;

import uhmami.modelo.entities.Cliente;

/**
 * Interfaz que define los servicios relacionados con la entidad Cliente.
 */
public interface ClienteService {

    /**
     * Busca un cliente por su ID.
     * 
     * @param idCliente El ID del cliente a buscar.
     * @return          El cliente encontrado, o null si no se encuentra ninguno.
     */
    Cliente buscarUno(Integer idCliente);

    /**
     * Busca un cliente por su nombre y número de teléfono.
     * 
     * @param nombre    El nombre del cliente a buscar.
     * @param telefono  El número de teléfono del cliente a buscar.
     * @return          El cliente encontrado, o null si no se encuentra ninguno.
     */
    Cliente buscarPorNombreYTelefono(String nombre, Integer telefono);

    /**
     * Busca todos los clientes.
     * 
     * @return Una lista de todos los clientes.
     */
    List<Cliente> buscarTodos();

    /**
     * Da de alta un nuevo cliente.
     * 
     * @param cliente El cliente a dar de alta.
     * @return        true si el cliente se dio de alta correctamente, false de lo contrario.
     */
    boolean altaCliente(Cliente cliente);

    /**
     * Borra un cliente por su ID.
     * 
     * @param idCliente El ID del cliente a borrar.
     * @return          true si el cliente se borró correctamente, false de lo contrario.
     */
    boolean borrarCliente(Integer idCliente);

    /**
     * Modifica los datos de un cliente existente.
     * 
     * @param cliente Los nuevos datos del cliente.
     * @return        true si el cliente se modificó correctamente, false de lo contrario.
     */
    boolean modificarCliente(Cliente cliente);
>>>>>>> main
}
