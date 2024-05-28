package uhmami.modelo.service;

import java.io.IOException;

import com.itextpdf.layout.Document;

public interface PdfClienteService {
	void espacioBlanco(Document document, int espacios);
	void texto(Document document, final String texto);
	void cabecera(Document document) throws IOException;
	void generarTextoReserva(Document document);
	void generarTextoContacto(Document document);
	void generarApartadoFinal(Document document) throws IOException;
	String piePagina(byte[] pdfBytes) throws IOException;
	String generarPdfCliente(String id) throws IOException;
	

}
