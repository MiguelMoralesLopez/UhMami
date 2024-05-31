package uhmami.modelo.service;

<<<<<<< HEAD
public interface ReservaService {

=======
import java.util.List;

import uhmami.modelo.dto.MesaDto;
import uhmami.modelo.dto.MesasBloqueadasDto;
import uhmami.modelo.dto.ModificarReservasDto;
import uhmami.modelo.entities.Reserva;

/**
 * Esta interfaz define los métodos que deben ser implementados por cualquier servicio relacionado con las reservas.
 */
public interface ReservaService {
	
	/**
	 * Busca una reserva por su identificador único.
	 *
	 * @param idReserva El identificador único de la reserva.
	 * @return La reserva encontrada, o null si no se encuentra ninguna reserva con ese identificador.
	 */
	Reserva buscarUna(String idReserva);
	
	/**
	 * Busca los detalles de una reserva por su identificador y el correo electrónico del cliente asociado.
	 *
	 * @param id El identificador único de la reserva.
	 * @param email El correo electrónico del cliente asociado a la reserva.
	 * @return Un DTO con los detalles de la reserva y del cliente asociado.
	 */
	ModificarReservasDto buscarPorIdReservaYClienteEmail(String id, String email);
	
	/**
	 * Busca todas las reservas.
	 *
	 * @return Una lista de todas las reservas.
	 */
	List<Reserva> buscarTodas();
	
	/**
	 * Busca todas las reservas para una fecha específica.
	 *
	 * @param fecha La fecha para la cual se desean buscar las reservas.
	 * @return Una lista de reservas para la fecha especificada.
	 */
	List<Reserva> buscarPorFecha(String fecha);
	
	/**
	 * Busca las mesas ocupadas para una fecha y hora específicas.
	 *
	 * @param fecha La fecha para la cual se desean buscar las mesas ocupadas.
	 * @param hora La hora para la cual se desean buscar las mesas ocupadas.
	 * @return Una lista de identificadores de las mesas ocupadas.
	 */
	List<Integer> buscarMesasOcupadas(String fecha, String hora);
	
	/**
	 * Registra una nueva reserva.
	 *
	 * @param reserva La reserva a ser registrada.
	 * @return true si la reserva se registró correctamente, false en caso contrario.
	 */
	boolean altaReserva(Reserva reserva);
	
	/**
	 * Elimina una reserva existente.
	 *
	 * @param idReserva El identificador único de la reserva a eliminar.
	 * @return true si la reserva se eliminó correctamente, false si no se encontró la reserva.
	 */
	boolean eliminarReserva(String idReserva);
	
	/**
	 * Modifica una reserva existente.
	 *
	 * @param modificarReservasDto DTO con los datos para modificar la reserva.
	 * @return true si la reserva se modificó correctamente, false si no se encontró la reserva o los datos son inválidos.
	 */
	boolean modificarReserva(ModificarReservasDto ModificarReservasDto);
	
	/**
	 * Obtiene un DTO con las mesas bloqueadas para una fecha y hora específicas.
	 *
	 * @param mesaDto DTO con la fecha y hora para la cual se desean obtener las mesas bloqueadas.
	 * @return Un DTO con las mesas bloqueadas para la fecha y hora especificadas.
	 */
	MesasBloqueadasDto mesasReservadas(MesaDto mesaDto);
>>>>>>> main
}
