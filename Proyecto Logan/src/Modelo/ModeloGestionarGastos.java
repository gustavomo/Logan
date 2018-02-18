
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloGestionarGastos {
    
    private ConexionBaseDatos obj1 = new ConexionBaseDatos();
    private Connection conexion;
    private ResultSet rss = null;
    
    public ModeloGestionarGastos(){
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                System.out.println("Erroy de MySql"+error);
            }
        }
        conexion = obj1.conexion();
    }
    
    public ResultSet consultaTipoGastos (){ 
        try {
            String sql="select descripcion "
                      +"from tipos_gastos";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);           
           
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
    
    //Metodo para registrar un gasto
    public boolean registrarGasto(int monto,String fecha,String tipo_gasto,int cartera){
        boolean verificar = false;
        int id_tipo_gasto=0;
        
        try {
            String sql="select id_tipo_gasto "
                      +"from tipos_gastos WHERE descripcion ='"+tipo_gasto+"'";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
            
            while(rss.next()){
                id_tipo_gasto=rss.getInt("id_tipo_gasto");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
               
        try{
            String sql="INSERT INTO gastos_operacionales VALUES(?,?,?,?,?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1,null);
            ps.setInt(2,monto);
            ps.setString(3,fecha);
            ps.setInt(4,id_tipo_gasto);
            ps.setInt(5,cartera);
            
            if(ps.executeUpdate()>0){
                verificar=true;
            }

           ps.close();             
        } catch(Exception e){
            System.out.print(e); 
        }

        return verificar;
    }
     
     //Metodo para obtener las carteras que tiene gastos
     public ResultSet getCarteras (){ 
        try {
            String sql="select distinct G.id_cartera "
                      +"from gastos_operacionales G INNER JOIN carteras C ON C.id_cartera=G.id_cartera";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);           
           
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
     ///////////////////////////////////////////////////////////
     
     //Metodo para obtener las carteras 
     public ResultSet getCarteras2 (){ 
        try {
            String sql="select  id_cartera from carteras ";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);           
           
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
     ///////////////////////////////////////////////////////////
     
     //Metodo para obtener los registros segun las fechas y id
     public ResultSet filtroFechas (String where){ 
        try {
            String sql="select id_gasto_operacional,descripcion,monto_gasto,id_cartera,fecha_gasto from gastos_operacionales G INNER JOIN tipos_gastos T ON T.id_tipo_gasto= G.id_tipo_gasto "+where;
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);           
           
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        return rss;
    }
     ///////////////////////////////////////////////////////////
     
     //Metodo para registrar un gasto
    public boolean modificarGasto(int monto,String tipo_gasto,int cartera,int idGasto){
        boolean verificar = false;
        int id_tipo_gasto=0;
        
        try {
            String sql="select id_tipo_gasto "
                      +"from tipos_gastos WHERE descripcion ='"+tipo_gasto+"'";
            Statement stt = conexion.createStatement();  
            rss = stt.executeQuery(sql);
            
            while(rss.next()){
                id_tipo_gasto=rss.getInt("id_tipo_gasto");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
               
        try{
            String sql="UPDATE gastos_operacionales set monto_gasto=?,id_tipo_gasto=?,id_cartera=? WHERE id_gasto_operacional="+idGasto;
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1,monto);
            ps.setInt(2,id_tipo_gasto);
            ps.setInt(3,cartera);
            
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