
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloGestionarModalidades {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    //Constructor
    public ModeloGestionarModalidades(){
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    
    public ResultSet consultaModalidades (){ 
        try {
            String sql="SELECT * from modalidades";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
     
    public String consultaCuotas (String modalidad){ 
           String cuota="";
        try {
            String sql="SELECT cantidad_cuotas,id_modalidad from modalidades WHERE descripcion='"+modalidad+"'";
           
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
            
            while(rss.next()){
                cuota=rss.getString("cantidad_cuotas")+"-"+rss.getString("id_modalidad");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return cuota;
    }
      
      //Metodo para actualizar una modalidad
    public boolean actualizarModalidad(int id,String modalidad,int cuota){
        boolean verificar = false;
               
        try{
            String sql="UPDATE  modalidades SET descripcion =?,cantidad_cuotas=? WHERE id_modalidad="+id;
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1,modalidad);
            ps.setInt(2,cuota);
            
            if(ps.executeUpdate()>0){
                verificar=true;
            }
           
           ps.close();  
        } catch(Exception e){  
            System.out.print(e);
        }
            
        return verificar;
    }
}