package uhmami.modelo.utils;


import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import uhmami.modelo.dto.ReservaDto;

@Service
@NoArgsConstructor
public class Utils {
	
	
	public String generarIdReserva(ReservaDto reservaDto) {
		String iniciales = String.valueOf(reservaDto.getNombre().charAt(0)) + String.valueOf(reservaDto.getApellidos().charAt(0));
		String id = iniciales + eliminarGuiones(reservaDto.getFecha()) + eliminarGuiones(reservaDto.getHora());
		return id;
	}
	
	public static String eliminarGuiones(String texto) {
        // Reemplaza los guiones de las string
        String resultado = texto.replaceAll("-", "");
        return resultado;
    }

}
