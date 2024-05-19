package uhmami.modelo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String>{
	
	@Query("SELECT r FROM Reserva r WHERE r.fecha = :fecha ORDER BY r.hora")
    List<Reserva> findByFecha(@Param("fecha") LocalDate fecha);

}
