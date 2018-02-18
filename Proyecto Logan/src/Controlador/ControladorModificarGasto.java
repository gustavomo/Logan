
package Controlador;

import Modelo.ModeloGestionarGastos;
import Vista.ModificarGasto;
import Vista.Principal;
import Vista.VerGastos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ControladorModificarGasto  implements ActionListener {
    
    private ModificarGasto obj1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private String desc,cuota;
    private int id;
    private String valor,tipo,cartera,idGasto;
    private ModeloGestionarGastos modelo = new ModeloGestionarGastos();
    private ResultSet rss = null;
    private ResultSet tipoGasto;
    
    
    public ControladorModificarGasto(ModificarGasto obj1) {
        this.obj1 = obj1;
        this.btn1 = obj1.btn1;
        
        this.btn1.addActionListener(this);
        this.obj1.btn2.addActionListener(this);
        
        cargarCar();
        
        bloquearPegar();
        cargarTipoGastos();
    }
    
    private void cargarCar() {        
        rss = modelo.getCarteras2();
        try {
            while (rss.next()){
                obj1.cbx2.addItem("Cartera "+rss.getString("id_cartera"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    public void cargarTipoGastos(){
        //Carga los tipos de gastos de la BD
        tipoGasto = modelo.consultaTipoGastos();        
        try { 
            
            while(tipoGasto.next()){
                obj1.cbx1.addItem(tipoGasto.getString("descripcion"));
            }
                              
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        ///////////////////////////////////////////////////////////////
    }
    
    private void bloquearPegar() {
        InputMap map = this.obj1.txt1.getInputMap(this.obj1.txt1.WHEN_FOCUSED);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    //////////////////////////////////////////////////////////////
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();
    }
    
    // Funciones para obtener valor y tipo de gasto delcontrolador gestionar gastos
    public void setGasto(String idGasto){
        this.idGasto=idGasto;
    }
    
    public void setValor(String valor){    
        this.valor=valor;
        this.obj1.txt1.setText(valor);
    }
    
    public void setTipo(String tipo){
        this.tipo=tipo;
        this.obj1.cbx1.setSelectedItem(tipo);
    }
    
    public void setCartera(String cartera){
        this.cartera=cartera;
        this.obj1.cbx2.setSelectedItem(cartera);
    }
    ///////////////////////////////////////////
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.obj1.btn1){
            if (this.obj1.txt1.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese un Valor");
            }
            int monto=Integer.valueOf(this.obj1.txt1.getText().replace(".", "").replace(",", ""));
            int cartera=Integer.valueOf(String.valueOf(obj1.cbx2.getSelectedItem()).replace("Cartera ",""));

            //System.out.println(monto+" "+this.obj1.cbx1.getSelectedItem().toString()+" "+cartera+" "+Integer.valueOf(idGasto));
            boolean verificar= modelo.modificarGasto(monto,this.obj1.cbx1.getSelectedItem().toString(),cartera ,Integer.valueOf(idGasto));
            if (verificar){
                JOptionPane.showMessageDialog(obj1,"Se Actualizo el gasto con Exito!");
            }
        }
        
        if (e.getSource()==this.obj1.btn2) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerGastos obj2 = new VerGastos();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
    }
}