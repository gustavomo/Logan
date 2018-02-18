
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloReporte {

    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    //Constructor
    public ModeloReporte() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }

    public ResultSet consultaReporte(String where) {
        try {
            String sql = "select * from reporte " + where;
            Statement stt = conexion.createStatement();
            rss = stt.executeQuery(sql);

        } catch (SQLException error) {
            System.out.println("Erroy de MySql" + error);
        } catch (NumberFormatException error) {
            System.out.println("se presento el siguiente error " + error.getMessage());
        }
        
        return rss;
    }

    public ResultSet getCarteras() {
        try {
            String sql = "select distinct CC.id_cartera from carteras C INNER JOIN clientes CC ON C.id_cartera=CC.id_cartera";
            Statement stt = conexion.createStatement();
            rss = stt.executeQuery(sql);

        } catch (SQLException error) {
            System.out.println("Erroy de MySql" + error);
        } catch (NumberFormatException error) {
            System.out.println("se presento el siguiente error " + error.getMessage());
        }
        
        return rss;
    }
}