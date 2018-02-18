
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloModificarDatos {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloModificarDatos() {
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
            String sql="select id_cliente, nombre, telefono, direccion, barrio, identificacion_ref, nombre_ref, telefono_ref, direccion_ref, barrio_ref "
                      +"from clientes "
                      +"where id_cliente = ?";
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
    
    public ResultSet consultaClienteExistente(String id) { 
        try {
            String sql="select nombre "
                      +"from clientes "
                      +"where id_cliente = ?";
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
    
    public int actualizarDatosCliente(String id, String nom, String tel, String dir,
                               String bar, String ideref, String nomref, String telref,
                               String dirref, String barref) {
        int respuesta = 0;
        
        try{
            String sql = "update clientes "
                        +"set id_cliente = ?, "
                        +"nombre = ?, "
                        +"telefono = ?, "
                        +"direccion = ?, "
                        +"barrio = ?, "
                        +"identificacion_ref = ?, "
                        +"nombre_ref = ?, "
                        +"telefono_ref = ?,"
                        +"direccion_ref = ?, "
                        +"barrio_ref = ? "
                        +"where id_cliente = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, nom);
            pst.setString(3, tel);
            pst.setString(4, dir);
            pst.setString(5, bar);
            pst.setString(6, ideref);
            pst.setString(7, nomref);
            pst.setString(8, telref);
            pst.setString(9, dirref);
            pst.setString(10, barref);
            pst.setString(11, id);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}