
package Controlador;

import Modelo.ModeloNuevoCobrador;
import Vista.NuevoCobrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControladorNuevoCobrador implements ActionListener, KeyListener {
    
    private NuevoCobrador obj1;
    private ModeloNuevoCobrador obj2 = new ModeloNuevoCobrador();
    private JTextField txt1, txt2, txt3, txt4, txt5;
    private JButton btn1;
    private ResultSet rss;
    
    public ControladorNuevoCobrador(NuevoCobrador obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.btn1 = obj1.btn1;
        
        this.txt1.addKeyListener(this);
        this.txt2.addKeyListener(this);
        this.txt3.addKeyListener(this);
        this.txt4.addKeyListener(this);
        this.txt5.addKeyListener(this);
        this.btn1.addActionListener(this);
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private int verificarUsuario() {
        int respuesta = 0;
        String id = txt2.getText();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
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
                            } else {
                                int respuesta = verificarUsuario();

                                if (respuesta==0) {
                                    if (ban==true) {
                                        alerta("Cobrador a registrar ya existente, "+
                                               "Por favor vuelva a intertarlo");
                                        ban = false;
                                    }
                                }
                            }
            
            if (ban==true) {
                String nom = txt1.getText(),
                ide = txt2.getText(),
                bar = txt3.getText(),
                dir = txt4.getText(),
                tel = txt5.getText(),
                est = "1";
                                
                int respuesta = obj2.insertarCobrador(ide, nom, tel, dir, bar, est);
                                
                if (respuesta==0) {
                    alerta("Ha ocurrido un error al registar un nuevo Cobrador, Vuelve a intertarlo");
                } else {
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                                    
                    alerta("Registro Exitoso!");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource()==txt1) {
            if (txt1.getText().length()==64) {
                e.consume();
            }
        }
            else if (e.getSource()==txt2) {
                if (txt2.getText().length()==32) {
                    e.consume();
                }
            }
                else if (e.getSource()==txt3) {
                    if (txt3.getText().length()==64) {
                        e.consume();
                    }
                }
                    else if (e.getSource()==txt4) {
                        if (txt4.getText().length()==32) {
                            e.consume();
                        }
                    }
                        else if (e.getSource()==txt5) {
                            if (txt5.getText().length()==16) {
                                e.consume();
                            }
                        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}