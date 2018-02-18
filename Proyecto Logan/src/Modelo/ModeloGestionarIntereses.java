
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloGestionarIntereses {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    //Constructor
    public ModeloGestionarIntereses(){
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaIntereses (){ 
        try {
            String sql="SELECT * from intereses";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
            
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
     
     //Metodo para actualizar un interes
    public boolean actualizarInteres(int id,int tasa){
        boolean verificar = false;
               
        try{
            String sql="UPDATE  intereses SET taza_interes =? WHERE id_interes="+id;
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1,tasa);           
            
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