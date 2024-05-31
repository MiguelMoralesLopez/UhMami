package uhmami.modelo.service;

import java.io.IOException;

import com.itextpdf.layout.Document;

/**
 * Interfaz que define los servicios relacionados con la generación de PDF para clientes.
 */
public interface PdfClienteService {

    /**
     * Agrega un espacio en blanco al documento.
     * 
     * @param document El documento al que se agrega el espacio en blanco.
     * @param espacios El número de espacios en blanco a agregar.
     */
    void espacioBlanco(Document document, int espacios);

    /**
     * Agrega un texto al documento.
     * 
     * @param document El documento al que se agrega el texto.
     * @param texto    El texto a agregar.
     */
    void texto(Document document, final String texto);

    /**
     * Agrega la cabecera al documento.
     * 
     * @param document El documento al que se agrega la cabecera.
     * @throws IOException Si hay un error de E/S al agregar la cabecera.
     */
    void cabecera(Document document) throws IOException;

    /**
     * Genera el texto para una reserva en el documento.
     * 
     * @param document El documento al que se agrega el texto de la reserva.
     */
    void generarTextoReserva(Document document);

    /**
     * Genera el texto para un contacto en el documento.
     * 
     * @param document El documento al que se agrega el texto del contacto.
     */
    void generarTextoContacto(Document document);

    /**
     * Genera un apartado final en el documento.
     * 
     * @param document El documento al que se agrega el apartado final.
     * @throws IOException Si hay un error de E/S al agregar el apartado final.
     */
    void generarApartadoFinal(Document document) throws IOException;

    /**
     * Agrega un pie de página al documento y devuelve el contenido en formato de bytes.
     * 
     * @param pdfBytes El contenido del documento en formato de bytes.
     * @return         El pie de página generado como una cadena de caracteres.
     * @throws IOException Si hay un error de E/S al agregar el pie de página.
     */
    String piePagina(byte[] pdfBytes) throws IOException;

    /**
     * Genera un PDF para clientes basado en el ID especificado.
     * 
     * @param id El ID para el cual se genera el PDF.
     * @return   El contenido del PDF generado como una cadena de caracteres.
     * @throws IOException Si hay un error de E/S al generar el PDF.
     */
    String generarPdfCliente(String id) throws IOException;
}
