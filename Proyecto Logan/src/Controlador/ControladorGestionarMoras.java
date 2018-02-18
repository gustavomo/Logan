
package Controlador;

import Modelo.ModeloGestionarMoras;
import Vista.GestionarMoras;
import Vista.Principal;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ControladorGestionarMoras implements ActionListener, KeyListener, ItemListener, PropertyChangeListener {
    
    private GestionarMoras obj1;
    private ModeloGestionarMoras obj2 = new ModeloGestionarMoras();
    private JTextField txt1, txt2;
    private JComboBox cbx1;
    private JDateChooser jdc1;
    private JTable jtb1;
    private JMenuItem jmi1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private DecimalFormat formato = new DecimalFormat("###,###.##");

    public ControladorGestionarMoras(GestionarMoras obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.cbx1 = obj1.cbx1;
        this.jdc1 = obj1.jdc1;
        this.jtb1 = obj1.jtb1;
        this.jmi1 = obj1.jmi1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.txt2.setEditable(false);
        
        txt1.addKeyListener(this);
        cbx1.addItemListener(this);
        jdc1.addPropertyChangeListener(this);
        jmi1.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
        modtab = (DefaultTableModel) jtb1.getModel();
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
    }

    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void cargarCarteras() {
        cbx1.addItem("Seleccione");
        
        rss = obj2.consultaCarteras();
        try {
            while (rss.next()){
                cbx1.addItem("Cartera "+rss.getString("id_cartera"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    private String asignarValor(long valor) {
        String resultado = null;
        resultado = formato.format(valor);
        
        return resultado;
    }
    
    private void vaciarTabla(){
        for (int i = 0; i < jtb1.getRowCount(); i++) {
            if (modtab!=null) {
               modtab.removeRow(i);
               i-=1; 
            }
        }
    }
    
     public void CargarMoras(int valor1, String valor2, int valor3) {
        vaciarTabla();
        
        String fecha = null;
        int dia, mes, año;
        Calendar calendario = new GregorianCalendar();
        if (valor3==1) {
            if (jdc1.getDate()!=null) {
                calendario = jdc1.getCalendar();
                dia = calendario.get(calendario.DATE);
                mes = calendario.get(calendario.MONTH)+1;
                año = calendario.get(calendario.YEAR);
            } else {
                dia = calendario.get(calendario.DATE);
                mes = calendario.get(calendario.MONTH)+1;
                año = calendario.get(calendario.YEAR);
            }
            fecha = año+"-"+mes+"-"+dia;
        } else {
            if (jdc1.getDate()!=null) {
                calendario = jdc1.getCalendar();
                dia = calendario.get(calendario.DATE);
                mes = calendario.get(calendario.MONTH)+1;
                año = calendario.get(calendario.YEAR);
                fecha = año+"-"+mes+"-"+dia;
            }
        }
        
        Object[] fila = new Object[5];
        
        if (valor1==1) {
            rss = obj2.consultaMoras(fecha);
        }
        else if(valor1==2) {
            rss = obj2.consultaMorasCliente(fecha, valor2);
        }
        else {
            rss = obj2.consultaMorasCartera(fecha, valor2);
        }
        
        try {
            while (rss.next()){
                String fecha1 = rss.getString("m.fecha_mora");
                String[] fecha2 = fecha1.split("-");
                
                fila[0] = rss.getString("c.id_cliente");
                fila[1] = rss.getString("c.nombre");         
                fila[2] = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0];
                fila[3] = rss.getString("e.descripcion");
                fila[4] = false;
                
                modtab.addRow(fila);
                jtb1.setModel(modtab);
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn1) {
            if(jtb1.getRowCount()!=0){
                if (btn1.getText().equals("Seleccionar Todos")) {
                    for (int i = 0; i < jtb1.getRowCount(); i++) {
                        jtb1.setValueAt(true, i, 4);
                    }
                    btn1.setText("Deseleccionar Todos");
                } else {
                    for (int i = 0; i < jtb1.getRowCount(); i++) {
                        jtb1.setValueAt(false, i, 4);
                    }
                    btn1.setText("Seleccionar Todos");
                }
            }
        } else {
            if (jtb1.getRowCount()!=0) {
                boolean check = false;
                boolean cambio = false;
                
                for (int i = 0; i < jtb1.getRowCount(); i++) {
                    cambio = (boolean) jtb1.getValueAt(i, 4);
                    
                    if (cambio==true) {
                        check  = true;
                        
                        Calendar calendario = new GregorianCalendar();
                        int dia, mes, año;
                        if (jdc1.getDate()!=null) {
                            calendario = jdc1.getCalendar();
                            dia = calendario.get(calendario.DATE);
                            mes = calendario.get(calendario.MONTH)+1;
                            año = calendario.get(calendario.YEAR);
                        } else {
                            dia = calendario.get(calendario.DATE);
                            mes = calendario.get(calendario.MONTH)+1;
                            año = calendario.get(calendario.YEAR);
                        }
                        
                        String id = (String) jtb1.getValueAt(i, 0);
                        String fecha = año+"-"+mes+"-"+dia;
                        String estado1 = (String) jtb1.getValueAt(i, 3);
                        int estado2 = 0;
                        if(estado1.equals("Activo")) {
                            estado2 = 2;
                        } else {
                            estado2 = 1;
                        }
                        String iddeu = null;
                        rss = obj2.ConsultarIdDeuda(id);
                        try {
                            while (rss.next()){
                                iddeu = rss.getString("id");
                            }                
                        } catch(SQLException error){
                            System.out.println("Erroy de MySql"+error);
                        } catch(NumberFormatException error){
                            System.out.println("se presento el siguiente error "+error.getMessage());
                        }
                        
                        int respuesta1 = obj2.actualizarMora(iddeu, fecha, estado2);
                        
                        int cuovie = 0, cuoact1 = 0, cuoact2 = 0;
                        long monvie = 0, monact1 = 0, monact2 = 0, monmor = 0;
                        String propag = null, idmov = null;
                        
                        rss = obj2.ConsultarDatosMovimientos(iddeu, fecha);
                        try {
                            while (rss.next()){
                                cuoact1 = rss.getInt("cuota_actual");
                                monact1 = rss.getLong("monto_actual");
                                monmor = rss.getLong("monto_mora");
                                propag = rss.getString("proximo_pago");
                            }                
                        } catch(SQLException error){
                            System.out.println("Erroy de MySql"+error);
                        } catch(NumberFormatException error){
                            System.out.println("se presento el siguiente error "+error.getMessage());
                        }
                        
                        if(estado1.equals("Activo")) {
                            idmov = "6";
                            cuovie = cuoact1;
                            cuoact2 = cuoact1-1;
                            monvie = monact1;
                            monact2 = monact1-monmor;
                            
                            if (monact2>=0 && cuoact2<=0) {
                                cuoact2 = 1;
                            }
                                else if(monact2<=0) {
                                    monact2 = 0;
                                    cuoact2 = 0;
                                } 
                        } else {
                            idmov = "5";
                            cuovie = cuoact1;
                            cuoact2 = cuoact1+1;
                            monvie = monact1;
                            monact2 = monact1+monmor;
                            obj2.actualizarDeuda2(iddeu);
                        }
                        
                        int respuesta2 = obj2.registrarMovimiento(cuovie, cuoact2, monvie, monact2, propag, idmov, iddeu);
                        
                        if (monact2<=0) {
                            obj2.actualizarDeuda1(iddeu);
                        }
                        
                        boolean val = false; 
                        if (respuesta1==0) {
                            val = true;
                        } 
                            else if (respuesta2==0) {
                                val = true;
                            }
                        
                        if (val==true) {
                            alerta("Ha ocurrido un error al Modificar una Mora, Vuelve a intertarlo");
                        } else {
                            vaciarTabla();
                            CargarMoras(1, "", 1);
                            txt1.setText("");
                            cbx1.setSelectedItem("Seleccione");
                            
                            alerta("Se realizaron los cambios con Exito!");
                        }
                    }   
                }
                if (check==false) {
                    alerta("No ha checkeado ninguna Deuda, Por favor vuelve a intertarlo");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource()==txt1) {
            if (txt1.getText().length()==32) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txt1) {
            vaciarTabla();
            cbx1.setSelectedItem("Seleccione");
            
            String id = txt1.getText();
            CargarMoras(2, id, 1);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor1 = null;

        if (cbx1.getItemCount()!=0) {
            valor1 = cbx1.getSelectedItem().toString();
            
            if (valor1.equals("Seleccione")) {
                if (txt1.getText().isEmpty() && txt1.getText().equals("")) {
                    CargarMoras(1, "", 1);
                }
            } else {
                vaciarTabla();
                txt1.setText("");

                String valor2 = cbx1.getSelectedItem().toString();
                valor2 = valor2.replace("Cartera ", "");       

                CargarMoras(3, valor2, 1);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        vaciarTabla();
        
        if (!txt1.getText().equals("") && !txt1.getText().equals(" ") && !txt1.getText().isEmpty()) {
            String id = txt1.getText();
            CargarMoras(2, id, 2);
        } 
            else if(!cbx1.getSelectedItem().equals("Seleccione")) {
                String valor2 = cbx1.getSelectedItem().toString();
                valor2 = valor2.replace("Cartera ", "");       

                CargarMoras(3, valor2, 2);
            }
                else {
                    CargarMoras(1, "", 2);
                }
    }
}
