
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloModificarDatosCobradores {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloModificarDatosCobradores() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCobrador(String id) { 
        try {
            String sql="select id_cobrador, nombre, telefono, direccion, barrio "
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
    
    public ResultSet consultaCobradorExistente(String id) { 
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
    
    public int actualizarDatosCobrador(String id2, String nom, String tel, String dir, String bar, String id1) {
        int respuesta = 0;
        
        try{
            String sql = "update cobradores "
                        +"set id_cobrador = ?, "
                        +"nombre = ?, "
                        +"telefono = ?, "
                        +"direccion = ?, "
                        +"barrio = ? "
                        +"where id_cobrador = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, id2);
            pst.setString(2, nom);
            pst.setString(3, tel);
            pst.setString(4, dir);
            pst.setString(5, bar);
            pst.setString(6, id1);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}