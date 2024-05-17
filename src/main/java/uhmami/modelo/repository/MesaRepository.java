package uhmami.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uhmami.modelo.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{
	
	@Query("SELECT m FROM Mesa m " +
	           "JOIN m.reservas r " +
	           "WHERE r.fecha = :fecha AND r.hora = :hora")
	    List<Mesa> findMesasReservadasByFechaAndHora(@Param("fecha") String fecha, @Param("hora") String hora);
	}
