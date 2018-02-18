
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ControladorGestionarModalidades implements ActionListener, ItemListener {
    
    private GestionarModalidades obj1;
    private ModificarModalidad obj2 = new ModificarModalidad();
    private JComboBox cbx1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private ModeloGestionarModalidades modelo = new ModeloGestionarModalidades();
    private ControladorModificarModalidad obj = new ControladorModificarModalidad(obj2);
    
    public ControladorGestionarModalidades(GestionarModalidades obj1) {
        this.obj1 = obj1;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.btn1;
        
        this.obj1.txt1.setEditable(false);
        
        ResultSet rs = modelo.consultaModalidades();
        
        try{
            cbx1.addItem("Seleccione");
            while(rs.next()){
                cbx1.addItem(rs.getString("descripcion"));
            }
        } catch(Exception e){
            
        }
        
        this.btn1.addActionListener(this);
        this.cbx1.addItemListener(this);
        bloquearPegar();
    }
    
    private void bloquearPegar() {
        InputMap map = this.obj1.txt1.getInputMap(this.obj1.txt1.WHEN_FOCUSED);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
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
            String valor = cbx1.getSelectedItem().toString();
            if (valor.equals("Seleccione")){
                JOptionPane.showMessageDialog(obj1,"Por favor seleccione una Modalidad");
                return;
            }
            
            String array[]= modelo.consultaCuotas(valor).split("-");
            obj.setValor(valor,this.obj1.txt1.getText(),Integer.valueOf(array[1]));
            posicinarFrame(obj2);
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor = cbx1.getSelectedItem().toString();
        if (valor.equals("Seleccione")) {
            this.obj1.txt1.setText("");
        } else {
            String array[]= modelo.consultaCuotas(valor).split("-");
            this.obj1.txt1.setText(array[0]);
        }
    }
}