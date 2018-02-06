
package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jes
 */
public class DatabaseUtil {

    /**
     * @param args the command line arguments
    
    */
    
    
    public static void main(String[] args) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultSet1 = null;
        
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
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
                    System.out.println(resultSet1.getString(1) + "  " + resultSet1.getString(2) + "       "
                            + resultSet1.getString(3) + "       " + resultSet1.getString(4));
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

    }

}
