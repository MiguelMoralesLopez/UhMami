package uhmami.modelo.service;

import java.io.IOException;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

/**
 * Interfaz que define los servicios relacionados con la generación de PDF para administradores.
 */
public interface PdfAdminService {

    /**
     * Agrega un espacio en blanco al documento.
     * 
     * @param document El documento al que se agrega el espacio en blanco.
     * @param espacios El número de espacios en blanco a agregar.
     */
    void espacioBlanco(Document document, int espacios);

    /**
     * Agrega un salto de línea al final de una tabla.
     * 
     * @param tabla      La tabla a la que se agrega el salto de línea.
     * @param numEspacios El número de espacios de separación entre la tabla y el siguiente contenido.
     */
    void brTabla(final Table tabla, Integer numEspacios);

    /**
     * Agrega un nombre de dato a una tabla.
     * 
     * @param tabla      La tabla a la que se agrega el nombre del dato.
     * @param nombreDato El nombre del dato a agregar.
     */
    void nombreDato(final Table tabla, final String nombreDato);

    /**
     * Agrega un valor de dato a una tabla.
     * 
     * @param tabla      La tabla a la que se agrega el valor del dato.
     * @param valorDato  El valor del dato a agregar.
     */
    void valorDato(final Table tabla, final String valorDato);

    /**
     * Agrega un mensaje de "sin registros" al documento.
     * 
     * @param document El documento al que se agrega el mensaje de "sin registros".
     * @param texto    El texto del mensaje de "sin registros".
     */
    void sinResistro(Document document, final String texto);

    /**
     * Agrega la cabecera al documento.
     * 
     * @param document El documento al que se agrega la cabecera.
     * @throws IOException Si hay un error de E/S al agregar la cabecera.
     */
    void cabecera(Document document) throws IOException;

    /**
     * Genera una tabla de reservas en el documento.
     * 
     * @param document El documento al que se agrega la tabla de reservas.
     * @throws IOException Si hay un error de E/S al generar la tabla de reservas.
     */
    void generarTablaReservas(Document document) throws IOException;

    /**
     * Agrega un pie de página al documento y devuelve el contenido en formato de bytes.
     * 
     * @param pdfBytes El contenido del documento en formato de bytes.
     * @return         El pie de página generado como una cadena de caracteres.
     * @throws IOException Si hay un error de E/S al agregar el pie de página.
     */
    String piePagina(byte[] pdfBytes) throws IOException;

    /**
     * Genera un PDF para administradores basado en la fecha especificada.
     * 
     * @param fecha La fecha para la que se genera el PDF.
     * @return      El contenido del PDF generado como una cadena de caracteres.
     * @throws IOException Si hay un error de E/S al generar el PDF.
     */
    String generarPdfAdmin(String fecha) throws IOException;
}
