package uhmami.modelo.dto;

import java.util.List;

import lombok.Data;
import uhmami.modelo.entities.Mesa;

/**
 * Data Transfer Object (DTO) para representar las mesas bloqueadas.
 * Utiliza la anotación de Lombok {@link Data} para generar automáticamente
 * los métodos getter, setter, toString, equals y hashCode.
 */
@Data
public class MesasBloqueadasDto {
    
    /**
     * Una lista de identificadores de las mesas bloqueadas.
     */
    List<Integer> idMesas;
}
