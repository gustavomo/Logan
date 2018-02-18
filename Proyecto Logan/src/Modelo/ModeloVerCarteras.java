
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloVerCarteras {

    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloVerCarteras() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaDatosCarteras() { 
        try {
            String sql="select ca.id_cartera, ca.monto_maximo, ca.capital_inicial, ca.fecha_inicio, co.nombre, count(id_cliente) as total_clientes, "
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
                            +"where cl.id_cartera = ca.id_cartera) as total_deuda_cartera, "
                            +"(select sum(go.monto_gasto) "
                            +"from gastos_operacionales go "
                            +"where go.id_cartera = ca.id_cartera) as total_gastos "
                      +"from carteras ca "
                      +"inner join cobradores co "
                      +"on co.id_cobrador = ca.id_cobrador "
                      +"left join clientes cl "
                      +"on ca.id_cartera = cl.id_cartera "
                      +"group by(ca.id_cartera)";
            Statement stt = conexion.createStatement();  
            
            rss = stt.executeQuery(sql);
        }
        catch (SQLException e){
            System.out.print(e);
        }
        
        return rss;
    }
}