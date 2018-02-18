
package Controlador;

import Modelo.ModeloGestionarIntereses;
import Vista.GestionarIntereses;
import Vista.ModificarInteres;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

public class ControladorGestionarIntereses implements ActionListener {
    
    private GestionarIntereses obj1;
    private ModificarInteres obj2 = new ModificarInteres();
    private JTextField txt1, txt2;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ModeloGestionarIntereses modelo = new ModeloGestionarIntereses();
    private ControladorModificarInteres obj = new ControladorModificarInteres(obj2);
    
    public ControladorGestionarIntereses(GestionarIntereses obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        
        txt1.setEditable(false);
        txt2.setEditable(false);
        
        ResultSet rs = modelo.consultaIntereses();
        
        try{
            while(rs.next()){                
                if(rs.getInt("id_interes")==1){
                   txt1.setText(rs.getString("taza_interes")); 
                }
                if(rs.getInt("id_interes")==2){
                   txt2.setText(rs.getString("taza_interes"));  
                }
            }
        }catch(Exception e){

        }
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
            obj.setValor(txt1.getText(),1);
            posicinarFrame(obj2);
        } else if (e.getSource()==btn2) {
            obj.setValor(txt2.getText(),2);
            posicinarFrame(obj2);
        }
    }
}