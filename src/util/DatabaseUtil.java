package util;

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
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    public DatabaseUtil() {
    }

    public ObservableList<Producto> anadirLista() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:noobs/damnoobs@//proyectofinaljfx.ckga19q2gpk5.eu-central-1.rds.amazonaws.com"
                    + ":8080/LIBRODB");
            System.out.println("Conectado a base de datos...");
            DatabaseMetaData BD = conexion.getMetaData();
            //Info conexion
            String driver = BD.getDriverName();
            String url = BD.getURL();
            String product = BD.getDatabaseProductName();
            String usuario = BD.getUserName();
            System.out.println("Driver: " + driver + " //URL:" + url + " //Producto: " + product + " //User: " + usuario);

            System.out.println("Select statement: ");
            String query = "select * from productos";
            sentencia = null;
            sentencia = conexion.createStatement();
            resultSet1 = null;
            resultSet1 = sentencia.executeQuery(query);

            if (resultSet1.next()) {
                do {
                    productoAux = new Producto(resultSet1.getString(4), resultSet1.getString(2),
                            resultSet1.getString(3), Double.parseDouble(resultSet1.getString(8)),
                            Integer.parseInt(resultSet1.getString(6)), Long.parseLong(resultSet1.getString(1)),
                            resultSet1.getDate(5), resultSet1.getDate(7));
                    listaProductos.add(productoAux);
                } while (resultSet1.next());
            } else {
                System.out.println("La tabla no tiene datos");
            }
            conexion.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProductos;
    }

    public void insertarProducto(Libro libro) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:noobs/damnoobs@//proyectofinaljfx.ckga19q2gpk5.eu-central-1.rds.amazonaws.com"
                    + ":8080/LIBRODB");
            System.out.println("Conectado a base de datos...");

            sentencia = null;
            resultSet1 = null;
            sentencia = conexion.createStatement();
            String insert;
            insert = "select * from jobs";

            ps = conexion.prepareStatement("insert into jobs values (?,?,?,?,?,?) ");
            ps.setString(1, String.valueOf(libro.getCodBarras()));
            ps.setString(2, libro.getNombre());
            ps.setString(3, libro.getDescription());
            ps.setString(4, libro.getFoto());
            
            
            if (ps.executeUpdate() != 1) {
                System.out.println("Error insercion");
            } else {
                System.out.println("Fila insertada");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public boolean editarProducto ( Libro libro ){
//        
//    } 

}
