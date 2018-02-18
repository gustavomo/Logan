
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloVerGastos {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;

    public ModeloVerGastos() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
}