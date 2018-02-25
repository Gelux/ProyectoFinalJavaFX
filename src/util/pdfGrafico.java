/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static util.CreaPdfCodBarras.documento;

/**
 *
 * @author gonza
 */
public class pdfGrafico {

    Document documento;
    Image image;
    String ruta, rutaImagen;

    public pdfGrafico(String ruta , String rutaImagen) throws BadElementException, IOException {
        this.ruta = ruta;

        documento = new Document();
        FileOutputStream ficheroPdf;

        try {
            ficheroPdf = new FileOutputStream(ruta);
            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        try {
            documento.open();

            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, Font.UNDERLINE, BaseColor.GRAY);
            Paragraph p = new Paragraph("GRAFICO STOCK", font);

            p.setAlignment(Paragraph.ALIGN_CENTER);

            documento.add(p);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            

            Image grafico = Image.getInstance(rutaImagen);
            grafico.setScaleToFitLineWhenOverflow(true);
            grafico.scaleToFit(20, 20);
            grafico.setAlignment(Element.ALIGN_CENTER);

            creaTabla(grafico);

            documento.close();
            File f = new File(rutaImagen);
            f.delete();

        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
        
    }

    private void creaTabla(Image image) throws DocumentException {

        PdfPTable t = new PdfPTable(1);
        t.addCell(image);
        documento.add(t);

    }
}

