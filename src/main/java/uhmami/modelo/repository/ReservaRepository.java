package uhmami.modelo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String>{
	
	@Query("SELECT r FROM Reserva r WHERE r.fecha = :fecha ORDER BY r.hora")
    List<Reserva> findByFecha(@Param("fecha") LocalDate fecha);
	
	@Query("SELECT r.ID, r.comensales, r.observaciones, r.fecha, r.hora, c.ID, c.nombre, c.apellidos, c.email, c.telefono FROM Reserva r "
			+ "	JOIN Cliente c"
			+ "	WHERE r.cliente.id = c.id AND (r.id= :id AND c.email = :email)")
	ModificarReservasDto findByIdAndEmailCliente(@Param("id") String id, @Param("email") String email);
	
	@Query("SELECT r.mesa.id FROM Reserva r WHERE r.fecha = :fecha AND r.hora = :hora")
	List<Integer> findMesaByFechaAndHora(@Param("fecha") LocalDate fecha, @Param("hora") String hora);

}
