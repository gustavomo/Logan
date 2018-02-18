
package Controlador;

import Modelo.ModeloNuevaCartera;
import Vista.NuevaCartera;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ControladorNuevaCartera implements ActionListener, KeyListener {
    
    private NuevaCartera obj1;
    private ModeloNuevaCartera obj2 = new ModeloNuevaCartera();
    private JTextField txt1, txt2;
    private JComboBox cbx1;
    private JButton btn1;
    private ResultSet rss;
    private String valor1 = "", valor2 = "";
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorNuevaCartera(NuevaCartera obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.cbx1 = obj1.cbx1;
        this.btn1 = obj1.btn1;
        
        this.txt1.addKeyListener(this);
        this.txt2.addKeyListener(this);
        this.cbx1.addKeyListener(this);
        this.btn1.addActionListener(this);
        
        bloquearPegar();
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
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private void bloquearPegar() {
        InputMap map1 = txt1.getInputMap(txt1.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
        
        InputMap map2 = txt2.getInputMap(txt2.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
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
                            
                            String fec = null,
                                   intdeu = "1",
                                   intmor = "2",
                                   cob = cbx1.getSelectedItem().toString();
                            
                            rss = obj2.consultaIdeCobrador(cob);
                            try {
                                while (rss.next()) {
                                    cob = rss.getString("id_cobrador");
                                }
                            } catch(SQLException error){
                                System.out.println("Erroy de MySql"+error);
                            } catch(NumberFormatException error){
                                System.out.println("se presento el siguiente error "+error.getMessage());
                            }
                            
                            int dia = 0, mes = 0, año = 0;
                            Calendar calendario = new GregorianCalendar();
                            dia = (calendario.get(Calendar.DATE));
                            mes = (calendario.get(Calendar.MONTH))+1;
                            año = (calendario.get(Calendar.YEAR));

                            fec = año+"-"+mes+"-"+dia;
                                
                            int respuesta = obj2.insertarCartera(mon, cap, fec, intdeu, intmor, cob);
                                
                            if (respuesta==0) {
                                alerta("Ha ocurrido un error al Registar una nueva Cartera, Vuelve a intertarlo");
                            } else {
                                txt1.setText("");
                                txt2.setText("");
                                cbx1.setSelectedItem("Seleccione");
                                
                                alerta("Registro Exitoso!");
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
