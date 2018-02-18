
package Controlador;

import Vista.AgregarAbono;
import Vista.NuevoPago;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class ControladorAgregarAbono implements ActionListener, KeyListener {
    
    private AgregarAbono obj1;
    private JTextField txt1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private String valor1 = "";
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    private static DefaultTableModel modtab;
    
    public ControladorAgregarAbono(AgregarAbono obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        txt1.addKeyListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
        bloquearPegar();
    }
    
    public static DefaultTableModel getModtab() {
        return modtab;
    }

    public static void setModtab(DefaultTableModel modtab) {
        ControladorAgregarAbono.modtab = modtab;
    }
    
    private void bloquearPegar() {
        InputMap map = txt1.getInputMap(txt1.WHEN_FOCUSED);
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
        } else {
            NuevoPago obj1 = new NuevoPago();
            ControladorNuevoPago obj2 = new ControladorNuevoPago(obj1);
            
            if (modtab.getRowCount()!=0) {
                if (txt1.getText()!=null && !txt1.getText().equals("") && !txt1.getText().equals("")) {
                    String valor = txt1.getText();
                    modtab.setValueAt(valor, ControladorNuevoPago.getFila(), 3);
                    obj1.jtb1.setModel(modtab);
                } else {
                    String valor = txt1.getText();
                    modtab.setValueAt(valor, ControladorNuevoPago.getFila(), 3);
                    obj1.jtb1.setModel(modtab);
                }
            }
            this.obj1.setVisible(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char caracter = e.getKeyChar();
        
        if (e.getSource()==txt1) {
            String valor = txt1.getText();
            // Verificar si la tecla pulsada no es un digito
            if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                || (valor.length()>16) ) {
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