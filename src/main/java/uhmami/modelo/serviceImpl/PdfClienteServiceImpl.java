package uhmami.modelo.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import uhmami.modelo.entities.Reserva;
import uhmami.modelo.service.PdfClienteService;

@Service
public class PdfClienteServiceImpl implements PdfClienteService{
	@Autowired
    private ReservaServiceImpl reservaServiceImpl;

    // Constantes reutilizadas en el documento
    static final Paragraph ESPACIO = new Paragraph().add(" ").setFontSize(9f);
    static final String CABECERA_UHMAMI = "classpath:static/assets/img/CabeceraPdf.jpg";
    static final String PIE_UHMAMI = "classpath:static/assets/img/PiePdf.jpg";
    static final String FORMATO_FECHA = "dd/MM/yyyy";
    static final String NOMBRE_ARCHIVO = "ReservasUhmami.pdf";

    // Variables para almacenar información de las reservas
    Reserva reserva = null;
    String fechaReserva;

    /**
     * Añade espacios en blanco al documento.
     *
     * @param document El documento PDF
     * @param espacios Número de espacios en blanco a añadir
     */
    public void espacioBlanco(Document document, int espacios) {
        for (int i = 0; i < espacios; i++) {
            document.add(ESPACIO);
        }
    }

    /**
     * Añade un mensaje de texto.
     *
     * @param document El documento PDF
     * @param texto El mensaje a mostrar
     */
    public void texto(Document document, final String texto) {
        final Paragraph linea = new Paragraph();
        linea.add(texto);
        linea.setFontSize(10f).setFontColor(ColorConstants.GRAY);
        linea.setMarginRight(50);
        linea.setRelativePosition(20, 0, 0, 20);
        document.add(linea);
    }
    

    /**
     * Añade la cabecera del documento, incluyendo el logo y el título.
     *
     * @param document El documento PDF
     * @throws IOException Si ocurre un error al cargar la imagen
     */
    public void cabecera(Document document) throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Paragraph cabecera = new Paragraph();
        Image logo = new Image(ImageDataFactory.create(loader.getResource(CABECERA_UHMAMI).getURL())).scaleToFit(590, 340)
                .setRelativePosition(0, 0, 0, 0);
        cabecera.add(logo);
        document.add(cabecera);
        this.espacioBlanco(document, 5);
        
