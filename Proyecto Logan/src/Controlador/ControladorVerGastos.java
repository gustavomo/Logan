
package Controlador;

import Modelo.ModeloGestionarGastos;
import Vista.GestionarGastos;
import Vista.ModificarGasto;
import Vista.Principal;
import Vista.VerGastos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorVerGastos implements ActionListener, ItemListener{
    
    private VerGastos obj1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel model = new DefaultTableModel(); 
    private ModeloGestionarGastos modelo = new ModeloGestionarGastos();
    private ModificarGasto vista= new ModificarGasto();
    private ControladorModificarGasto obj2 = new ControladorModificarGasto(vista);
    private ArrayList idGasto;
    
    public ControladorVerGastos(VerGastos obj1) {
        this.obj1 = obj1;
        this.btn1 = obj1.jButton1;        
        this.btn1.addActionListener(this);
        this.obj1.limpiar.addActionListener(this);
        this.obj1.btn2.addActionListener(this);
        this.obj1.cbx1.addItemListener(this);
        idGasto= new ArrayList();
        
        //Modelo para la tabla
        String datos[]={"Tipo Gasto","Valor Gasto","Cartera","Fecha Gasto"};
        model.setColumnIdentifiers(datos);
        this.obj1.jtb1.setModel(model);
        //////////////
        
        cargarCar();
        cargarGastos(null,null,0);
        
        // Sirve para obtener el evento cuando cambia un datachooser se verifica que las fecha que se obtienen de los datachooser no sea null, si  es null es manda al metodo un string null
        this.obj1.jdc1.getDateEditor().addPropertyChangeListener(new  PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())){
                    Date fecha=obj1.jdc1.getDate();
                    Date fecha2=obj1.jdc2.getDate();
                    String valor = obj1.cbx1.getSelectedItem().toString();
                    
                    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    String date = "";
                    String date2="";
                    if (fecha==null){
                        date=null;
                    }
                    else{
                        date = fmt.format(fecha);
                    }
                    
                    if (fecha2==null){
                        date2=null;
                    }
                    else{
                       date2 = fmt.format(fecha2);                    
                    }
                    
                    if (valor!="Seleccione"){
                        int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ",""));
                        cargarGastos(date,date2,idCartera);
                    }else{
                        cargarGastos(date,date2,0);
                    }  
                }
            }
        });
        ////////////////////////////////////////////////////
        
        // Sirve para obtener el evento cuando cambia un datachooser se verifica que las fecha que se obtienen de los datachooser no sea null, si  es null es manda al metodo un string null
        this.obj1.jdc2.getDateEditor().addPropertyChangeListener(new  PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())){
                    Date fecha=obj1.jdc1.getDate();
                    Date fecha2=obj1.jdc2.getDate();
                    String valor = obj1.cbx1.getSelectedItem().toString();
                    
                    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    String date ="" ;
                    String date2="";
                    
                    if (fecha2==null){
                        date2=null;
                    }
                    else{
                       date2 = fmt.format(fecha2);                    
                    }

                    if (fecha==null){
                        date=null;
                    }
                    else{
                        date = fmt.format(fecha);  
                        
                        if (valor!="Seleccione"){
                            int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ",""));
                            cargarGastos(date,date2,idCartera);
                        }else{
                            cargarGastos(date,date2,0);
                        }
                    }  
                }
            }
        });
       ////////////////////////////////////////////////////
    
       this.obj1.txt17.setEditable(false);
       this.obj1.txt18.setEditable(false);
       this.obj1.txt17.setText("—/—/———");
       this.obj1.txt18.setText("—/—/———");
       
       this.obj1.txt17.requestFocus();
       this.obj1.txt17.requestFocusInWindow();
       this.obj1.txt18.requestFocus();
       this.obj1.txt18.requestFocusInWindow();
    }
    
    private void cargarCar() {
        obj1.cbx1.addItem("Seleccione");
        
        rss = modelo.getCarteras();
        try {
            while (rss.next()){
                obj1.cbx1.addItem("Cartera "+rss.getString("id_cartera"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }

    //Metodo para cargar los datos de gastos segun los parametros
    //Explicacion: se pasa como parametro las fechas y el id de la cartera y segun lo que este null se hara la consulta con el where
    public void cargarGastos(String fechaI, String fechaF,int id) {
        Boolean verificar = false;

        if (obj1.jtb1.getRowCount()!=0){
            for (int i = 0; i < obj1.jtb1.getRowCount(); i++) {
                model.removeRow(i);
                i -= 1;
            }
        }
        
        String where = "";
        if (id!=0 && fechaI!=null && fechaF==null){
            where= "WHERE fecha_gasto= '"+fechaI+"' AND id_cartera="+id;
        }
        else if (id!=0 && fechaI!=null && fechaF!=null){
            where= "WHERE fecha_gasto BETWEEN '"+fechaI+"' AND '"+fechaF+"' AND id_cartera="+id;
        }
        else if (fechaI!=null && fechaF==null && id==0){
            where= "WHERE fecha_gasto= '"+fechaI+"'";
        }else if (fechaI!=null && fechaF!=null && id==0){
            where= "WHERE fecha_gasto BETWEEN '"+fechaI+"' AND '"+fechaF+"'";
        }
        else if (id!=0){
            where= "WHERE id_cartera="+id;
        }
        //System.out.println(where);
        ResultSet rs = modelo.filtroFechas(where);

        try {
            DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat formateador = new DecimalFormat("###,###.###");
            
            while (rs.next()) { 
                idGasto.add(rs.getInt("id_gasto_operacional"));
                String fecha = fmt.format(rs.getDate("fecha_gasto"));
                String monto = formateador.format(Double.valueOf(rs.getString("monto_gasto")));
                model.addRow(new Object[]{rs.getString("descripcion"),monto , "Cartera "+rs.getString("id_cartera"), fecha});   
            }              
        } catch (SQLException | HeadlessException er) {
            System.out.println(er);
        }
    }    
    ////////////////////////////////////////////////
    
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();
    }
    
    private void posicinarFrame(JInternalFrame ventana) {
        Principal.jDesktopPane1.remove(ventana);
        Principal.jDesktopPane1.add(ventana);
        Dimension desktopSize = Principal.jDesktopPane1.getSize();
        ventana.setBackground(fondo);
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.toFront();
        ventana.setVisible(true);
        obj1.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarGastos obj2 = new GestionarGastos();
            posicinarFrame(Principal.jDesktopPane1, obj2);
            ControladorGestionarGastos obj1 = new ControladorGestionarGastos(obj2);
        }
        
        //Funcion para limpiar los elementos
        if (e.getSource()==obj1.limpiar){
            obj1.cbx1.setSelectedItem("Seleccione");
            obj1.jdc1.setDate(null);
            obj1.jdc2.setDate(null);
            cargarGastos(null,null,0);
        }
        /////////////
        
        if (e.getSource()==obj1.btn2){
            int i = this.obj1.jtb1.getSelectedRow();
            
            if (i==-1){
                JOptionPane.showMessageDialog(obj1,"Por favor seleccione un Gasto");
                return;
            }
            
            String tipo = this.obj1.jtb1.getValueAt(i,0).toString();
            String cartera=this.obj1.jtb1.getValueAt(i,2).toString();
            String valor=this.obj1.jtb1.getValueAt(i,1).toString();
            obj2.setValor(valor);
            obj2.setCartera(cartera);
            obj2.setTipo(tipo);
            obj2.setGasto(idGasto.get(i).toString());
            //System.out.print(idGasto.get(i));
            posicinarFrame(vista);
        }
    }
    
    // item listener lara el comboBox
    @Override
    public void itemStateChanged(ItemEvent e) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String valor = obj1.cbx1.getSelectedItem().toString();
        
        if (valor.equals("Seleccione")){       
            Date fecha=obj1.jdc1.getDate();
            Date fecha2=obj1.jdc2.getDate();
            String date;
            String date2;
            if (fecha2==null){
                date2=null;
            }else{
                date2 = fmt.format(fecha2);                    
            }
            
            if (fecha==null){
                date=null;
            } else{
                date = fmt.format(fecha);          
            }
            cargarGastos(date,date2,0);
        }

        else{            
            int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ",""));
            Date fecha=obj1.jdc1.getDate();
            Date fecha2=obj1.jdc2.getDate();
            String date;
            String date2;
            if (fecha2==null){
                date2=null;
            }else{
                date2 = fmt.format(fecha2);                    
            }
            
            if (fecha==null){
                date=null;
            } else{
                date = fmt.format(fecha);         
            }

            cargarGastos(date,date2,idCartera);
        }
    }
}