
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerDeudas {

    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloVerDeudas() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaDeudas(String id) { 
        try {
            String sql="select d.fecha_solicitud, d.fecha_inicio, mo.descripcion, d.monto_prestado, "
                      +"d.monto_interes, d.cantidad_cuotas, e.descripcion, "
                      +"(select count(mr.id_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as cuotas_mora, "
                      +"(select count(p.id_pago) from pagos p where d.id_deuda = p.id_deuda) as cuotas_pagas, "
                      +"(select md1.cuota_actual from movimientos_deudas md1 where md1.id_movimiento_deuda = (select max(md2.id_movimiento_deuda) from movimientos_deudas md2 where d.id_deuda = md2.id_deuda)) as cuotas_pendientes, "
                      +"(select sum(mr.monto_mora) from moras mr where d.id_deuda = mr.id_deuda and mr.id_estado = 1) as monto_mora, "
                      +"(select sum(p.monto_pagado) from pagos p where d.id_deuda = p.id_deuda) as monto_pagado "
                      +"from deudas d "
                      +"inner join modalidades mo "
                      +"on mo.id_modalidad = d.id_modalidad "
                      +"inner join estados_deudas e "
                      +"on e.id_estado_deuda = d.id_estado_deuda "
                      +"where d.id_cliente = ? "
                      +"order by(d.fecha_inicio)";
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
    
    public ResultSet consultaPagos(String id, String fec) { 
        try {
            String sql="select count(p.id_pago) as pagos "
                      +"from pagos p "
                      +"inner join deudas d "
                      +"on d.id_deuda = p.id_deuda "
                      +"where d.id_cliente = ? and d.fecha_inicio = ?";
            PreparedStatement st = conexion.prepareStatement(sql);  
            st.setString(1, id);
            st.setString(2, fec);
            
            rss = st.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaObservacion(String id, String fecini) { 
        try {
            String sql="select d.observacion "
                      +"from deudas d "
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