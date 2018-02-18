
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerMovimientosDeuda {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloVerMovimientosDeuda() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaMovimientosDeuda(String id, String fecini) { 
        try {
            String sql="select md.cuota_vieja, md.cuota_actual, md.monto_vieja, "
                      +"md.monto_actual, md.proximo_pago, tm.descripcion "
                      +"from movimientos_deudas md "
                      +"inner join deudas d "
                      +"on d.id_deuda = md.id_deuda "
                      +"inner join tipos_movimientos tm "
                      +"on tm.id_tipo_movimiento = md.id_tipo_movimiento "
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