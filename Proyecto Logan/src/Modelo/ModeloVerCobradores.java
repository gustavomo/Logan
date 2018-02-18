
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerCobradores {

    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloVerCobradores() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCobradores(int est) { 
        try {
            String sql="select c.id_cobrador, c.nombre, c.telefono, c.direccion, c.barrio, e.descripcion "
                      +"from cobradores c "
                      +"inner join estados e "
                      +"on e.id_estado = c.id_estado "
                      +"where c.id_estado = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setInt(1, est);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
}