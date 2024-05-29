package uhmami.modelo.utils;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import uhmami.modelo.dto.ReservaDto;

/**
 * Esta clase proporciona utilidades útiles para diversas operaciones en la aplicación.
 */
@Service
@NoArgsConstructor
public class Utils {
	
	/**
	 * Genera un identificador único para una reserva basado en los datos proporcionados en el DTO de reserva.
	 *
	 * @param reservaDto El DTO de reserva que contiene los datos necesarios.
	 * @return El identificador único generado para la reserva.
	 */
	public String generarIdReserva(ReservaDto reservaDto) {
		String iniciales = String.valueOf(reservaDto.getNombre().charAt(0)) + String.valueOf(reservaDto.getApellidos().charAt(0));
		String id = iniciales + eliminarGuiones(reservaDto.getFecha()) + eliminarGuiones(reservaDto.getHora());
		return id;
	}
	
	/**
	 * Elimina los guiones de una cadena de texto.
	 *
	 * @param texto La cadena de texto de la cual se desean eliminar los guiones.
	 * @return La cadena de texto sin guiones.
	 */
	public static String eliminarGuiones(String texto) {
        // Reemplaza los guiones de las string
        String resultado = texto.replaceAll("-", "");
        return resultado;
    }

}
