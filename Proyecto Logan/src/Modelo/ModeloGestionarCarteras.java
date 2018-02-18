
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloGestionarCarteras {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloGestionarCarteras() {
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
    
    public ResultSet consultaDatosCarteras(String id) { 
        try {
            String sql="select ca.monto_maximo, ca.capital_inicial, ca.fecha_inicio, co.nombre, count(cl.id_cliente) as total_clientes, "
                            +"(select count(md.id_movimiento_deuda) " 
                            +"from clientes cl " 
                            +"inner join deudas d " 
                            +"on cl.id_cliente = d.id_cliente " 
                            +"inner join movimientos_deudas md " 
                            +"on d.id_deuda = md.id_deuda " 
                            +"where cl.id_cartera = ca.id_cartera " 
                            +"and md.id_tipo_movimiento = 4) as total_renovacion_cupos, "
                            +"(select sum(d.monto_prestado) "
                            +"from deudas d "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente "
                            +"where cl.id_cartera = ca.id_cartera) as total_prestado, "
                            +"(select sum(p.monto_pagado) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente "
                            +"where cl.id_cartera = ca.id_cartera) as total_pagado, "
                            +"(select sum(m.monto_mora) "
                            + "from moras m "
                            + "inner join deudas d "
                            + "on m.id_deuda = d.id_deuda "
                            + "inner join clientes cl "
                            + "on cl.id_cliente = d.id_cliente "
                            + "where cl.id_cartera = ca.id_cartera) as total_moras, "
                            +"(select sum(d.monto_prestado+d.monto_interes) "
                            +"from deudas d "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente "
                            +"where cl.id_cartera = ca.id_cartera) as total_deuda_cartera, "
                            +"(select sum(go.monto_gasto) "
                            +"from gastos_operacionales go "
                            +"where go.id_cartera = ca.id_cartera) as total_gastos, "
                            +"(select count(p.id_pago) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente  "
                            +"where cl.id_cartera = ca.id_cartera "
                            +"and d.id_modalidad = 1) as total_diarios, "
                            +"(select count(p.id_pago) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente  "
                            +"where cl.id_cartera = ca.id_cartera "
                            +"and d.id_modalidad = 2) as total_semanales, "
                            +"(select count(p.id_pago) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente  "
                            +"where cl.id_cartera = ca.id_cartera "
                            +"and d.id_modalidad = 3) as total_quincenales, "
                            +"(select count(p.id_pago) "
                            +"from pagos p "
                            +"inner join deudas d "
                            +"on d.id_deuda = p.id_deuda "
                            +"inner join clientes cl "
                            +"on cl.id_cliente = d.id_cliente  "
                            +"where cl.id_cartera = ca.id_cartera "
                            +"and d.id_modalidad = 4) as total_mensuales "
                      +"from carteras ca "
                      +"inner join cobradores co "
                      +"on co.id_cobrador = ca.id_cobrador "
                      +"left join clientes cl "
                      +"on ca.id_cartera = cl.id_cartera "
                      +"where ca.id_cartera = ? "
                      +"group by(ca.id_cartera)";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, id);
            
            rss = pst.executeQuery();
        }
        catch (SQLException e){
            System.out.print(e);
        }
        return rss;
    }
}