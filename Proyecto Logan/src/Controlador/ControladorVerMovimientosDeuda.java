
package Controlador;

import Controlador.ContenedorClases.DatosGestionarClientes;
import Modelo.ModeloVerMovimientosDeuda;
import Vista.GestionarClientes;
import Vista.VerMovimientosDeuda;
import Vista.Principal;
import Vista.VerDeudas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVerMovimientosDeuda implements ActionListener {
    
    private VerMovimientosDeuda obj1;
    private ModeloVerMovimientosDeuda obj2 = new ModeloVerMovimientosDeuda();
    private JTable jtb1;
    private JButton btn1, btn2;
    private Color fondo = new Color(232, 238, 244);
    private ResultSet rss = null;
    private DefaultTableModel modtab;
    private DecimalFormat formato = new DecimalFormat("###,###.##");
    
    public ControladorVerMovimientosDeuda(VerMovimientosDeuda obj1) {
        this.obj1 = obj1;
        this.jtb1 = obj1.jtb1;
        this.btn1 = obj1.btn1;
        this.btn2 = obj1.btn2;
        
        this.btn1.addActionListener(this);
        this.btn2.addActionListener(this);
        
        modtab = (DefaultTableModel) jtb1.getModel();
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
    
    public void CargarMovimientos(String fecini) {
        vaciarTabla();

        Object[] fila = new Object[6];
        
        rss = obj2.consultaMovimientosDeuda(DatosGestionarClientes.getId(), fecini);
        
        try {
            while (rss.next()) {
                long monvie, monact;
                
                String fecha1 = rss.getString("md.proximo_pago");
                String[] fecha2 = new String[3];
                if (fecha1!=null) {
                    fecha2 = fecha1.split("-");
                }

                monvie = rss.getLong("md.monto_vieja");
                monact = rss.getLong("md.monto_actual");
                
                fila[0] = rss.getString("md.cuota_vieja");
                fila[1] = rss.getString("md.cuota_actual");
                fila[2] = asignarValor(monvie);
                fila[3] = asignarValor(monact);
                if (fecha1!=null) {
                    fila[4] = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0];
                } else {
                    fila[4] = "";
                }
                fila[5] = rss.getString("tm.descripcion");
                
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
        if (e.getSource()==btn1) {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            VerDeudas obj2 = new VerDeudas();
            ControladorVerDeudas obj2obj1 = new ControladorVerDeudas(obj2);
            obj2obj1.CargarDeudas();
            posicinarFrame(Principal.jDesktopPane1, obj2);
        } else {
            obj1.setVisible(false);
            Principal.jDesktopPane1.remove(obj1);
            GestionarClientes obj2 = new GestionarClientes();
            ControladorGestionarClientes obj2obj1 = new ControladorGestionarClientes(obj2);
            obj2.jPanel1.setBackground(fondo);
            obj2.jPanel2.setBackground(fondo);
            posicinarFrame(Principal.jDesktopPane1, obj2);
        }
    }
}