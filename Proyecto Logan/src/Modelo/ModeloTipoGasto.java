
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Modelo del tipo gasto
public class ModeloTipoGasto {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    
    //Constructor
    public ModeloTipoGasto(){
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    //Metodo para registrar un tipo de gasto
    public boolean registrarTipoGasto(String descripcion){
        boolean verificar = false;
               
        try{
            String sql="INSERT INTO tipos_gastos VALUES(?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1,null);
            ps.setString(2,descripcion);
            
            if(ps.executeUpdate()>0){
                verificar=true;
            }
           
           ps.close();
        }catch(Exception e){
            System.out.print(e);
        }
        
        return verificar;
    }
}