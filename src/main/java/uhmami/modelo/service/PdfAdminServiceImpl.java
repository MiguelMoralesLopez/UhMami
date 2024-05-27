package uhmami.modelo.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;

import uhmami.modelo.dto.PdfDto;
import uhmami.modelo.entities.Reserva;

@Service
public class PdfAdminServiceImpl {
	
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	static final Paragraph ESPACIO = new Paragraph().add(" ").setFontSize(9f);
	final float[] COLUMNAS = { 84f, 84f, 384f, 84f, 384f, 600f};
	static final String CABECERA_UHMAMI = "classpath:static/assets/img/logo.png";
	static final String FORMATO_FECHA = "dd/MM/yyyy";
	static final String NOMBRE_ARCHIVO = "ReservasUhmami.pdf";
	
	List<Reserva> lista = new ArrayList<>();
	String fechaReserva;
	
	int registrosImpresos = 0;
    int numLineas = 0;
	
	public void espacioBlanco(Document document, int espacios) {
		for (int i = 0; i < espacios; i++) {
            document.add(ESPACIO);
        }
	}
	
	// Genera el salto de linea en tablas
    private void brTabla(final Table tabla, Integer numEspacios) {
        for (int i = 0; i < numEspacios; i++) {
            tabla.addCell(new Cell().add(ESPACIO).setBorder(Border.NO_BORDER));
        }
    }

    // Métodos para imprimir los datos en el formato correspondiente
    private void nombreDato(final Table tabla, final String nombreDato) {
        final Paragraph dato = new Paragraph().add(nombreDato).setMaxWidth(250);
        dato.setFontColor(ColorConstants.GRAY);
        dato.setFontSize(11f).setBold();
        tabla.addCell(new Cell().add(dato).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER));
    }

    private void valorDato(final Table tabla, final String valorDato) {
        final Paragraph dato = new Paragraph().add(valorDato).setMaxWidth(250);
        dato.setFontSize(9f);
        tabla.addCell(new Cell().add(dato).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER)
                .setBorderRight(Border.NO_BORDER));
    }

    private void sinResistro(Document document, final String texto) {
        final Paragraph sinRegistro = new Paragraph();
        sinRegistro.add(texto);
        sinRegistro.setFontSize(9f).setFontColor(ColorConstants.GRAY);
        sinRegistro.setRelativePosition(20, 0, 0, 20);
        document.add(sinRegistro);
    }
	
	public void cabecera(Document document) throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();
		Paragraph cabecera = new Paragraph();
		Image logo = new Image(ImageDataFactory.create(loader.getResource(CABECERA_UHMAMI).getURL())).scaleToFit(140, 140)
                .setRelativePosition(0, 0, 0, 0);
		cabecera.add(logo);
		Paragraph titulo = new Paragraph();
		titulo.add("Reservas para el día: " + this.fechaReserva);
        titulo.setFontSize(15f).setUnderline().setFontColor(new DeviceRgb(247, 133, 15));
        titulo.setBold().setRelativePosition(20, 0, 0, 20);
        cabecera.add(titulo);
		document.add(cabecera);
	}
	
	public void generarTablaReservas(Document document) throws IOException {
        if (lista != null && !lista.isEmpty()) {
        	registrosImpresos = 0;
            List<List<Reserva>> sublistas = new ArrayList<>();
            for (int i = 0; i < lista.size(); i += 8) {
                sublistas.add(lista.subList(i, Math.min(i + 8, lista.size())));
            }
            Integer numLista = 0;
            for (List<Reserva> sublista : sublistas) {
            	final Table tablaReserva = new Table(COLUMNAS);
            	this.nombreDato(tablaReserva, "Hora");
                this.nombreDato(tablaReserva, "Mesa");
                this.nombreDato(tablaReserva, "Nombre");
                this.nombreDato(tablaReserva, "Comensales");
                this.nombreDato(tablaReserva, "Teléfono");
                this.nombreDato(tablaReserva, "Observación");
                for (final Reserva reserva : sublista) {
                	String nombre = reserva.getCliente().getNombre() != null ? reserva.getCliente().getNombre() : "";
                	String apellidos = reserva.getCliente().getApellidos() != null ? reserva.getCliente().getApellidos() : "";
                	String cliente = nombre + " " + apellidos;
                	this.valorDato(tablaReserva, reserva.getHora() != null ? reserva.getHora() : "");
                	this.valorDato(tablaReserva, reserva.getMesa() != null ? reserva.getMesa().getId().toString() : "");
                    this.valorDato(tablaReserva, cliente);
                    this.valorDato(tablaReserva, reserva.getComensales() != null ? reserva.getComensales().toString() : "");
                    this.valorDato(tablaReserva, reserva.getCliente().getTelefono() != null ? reserva.getCliente().getTelefono().toString() : "");
                    this.valorDato(tablaReserva, (reserva.getObservaciones() != null && !reserva.getObservaciones().equals("")) ? reserva.getObservaciones() : "Sin observaciones.");
                    this.brTabla(tablaReserva, 6);
                    registrosImpresos++;
                }
                numLista++;
                tablaReserva.setRelativePosition(20, 0, 0, 20);
                document.add(tablaReserva);
                if (numLista != sublistas.size()) {
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    this.espacioBlanco(document, 8);
                }
            }
        	
        } else {
            this.sinResistro(document, "No existen reservas para esta fecha.");
        }
	}
	

	public String piePagina(byte[] pdfBytes) throws IOException {
        try (PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(pdfBytes)),
                new PdfWriter(NOMBRE_ARCHIVO))) {
        	String diaActual = new SimpleDateFormat(FORMATO_FECHA).format(new Date());
            int totalPages = pdfDoc.getNumberOfPages();
            float x = ((pdfDoc.getPage(1).getPageSize().getLeft() + pdfDoc.getPage(1).getPageSize().getRight()) / 2)
                    - 70;
            float y = pdfDoc.getPage(1).getPageSize().getBottom() + 25;
            for (int i = 1; i <= totalPages; i++) {
                PdfPage page = pdfDoc.getPage(i);
                PdfCanvas canvas = new PdfCanvas(page);
                canvas.beginText().setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 9f).moveText(x, y)
                	.showText(String.format("Impreso el día " + diaActual + ". Página %d de %d", i, totalPages)).endText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] updatedPdfBytes = java.nio.file.Files.readAllBytes(new File(NOMBRE_ARCHIVO).toPath());
        return java.util.Base64.getEncoder().encodeToString(updatedPdfBytes);
    }
	
	
	
	public String generarPdfAdmin(String fecha) throws IOException {
		
		lista = reservaServiceImpl.buscarPorFecha(fecha);
		
		String[] fechaSplit = fecha.split("-");
        fechaReserva = fechaSplit[2] + "/" + fechaSplit[1] + "/" + fechaSplit[0];

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final Document document = new Document(new PdfDocument(new PdfWriter(outputStream)));

        document.setMargins(2, 2, 2, 2);
        
        this.cabecera(document);
        this.espacioBlanco(document, 2);
        this.generarTablaReservas(document);
        
        document.close();
        
        PdfDto pdfDto = new PdfDto();
        pdfDto.setFechaGeneracion(fechaReserva);
        pdfDto.setDocumento(this.piePagina(outputStream.toByteArray()));
        
        Path path = Paths.get(NOMBRE_ARCHIVO);
        if (Files.exists(path) && Files.isRegularFile(path)) {
            Files.delete(path);
        }
        
        return this.piePagina(outputStream.toByteArray());
        
	}

}
