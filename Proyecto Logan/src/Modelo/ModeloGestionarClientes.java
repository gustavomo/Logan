
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloGestionarClientes {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloGestionarClientes() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaCliente(String id) { 
        try {
            String sql="select id_cliente, nombre, telefono, direccion, barrio, identificacion_ref, nombre_ref, telefono_ref, direccion_ref, barrio_ref "
                      +"from clientes "
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
    
    public ResultSet consultaEstadoCliente(String id) { 
        try {
            String sql="select id_estado "
                      +"from clientes "
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
    
    public ResultSet consultaPagos(String id) { 
        try {
            String sql="select count(id_pago) as total_pagos "
                      +"from pagos "
                      +"where id_deuda = (select max(id_deuda) from deudas where id_cliente = ?)";
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
    
    public ResultSet consultaEstadoDeuda(String id) {
        try {
            String sql="select id_estado_deuda "
                      +"from deudas where id_deuda = "
                        +"(select max(id_deuda) "
                        +"from deudas where id_cliente = ?)";
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
    
    public int actualizarEstadoCliente(String id, int est) {
        int respuesta = 0;
        
        try{
            String sql = "update Clientes set id_estado = ? where id_cliente = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setInt(1, est);
            pst.setString(2, id);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}
