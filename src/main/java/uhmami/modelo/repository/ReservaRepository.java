package uhmami.modelo.repository;

<<<<<<< HEAD
public interface ReservaRepository {

=======
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Reserva;

/**
 * Interfaz que define un repositorio para la entidad Reserva.
 * Extiende JpaRepository, lo que proporciona operaciones CRUD estándar
 * y métodos de búsqueda predefinidos.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {
    
    /**
     * Busca reservas por fecha.
     * 
     * @param fecha La fecha de las reservas a buscar.
     * @return      Una lista de reservas para la fecha dada, ordenada por hora.
     */
    @Query("SELECT r FROM Reserva r WHERE r.fecha = :fecha ORDER BY r.hora")
    List<Reserva> findByFecha(@Param("fecha") LocalDate fecha);
    
    /**
     * Busca una reserva por su ID y el correo electrónico del cliente asociado.
     * 
     * @param id    El ID de la reserva.
     * @param email El correo electrónico del cliente asociado a la reserva.
     * @return      La reserva encontrada, o null si no se encuentra ninguna.
     */
    @Query("SELECT r FROM Reserva r JOIN Cliente c ON r.cliente.id = c.id WHERE r.id= :id AND c.email = :email")
    Reserva findByIdAndClienteEmail(@Param("id") String id, @Param("email") String email);
    
    /**
     * Busca las mesas disponibles para una fecha y hora específicas.
     * 
     * @param fecha La fecha para la cual se buscan las mesas disponibles.
     * @param hora  La hora para la cual se buscan las mesas disponibles.
     * @return      Una lista de identificadores de las mesas disponibles.
     */
    @Query("SELECT r.mesa.id FROM Reserva r WHERE r.fecha = :fecha AND r.hora = :hora")
    List<Integer> findMesaByFechaAndHora(@Param("fecha") LocalDate fecha, @Param("hora") String hora);
>>>>>>> main
}
