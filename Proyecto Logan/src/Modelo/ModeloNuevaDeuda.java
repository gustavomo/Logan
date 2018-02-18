
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloNuevaDeuda {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloNuevaDeuda() {
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
    
    public int registrarDueda (long monpre, long monint, int cancuo,String fecini, String  idmod,
                               String idestcue, String idcli){
        int respuesta = 0;
        
        try{
            String sql = "insert into deudas values(null, ?, ?, ?, now(), ?, null, ?, ?, ?)";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, monpre);
            pst.setLong(2, monint);
            pst.setInt(3, cancuo);
            pst.setString(4, fecini);
            pst.setString(5, idmod);
            pst.setString(6, idestcue);
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