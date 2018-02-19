/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code93;

/**
 *
 * @author franp
 */
public class GeneradorCodeBar {

    static String ruta;
    String nombreProd;
    byte[] bufferImagen;

    public void SaveImgCB(String numero, String nombreProd) throws IOException {
        //Llamamos a la clase que genera el código de barras.
        JBarcodeBean barcode = new JBarcodeBean();

        //Asignamos el formato de codigo de barras.
        barcode.setCodeType(new Code93());

        barcode.setCode(numero);
        barcode.setCheckDigit(true);

        BufferedImage bufferedImage = barcode.draw(new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB));
        
        
        
//        final DirectoryChooser directoryChooser =
//                new DirectoryChooser();
//            final File selectedDirectory =
//                    directoryChooser.showDialog(stage);
//            if (selectedDirectory != null) {
//                selectedDirectory.getAbsolutePath();
//            }

        //File choser para elegir el directorio donde guardar la imagen.
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setCurrentDirectory(new File("c:\\"));
        jfc.setDialogTitle("Selecciona el directorio donde guardar la imagen.");

        if ((jfc.showDialog(jfc, "Seleccionar") == JFileChooser.CANCEL_OPTION)) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un archivo.");
        } else {
            File archivo = jfc.getSelectedFile();
            ruta = archivo.getAbsolutePath();

            // guardar en disco como jpg.
            File file = new File(ruta + "\\" + nombreProd + ".jpg");
            ImageIO.write(bufferedImage, "jpg", file);

        }
    }

    public byte[] CrearImgCB(String numero) throws IOException {

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

    public byte[] crearBufferDesdeImagen(BufferedImage imagen) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "jpg", baos);
        baos.flush();
        bufferImagen = baos.toByteArray();
        baos.close();

        return bufferImagen;
    }

}
