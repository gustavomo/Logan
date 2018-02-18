
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCobradores;
import Modelo.ModeloGestionarCobradores;
import Vista.GestionarCobradores;
import Vista.ModificarDatosCobradores;
import Vista.Principal;
import Vista.VerCobradores;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorGestionarCobradores implements ActionListener, KeyListener {
    
    private GestionarCobradores obj1;
    private ModeloGestionarCobradores obj2 = new ModeloGestionarCobradores();
    private ModificarDatosCobradores obj3 = new ModificarDatosCobradores();
    private ControladorModificarDatosCobradores obj3obj1 = new ControladorModificarDatosCobradores(obj3);
    private VerCobradores obj4 = new VerCobradores();
    private ControladorVerCobradores obj4obj1 = new ControladorVerCobradores(obj4);
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6;
    private JButton btn1, btn2, btn3;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);
    
    public ControladorGestionarCobradores(GestionarCobradores obj1) {
        this.obj1 = obj1;
        txt1 = obj1.txt1;
        txt2 = obj1.txt2;
        txt3 = obj1.txt3;
        txt4 = obj1.txt4;
        txt5 = obj1.txt5;
        txt6 = obj1.txt6;
        btn1 = obj1.btn1;
        btn2 = obj1.btn2;
        btn3 = obj1.btn3;
        
        txt2.setEditable(false);
        txt3.setEditable(false);
        txt4.setEditable(false);
        txt5.setEditable(false);
        txt6.setEditable(false);
        
        txt1.addKeyListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        
        cargarForm(true);
    }
    
    public void cargarForm(boolean val) {
        if (val==true) {
            txt1.setText(DatosGestionarCobradores.getId());
        }
        
        String id = txt1.getText();
            
        rss = obj2.consultaCobrador(id);
        try {
            boolean ban = false;
            while (rss.next()){
                txt2.setText(rss.getString("nombre"));
                txt3.setText(rss.getString("id_cobrador"));
                txt4.setText(rss.getString("barrio"));
                txt5.setText(rss.getString("direccion"));
                txt6.setText(rss.getString("telefono"));
                
                ban = true;
            }
                
            if (ban==false) {
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                txt5.setText("");
                txt6.setText("");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
            
        rss = obj2.consultaEstadoCobrador(id);
        try {
            boolean ban = false;
            while (rss.next()){
                if (rss.getInt("id_estado")==1) {
                    btn2.setText("Desactivar");
                } else  {
                    btn2.setText("Activar");
                }
                
                ban = true;
            }
                
            if (ban==false) {
                btn2.setText("Estado");
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
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
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private int verificarCobrador() {
        int respuesta = 0;
        String id = txt3.getText();
        String valor = null;
        
        rss = obj2.consultaCobradorCartera(id);
        try {
            while (rss.next()){
                valor = rss.getString("id_cobrador");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                DatosGestionarCobradores.setId(txt3.getText());
                posicinarFrame(obj3);
                obj3obj1.cargarForm();
            } else {
                alerta("Debe de cargar los Datos de un Cobrador antes de ir a Modificar Datos");
            }
        }
            else if(e.getSource()==btn2) {
                if(!txt3.getText().isEmpty() && !txt3.getText().equals("")) {
                    int respuesta1 = verificarCobrador();
                    
                    if(respuesta1==0) {
                        alerta("Para poder Inactivar al cobrador debe de retirarlo de la Cartera a la que este asociado");
                    } else {
                        String id = txt3.getText();
                        int respuesta2 = 0;

                        if (btn2.getText().equals("Activar")) {
                            respuesta2 = obj2.actualizarEstadoCobrador(id,1);
                        } else {
                            respuesta2 = obj2.actualizarEstadoCobrador(id, 2);
                        }

                        if (respuesta2==0) {
                            alerta("Ha ocurrido un error al cambiar el Estado del Cobrador, Vuelve a intertarlo");
                        } else {
                            if (btn2.getText().equals("Activar")) {
                                btn2.setText("Inactivar");
                                alerta("Se Activo al Cobrador Exitosamente!");
                            } else {
                                btn2.setText("Activar");
                                alerta("Se Inactivo al Cobrador Exitosamente!");
                            }
                        }
                    }
                } else {
                    alerta("Debe de cargar los Datos de un Cobrador, antes de poder\n"
                          +"realizar una accion de cambio de Estado");
                }
            }
                else {
                    obj4obj1.cargarCobradores(1);
                    obj4.jrd1.setSelected(true);
                    obj4.jrd1.setBackground(fondo);
                    obj4.jrd2.setBackground(fondo);
                    posicinarFrame(obj4);
                }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        cargarForm(false);
    }
}
