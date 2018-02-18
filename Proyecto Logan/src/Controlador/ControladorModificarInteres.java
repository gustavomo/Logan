
package Controlador;

import Modelo.ModeloGestionarIntereses;
import Vista.GestionarIntereses;
import Vista.ModificarInteres;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class ControladorModificarInteres implements ActionListener {
    
    private ModificarInteres obj1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private String valor;
    private int id;
    private ModeloGestionarIntereses modelo = new ModeloGestionarIntereses();
    
    public ControladorModificarInteres(ModificarInteres obj1) {
        this.obj1 = obj1;
        this.btn1 = obj1.btn1;
            
        this.btn1.addActionListener(this);
        this.obj1.btn2.addActionListener(this);
    }
    
    private void posicinarFrame(JDesktopPane escritorio, JInternalFrame ventana) {
        escritorio.removeAll();
        escritorio.add(ventana);
        ventana.setBackground(fondo);
        Dimension desktopSize = escritorio.getSize();
        Dimension FrameSize = ventana.getSize();
        ventana.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        ventana.show();
    }
    
    // Funciones para obtener valor 
    public void setValor(String valor,int id){
        this.valor=valor;
        this.obj1.txt1.setText(valor); 
        this.id=id;
    }
    //////////////////////////////////////////////////////////////

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarIntereses obj2 = new GestionarIntereses();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
        
        if(e.getSource()==this.obj1.btn2){
            if (this.obj1.txt1.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese un Valor");
                return;
            }
            
            int tasa =Integer.valueOf(this.obj1.txt1.getText().replace(".", "").replace(",", ""));
            Boolean ok= modelo.actualizarInteres(id,tasa);
           
            if(ok){
                JOptionPane.showMessageDialog(obj1,"Se ha Modificado la Tasa con Exito!");
            }
        }
    }
}