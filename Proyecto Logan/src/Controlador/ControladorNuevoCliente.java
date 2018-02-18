
package Controlador;

import Controlador.ContenedorClases.DatosNuevoCliente;
import Modelo.ModeloNuevoCliente;
import Vista.DatosCartera;
import Vista.NuevoCliente;
import Vista.Principal;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ControladorNuevoCliente implements ActionListener, KeyListener, ItemListener {
    
    private NuevoCliente obj1;
    private DatosCartera obj2 = new DatosCartera();
    private ControladorDatosCartera obj2obj3 = new ControladorDatosCartera(obj2);
    private ModeloNuevoCliente obj4 = new ModeloNuevoCliente();
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10,
                       txt11, txt12, txt13, txt14, txt15, txt16, txt17;
    private JComboBox cbx1;
    private JCheckBox ccb1;
    private JDateChooser jdc1;
    private JButton btn1;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    public static boolean ban = false;
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorNuevoCliente(NuevoCliente obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.txt6 = obj1.txt6;
        this.txt7 = obj1.txt7;
        this.txt8 = obj1.txt8;
        this.txt9 = obj1.txt9;
        this.txt10 = obj1.txt10;
        this.txt11 = obj1.txt11;
        this.txt12 = obj1.txt12;
        this.txt13 = obj1.txt13;
        this.txt14 = obj1.txt14;
        this.txt15 = obj1.txt15;
        this.txt16 = obj1.txt16;
        this.txt17 = obj1.txt17;
        this.cbx1 = obj1.cbx1;
        this.ccb1 = obj1.ccb1;
        this.jdc1 = obj1.jdc1;
        this.btn1 = obj1.btn1;
        
        this.txt12.setEditable(false);
        this.txt13.setEditable(false);
        this.txt14.setEditable(false);
        this.txt15.setEditable(false);
        this.txt16.setEditable(false);
        this.txt17.setEditable(false);
        
        this.txt1.addKeyListener(this);
        this.txt2.addKeyListener(this);
        this.txt3.addKeyListener(this);
        this.txt4.addKeyListener(this);
        this.txt5.addKeyListener(this);
        this.txt6.addKeyListener(this);
        this.txt7.addKeyListener(this);
        this.txt8.addKeyListener(this);
        this.txt9.addKeyListener(this);
        this.txt10.addKeyListener(this);
        this.txt11.addKeyListener(this);
        this.txt12.addKeyListener(this);
        this.cbx1.addItemListener(this);
        this.ccb1.addActionListener(this);
        this.btn1.addActionListener(this);
        
        cargarForm();
        bloquearPegar();
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
    
    public void cargarModalidades() {
        cbx1.addItem("Seleccione");
        
        rss = obj4.consultaModalidad();
        try {
            while (rss.next()){
                cbx1.addItem(rss.getString("descripcion"));
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    public void cargarTazint() {
        rss = obj4.consultaInteres();
        try {
            while (rss.next()){
                txt13.setText(rss.getString("taza_interes")+"%");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
    }
    
    private void cargarForm() {
        String valor1, valor2, valor3, valor4, valor5, valor6;
        valor1 = String.valueOf(formato.format(DatosNuevoCliente.getMonpre()) );
        valor2 = String.valueOf(formato.format(DatosNuevoCliente.getCuo()) );
        valor3 = String.valueOf(formato.format(DatosNuevoCliente.getMoncuo()) );
        valor4 = String.valueOf(formato.format(DatosNuevoCliente.getMonint()) );
        valor5 = String.valueOf(formato.format(DatosNuevoCliente.getMonpag()) );
        valor6 = String.valueOf(DatosNuevoCliente.getCancuo());
        
        txt1.setText(DatosNuevoCliente.getNom());
        txt2.setText(DatosNuevoCliente.getId());
        txt3.setText(DatosNuevoCliente.getBar());
        txt4.setText(DatosNuevoCliente.getDir());
        txt5.setText(DatosNuevoCliente.getTel());
        txt6.setText(DatosNuevoCliente.getNomref());
        txt7.setText(DatosNuevoCliente.getIdref());
        txt8.setText(DatosNuevoCliente.getBarref());
        txt9.setText(DatosNuevoCliente.getDirref());
        txt10.setText(DatosNuevoCliente.getTelref());
        if (DatosNuevoCliente.getNom()!=null) {
            txt11.setText(valor1);
            txt12.setText(valor2);
            txt14.setText(valor3);
            txt15.setText(valor4);
            txt16.setText(valor5);
            
            int dia = DatosNuevoCliente.getDia();
            int mes = DatosNuevoCliente.getMes();
            int año = DatosNuevoCliente.getAño();
            String vdia = String.valueOf(dia);
            if (vdia.length()==1) {
                vdia = "0"+dia;
            } else {
                vdia = String.valueOf(dia);
            }
            String vmes = String.valueOf(mes);
            if (vmes.length()==1) {
                vmes = "0"+mes;
            } else {
                vmes = String.valueOf(mes);
            }
            
            txt17.setText(vdia+"/"+vmes+"/"+año);
        } else {
            if (DatosNuevoCliente.getDia()!=0) {
                int dia = DatosNuevoCliente.getDia();
                int mes = DatosNuevoCliente.getMes();
                int año = DatosNuevoCliente.getAño();
                String vdia = String.valueOf(dia);
                if (vdia.length()==1) {
                    vdia = "0"+dia;
                } else {
                    vdia = String.valueOf(dia);
                }
                String vmes = String.valueOf(mes);
                if (vmes.length()==1) {
                    vmes = "0"+mes;
                } else {
                    vmes = String.valueOf(mes);
                }

                txt17.setText(vdia+"/"+vmes+"/"+año);
            }
        }
        txt12.setText(valor6);
        int valor7 = DatosNuevoCliente.getCcb();
        if (valor7==1) {
            txt12.setEditable(true);
            ccb1.setSelected(true);
            ban = true;
        } else{
            txt12.setEditable(false);
            ccb1.setSelected(false);
            ban = false;
        }
    }
    
    private void cargarDatos() {
        long valor1, valor2, valor3, valor4 ,valor5;
        valor1 = Long.parseLong(txt11.getText().replace(".","").replace(",",""));
        valor2 = Long.parseLong(txt12.getText().replace(".","").replace(",",""));
        valor3 = Long.parseLong(txt14.getText().replace(".","").replace(",",""));
        valor4 = Long.parseLong(txt15.getText().replace(".","").replace(",",""));
        valor5 = Long.parseLong(txt16.getText().replace(".","").replace(",",""));
        int valor6 = Integer.valueOf(txt12.getText());
        
        DatosNuevoCliente.setNom(txt1.getText());
        DatosNuevoCliente.setId(txt2.getText());
        DatosNuevoCliente.setBar(txt3.getText());
        DatosNuevoCliente.setDir(txt4.getText());
        DatosNuevoCliente.setTel(txt5.getText());
        DatosNuevoCliente.setNomref(txt6.getText());
        DatosNuevoCliente.setIdref(txt7.getText());
        DatosNuevoCliente.setBarref(txt8.getText());
        DatosNuevoCliente.setDirref(txt9.getText());
        DatosNuevoCliente.setTelref(txt10.getText());
        DatosNuevoCliente.setMonpre(valor1);
        DatosNuevoCliente.setMol((String) cbx1.getSelectedItem());
        DatosNuevoCliente.setCuo(valor2);
        DatosNuevoCliente.setMoncuo(valor3);
        DatosNuevoCliente.setMonint(valor4);
        DatosNuevoCliente.setMonpag(valor5);
        Calendar calendario = new GregorianCalendar();
        if (jdc1.getDate()!=null) {
            calendario = jdc1.getCalendar();
            int dia = calendario.get(calendario.DATE);
            int mes = calendario.get(calendario.MONTH)+1;
            int año = calendario.get(calendario.YEAR);
            DatosNuevoCliente.setDia(dia);
            DatosNuevoCliente.setMes(mes);
            DatosNuevoCliente.setAño(año);
            DatosNuevoCliente.setCalendario(calendario);
        } else {
            int dia = calendario.get(calendario.DATE);
            int mes = calendario.get(calendario.MONTH)+1;
            int año = calendario.get(calendario.YEAR);
            DatosNuevoCliente.setDia(dia);
            DatosNuevoCliente.setMes(mes);
            DatosNuevoCliente.setAño(año);
            DatosNuevoCliente.setCalendario(calendario);
        }
        DatosNuevoCliente.setCancuo(valor6);
        if (ccb1.isSelected()) {
            DatosNuevoCliente.setCcb(1);
        } else {
            DatosNuevoCliente.setCcb(0);
        }
    }
    
    private void alerta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    private int verificarUsuario() {
        int respuesta = 0;
        String id = txt2.getText();
        String valor = null;
        
        rss = obj4.consultaCliente(id);
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
    
    private void bloquearPegar() {
        InputMap map1 = txt11.getInputMap(txt11.WHEN_FOCUSED);
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
        InputMap map2 = txt12.getInputMap(txt12.WHEN_FOCUSED);
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
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
                                                    else if (txt11.getText().isEmpty()) {
                                                        if (ban==true) {
                                                            alerta("Por favor llene el campo de Monto Prestamo");
                                                            ban = false;
                                                        }
                                                    }
                                                        else if (cbx1.getSelectedItem().equals("Seleccione")) {
                                                            if (ban==true) {
                                                                alerta("Por favor selecione una Modalidad");
                                                                ban = false;
                                                            }
                                                        }
                                                            else if(txt12.getText().equals("") || txt12.getText().equals("0")) {
                                                                if (ban==true) {
                                                                    if (txt12.getText().equals("")) {
                                                                        alerta("Por favor llene el campo Cuotas");
                                                                    } else {
                                                                        alerta("No puede dejar campo Cuotas con el valor \"0\"");
                                                                    }
                                                                    
                                                                    ban = false;
                                                                }
                                                            }
                                                            else if(jdc1.getDate()!=null) {
                                                                Calendar calendario1 = new GregorianCalendar();
                                                                calendario1 = jdc1.getCalendar();
                                                                int dia1 = calendario1.get(calendario1.DATE);
                                                                int mes1 = calendario1.get(calendario1.MONTH)+1;
                                                                int año1 = calendario1.get(calendario1.YEAR);
                                                                
                                                                Calendar calendario2 = new GregorianCalendar();
                                                                int dia2 = (calendario2.get(Calendar.DATE));
                                                                int mes2 = (calendario2.get(Calendar.MONTH))+1;
                                                                int año2 = (calendario2.get(Calendar.YEAR));
                                                                
                                                                int diamax1 = calendario1.getActualMaximum(Calendar.DAY_OF_MONTH);
                                                                int diamax2 = calendario2.getActualMaximum(Calendar.DAY_OF_MONTH);
                                                                
                                                                /*if ( (año1<año2) ||
                                                                     (año1==año2 && mes1<mes2) ||
                                                                     (año1==año2 && mes1==mes2 && dia1<dia2) ||
                                                                     (año1==año2 && mes1==mes2 && dia1-dia2>15) ||
                                                                     ((año1==año2) && (mes1-mes2>=1) && ((diamax2-dia2)+(diamax1-dia1)>15)) ||
                                                                     ((año1-año2>=1) && (mes1<mes2) && ((diamax2-dia2)+(diamax1-dia1)>15)) ) {
                                                                    if (ban=true) {
                                                                        alerta("La fecha que ha seleccionado es incorrecta, "+
                                                                               "Por favor vuelva a intertarlo");
                                                                        ban = false;
                                                                    }
                                                                }*/
                                                                
                                                                int diasemana = calendario1.get(calendario1.DAY_OF_WEEK);
                                                                
                                                                if(diasemana==1 || diasemana==7) {
                                                                    if (ban=true) {
                                                                        alerta("La Fecha que ha seleccionado es incorrecta "
                                                                              +"no puede seleccionar \nuna Fecha que sea Sábado o Domingo, "
                                                                              +"Por favor vuelva a intertarlo");
                                                                        ban = false;
                                                                    }
                                                                }
                                                            }
                                                                else {
                                                                    int respuesta = verificarUsuario();

                                                                    if (respuesta==0) {
                                                                        if (ban==true) {
                                                                            alerta("Cliente a registrar ya existente, "+
                                                                                   "Por favor vuelva a intertarlo");
                                                                            ban = false;
                                                                        }
                                                                    }
                                                                }
            
            if (ban==true) {
                obj2obj3.setRecibir(1);
                JButton boton = obj2obj3.getBtn2();
                boton.setText("Registrar Prestamo");
                obj2obj3.setBtn2(boton);
                posicinarFrame(obj2);
                cargarDatos();
                obj2.cbx1.removeAllItems();
                obj2obj3.cargarCarteras();
            }
        } else {
            if (ccb1.isSelected()==true) {
                txt12.setEditable(true);
                ban = true;
            } else{
                txt12.setEditable(false);
                ban = false;
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
                            else if (e.getSource()==txt6) {
                                if (txt6.getText().length()==64) {
                                    e.consume();
                                }
                            }
                                else if (e.getSource()==txt7) {
                                    if (txt7.getText().length()==32) {
                                        e.consume();
                                    }
                                }
                                    else if (e.getSource()==txt8) {
                                        if (txt8.getText().length()==64) {
                                            e.consume();
                                        }
                                    }
                                        else if (e.getSource()==txt9) {
                                            if (txt9.getText().length()==32) {
                                                e.consume();
                                            }
                                        }
                                            else if (e.getSource()==txt10) {
                                                if (txt10.getText().length()==16) {
                                                    e.consume();
                                                }
                                            }
                                                else if (e.getSource()==txt11) {
                                                    char caracter = e.getKeyChar();

                                                    // Verificar si la tecla pulsada no es un digito
                                                    if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                                                        || (txt11.getText().length()>16) ) {
                                                        e.consume();  // ignorar el evento de teclado
                                                    }
                                                }
                                                    else {
                                                        char caracter = e.getKeyChar();

                                                        // Verificar si la tecla pulsada no es un digito
                                                        if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                                                            || (txt12.getText().length()>1) ) {
                                                            e.consume();  // ignorar el evento de teclado
                                                        }
                                                    } 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource()==txt11) {
            if (!txt11.getText().isEmpty() && !txt11.getText().equals("") && !txt11.getText().equals(" ")) {
                long valor = Long.valueOf( txt11.getText().replace(".", "").replace(",", "") );
                txt11.setText( formato.format(valor) );
            }
            
            if (!txt11.getText().isEmpty() && !txt12.getText().isEmpty()) {
                long num1 = Long.parseLong(txt11.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt12.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt13.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;
                
                if (num2!=0) {
                    long moncuo = monpag/num2;
                    
                    String res1 = String.valueOf(formato.format(moncuo));
                    txt14.setText(res1);
                    
                    String res2 = String.valueOf(formato.format(monint));
                    txt15.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt16.setText(res3);
                }
            } else {
                txt14.setText("0");
                txt15.setText("0");
                txt16.setText("0");
            }
        } else if(e.getSource()==txt12) {
            if (!txt11.getText().isEmpty() && !txt12.getText().isEmpty()) {
                long num1 = Long.parseLong(txt11.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt12.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt13.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;
                
                if (num2!=0) {
                    long moncuo = monpag/num2;
                    
                    String res1 = String.valueOf(formato.format(moncuo));
                    txt14.setText(res1);
                    
                    String res2 = String.valueOf(formato.format(monint));
                    txt15.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt16.setText(res3);
                }
            } else {
                txt14.setText("0");
                txt15.setText("0");
                txt16.setText("0");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String valor = null;

        if (cbx1.getItemCount()!=0) {
            valor = cbx1.getSelectedItem().toString();
        }
        
        if (valor!=null && ban==false) {
            if (!valor.equals("Seleccione")) {
                rss = obj4.consultaCantidadCoutas(valor);
                try {
                    while (rss.next()){
                        txt12.setText(rss.getString("cantidad_cuotas"));
                    }                
                } catch(SQLException error){
                    System.out.println("Erroy de MySql"+error);
                } catch(NumberFormatException error){
                    System.out.println("se presento el siguiente error "+error.getMessage());
                }
            } else {
                txt12.setText("0");
                txt14.setText("0");
                txt15.setText("0");
                txt16.setText("0");
            }
            
            if (!txt11.getText().isEmpty() && !txt12.getText().isEmpty()) {
                long num1 = Long.parseLong(txt11.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt12.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt13.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;

                if (num2!=0) {
                    long moncuo = monpag/num2;

                    String res1 = String.valueOf(formato.format(moncuo));
                    txt14.setText(res1);

                    String res2 = String.valueOf(formato.format(monint));
                    txt15.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt16.setText(res3);
                }
            }
                else if (!txt11.getText().isEmpty() && txt12.getText().isEmpty()) {
                    txt14.setText("0");
                    txt15.setText("0");
                    txt16.setText("0");
                }
                    else if (txt11.getText().isEmpty() && !txt12.getText().isEmpty()) {
                        txt14.setText("0");
                        txt15.setText("0");
                        txt16.setText("0");
                    }
        }
    }
}