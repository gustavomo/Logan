
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Controlador.ContenedorClases.DatosVerDeudas;
import Modelo.ModeloModificarUltimoPago;
import Vista.ModificarUltimoPago;
import Vista.Principal;
import Vista.VerPagos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ControladorModificarUltimoPago implements ActionListener, KeyListener {
    
    private ModificarUltimoPago obj1;
    private ModeloModificarUltimoPago obj2 = new ModeloModificarUltimoPago();
    private JTextField txt1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    private ResultSet rss = null;

    public ControladorModificarUltimoPago(ModificarUltimoPago obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.txt1.addKeyListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        
        bloquearPegar();
    }
    
    public void cargarForm(String monpag) {
        txt1.setText(monpag);
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
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private void bloquearPegar() {
        InputMap map1 = txt1.getInputMap(txt1.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerPagos obj3 = new VerPagos();
            ControladorVerPagos obj3obj1 = new ControladorVerPagos(obj3);
            obj3obj1.CargarPagos(DatosVerDeudas.getFecini());
            posicinarFrame(Principal.jDesktopPane1, obj3);
        } else {  
            if (txt1.getText()!=null) {
                long monpagnue = 0;
                if (!txt1.getText().isEmpty() && !txt1.getText().equals(" ")) {
                    monpagnue = Long.valueOf(txt1.getText().replace(",", "").replace(".", ""));
                }
            
                if (monpagnue!=0) {
                    String id = DatosGestionarClientes.getId();
                    String iddeu = null;
                    
                    rss = obj2.consultaIdDeuda(id);
                    try {
                        while (rss.next()){
                            iddeu = rss.getString("id_deuda");
                        }                
                    } catch(SQLException error){
                        System.out.println("Erroy de MySql"+error);
                    } catch(NumberFormatException error){
                        System.out.println("se presento el siguiente error "+error.getMessage());
                    }
                    
                    int cuovie = 0, cuoact = 0;
                    long monvie = 0, monact = 0;
                    String propag = null;
                    
                    rss = obj2.consultaMovimiento(iddeu);
                    try {
                        while (rss.next()){
                            monvie = rss.getInt("monto_vieja");
                            cuovie = rss.getInt("cuota_vieja");
                            monact = rss.getInt("monto_actual");
                            cuoact = rss.getInt("cuota_actual");
                            propag = rss.getString("proximo_pago");
                        }                
                    } catch(SQLException error){
                        System.out.println("Erroy de MySql"+error);
                    } catch(NumberFormatException error){
                        System.out.println("se presento el siguiente error "+error.getMessage());
                    }
                    
                    //long monpagvie = monvie - monact;
                    //long res = monpagnue-monpagvie;
                    
                    long monactnue = monvie-monpagnue;
                    //monact = monact+res;
                    //System.out.println(monvie+".."+cuovie+".."+monact+".."+cuoact+".."+propag+"|"+monactnue+".."+monact+"--"+monpagvie);
                    
                    if (monactnue<=0) {
                        cuoact = 0;
                        monactnue = 0;
                        propag = null;
                    }
                    
                    int respuesta1 = obj2.actualizarPago(monpagnue, iddeu);
                    int respuesta2 = obj2.actualizarMovimiento(cuoact, monactnue, propag, iddeu);
                    
                    if (monact==0) {
                        obj2.actualizarDeuda(iddeu);
                    }
                    
                    boolean val = false; 
                    if (respuesta1==0) {
                        val = true;
                    } 
                        else if (respuesta2==0) {
                            val = true;
                        }
                        
                    if (val==true) {
                        alerta("Ha ocurrido un error al Modificar el Ultimo Pago, Vuelve a intertarlo");
                    } else {
                        txt1.setText("");
                        alerta("Se Modifico el Ultimo Pago con Exito!");
                    }
                } else {
                    alerta("Debe de agrebar un Valor Valido, \npara poder Modificar el Ultimo Pago");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource()==txt1) {
            char caracter = e.getKeyChar();

            // Verificar si la tecla pulsada no es un digito
            if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                || (txt1.getText().length()>16) ) {
                e.consume();  // ignorar el evento de teclado
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txt1) {
            if (!txt1.getText().isEmpty() && !txt1.getText().equals("") && !txt1.getText().equals(" ")) {
                long valor = Long.valueOf( txt1.getText().replace(".", "").replace(",", "") );
                txt1.setText( formato.format(valor) );
            }
        }
    }
}