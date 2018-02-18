
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloModificarDatos;
import Vista.GestionarClientes;
import Vista.ModificarDatos;
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

public class ControladorModificarDatos implements ActionListener {
    
    private ModificarDatos obj1;
    private ModeloModificarDatos obj2 = new ModeloModificarDatos();
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;
    private JButton btn1, btn2;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);

    public ControladorModificarDatos(ModificarDatos obj1) {
        this.obj1 = obj1;
        txt1 = obj1.txt1;
        txt2 = obj1.txt2;
        txt3 = obj1.txt3;
        txt4 = obj1.txt4;
        txt5 = obj1.txt5;
        txt6 = obj1.txt6;
        txt7 = obj1.txt7;
        txt8 = obj1.txt8;
        txt9 = obj1.txt9;
        txt10 = obj1.txt10;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    public void cargarForm() {
        String id = DatosGestionarClientes.getId();
        
        rss = obj2.consultaCliente(id);
        try {
            while (rss.next()){
                txt1.setText(rss.getString("nombre"));
                txt2.setText(rss.getString("id_cliente"));
                txt3.setText(rss.getString("barrio"));
                txt4.setText(rss.getString("direccion"));
                txt5.setText(rss.getString("telefono"));
                txt6.setText(rss.getString("nombre_ref"));
                txt7.setText(rss.getString("identificacion_ref"));
                txt8.setText(rss.getString("barrio_ref"));
                txt9.setText(rss.getString("direccion_ref"));
                txt10.setText(rss.getString("telefono_ref"));
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
    
    private int verificarCliente() {
        int respuesta = 0;
        String id = txt1.getText();
        String valor = null;
        
        rss = obj2.consultaCliente(id);
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
            GestionarClientes obj2 = new GestionarClientes();
            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        } else {
            boolean ban = true;
            
            if (txt1.getText().isEmpty()) {
                if (ban==true) {
                    alerta("Por favor llene el campo de Nombre Cliente");
                    ban = false;
                }
            }
                else if (txt2.getText().isEmpty()) {
                    if (ban==true) {
                        alerta("Por favor llene el campo de N° Identificación Cliente");
                        ban = false;
                    }
                }
                    else if (txt3.getText().isEmpty()) {
                        if (ban==true) {
                            alerta("Por favor llene el campo de Dirección Cliente");
                            ban = false;
                        }
                    }
                        else if (txt4.getText().isEmpty()) {
                            if (ban==true) {
                                alerta("Por favor llene el campo de Barrio Cliente");
                                ban = false;
                            }
                        }
                            else if (txt5.getText().isEmpty()) {
                                if (ban==true) {
                                    alerta("Por favor llene el campo de Teléfono Cliente");
                                    ban = false;
                                }
                            }
                                else if (txt6.getText().isEmpty()) {
                                    if (ban==true) {
                                        alerta("Por favor llene el campo de Nombre Referenia");
                                        ban = false;
                                    }
                                }
                                    else if (txt7.getText().isEmpty()) {
                                        if (ban==true) {
                                            alerta("Por favor llene el campo de N° Identifiación Referencia");
                                            ban = false;
                                        }
                                    }
                                        else if (txt8.getText().isEmpty()) {
                                            if (ban==true) {
                                                alerta("Por favor llene el campo de Dirección Referencia");
                                                ban = false;
                                            }
                                        }
                                            else if (txt9.getText().isEmpty()) {
                                                if (ban==true) {
                                                    alerta("Por favor llene el campo de Barrio Referencia");
                                                    ban = false;
                                                }
                                            }
                                                else if (txt10.getText().isEmpty()) {
                                                    if (ban==true) {
                                                        alerta("Por favor llene el campo de Teléfono Referencia");
                                                        ban = false;
                                                    }
                                                }
            
            String id = txt2.getText();
            
            if (!id.equals(DatosGestionarClientes.getId())) {
                int respuesta = verificarCliente();

                if (respuesta==0) {
                    if (ban==true) {
                        alerta("La Identificación que ha Modificado le pertenece a otro Cliente, Por favor vuelva a intertarlo");
                        ban = false;
                    }
                }
            }
            
            if (ban==true) {
                String nom = txt1.getText();
                String bar = txt3.getText();
                String dir = txt4.getText();
                String tel = txt5.getText();
                String nomref = txt6.getText();
                String ideref = txt7.getText();
                String barref = txt8.getText();
                String dirref = txt9.getText();
                String telref = txt10.getText();
                
                int respuesta = obj2.actualizarDatosCliente(id, nom, tel, dir, bar, ideref, nomref, telref, dirref, barref);
                
                boolean val = false; 
                    
                if (respuesta==0) {
                    val = true;
                } 
                
                if (val==true) {
                    alerta("Ha ocurrido un error al Modficar los Datos del Cliente, Vuelve a intertarlo");
                } else {
                    obj1.setVisible(false);
                    Principal.jDesktopPane1.remove(obj1);
                    GestionarClientes obj2 = new GestionarClientes();
                    ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
                    obj2.jPanel1.setBackground(fondo);
                    obj2.jPanel2.setBackground(fondo);
                    posicinarFrame(Principal.jDesktopPane1, obj2);
                    alerta("Se Modificarón los Datos con Exito!");
                }
            }
        }
    }
}
