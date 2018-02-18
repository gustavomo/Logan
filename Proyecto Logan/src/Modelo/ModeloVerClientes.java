
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloVerClientes {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloVerClientes() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCarteras() { 
        try {
            String sql="select id_cartera "
                      +"from carteras";
            Statement stt = conexion.createStatement();  
            
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaClientes(String est) { 
        try {
            String sql="select c.id_cliente, c.nombre, c.telefono, c.direccion, c.barrio, e.descripcion "
                      +"from clientes c "
                      +"inner join estados e "
                      +"on e.id_estado = c.id_estado "
                      +"where c.id_estado = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, est);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaClientes(String car, String est) { 
        try {
            String sql="select c.id_cliente, c.nombre, c.telefono, c.direccion, c.barrio, e.descripcion "
                      +"from clientes c "
                      +"inner join estados e "
                      +"on e.id_estado = c.id_estado "
                      +"where c.id_cartera = ? and c.id_estado = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, car);
            st.setString(2, est);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
}