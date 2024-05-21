package uhmami.modelo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Reserva;
import uhmami.modelo.repository.ReservaRepository;

@Repository
public class ReservaServiceImpl implements ReservaService{
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Reserva buscarUna(String idReserva) {
		return reservaRepository.findById(idReserva).orElse(null);
	}

	@Override
	public List<Reserva> buscarTodas() {
		return reservaRepository.findAll();
	}

	@Override
	public boolean altaReserva(Reserva reserva) {
		reservaRepository.save(reserva);
		return true;
	}

	@Override
	public boolean eliminarReserva(String idReserva) {
		if(buscarUna(idReserva) != null) {
			reservaRepository.delete(buscarUna(idReserva));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificarReserva(Reserva reserva) {
		if(buscarUna(reserva.getId()) != null) {
			reservaRepository.save(reserva);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Reserva> buscarPorFecha(String fecha)  {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(fecha != null) {
			//Date fechaBbdd = new java.sql.Date(formatter.parse(fecha).getTime());
			LocalDate fechaBbdd = LocalDate.parse(fecha, formatter);
			System.out.println(fechaBbdd);
			List<Reserva> lista = reservaRepository.findByFecha(fechaBbdd);
			System.out.println(lista);
			return lista;
		}
        
		return null;
	}

	@Override
	public ModificarReservasDto buscarPorIdReservaYClienteEmail(String id, String email) {
		return reservaRepository.findByIdAndEmailCliente(id, email);
	}

	@Override
	public List<Integer> buscarMesasOcupadas(String fecha, String hora) {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				if(fecha != null) {
					//Date fechaBbdd = new java.sql.Date(formatter.parse(fecha).getTime());
					LocalDate fechaBbdd = LocalDate.parse(fecha, formatter);
					System.out.println(fechaBbdd);
					List<Integer> lista = reservaRepository.findMesaByFechaAndHora(fechaBbdd, hora);
					System.out.println(lista);
					return lista;
				}
		        
				return null;
	}

}
