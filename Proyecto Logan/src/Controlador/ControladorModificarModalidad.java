
package Controlador;

import Modelo.ModeloGestionarModalidades;
import Vista.GestionarModalidades;
import Vista.ModificarModalidad;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ControladorModificarModalidad implements ActionListener {
    
    private ModificarModalidad obj1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private String desc,cuota;
    private int id;
    private ModeloGestionarModalidades modelo = new ModeloGestionarModalidades();
    
    public ControladorModificarModalidad(ModificarModalidad obj1) {
        this.obj1 = obj1;
        this.btn1 = obj1.btn1;
        
        this.btn1.addActionListener(this);
        this.obj1.btn2.addActionListener(this);
        this.obj1.txt1.setEditable(false);
        
        bloquearPegar();
    }
    
    private void bloquearPegar() {
        InputMap map = this.obj1.txt2.getInputMap(this.obj1.txt2.WHEN_FOCUSED);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    // Funciones para obtener valor 
    public void setValor(String desc,String cuota,int id){    
        this.desc=desc;
        this.obj1.txt1.setText(desc);
        this.cuota=cuota;
        this.obj1.txt2.setText(cuota);
        this.id=id;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarModalidades obj2 = new GestionarModalidades();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }

        if(e.getSource()==this.obj1.btn2){
            if (this.obj1.txt1.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese una Modalidad");
                return;
            }
            if (this.obj1.txt2.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese la Cantidad de Cuotas");
                return;
            }
            
            Boolean ok = modelo.actualizarModalidad(id,this.obj1.txt1.getText(),Integer.valueOf(this.obj1.txt2.getText()));
            
            if(ok){
                JOptionPane.showMessageDialog(obj1,"Se ha Modificado la Modalidad con Exito!");
            }
        }
    }
}