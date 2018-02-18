
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloPrincipal {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloPrincipal() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaClientes() { 
        try {
            String sql="select d.id_deuda, d.monto_prestado, d.monto_interes, d.cantidad_cuotas, md.cuota_actual, md.monto_actual, md.proximo_pago "
                      +"from deudas d "
                      +"inner join movimientos_deudas md "
                      +"on d.id_deuda = md.id_deuda "
                      +"where md.id_movimiento_deuda in ( "
                        +"select max(md.id_movimiento_deuda) "
                        +"from movimientos_deudas md "
                        +"where md.id_deuda = d.id_deuda) "
                      +"order by md.proximo_pago desc";
            Statement stt = conexion.createStatement();
            
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaInteresMora() { 
        try {
            String sql="select taza_interes from intereses where id_interes = 2";
            Statement stt = conexion.createStatement();
            
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    public int insertarMora(long mon, int cuo, String fec, String idest, String iddeu) {
        int respuesta = 0;
        
        try{
            String sql = "insert moras values (null, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, mon);
            pst.setInt(2, cuo);
            pst.setString(3, fec);
            pst.setString(4, idest);
            pst.setString(5, iddeu);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
    
    public ResultSet consultaModalidad(String id) { 
        try {
            String sql="select m.descripcion "
                      +"from deudas d "
                      +"inner join modalidades m "
                      +"on m.id_modalidad = d.id_modalidad "
                      +"where d.id_deuda = ?";
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
}