
package Controlador;

import Modelo.ModeloTipoGasto;
import Vista.GestionarGastos;
import Vista.Principal;
import Vista.RegistrarTipoGasto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class ControladorRegistrarTipoGasto implements ActionListener{
    
    private RegistrarTipoGasto obj1;
    private JButton boton1,registrar;
    private Color fondo = new Color(232, 238, 244);
    private ModeloTipoGasto modelo = new ModeloTipoGasto();
    
    public ControladorRegistrarTipoGasto(RegistrarTipoGasto obj1) {
        this.obj1 = obj1;
        boton1 = obj1.jButton2;
        boton1.addActionListener(this);
        registrar= obj1.jButton1;
        registrar.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarGastos obj2 = new GestionarGastos();
            posicinarFrame(Principal.jDesktopPane1, obj2);
            ControladorGestionarGastos obj1 = new ControladorGestionarGastos(obj2);
        }
        
        if(e.getSource()==registrar){
            Boolean verificar;
            
            //Validacion para no dejar el textfield vacio
            if (obj1.txt1.getText().equals("")){
                JOptionPane.showMessageDialog(obj1,"Por favor ingrese el Tipo de Gasto");
                return;
            }
            
            verificar=modelo.registrarTipoGasto(obj1.txt1.getText());
            
            if(verificar){
                obj1.txt1.setText("");
                JOptionPane.showMessageDialog(obj1,"Se ha Registrado el Tipo de Gasto con Exito!");
            }
        }
    }
}