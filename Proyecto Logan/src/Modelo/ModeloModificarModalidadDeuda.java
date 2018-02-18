
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloModificarModalidadDeuda {

    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloModificarModalidadDeuda() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaModalidad() { 
        try {
            String sql="select descripcion "
                      +"from modalidades";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaModalidadDeuda(String id, String fecini) { 
        try {
            String sql="select mo.descripcion "
                      +"from deudas d "
                      +"inner join modalidades mo "
                      +"on mo.id_modalidad = d.id_modalidad "
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
    
    public ResultSet consultaIdModalidad(String des) { 
        try {
            String sql="select id_modalidad "
                      +"from modalidades "
                      +"where descripcion = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, des);
            
            rss = pst.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public int actualizarModalidadDeuda(String idmod, String idcli, String fecini) {
        int respuesta = 0;
        
        try{
            String sql = "update deudas "
                        +"set id_modalidad = ? "
                        +"where id_deuda = (select * from (select id_deuda from deudas where id_cliente = ? and fecha_inicio = ?) as dd)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, idmod);
            pst.setString(2, idcli);
            pst.setString(3, fecini);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}