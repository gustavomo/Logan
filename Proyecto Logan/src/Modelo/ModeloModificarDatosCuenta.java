
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloModificarDatosCuenta {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloModificarDatosCuenta() {
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
    
    public ResultSet consultaInteres() { 
        try {
            String sql="select taza_interes "
                      +"from intereses "
                      +"where id_interes = 1";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaCantidadCoutas(String id) { 
        try {
            String sql="select cantidad_cuotas "
                      +"from modalidades "
                      +"where descripcion = ?";
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
    
    public ResultSet consultaDatosCartera(String id) { 
        try {
            String sql="select ca.monto_maximo, ca.capital_inicial, "
                            +"(select sum(p.monto_pagado) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente "
                            +"where cl.id_cartera = ca.id_cartera) as total_pagado, "
                            +"(select sum(d.monto_prestado) "
                            +"from deudas d "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente "
                            +"where cl.id_cartera = ca.id_cartera) as total_prestado, "
                            +"(select sum(go.monto_gasto) "
                            +"from gastos_operacionales go "
                            +"where go.id_cartera = ca.id_cartera) as total_gastos "
                      +"from carteras ca "
                      +"inner join clientes cl "
                      +"on ca.id_cartera = cl.id_cartera "
                      +"where cl.id_cliente = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, id);
            
            rss = pst.executeQuery();
        }
        catch (SQLException e){
            System.out.print(e);
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
    
    public ResultSet consultaDatosCuenta(String id, String fec) { 
        try {
            String sql="select d.monto_prestado, d.cantidad_cuotas, d.fecha_inicio, "
                      +"d.observacion, m.descripcion "
                      +"from deudas d "
                      +"inner join modalidades m "
                      +"on m.id_modalidad = d.id_modalidad "
                      +"where d.id_cliente = ? and d.fecha_inicio = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, id);
            pst.setString(2, fec);
            
            rss = pst.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public int actualizarDatosDeuda(String idcli, long monpres, long monint,
                                      int cancuo, String fecini, String obs,
                                      String idmod) {
        int respuesta = 0;
        
        try{
            String sql = "update deudas "
                        +"set monto_prestado = ?, "
                        +"monto_interes = ?, "
                        +"cantidad_cuotas = ?, "
                        +"fecha_inicio = ?, "
                        +"observacion = ?, "
                        +"id_modalidad = ? "
                        +"where id_deuda = (select * from (select max(id_deuda) from deudas where id_cliente = ?) as dd)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, monpres);
            pst.setLong(2, monint);
            pst.setInt(3, cancuo);
            pst.setString(4, fecini);
            pst.setString(5, obs);
            pst.setString(6, idmod);
            pst.setString(7, idcli);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
    
    public ResultSet consultaIdDeuda(String id) { 
        try {
            String sql="select max(id_deuda) as id "
                      +"from deudas "
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
    
    public int actualizarMovimiento (int cuoact, long monact, String propag, String iddeu){
        int respuesta = 0;
        
        try{
            String sql = "update movimientos_deudas "
                        +"set cuota_actual = ?, "
                        +"monto_actual = ?, "
                        +"proximo_pago = ? "
                        +"where id_deuda = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, cuoact);
            pst.setLong(2, monact);
            pst.setString(3, propag); 
            pst.setString(4, iddeu);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}