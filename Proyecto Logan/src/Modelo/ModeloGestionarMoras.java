
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloGestionarMoras {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloGestionarMoras() {
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
    
    public ResultSet consultaMoras(String fec) { 
        try {
            String sql="select c.id_cliente, c.nombre, m.fecha_mora, e.descripcion "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join moras m "
                      +"on d.id_deuda = m.id_deuda "
                      +"inner join estados e "
                      +"on e.id_estado = m.id_estado "
                      +"where fecha_mora = ? and c.id_estado = 1";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, fec);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public ResultSet consultaMorasCliente(String fec, String id) { 
        try {
            String sql="select c.id_cliente, c.nombre, m.fecha_mora, e.descripcion "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join moras m "
                      +"on d.id_deuda = m.id_deuda "
                      +"inner join estados e "
                      +"on e.id_estado = m.id_estado "
                      +"where fecha_mora = ? and c.id_cliente like ? and c.id_estado = 1";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, fec);
            st.setString(2, "%"+id+"%");
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public ResultSet consultaMorasCartera(String fec, String id) { 
        try {
            String sql="select c.id_cliente, c.nombre, m.fecha_mora, e.descripcion "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join moras m "
                      +"on d.id_deuda = m.id_deuda "
                      +"inner join estados e "
                      +"on e.id_estado = m.id_estado "
                      +"where fecha_mora = ? and c.id_cartera = ? and c.id_estado = 1";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, fec);
            st.setString(2, id);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public ResultSet ConsultarIdDeuda(String id) { 
        try {
            String sql="select max(id_deuda) as id "
                      +"from deudas "
                      +"where id_cliente = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, id);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public int actualizarMora(String id, String fec, int est){
        int respuesta = 0;
        
        try{
            String sql = "update moras set id_estado = ? where id_deuda = ? and fecha_mora = ? ";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, est);
            pst.setString(2, id);         
            pst.setString(3, fec);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
    
    public ResultSet ConsultarDatosMovimientos(String id, String fec) { 
        try {
            String sql="select cuota_actual, monto_actual, proximo_pago, "
                      +"(select monto_mora from moras where id_deuda = ? and fecha_mora = ?) as monto_mora "
                      +"from movimientos_deudas "
                      +"where id_movimiento_deuda = (select max(id_movimiento_deuda) from movimientos_deudas where id_deuda = ?)";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, id);
            st.setString(2, fec);
            st.setString(3, id);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public int registrarMovimiento (int cuovie, int cuoact, long monvie, long monact,
                                    String propag, String idmov, String iddeu){
        int respuesta = 0;
        
        try{
            String sql = "insert into movimientos_deudas values(null, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, cuovie);
            pst.setInt(2, cuoact);
            pst.setLong(3, monvie);
            pst.setLong(4, monact);
            pst.setString(5, propag);
            pst.setString(6, idmov);         
            pst.setString(7, iddeu);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
    
    public int actualizarDeuda1(String id) {
        int respuesta = 0;
        
        try{
            String sql = "update deudas set id_estado_deuda = 2 where id_deuda = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);        
            pst.setString(1, id);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
    
    public int actualizarDeuda2(String id) {
        int respuesta = 0;
        
        try{
            String sql = "update deudas set id_estado_deuda = 1 where id_deuda = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);        
            pst.setString(1, id);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}