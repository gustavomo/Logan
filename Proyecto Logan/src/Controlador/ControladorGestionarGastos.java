
package Controlador;

import Modelo.ModeloGestionarGastos;
import Vista.DatosCartera;
import Vista.GestionarGastos;
import Vista.Principal;
import Vista.RegistrarTipoGasto;
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
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ControladorGestionarGastos implements ActionListener {
    private ModeloGestionarGastos modelo = new ModeloGestionarGastos();
    private GestionarGastos obj1;
    private DatosCartera obj2 = new DatosCartera();
    private RegistrarTipoGasto obj3 = new RegistrarTipoGasto();
    private VerGastos obj4 = new VerGastos();
    private ControladorDatosCartera obj5 = new ControladorDatosCartera(obj2);
    private JButton boton1, boton2, boton3;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet tipoGasto;    
    
    
    public ControladorGestionarGastos(GestionarGastos obj1) {
        this.obj1 = obj1;        
        this.obj1.txt1.setText("");
        boton1 = obj1.btn2;
        boton2 = obj1.btn3;
        boton3 = obj1.btn1;
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        this.obj1.cbx1.removeAllItems();
        cargarTipoGastos();
        this.obj1.cbx1.setSelectedIndex(0);

        bloquearPegar();
    }
    
    private void bloquearPegar() {
        InputMap map = this.obj1.txt1.getInputMap(this.obj1.txt1.WHEN_FOCUSED);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    public void cargarTipoGastos(){
        //Carga los tipos de gastos de la BD
        tipoGasto = modelo.consultaTipoGastos();        
        try { 
            obj1.cbx1.addItem("Seleccione");
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
        if(e.getSource()==boton1) { 
            //Se valida Para que no se deje vacio el valor
            if (obj1.txt1.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese el Valor del Gasto");
                return;
            }  
            
            if (obj1.cbx1.getSelectedItem().equals("Seleccione")){
                JOptionPane.showMessageDialog(obj1,"Por favor seleccione un Tipo de Gasto");
                return;
            }
            obj5.setValor(obj1.txt1.getText());
            obj5.setTipo(String.valueOf(obj1.cbx1.getSelectedItem()));
            ////////////////////////////////////////
            
            obj5.setRecibir(2);            
            JButton boton1obj5 = obj5.getBtn2();
            boton1obj5.setText("Registrar Gasto");
            obj5.setBtn2(boton1obj5);
            posicinarFrame(obj2);
            obj5.cargarCarteras();
        }
            else if(e.getSource()==boton2) { 
                posicinarFrame(obj3);
            }
                else if(e.getSource()==boton3) { 
                    posicinarFrame(obj4);
                }
    }
}