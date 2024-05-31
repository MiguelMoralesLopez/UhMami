package uhmami.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Cliente;

/**
 * Interfaz que define un repositorio para la entidad Cliente.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Busca un cliente por su nombre y número de teléfono.
     * 
     * @param nombre   El nombre del cliente a buscar.
     * @param telefono El número de teléfono del cliente a buscar.
     * @return         El cliente encontrado, o null si no se encuentra ninguno.
     */
    Cliente findByNombreAndTelefono(String nombre, Integer telefono);
}
