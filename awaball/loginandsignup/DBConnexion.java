/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.loginandsignup;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Awa Ball
 */
public class DBConnexion {
    static final String DB_URL= "jdbc:mysql://localhost/awaball";
    static final String USER="root";
    static final String PASS="";
    
    public static Connection connectDB(){
        
        Connection conn = null;
        try{            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch(Exception ex){
            System.out.println("erreur de connexion");
            return null;
        }
        
    }
     
}
