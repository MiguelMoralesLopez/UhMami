package uhmami.modelo.service;

import java.text.ParseException;

import uhmami.modelo.dto.ReservaDto;

/**
 * Interfaz que define los servicios relacionados con formularios.
 */
public interface FormService {

    /**
     * Procesa el formulario de contacto.
     * 
     * @param nombre     El nombre del contacto.
     * @param telefono   El número de teléfono del contacto.
     * @param comentario El comentario del contacto.
     * @return           true si el formulario se procesó correctamente, false de lo contrario.
     */
    boolean procesarFormContacto(String nombre, Integer telefono, String comentario);

    /**
     * Procesa el formulario de reserva.
     * 
     * @param reservaDto Los datos de la reserva.
     * @return           El resultado del procesamiento del formulario.
     * @throws ParseException Si ocurre un error de análisis al procesar la fecha y hora de la reserva.
     */
    String procesarFormReserva(ReservaDto reservaDto) throws ParseException;
}
