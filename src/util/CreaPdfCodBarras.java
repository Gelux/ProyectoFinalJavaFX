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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code93;

/**
 *
 * @author gonza
 */
public class CreaPdfCodBarras extends Application {

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
    static String numero, rutaGuardar;
    static byte[] bufferImagen, imgCB;
    static Stage chooserStage, alertStage;

    public CreaPdfCodBarras(String nProducto, Image imgCodBarras, int numCopias) {
        this.codBarras = imgCodBarras;
        this.nProducto = nProducto;
        this.numCopias = numCopias;
        generaPdf(nProducto, imgCodBarras, numCopias);

    }

    public static void generaPdf(String nProducto, Image imgCodBarras, int numCopias) {

        documento = new Document();
        FileOutputStream ficheroPdf;
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Elige el directorio donde guardar el PDF");
        File defaultDirectory = new File("C:/");
        fc.setInitialDirectory(defaultDirectory);

        File selectedDirectory = fc.showSaveDialog(chooserStage);

        if (selectedDirectory != null) {
            rutaGuardar = selectedDirectory.getAbsolutePath();
            
            try {
            ficheroPdf = new FileOutputStream(rutaGuardar + "\\" + nProducto + ".PDF");
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
        
        } else {
            
            
            
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

    public static byte[] CrearImgCB(String numero) throws IOException {

        JBarcodeBean barcode = new JBarcodeBean();

        // nuestro tipo de codigo de barra
        barcode.setCodeType(new Code93());
        //barcode.setCodeType(new Code39());

        // nuestro valor a codificar y algunas configuraciones mas
        barcode.setCode(numero);
        barcode.setCheckDigit(true);

        BufferedImage bufferedImage = barcode.draw(new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB));

        byte[] bufferImage = crearBufferDesdeImagen(bufferedImage);

        return bufferImage;
    }

    public static byte[] crearBufferDesdeImagen(BufferedImage imagen) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "jpg", baos);
        baos.flush();
        bufferImagen = baos.toByteArray();
        baos.close();

        return bufferImagen;
    }

    public void botonGenerar(String codigoBarras) throws IOException, BadElementException {

        
    }
    
    public static void main(String[] args) throws IOException, BadElementException {
        imgCB = CrearImgCB("1234567894561");

        Image imagen = Image.getInstance(imgCB);
        generaPdf("evolandia", imagen, 10);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
    }

}