        Paragraph titulo = new Paragraph();
        titulo.add("Datos de la reserva: " + reserva.getId());
        titulo.setFontSize(15f).setUnderline().setFontColor(new DeviceRgb(247, 133, 15));
        titulo.setBold().setRelativePosition(20, 0, 0, 20);
        document.add(titulo);
    }
    
    /**
     * Genera el texto con los datos de la reserva
     * @param document
     */
    public void generarTextoReserva(Document document) {
    	String nombre = reserva.getCliente().getNombre() != null ? reserva.getCliente().getNombre() : "";
        String apellidos = reserva.getCliente().getApellidos() != null ? reserva.getCliente().getApellidos() : "";
        String cliente = nombre + " " + apellidos;
        String hora = reserva.getHora().substring(0, 2) + ":" + reserva.getHora().substring(2);
        
        String observaciones = null;
        int espacios = 0;
        if (reserva.getObservaciones() != null && !reserva.getObservaciones().equals("")) {
        	observaciones = reserva.getObservaciones();
        	if (observaciones.length() <= 104) espacios = 8;
        	if (observaciones.length() >= 105 && observaciones.length() <= 170) espacios = 4;
        	if (observaciones.length() >= 214) espacios = 2;
        } else {
        	observaciones = "Sin observaciones.";
        	espacios = 8;
        }
        
        this.texto(document, "Fecha: " + this.fechaReserva);
        this.texto(document, "Hora: " + hora);
        this.texto(document, "Comensales: " + reserva.getComensales());
        this.texto(document, "Mesa: " + reserva.getMesa().getId());
        this.texto(document, "Cliente: " + cliente);
        this.texto(document, "Teléfono: " + reserva.getCliente().getTelefono());
        this.texto(document, "Observaciones: " + observaciones);
        this.espacioBlanco(document, espacios);
        this.texto(document, "-----------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * Genera el texto con los datos de contacto
     * @param document
     */
    public void generarTextoContacto(Document document) {
        this.texto(document, "Recuerda que puedes ponerte en contacto con nosotros a través de:");
        this.texto(document, "Teléfono: 687598641");
        this.texto(document, "Redes sociales: @UhMamiIbiza");
        this.texto(document, "Dirección: Cala Gració  07800 - Ibiza");
        this.texto(document, "-----------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * Genera el apartado final
     * @param document
     * @throws IOException 
     */
    public void generarApartadoFinal(Document document) throws IOException {
    	this.texto(document, "Para modificar la reserva, puedes hacerlo a través del formulario de modificación de la web,"
        		+ "en el apartado reservas, justo debajo del formulario de reserva. Podrás modificar tanto los datos de "
        		+ "contacto como la fecha y la hora de reserva.");
        
    	ResourceLoader loader = new DefaultResourceLoader();
        Paragraph pie = new Paragraph();
        Image imagenPie = new Image(ImageDataFactory.create(loader.getResource(PIE_UHMAMI).getURL())).scaleToFit(590, 340)
                .setRelativePosition(0, 0, 0, 0);
        pie.add(imagenPie);
        document.add(pie);
    }
         
    /**
     * Añade el pie de página con la fecha y el número de página al documento.
     *
     * @param pdfBytes El contenido del documento PDF
     * @return El documento PDF actualizado en formato Base64
     * @throws IOException Si ocurre un error al manipular el PDF
     */
    public String piePagina(byte[] pdfBytes) throws IOException {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(pdfBytes)),
                new PdfWriter(NOMBRE_ARCHIVO))) {
            String diaActual = new SimpleDateFormat(FORMATO_FECHA).format(new Date());
            int totalPages = pdfDoc.getNumberOfPages();
            float x = ((pdfDoc.getPage(1).getPageSize().getLeft() + pdfDoc.getPage(1).getPageSize().getRight()) / 2)
                    - 80;
            float y = pdfDoc.getPage(1).getPageSize().getBottom() + 35;
            for (int i = 1; i <= totalPages; i++) {
                PdfPage page = pdfDoc.getPage(i);
                PdfCanvas canvas = new PdfCanvas(page);
                canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 9f).moveText(x, y)
                        .showText(String.format("Impreso el día " + diaActual + ". Página %d de %d", i, totalPages)).endText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] updatedPdfBytes = Files.readAllBytes(new File(NOMBRE_ARCHIVO).toPath());
        return java.util.Base64.getEncoder().encodeToString(updatedPdfBytes);
    }

    /**
     * Genera el documento PDF con la información de las reservas.
     *
     * @param fecha La fecha de las reservas
     * @return El documento PDF generado en formato Base64
     * @throws IOException Si ocurre un error al generar el PDF
     */
    public String generarPdfCliente(String id) throws IOException {

        reserva = reservaServiceImpl.buscarUna(id);

        String[] fechaSplit = reserva.getFecha().toString().split("-");
        fechaReserva = fechaSplit[2] + "/" + fechaSplit[1] + "/" + fechaSplit[0];

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Document document = new Document(new PdfDocument(new PdfWriter(outputStream)));

        document.setMargins(2, 2, 2, 2);

        this.cabecera(document);
        this.generarTextoReserva(document);
        this.generarTextoContacto(document);
        this.generarApartadoFinal(document);
        
        document.close();

        Path path = Paths.get(NOMBRE_ARCHIVO);
        if (Files.exists(path) && Files.isRegularFile(path)) {
            Files.delete(path);
        }

        return this.piePagina(outputStream.toByteArray());

    }
}
