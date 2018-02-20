/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jes
 */
public class ConnectDB {
    
    static Connection con = null;
    
    public static Connection getConnection(){
        if(con != null){
            return con;
        }else{
            return getConnectionIn();
        }
    }

    private static Connection getConnectionIn() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:noobs/damnoobs@//proyectofinaljfx.ckga19q2gpk5.eu-central-1.rds.amazonaws.com"
                        + ":8080/LIBRODB");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
                
    }
}
