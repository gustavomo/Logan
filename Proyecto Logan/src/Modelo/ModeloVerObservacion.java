
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerObservacion {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloVerObservacion() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaObservacion(String id, String fecini) { 
        try {
            String sql="select d.observacion "
                      +"from deudas d "
                      +"where d.id_cliente = ? and d.fecha_inicio = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, id);
            st.setString(2, fecini);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
}