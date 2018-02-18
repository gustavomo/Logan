
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloNuevaDeuda;
import Vista.GestionarClientes;
import Vista.NuevaDeuda;
import Vista.Principal;
import Vista.VerDeudas;
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
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ControladorNuevaDeuda implements ActionListener, KeyListener, ItemListener {
    
    private NuevaDeuda obj1;
    private ModeloNuevaDeuda obj2 = new ModeloNuevaDeuda();
    private JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;
    private JComboBox cbx1;
    private JCheckBox ccb1;
    private JDateChooser jdc1;
    private JButton btn1, btn2, btn3;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private boolean ban = false;
    private DecimalFormat formato = new DecimalFormat("###,###.##");

    public ControladorNuevaDeuda(NuevaDeuda obj1) {
        this.obj1 = obj1;
        this.txt1 = obj1.txt1;
        this.txt2 = obj1.txt2;
        this.txt3 = obj1.txt3;
        this.txt4 = obj1.txt4;
        this.txt5 = obj1.txt5;
        this.txt6 = obj1.txt6;
        this.txt7 = obj1.txt7;
        this.cbx1 = obj1.cbx1;
        this.ccb1 = obj1.ccb1;
        this.jdc1 = obj1.jdc1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        this.btn3 = obj1.btn3;
        
        this.txt2.setEditable(false);
        this.txt3.setEditable(false);
        this.txt4.setEditable(false);
        this.txt5.setEditable(false);
        this.txt6.setEditable(false);
        this.txt7.setEditable(false);
        
        this.txt1.addKeyListener(this);
        this.txt2.addKeyListener(this);
        this.cbx1.addItemListener(this);
        this.ccb1.addActionListener(this);
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        this.btn3.addActionListener(this);
        
        bloquearPegar();
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
    
    public void cargarModalidades() {
        cbx1.addItem("Seleccione");
        
        rss = obj2.consultaModalidad();
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
        rss = obj2.consultaInteres();
        try {
            while (rss.next()){
                txt3.setText(rss.getString("taza_interes")+"%");
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
    
    private int insertarDeuda() {
        long monpre, monint;
        String fecini = null, idmol = null, idestcue, idcli;
        monpre = Long.valueOf(txt1.getText().replace(".","").replace(",",""));
        monint = Long.valueOf(txt6.getText().replace(".","").replace(",",""));
        int cancou = Integer.valueOf(txt2.getText());
        
        String des = cbx1.getSelectedItem().toString();
        rss = obj2.consultaIdModalidad(des);
        try {
            while (rss.next()){
                idmol = rss.getString("id_modalidad");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        if (jdc1.getDate()!=null) {
            int dia = 0, mes = 0, año = 0;
            Calendar calendario = jdc1.getCalendar();
            
            dia = (calendario.get(Calendar.DATE));
            mes = (calendario.get(Calendar.MONTH))+1;
            año = (calendario.get(Calendar.YEAR));
            
            fecini = año+"-"+mes+"-"+dia;
        } else {
            Calendar calendario = new GregorianCalendar();
            int dia = (calendario.get(Calendar.DATE));
            int mes = (calendario.get(Calendar.MONTH))+1;
            int año = (calendario.get(Calendar.YEAR));
            
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
            
            fecini = año+"-"+mes+"-"+dia;
        }
        
        idestcue = "1";
        idcli = DatosGestionarClientes.getId();
        
        int respuesta = obj2.registrarDueda(monpre, monint, cancou, fecini, idmol,
                                            idestcue, idcli);
        
        return respuesta;
    }
    
    private Calendar ajustarfecha1(int vala, int dia, int diaa, int mesa, int año,
                                   int diamax, Calendar calendario) {
        diaa = dia+vala;
        if(diaa>diamax) {
            if (mesa>11) {
                calendario.set(calendario.MONTH, 0);
                calendario.set(calendario.YEAR, año+1);
            } else {
                calendario.set(calendario.MONTH, mesa);
            }
            diaa = vala-(diamax-dia);
            calendario.set(calendario.DATE, diaa);
        } else {
            calendario.set(calendario.DATE, diaa);
        }
        
        return calendario;
    }
    
    private Calendar ajustarfecha2(int vala, int diaa1, int diaa2, int mesa, int añoa,
                                   int diamax, Calendar calendario) {
        diaa2 = diaa1+vala;
        if(diaa2>diamax) {
            if (mesa>11) {
                calendario.set(calendario.MONTH, 0);
                calendario.set(calendario.YEAR, añoa);
            } else {
                calendario.set(calendario.MONTH, mesa);
            }
            diaa2 = vala-(diamax-diaa1);
            calendario.set(calendario.DATE, diaa2);
        } else {
            calendario.set(calendario.DATE, diaa2);
        }
        
        return calendario;
    }
    
    private int insertarMovimiento() {
        int cuovie, cuoact;
        long monvie, monact;
        String propag = null, idmov, iddeu = null;
        
        cuovie = 0;
        cuoact = Integer.valueOf(txt2.getText());
        monvie = 0;
        monact = Long.valueOf(txt7.getText().replace(".","").replace(",",""));
        
        int dia = 0, mes = 0, año = 0;
        if (jdc1.getDate()!=null) {
            Calendar calendario = jdc1.getCalendar();
            dia = (calendario.get(Calendar.DATE));
            mes = (calendario.get(Calendar.MONTH));
            año = (calendario.get(Calendar.YEAR));
        } else {
            Calendar calendario = new GregorianCalendar();
            dia = (calendario.get(Calendar.DATE));
            mes = (calendario.get(Calendar.MONTH));
            año = (calendario.get(Calendar.YEAR));
        }
        
        Calendar calendario;
        if (jdc1.getDate()!=null) {
            calendario = jdc1.getCalendar();
        } else {
            calendario = new GregorianCalendar();
        }
        
        int diamax1 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mol = cbx1.getSelectedItem().toString();
        int diaa1 = 0, mesa1 = 0;
        mesa1 = mes+1;
        int diasemana = (calendario.get(Calendar.DAY_OF_WEEK));
        
        if(mol.equals("Diario")) {
            ajustarfecha1(1, dia, diaa1, mesa1, año, diamax1, calendario);
        }
            else if(mol.equals("Semanal")) {
                ajustarfecha1(8, dia, diaa1, mesa1, año, diamax1, calendario);
            }
                else if(mol.equals("Quincenal")) {
                    ajustarfecha1(15, dia, diaa1, mesa1, año, diamax1, calendario);
                }
                    else {
                        diaa1 = dia+30;
                        int diamax2 = 0;
                        if(diaa1>diamax1) {
                            diaa1 = 30-(diamax1-dia);
                            if (mesa1>11) {
                                calendario.set(calendario.MONTH, 0);
                                calendario.set(calendario.YEAR, año+1);
                            } else {
                                calendario.set(calendario.DATE, 1);
                                calendario.set(calendario.MONTH, mesa1);
                                diamax2 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
                                
                                if (diaa1>diamax2) {
                                    diaa1 = diaa1-diamax2;
                                    mesa1 = mesa1+1; 
                                    calendario.set(calendario.MONTH, mesa1);
                                }
                            }
                            
                            calendario.set(calendario.DATE, diaa1);
                        } else {
                            calendario.set(calendario.DATE, diaa1);
                        }
                    }
        
        int diaa2 = 0;
        int mesa2 = calendario.get(calendario.MONTH)+1;
        int añoa2 = calendario.get(calendario.YEAR)+1;
        int diamax3 = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if(mol.equals("Diario") && diasemana==6) {
            calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
        } else if(mol.equals("Diario") && diasemana==7) {
            calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
        }
            if(mol.equals("Semanal") && diasemana==6) {
                calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
            } else if(mol.equals("Semanal") && diasemana==7) {
                calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
            }
                if(mol.equals("Quicenal") && diasemana==6) {
                    calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                } else if(mol.equals("Quincenal") && diasemana==7) {
                    calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                }
                    if(mol.equals("Mensual") && diasemana==5) {
                        calendario = ajustarfecha2(2, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                    } else if(mol.equals("Mensual") && diasemana==6) {
                        calendario = ajustarfecha2(1, calendario.get(calendario.DATE), diaa2, mesa2, añoa2, diamax3, calendario);
                    }
                    
        propag = calendario.get(calendario.YEAR)+"-"+
                 (calendario.get(calendario.MONTH)+1)+"-"+
                 calendario.get(calendario.DATE);
                    
        idmov = "1";
        
        String ide = DatosGestionarClientes.getId();
        rss = obj2.consultaIdDeuda(ide);
        try {
            while (rss.next()){
                iddeu = rss.getString("id");
            }                
        } catch(SQLException error){
            System.out.println("Erroy de MySql"+error);
        } catch(NumberFormatException error){
            System.out.println("se presento el siguiente error "+error.getMessage());
        }
        
        int resultado = obj2.registrarMovimiento(cuovie, cuoact, monvie, monact, propag, idmov, iddeu);
        
        return resultado;
    }
    
    private void restaurarCampos() {
        txt1.setText("");
        cbx1.setSelectedItem("Seleccione");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        
        Calendar calendario = new GregorianCalendar();
        int dia = (calendario.get(Calendar.DATE));
        int mes = (calendario.get(Calendar.MONTH))+1;
        int año = (calendario.get(Calendar.YEAR));
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
        txt5.setText(vdia+""+vmes+""+año);
        txt6.setText("");
        txt7.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerDeudas obj2 = new VerDeudas();
            ControladorVerDeudas obj2obj1 = new ControladorVerDeudas(obj2);
            obj2obj1.CargarDeudas();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
            else if(e.getSource()==btn2) {
                obj1.setVisible(false);
                Principal.jDesktopPane1.remove(obj1);
                GestionarClientes obj2 = new GestionarClientes();
                ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
                obj2.jPanel1.setBackground(fondo);
                obj2.jPanel2.setBackground(fondo);
                posicinarFrame(Principal.jDesktopPane1, obj2);
            }
                else if (e.getSource()==btn3) {
                    boolean ban = true;
                    
                    long valor = 0, monmax = 0, capini = 0, totpre = 0, totgas = 0, totpag = 0, saldo = 0;
                    
                    if (!txt1.getText().equals("") && !txt1.getText().equals("") && !txt1.getText().isEmpty()) {
                        valor = Long.valueOf(txt1.getText().replace(".","").replace(",",""));
                    }
                    
                    
                    rss = obj2.consultaDatosCartera(DatosGestionarClientes.getId());
                    try {
                        while (rss.next()){
                            monmax = rss.getLong("ca.monto_maximo");
                            capini = rss.getLong("ca.capital_inicial");
                            totpre = rss.getLong("total_prestado");
                            totgas = rss.getLong("total_gastos");
                            totpag = rss.getLong("total_pagado");
                            saldo = (capini+totpag)-(totpre+totgas);
                        }                
                    } catch(SQLException error){
                        System.out.println("Erroy de MySql"+error);
                    } catch(NumberFormatException error){
                        System.out.println("se presento el siguiente error "+error.getMessage());
                    }
                    
                    if (txt1.getText().isEmpty()) {
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
                            else if(txt2.getText().equals("") || txt2.getText().equals("0")) {
                                if (ban==true) {
                                    if (txt2.getText().equals("")) {
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

                                    int diamax1 = calendario1.getMaximum(Calendar.DAY_OF_MONTH);
                                    int diamax2 = calendario2.getMaximum(Calendar.DAY_OF_MONTH);

                                    if ( (año1<año2) ||
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
                                    }
                                }
                                    else if (valor>monmax) {
                                        if (ban=true) {
                                            alerta("El Monto a Prestar supera el Máximo Monto a Prestar de "+formato.format(monmax)+",\n"
                                                  +"Por favor cambie el Valor del Monto a Prestar");
                                            ban = false;
                                        }
                                    }
                                        else if (valor>saldo) {
                                            if (ban=true) {
                                                alerta("El Monto a Prestar supera el Saldo de la Cartera de "+formato.format(saldo)+",\n"
                                                  +"Por favor cambie el Valor del Monto a Prestar");
                                                ban = false;
                                            }
                                        }
                    
                    if (ban==true) {
                        int respuesta1 = insertarDeuda();
                        int respuesta2 = insertarMovimiento();

                        boolean val = false; 

                        if (respuesta1==0) {
                            val = true;
                        } 
                            else if (respuesta2==0) {
                                val = true;
                            }

                        if (val==true) {
                            alerta("Ha ocurrido un error al registar una nueva Deuda, Vuelve a intertarlo");
                        } else {
                            restaurarCampos();
                            alerta("Registro Exitoso!");
                        }
                    }
                }
                    else {
                        if (ccb1.isSelected()==true) {
                            txt2.setEditable(true);
                            ban = true;
                        } else{
                            txt2.setEditable(false);
                            ban = false;
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
        } else {
            char caracter = e.getKeyChar();

            // Verificar si la tecla pulsada no es un digito
            if( (((caracter<'0') || (caracter>'9')) && (caracter!='\b' /*corresponde a BACK_SPACE*/))
                || (txt2.getText().length()>1) ) {
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
            
            if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty()) {
                long num1 = Long.parseLong(txt1.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt2.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt3.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;
                
                if (num2!=0) {
                    long moncuo = monpag/num2;
                    
                    String res1 = String.valueOf(formato.format(moncuo));
                    txt4.setText(res1);
                    
                    String res2 = String.valueOf(formato.format(monint));
                    txt6.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt7.setText(res3);
                }
            } else {
                txt4.setText("0");
                txt6.setText("0");
                txt7.setText("0");
            }
        } else if(e.getSource()==txt2) {
            if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty()) {
                long num1 = Long.parseLong(txt1.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt2.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt3.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;
                
                if (num2!=0) {
                    long moncuo = monpag/num2;
                    
                    String res1 = String.valueOf(formato.format(moncuo));
                    txt4.setText(res1);
                    
                    String res2 = String.valueOf(formato.format(monint));
                    txt6.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt7.setText(res3);
                }
            } else {
                txt4.setText("0");
                txt6.setText("0");
                txt7.setText("0");
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
                rss = obj2.consultaCantidadCoutas(valor);
                try {
                    while (rss.next()){
                        txt2.setText(rss.getString("cantidad_cuotas"));
                    }                
                } catch(SQLException error){
                    System.out.println("Erroy de MySql"+error);
                } catch(NumberFormatException error){
                    System.out.println("se presento el siguiente error "+error.getMessage());
                }
            } else {
                txt2.setText("0");
                txt4.setText("0");
                txt6.setText("0");
                txt7.setText("0");
            }
            
            if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty()) {
                long num1 = Long.parseLong(txt1.getText().replace(".","").replace(",",""));
                long num2 = Long.parseLong(txt2.getText().replace(".","").replace(",",""));
                int num3 = Integer.parseInt(txt3.getText().replace("%", ""));
                long monpag = num1*num3/100;
                long monint = monpag;
                monpag = num1+monpag;

                if (num2!=0) {
                    long moncuo = monpag/num2;

                    String res1 = String.valueOf(formato.format(moncuo));
                    txt4.setText(res1);

                    String res2 = String.valueOf(formato.format(monint));
                    txt6.setText(res2);

                    String res3 = String.valueOf(formato.format(monpag));
                    txt7.setText(res3);
                }
            }
                else if (!txt1.getText().isEmpty() && txt2.getText().isEmpty()) {
                    txt4.setText("0");
                    txt6.setText("0");
                    txt7.setText("0");
                }
                    else if (txt1.getText().isEmpty() && !txt2.getText().isEmpty()) {
                        txt4.setText("0");
                        txt6.setText("0");
                        txt7.setText("0");
                    }
        }
    }
}