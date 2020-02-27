/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghass
 */
public class connexionDB {
    
     String url = "jdbc:mysql://localhost:3306/PIDEV";
        String login = "root";
        String pwd = "";
        public  static connexionDB db;
        public Connection con;
        private connexionDB() {
            
         try {
             con = DriverManager.getConnection(url,login,pwd);
         } catch (SQLException ex) {
             Logger.getLogger(connexionDB.class.getName()).log(Level.SEVERE, null, ex);
         }
                System.out.println("connexion etablie");
            
        }

        public Connection  getConnection()
        {
            return con;
        }
        
        public static connexionDB getInstance()
        {if(db==null)
            db=new connexionDB();
            return db;
        }
     
}
