
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerPagos {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloVerPagos() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }

    public ResultSet consultaPagos(String id, String fecini) { 
        try {
            String sql="select fecha_pago, monto_pagado "
                      +"from pagos p "
                      +"inner join deudas d "
                      +"on d.id_deuda = p.id_deuda "
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
    
    public ResultSet consultaIdEstadoDeuda(String id, String fecini) { 
        try {
            String sql="select id_estado_deuda from deudas "
                      +"where id_cliente = ? and fecha_inicio = ?";
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