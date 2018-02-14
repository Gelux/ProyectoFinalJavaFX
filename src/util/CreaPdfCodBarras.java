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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author gonza
 */
public class CreaPdfCodBarras extends JFrame{

    //IMPORTANTEE!!!!!!!!!!!!!!!!!!
    
    //importar librearia itextpdf-5.3.2.jar
    
    // poner nombre producto , la imagen , y numero de copias
    //ejemplo:
    //Image imagen = Image.getInstance("codBarrasImg/codebar.jpg");
    //CreaPdf nuevo = new CreaPdfCodBarras("jesus", imagen, 50);
    
    //importar librearia itextpdf-5.3.2.jar
    

    public static Document documento;
    String nProducto;
    Image codBarras;
    int numCopias;
    static String numero;
    static String rutaGuardar;
    

    public CreaPdfCodBarras(String nProducto, Image imgCodBarras, int numCopias) {
        this.codBarras = imgCodBarras;
        this.nProducto = nProducto;
        this.numCopias = numCopias;
        generaPdf(nProducto, imgCodBarras, numCopias);

    }

    public static void generaPdf(String nProducto, Image imgCodBarras, int numCopias) {

        documento = new Document();
        FileOutputStream ficheroPdf;
        
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setCurrentDirectory(new File("c:\\"));
        jfc.setDialogTitle("Selecciona el directorio donde vas a guardar el PDF.");

        if ((jfc.showDialog(jfc, "Seleccionar") == JFileChooser.CANCEL_OPTION)) {
            JOptionPane.showMessageDialog(null, "No has seleccionado una ruta para guardar.");
        } else {
            File archivo = jfc.getSelectedFile();
            rutaGuardar = archivo.getAbsolutePath();
        }
        
        try {
            ficheroPdf = new FileOutputStream(rutaGuardar + nProducto + ".PDF");
            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        try {
            documento.open();

            
            Font f = new Font(FontFamily.TIMES_ROMAN, 25.0f, Font.UNDERLINE, BaseColor.GRAY);
            Paragraph p = new Paragraph(nProducto, f);

            p.setAlignment(Paragraph.ALIGN_CENTER);

            documento.add(p);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            Image codBarras = Image.getInstance(imgCodBarras);
            codBarras.scaleToFit(20, 20);
            codBarras.setAlignment(Element.ALIGN_CENTER);

            creaTabla(codBarras, numCopias);

            documento.close();

        } catch (DocumentException ex) {
            System.out.println(ex.toString());
        }
    }

    private static void creaTabla(Image codBarras, int numCopias) throws DocumentException {
        
        int nad = numCopias % 4;

        PdfPTable tabla = new PdfPTable(4);
        for (int i = 1; i <= numCopias; i++) {
            tabla.addCell(codBarras);

        }
        for (int i = 0; i <= nad; i++) {
            tabla.addCell("");
        }
        documento.add(tabla);

    }
    public static void main(String[] args) throws BadElementException, IOException {
        
        GeneradorCodeBar codeBar = new GeneradorCodeBar();
        
        Image imagen = Image.getInstance(codeBar.CrearImgCB("1234567894686"));
        generaPdf("evolandia", imagen, 1000);
    }

}
