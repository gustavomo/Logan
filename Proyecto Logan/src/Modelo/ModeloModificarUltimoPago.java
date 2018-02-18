
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloModificarUltimoPago {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloModificarUltimoPago() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
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
    
    public ResultSet consultaMovimiento(String id) { 
        try {
            String sql="select cuota_vieja, monto_vieja, cuota_actual, monto_actual, proximo_pago "
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
    
    public int actualizarPago(long monpag, String iddeu) {
        int respuesta = 0;
        
        try{
            String sql = "update pagos set monto_pagado = ? "
                        +"where id_pago = (select * from (select max(id_pago) from pagos where id_deuda = ?) as pg)";
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
    
    public int actualizarMovimiento(int cuoact, long monact, String propag, String iddeu) {
        int respuesta = 0;
        
        try{
            String sql = "update movimientos_deudas "
                        +"set cuota_actual = ?, "
                        +"monto_actual = ?, "
                        +"proximo_pago = ? "
                        +"where id_movimiento_deuda = (select * from (select max(id_movimiento_deuda) from movimientos_deudas where id_deuda = ?) as md)";
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
    
    public int actualizarDeuda(String id) {
        int respuesta = 0;
        
        try{
            String sql = "update deudas set id_estado_deuda = 2 "
                        +"where id_deuda = "
                            +"(select * from (select max(id_deuda) as id "
                            +"from deudas "
                            +"where id_cliente = ? and id_estado_deuda = 1) as dd)";
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