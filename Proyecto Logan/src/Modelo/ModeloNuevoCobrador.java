
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloNuevoCobrador {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloNuevoCobrador() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCliente(String id) { 
        try {
            String sql="select nombre "
                      +"from cobradores "
                      +"where id_cobrador = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, id);
            
            rss = pst.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public int insertarCobrador (String id, String nom, String tel, String dir,
                                 String bar, String est){
        int respuesta = 0;
        
        try{
            String sql = "insert into cobradores values(?,?,?,?,?,?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, nom);
            pst.setString(3, tel);
            pst.setString(4, dir);
            pst.setString(5, bar);
            pst.setString(6, est);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}