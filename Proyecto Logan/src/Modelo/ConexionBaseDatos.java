
package Modelo;

import java.sql.*;

public class ConexionBaseDatos {
    
    private Connection conexion = null;
    
    public Connection conexion(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_logan","root",""); 
        } catch(ClassNotFoundException | SQLException e){
            System.out.print(e);
        }
        return conexion;
    }  
}

