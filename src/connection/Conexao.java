/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Usuario
 */
public class Conexao {
    public static final String url = "jdbc:mysql://localhost:3306/lavarapido?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String user = "root";
    public static final String senha = "naosei161076";
    public static final String driver = "com.mysql.cj.jdbc.Driver";

      public static Connection conectar(){
            try {
           Class.forName(driver);
           Connection con = null; 
           con = DriverManager.getConnection(url, user, senha);
         return con; 
        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
        
      }

}
