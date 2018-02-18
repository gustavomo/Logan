
package Controlador;

import Controlador.ContenedorClases.DatosGestionarCarteras;
import Modelo.ModeloModificarCartera;
import Vista.GestionarCarteras;
import Vista.ModificarCartera;
import Vista.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorModificarCartera implements ActionListener, KeyListener {
    
    private ModificarCartera obj1;
    private ModeloModificarCartera obj2 = new ModeloModificarCartera();
    private JTextField txt1, txt2;
    private JComboBox cbx1;
    private JButton btn1, btn2;
    private ResultSet rss = null;
    private Color fondo = new Color(232, 238, 244);
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorModificarCartera(ModificarCartera obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.txt1.addKeyListener(this);
        this.txt2.addKeyListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
    }
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    public void cargarForm() {
        String id = DatosGestionarCarteras.getId();
        
        rss = obj2.consultaDatosCarteras(id);
        try {
            while (rss.next()){
                long capini = rss.getLong("ca.capital_inicial");
                txt1.setText(asignarValor(capini));
                long montmax = rss.getLong("ca.monto_maximo");
                txt2.setText(asignarValor(montmax));
                
            }
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    public void cargarCobradores() {
        cbx1.addItem("Seleccione");
        
        rss = obj2.consultaCobradores();
        try {
            while (rss.next()){
                cbx1.addItem(rss.getString("nombre"));
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

    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarCarteras obj2 = new GestionarCarteras();
            ControladorGestionarCarteras obj2obj1 = new ControladorGestionarCarteras(obj2);
            posicinarFrame(Principal.jDesktopPane1, obj2);
            obj2obj1.cargarCarteras();
            obj2.cbx1.setSelectedItem(DatosGestionarCarteras.getCartera());
        } else {
            boolean ban = true;
            
            if (txt1.getText().isEmpty()) {
                if (ban==true) {
                    alerta("Por favor llene el campo de Capital Inicial");
                    ban = false;
                }
            }
                else if (txt2.getText().isEmpty()) {
                    if (ban==true) {
                        alerta("Por favor llene el campo de Monto Máximo a Prestar");
                        ban = false;
                    }
                }
                    else if (cbx1.getSelectedItem().equals("Seleccione")) {
                        if (ban==true) {
                            alerta("Por favor seleccione un Cobrador");
                            ban = false;
                        }
                    }
                        else {
                            String valor1 = txt1.getText().replace(".","").replace(",","");
                            String valor2 = txt2.getText().replace(".","").replace(",","");
                            long mon = Long.parseLong(valor2), cap = Long.parseLong(valor1);
                            String nomcob = cbx1.getSelectedItem().toString();
                            String idcob = null;
                            String idcar = DatosGestionarCarteras.getId();
                            
                            rss = obj2.consultaIdeCobrador(nomcob);
                            try {
                                while (rss.next()) {
                                    idcob = rss.getString("id_cobrador");
                                }
                            } catch(SQLException error){
                                System.out.println("Erroy de MySql"+error);
                            } catch(NumberFormatException error){
                                System.out.println("se presento el siguiente error "+error.getMessage());
                            }
                                
                            int respuesta = obj2.actualizarDatosCartera(mon, cap, idcob, idcar);
                                
                            if (respuesta==0) {
                                alerta("Ha ocurrido un error al Modificar los Datos de la Cartera, Vuelve a intertarlo");
                            } else {
                                obj1.setVisible(false);
                                Principal.jDesktopPane1.remove(obj1);
                                GestionarCarteras obj2 = new GestionarCarteras();
                                ControladorGestionarCarteras obj2obj1 = new ControladorGestionarCarteras(obj2);
                                posicinarFrame(Principal.jDesktopPane1, obj2);
                                obj2obj1.cargarCarteras();
                                obj2.cbx1.setSelectedItem(DatosGestionarCarteras.getCartera());
                                alerta("Se Modificaron los Datos con Exito!");
                            }
                        }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        
        if (e.getSource()==txt1) {
            // Verificar si la tecla pulsada no es un digito
            if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                || (txt1.getText().length()>16) ) {
                e.consume();  // ignorar el evento de teclado
            }
        } else {
            // Verificar si la tecla pulsada no es un digito
            if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                || (txt2.getText().length()>16) ) {
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
        } else {
            if (!txt2.getText().isEmpty() && !txt2.getText().equals("") && !txt2.getText().equals(" ")) {
                long valor = Long.valueOf( txt2.getText().replace(".", "").replace(",", "") );
                txt2.setText( formato.format(valor) );
            }
        }
    }
}
