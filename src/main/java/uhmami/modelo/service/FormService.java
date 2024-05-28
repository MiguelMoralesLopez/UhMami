package uhmami.modelo.service;

import java.text.ParseException;

import uhmami.modelo.dto.ReservaDto;

public interface FormService {
	boolean procesarFormContacto(String nombre, Integer telefono, String comentario);
	boolean procesarFormReserva(ReservaDto reservaDto) throws ParseException;
}
