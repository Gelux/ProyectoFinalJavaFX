package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.imageio.ImageIO;
import model.Libro;
import model.Producto;

/**
 *
 * @author Jes
 */
public class DatabaseUtil {

    Producto productoAux = null;
    Connection conexion = null;
    Statement sentencia = null;
    ResultSet resultSet1 = null;
    PreparedStatement ps = null;
    static DatabaseMetaData conexInfo;
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    public DatabaseUtil() {
        if (conexion == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexion = DriverManager.getConnection("jdbc:oracle:thin:noobs/damnoobs@//proyectofinaljfx.ckga19q2gpk5.eu-central-1.rds.amazonaws.com"
                        + ":8080/LIBRODB");
            } catch (ClassNotFoundException cn) {
                cn.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public ObservableList<Producto> anadirLista() {

        try {

            String query = "select * from productos";
            sentencia = null;
            sentencia = conexion.createStatement();
            resultSet1 = null;
            resultSet1 = sentencia.executeQuery(query);

            if (resultSet1.next()) {
                do {
                    productoAux = new Producto(resultSet1.getString(2),
                            resultSet1.getString(3), Double.parseDouble(resultSet1.getString(7)),
                            Integer.parseInt(resultSet1.getString(5)), Long.parseLong(resultSet1.getString(1)),
                            resultSet1.getDate(4), resultSet1.getDate(6));
                    listaProductos.add(productoAux);
                } while (resultSet1.next());
            } else {
                System.out.println("La tabla no tiene datos");
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProductos;
    }

    public void insertarNuevoLibro(Libro libro) {
        try {

            ps = conexion.prepareStatement("insert into productos values (?,?,?, sysdate, ?, sysdate, ?, 'LI', null) ");
            ps.setLong(1, 123456789456123L);
            ps.setString(2, libro.getNombre());
            ps.setString(3, libro.getDescription());
            ps.setInt(4, libro.getStock());
            ps.setDouble(6, libro.getPrecio());

            if (ps.executeUpdate() != 1) {
                System.out.println("Error insercion");
            } else {
                System.out.println("Fila insertada");
                
                conexion.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Libro detallesLibro(long codigoLibro) {

        Libro libroAux = null;

        try {
            sentencia = null;
            resultSet1 = null;
            sentencia = conexion.createStatement();
            String select3 = "select productos.codigo, cod_isbn, genero, autor, editorial, ano_publicacion, nombre, descripcion, "
                    + "fecha_alta, stock, fecha_modificacion, precio from libros inner join productos on libros.codigo = productos.codigo where libros.codigo = "
                    + codigoLibro;
            resultSet1 = sentencia.executeQuery(select3);

            if (resultSet1.next()) {
                do {
                    libroAux = new Libro(Long.parseLong(resultSet1.getString(2)), resultSet1.getString(3),
                            resultSet1.getString(4), resultSet1.getString(6), resultSet1.getString(5), resultSet1.getString(7),
                            resultSet1.getString(8), Double.parseDouble(resultSet1.getString(12)), Integer.parseInt(resultSet1.getString(10)), Long.parseLong(resultSet1.getString(1)),
                            resultSet1.getDate(9), resultSet1.getDate(11));
                } while (resultSet1.next());
            } else {
                System.out.println("La tabla no tiene datos//Sentencia con valores erroneos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return libroAux;
    }

    public BufferedImage imagenProducto(long codigoFoto) {

        BufferedImage auxImage = null;
        try {
            java.sql.Blob blob = null;
            String query = "select foto from productos where codigo = " + codigoFoto;
            sentencia = null;
            sentencia = conexion.createStatement();
            resultSet1 = null;
            resultSet1 = sentencia.executeQuery(query);

            resultSet1.next();
            blob = resultSet1.getBlob(1);
            InputStream in = blob.getBinaryStream();
            auxImage = ImageIO.read(in);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return auxImage;
    }

//    public boolean insertarImagen(File file, long codigoFoto){
//        
//    }
//    public boolean editarProducto ( Libro libro ){
//        
//    } 
}
