
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloNuevaCartera {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloNuevaCartera() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCobradores (){ 
        try {
            String sql="select nombre "
                      +"from cobradores "
                      +"where id_estado = 1";
            Statement stt = conexion.createStatement();  
            
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaIdeCobrador (String ide){ 
        try {
            String sql="select id_cobrador "
                      +"from cobradores "
                      +"where nombre = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, ide);
            
            rss = pst.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public int insertarCartera (long mon, long cap, String fec, String intdeu,
                                String intmor, String cob){
        int respuesta = 0;
        
        try{
            String sql = "insert into carteras values(null,?,?,?,?,?,?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, mon);
            pst.setLong(2, cap);
            pst.setString(3, fec);
            pst.setString(4, intdeu);
            pst.setString(5, intmor);
            pst.setString(6, cob);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}