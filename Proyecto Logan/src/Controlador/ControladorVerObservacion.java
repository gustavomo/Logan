
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloVerObservacion;
import Vista.GestionarClientes;
import Vista.Principal;
import Vista.VerDeudas;
import Vista.VerObservacion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

public class ControladorVerObservacion implements ActionListener{

    private VerObservacion obj1;
    private ModeloVerObservacion obj2 = new ModeloVerObservacion();
    private JTextArea jta1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    
    public ControladorVerObservacion(VerObservacion obj1) {
        this.obj1 = obj1;
        this.jta1 = obj1.jta1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.jta1.setEditable(false);
        this.jta1.setLineWrap(true);
        this.jta1.setWrapStyleWord(true);
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
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
    
    public void CargarObservacion(String fecini) {
        rss = obj2.consultaObservacion(DatosGestionarClientes.getId(), fecini);
        
        try {
            while (rss.next()) {
                jta1.setText(rss.getString("d.observacion"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerDeudas obj2 = new VerDeudas();
            ControladorVerDeudas obj2obj1 = new ControladorVerDeudas(obj2);
            obj2obj1.CargarDeudas();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        } else {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarClientes obj2 = new GestionarClientes();
            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
    }
}