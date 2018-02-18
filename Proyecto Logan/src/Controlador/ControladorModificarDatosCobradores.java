
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCobradores;
import Modelo.ModeloModificarDatosCobradores;
import Vista.GestionarCobradores;
import Vista.ModificarDatosCobradores;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorModificarDatosCobradores implements ActionListener {
    
    private ModificarDatosCobradores obj1;
    private ModeloModificarDatosCobradores obj2 = new ModeloModificarDatosCobradores();
    private JTextField txt1, txt2, txt3, txt4, txt5;
    private JButton btn1, btn2;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);
    
    public ControladorModificarDatosCobradores(ModificarDatosCobradores obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    public void cargarForm() {
        String id = DatosGestionarCobradores.getId();
        
        rss = obj2.consultaCobrador(id);
        try {
            while (rss.next()){
                txt1.setText(rss.getString("nombre"));
                txt2.setText(rss.getString("id_cobrador"));
                txt3.setText(rss.getString("barrio"));
                txt4.setText(rss.getString("direccion"));
                txt5.setText(rss.getString("telefono"));
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
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
    
    private int verificarCobrador() {
        int respuesta = 0;
        String id = txt2.getText();
        String valor = null;
        
        rss = obj2.consultaCobrador(id);
        try {
            while (rss.next()){
                valor = rss.getString("nombre");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        if (valor==null) {
            respuesta = 1;
        } else {
            respuesta = 0;
        }
        
        return respuesta;
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarCobradores obj2 = new GestionarCobradores();
            ControladorGestionarCobradores obj2obj1 = new ControladorGestionarCobradores(obj2);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        } else {
            boolean ban = true;
            
            if (txt1.getText().isEmpty()) {
                if (ban==true) {
                    alerta("Por favor llene el campo de Nombre Cobrador");
                    ban = false;
                }
            }
                else if (txt2.getText().isEmpty()) {
                    if (ban==true) {
                        alerta("Por favor llene el campo de N° Identificación");
                        ban = false;
                    }
                }
                    else if (txt3.getText().isEmpty()) {
                        if (ban==true) {
                            alerta("Por favor llene el campo de Dirección");
                            ban = false;
                        }
                    }
                        else if (txt4.getText().isEmpty()) {
                            if (ban==true) {
                                alerta("Por favor llene el campo de Barrio");
                                ban = false;
                            }
                        }
                            else if (txt5.getText().isEmpty()) {
                                if (ban==true) {
                                    alerta("Por favor llene el campo de Teléfono");
                                    ban = false;
                                }
                            }
            
            String id1 = DatosGestionarCobradores.getId();
            String id2 = txt2.getText();
            
            if (!id1.equals(id2)) {
                int respuesta = verificarCobrador();

                if (respuesta==0) {
                    if (ban==true) {
                        alerta("La Identificación que ha modificado le pertenece a otro Cobrador, Por favor vuelva a intertarlo");
                        ban = false;
                    }
                }
            }
            
            if (ban==true) {
                String nom = txt1.getText();
                String bar = txt3.getText();
                String dir = txt4.getText();
                String tel = txt5.getText();
                
                int respuesta = obj2.actualizarDatosCobrador(id2, nom, tel, dir, bar, id1);
                
                boolean val = false; 
                    
                if (respuesta==0) {
                    val = true;
                } 
                
                if (val==true) {
                    alerta("Ha ocurrido un error al modificar los Datos del Cobrador, Vuelve a intertarlo");
                } else {
                    DatosGestionarCobradores.setId(id2);
                    
                    obj1.setVisible(false);
                    Principal.jDesktopPane1.remove(obj1);
                    GestionarCobradores obj2 = new GestionarCobradores();
                    ControladorGestionarCobradores obj2obj1 = new ControladorGestionarCobradores(obj2);
                    posicinarFrame(Principal.jDesktopPane1, obj2);
                    alerta("Se Modificarón los Datos con Exito!");
                }
            }
        }
    }
}
