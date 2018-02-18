
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloModificarCartera {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloModificarCartera() {
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaDatosCarteras(String id) { 
        try {
            String sql="select ca.monto_maximo, ca.capital_inicial, co.nombre "
                      +"from carteras ca "
                      +"inner join cobradores co "
                      +"on co.id_cobrador = ca.id_cobrador "
                      +"where ca.id_cartera = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, id);
            
            rss = pst.executeQuery();
        }
        catch (SQLException e){
            System.out.print(e);
        }
        
        return rss;
    }
    
    public ResultSet consultaCobradores (){ 
        try {
            String sql="select nombre "
                      +"from cobradores "
                      +"where id_estado = 1";
            Statement stt = conexion.createStatement();  
            
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public ResultSet consultaIdeCobrador (String ide){ 
        try {
            String sql="select id_cobrador "
                      +"from cobradores "
                      +"where nombre = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);  
            pst.setString(1, ide);
            
            rss = pst.executeQuery();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return rss;
    }
    
    public int actualizarDatosCartera(long monmax, long capini, String idcob, String idcar) {
        int respuesta = 0;
        
        try{
            String sql = "update carteras "
                        +"set monto_maximo = ?, "
                        +"capital_inicial = ?, "
                        +"id_cobrador = ? "
                        +"where id_cartera = ?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setLong(1, monmax);
            pst.setLong(2, capini);
            pst.setString(3, idcob);
            pst.setString(4, idcar);
            
            respuesta = pst.executeUpdate();
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        return respuesta;
    }
}