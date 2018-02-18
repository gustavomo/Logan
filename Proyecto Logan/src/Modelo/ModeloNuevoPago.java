
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloNuevoPago {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloNuevoPago() {
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
     
    public ResultSet consultaPagos(String fec) { 
        try {
            String sql="select c.id_cliente, c.nombre, d.monto_prestado, d.monto_interes, "
                      +"d.cantidad_cuotas, "
                      +"(select count(mr.id_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as cuotas_mora, "
                      +"(select sum(mr.monto_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as monto_mora, "
                      +"(select sum(p.monto_pagado) from pagos p where d.id_deuda = p.id_deuda) as monto_pagado "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join modalidades md "
                      +"on md.id_modalidad = d.id_modalidad "
                      +"inner join movimientos_deudas mv "
                      +"on d.id_deuda = mv.id_deuda "
                      +"where mv.proximo_pago = ? "
                      +"and d.id_estado_deuda = 1 "
                      +"and c.id_estado = 1 "
                      +"and mv.id_movimiento_deuda = (select max(mv2.id_movimiento_deuda) from movimientos_deudas mv2 where mv2.id_deuda = d.id_deuda) "
                      +"and mv.proximo_pago not in (select p.fecha_pago from pagos p where p.id_deuda = mv.id_deuda)";
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
    
    public ResultSet consultaPagosCliente(String fec, String id) { 
        try {
            String sql="select c.id_cliente, c.nombre, d.monto_prestado, d.monto_interes, "
                      +"d.cantidad_cuotas, "
                      +"(select count(mr.id_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as cuotas_mora, "
                      +"(select sum(mr.monto_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as monto_mora, "
                      +"(select sum(p.monto_pagado) from pagos p where d.id_deuda = p.id_deuda) as monto_pagado "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join modalidades md "
                      +"on md.id_modalidad = d.id_modalidad "
                      +"inner join movimientos_deudas mv "
                      +"on d.id_deuda = mv.id_deuda "
                      +"where mv.proximo_pago = ? "
                      +"and c.id_cliente like ? "
                      +"and d.id_estado_deuda = 1 "
                      +"and c.id_estado = 1 "
                      +"and mv.id_movimiento_deuda = (select max(mv2.id_movimiento_deuda) from movimientos_deudas mv2 where mv2.id_deuda = d.id_deuda) "
                      +"and mv.proximo_pago not in (select p.fecha_pago from pagos p where p.id_deuda = mv.id_deuda)";
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
    
    public ResultSet consultaPagosCartera(String fec, String id) { 
        try {
            String sql="select c.id_cliente, c.nombre, d.monto_prestado, d.monto_interes, "
                      +"d.cantidad_cuotas, "
                      +"(select count(mr.id_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as cuotas_mora, "
                      +"(select sum(mr.monto_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as monto_mora, "
                      +"(select sum(p.monto_pagado) from pagos p where d.id_deuda = p.id_deuda) as monto_pagado "
                      +"from clientes c "
                      +"inner join deudas d "
                      +"on c.id_cliente = d.id_cliente "
                      +"inner join modalidades md "
                      +"on md.id_modalidad = d.id_modalidad "
                      +"inner join movimientos_deudas mv "
                      +"on d.id_deuda = mv.id_deuda "
                      +"where mv.proximo_pago = ? "
                      +"and c.id_cartera = ? "
                      +"and d.id_estado_deuda = 1 "
                      +"and c.id_estado = 1 "
                      +"and mv.id_movimiento_deuda = (select max(mv2.id_movimiento_deuda) from movimientos_deudas mv2 where mv2.id_deuda = d.id_deuda) "
                      +"and mv.proximo_pago not in (select p.fecha_pago from pagos p where p.id_deuda = mv.id_deuda)";
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
    
    public ResultSet consultaIdDeuda(String id) { 
        try {
            String sql="select max(id_deuda) as id_deuda "
                      +"from deudas "
                      +"where id_cliente = ? "
                      +"and id_estado_deuda = 1";
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
    
    public int registrarPago(long monpag, String iddeu) {
        int respuesta = 0;
        
        try{
            String sql = "insert into pagos values(null, ?, now(), ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, monpag);
            pst.setString(2, iddeu);
            
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
    
    public ResultSet consultaMovimiento(String id) { 
        try {
            String sql="select cuota_actual, monto_actual "
                      +"from movimientos_deudas "
                      +"where id_movimiento_deuda = (select max(id_movimiento_deuda) from movimientos_deudas where id_deuda = ?)";
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
    
    public int registrarMovimiento(int cuovie, int cuoact, long monvie, long monact,
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
    
    public int actualizarDeuda(String id) {
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
}