package uhmami.modelo.service;

import java.io.IOException;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

public interface PdfAdminService {
	
	void espacioBlanco(Document document, int espacios);
	void brTabla(final Table tabla, Integer numEspacios);
	void nombreDato(final Table tabla, final String nombreDato);
	void valorDato(final Table tabla, final String valorDato);
	void sinResistro(Document document, final String texto);
	void cabecera(Document document) throws IOException;
	void generarTablaReservas(Document document) throws IOException;
	String piePagina(byte[] pdfBytes) throws IOException;
	String generarPdfAdmin(String fecha) throws IOException;

}
