package Controlador;

import Modelo.ModeloReporte;
import Vista.Principal;
import Vista.ReporteCartera;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ControladorReporteCartera implements ActionListener, ItemListener {

    private ReporteCartera obj1;
    private Color fondo = new Color(232, 238, 244);
    private DefaultTableModel model = new DefaultTableModel();
    private ModeloReporte modelo = new ModeloReporte();
    private ResultSet rss;

    public ControladorReporteCartera(ReporteCartera obj1, String fe) {

        this.obj1 = obj1;
        //Modelo para la tabla
        String datos[] = {"Identificación", "Cliente", "Teléfono", "Dirección", "Barrio", "Fecha Inicio", "Fecha Ultimo Pago", "Fecha Pagos S-Q-M", "Valor Cuota", "Cantidad Cuotas", "Cuotas Pagadas","Cuotas Pendientes","Cuotas con mora",  "Valor cuota adicional", "Valor pagado", "Saldo"};
        model.setColumnIdentifiers(datos);
        this.obj1.tabla.setModel(model);
        //////////////

        this.obj1.btn1.addActionListener(this);
        this.obj1.btn2.addActionListener(this);
        this.obj1.cbx1.addItemListener(this);

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
        String anio = Integer.toString(c.get(Calendar.YEAR));

        String fecha = anio + "/" + mes + "/" + dia;

        this.obj1.txt17.setEditable(false);
        this.obj1.txt18.setEditable(false);
        //this.obj1.txt17.setText(fe);

        // Sirve para obtener el evento cuando cambia un datachooser se verifica que las fecha que se obtienen de los datachooser no sea null, si  es null es manda al metodo un string null
        this.obj1.jdc1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {

                if ("date".equals(e.getPropertyName())) {

                    Date fecha = obj1.jdc1.getDate();
                    if (fecha != null) {

                        Date fecha2 = obj1.jdc2.getDate();
                        String valor = obj1.cbx1.getSelectedItem().toString();

                        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        String date = "";
                        String date2 = "";
                        if (fecha == null) {
                            date = null;
                        } else {
                            date = fmt.format(fecha);
                        }

                        if (fecha2 == null) {
                            date2 = null;
                        } else {
                            date2 = fmt.format(fecha2);
                        }

                        if (valor != "Seleccione") {
                            int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ", ""));
                            cargarReporte(date, date2, idCartera);
                        } else {
                            cargarReporte(date, date2, 0);
                        }
                    }
                }
            }
        });
        ////////////////////////////////////////////////////

        // Sirve para obtener el evento cuando cambia un datachooser se verifica que las fecha que se obtienen de los datachooser no sea null, si  es null es manda al metodo un string null
        this.obj1.jdc2.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {

                if ("date".equals(e.getPropertyName())) {
                    Date fecha = obj1.jdc1.getDate();
                    Date fecha2 = obj1.jdc2.getDate();

                    if (fecha2 != null) {
                        String valor = obj1.cbx1.getSelectedItem().toString();

                        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        String date = "";
                        String date2 = "";

                        if (fecha2 == null) {
                            date2 = null;
                        } else {
                            date2 = fmt.format(fecha2);
                        }

                        if (fecha == null) {
                            date = null;
                        } else {
                            date = fmt.format(fecha);

                            if (valor != "Seleccione") {
                                int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ", ""));
                                cargarReporte(date, date2, idCartera);
                            } else {
                                cargarReporte(date, date2, 0);
                            }
                        }
                    }
                }
            }
        });
        ////////////////////////////////////////////////////
        
        this.obj1.txt17.requestFocus();
        this.obj1.txt17.requestFocusInWindow();
        this.obj1.txt18.requestFocus();
        this.obj1.txt18.requestFocusInWindow();
        cargarCar();
        this.obj1.cbx1.setSelectedIndex(0);
    }

    public void cargarCar() {
        obj1.cbx1.addItem("Seleccione");

        rss = modelo.getCarteras();
        try {
            while (rss.next()) {
                obj1.cbx1.addItem("Cartera " + rss.getString("CC.id_cartera"));
            }
        } catch (SQLException error) {
            System.out.println("Erroy de MySql" + error);
        } catch (NumberFormatException error) {
            System.out.println("se presento el siguiente error " + error.getMessage());
        }
    }

    //Metodo para cargar los datos de gastos segun los parametros
    //Explicacion: se pasa como parametro las fechas y el id de la cartera y segun lo que este null se hara la consulta con el where
    public void cargarReporte(String fechaI, String fechaF, int id) {
        Boolean verificar = false;

        String where = "";
        if (id != 0 && fechaI != null && fechaF == null) {
            where = "WHERE fechaPago= '" + fechaI + "' AND id_cartera=" + id;
        } else if (id != 0 && fechaI != null && fechaF != null) {
            where = "WHERE fechaPago BETWEEN '" + fechaI + "' AND '" + fechaF + "' AND id_cartera=" + id;
        } else if (fechaI != null && fechaF == null && id == 0) {
            where = "WHERE fechaPago= '" + fechaI + "'";
        } else if (fechaI != null && fechaF != null && id == 0) {
            where = "WHERE fechaPago BETWEEN '" + fechaI + "' AND '" + fechaF + "'";
        } else if (id != 0) {
            where = "WHERE id_cartera=" + id;
        }

        // System.out.println(where + " " + fechaI);

        //this.obj1.txt17.setText(fechaI);
        for (int i = 0; i < obj1.tabla.getRowCount(); i++) {
            if (model.getRowCount() != 0) {
                model.removeRow(i);
                i -= 1;
            }
        }

        ResultSet rs = modelo.consultaReporte(where);

        try {
            DecimalFormat formateador = new DecimalFormat("###,###.###");
            while (rs.next()) {
                long montoPrestado=rs.getLong("monto_prestado");
                long montoInteres=rs.getLong("monto_interes");
                long montoAdicional=rs.getLong("cuotaAdicional"); 
                long operacion=(montoPrestado+montoInteres+montoAdicional)/(rs.getLong("cantidad_cuotas")+rs.getLong("cuotaMora"));
                int cuotas =rs.getInt("cantidad_cuotas")+rs.getInt("cuotaMora")-rs.getInt("cuotasPagadas");
                
                String montoActual = String.valueOf(operacion);
                String montoPagado = rs.getString("valorPagado");
                String montiInteres = rs.getString("cuotaAdicional");
                String cuotasPendientes = String.valueOf(cuotas);
                String cuotasMoras = rs.getString("cuotaMora");

                if (cuotasMoras == null) {
                    cuotasMoras = "0";
                }
                if (cuotasPendientes == null || Integer.parseInt(cuotasPendientes)<0) {
                    cuotasPendientes = "0";
                }
                if (montoActual == null) {
                    montoActual = "0";
                } else {
                    montoActual = formateador.format(Integer.valueOf(String.valueOf(operacion)));
                }
                if (montoPagado == null) {
                    montoPagado = "0";
                } else {
                    montoPagado = formateador.format(Integer.valueOf(rs.getString("valorPagado")));
                }
                if (montiInteres == null || montiInteres.equals("")) {
                    montiInteres = "0";
                }
                else {
                    montiInteres = formateador.format(Integer.valueOf(rs.getString("cuotaAdicional")));
                }
                
                DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
                String fechaPago = rs.getString("fechaPago");
                String proximoPago = rs.getString("proximoPago");

                if (fechaPago == null) {
                    fechaPago = "";
                }
                else{
                    fechaPago=fmt.format(rs.getDate("fechaPago"));
                }

                if (proximoPago == null) {
                    proximoPago = "";
                }
                else{ 
                    proximoPago=fmt.format(rs.getDate("proximoPago"));
                }

                model.addRow(new Object[]{rs.getString("id_cliente"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("barrio"), fmt.format(rs.getDate("fecha_inicio")), fechaPago, proximoPago, montoActual, rs.getString("cantidad_cuotas"), rs.getString("cuotasPagadas"), cuotasPendientes,cuotasMoras, montiInteres, montoPagado, formateador.format(Integer.valueOf(rs.getString("saldo")))});
            }

            /*if (this.obj1.tabla.getRowCount() != 0) {
                for (int i = 0; i < this.obj1.tabla.getRowCount(); i++) {

                    if ("2".equals(model.getValueAt(i, 17).toString())) {

                        this.obj1.tabla.setDefaultRenderer(Object.class, new TableCellRenderColor(i));
                    }
                }idd
            }

            this.obj1.tabla.getColumnModel().getColumn(17).setMaxWidth(0);

            this.obj1.tabla.getColumnModel().getColumn(17).setMinWidth(0);

            this.obj1.tabla.getTableHeader().getColumnModel().getColumn(17).setMaxWidth(0);

            this.obj1.tabla.getTableHeader().getColumnModel().getColumn(17).setMinWidth(0);
            */
        } catch (SQLException er) {
            System.out.println(er + "dsfdfs");
        }
    }
    ////////////////////////////////////////////////

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.obj1.btn1) {
            if (this.obj1.tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(obj1, "No ha consultado datos para poder Generar un Reporte");
                return;
            } else {
                String ruta = "";
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Elegir Ruta");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                chooser.setAcceptAllFileFilterUsed(false);                	
                chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
                chooser.setApproveButtonText("Guardar");

                if (chooser.showOpenDialog(this.obj1) == JFileChooser.APPROVE_OPTION) {
                    ruta = String.valueOf(chooser.getSelectedFile());
                    JOptionPane.showMessageDialog(this.obj1,"Se ha Generado el Reporte con Exito!");
                } else {
                    System.out.println("No Selection ");
                }

                //Creamos el documento con su tamaño
                Document document = new Document(PageSize.A1,10,10,50,30);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(ruta + "\\" + "Reporte.pdf"));
                    document.open();

                    //////////////////////////////////
                    //Creamos la tabla con la cantidad de columnas
                    PdfPTable tab = new PdfPTable(16);
                    tab.setTotalWidth(PageSize.A1.getWidth()-70);
                    tab.setLockedWidth(true);
                    tab.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tab.setHeaderRows(3);
                    
                    //////////////////////////

                    //Creamos las celdas para las cabeceras con el color
                    Font fuente = new Font();
                    Font fuente2 = new Font();

                    fuente2.setSize(12);
                    fuente2.setFamily("Arial");
                    
                    Image imagen = Image.getInstance("src/Archivos/Azul.png");                  
                      
                    imagen.scaleAbsolute(95, 95);
                    imagen.scaleToFit(95, 95);
                    
                    BaseColor color = new BaseColor(129, 168, 194);                    
                    BaseColor color2 = new BaseColor(232, 238, 244);
                    
                    fuente.setSize(28);
                    fuente.setColor(BaseColor.WHITE);
                    fuente.setFamily("Arial");
                    
                    PdfPCell cell = new PdfPCell(imagen);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingTop(15);
                    cell.setPaddingLeft(52);   
                    cell.setColspan(2);
                    cell.setBackgroundColor(color);                    
                    tab.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph("Reporte Cartera-Comercializadora Logan", fuente));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(48); 
                    cell.setPaddingTop(48);
                    cell.setColspan(15);                    
                    cell.setBackgroundColor(color);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph(" "));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPaddingBottom(25);                    
                    cell.setColspan(17);
                    cell.setBackgroundColor(BaseColor.WHITE);                    
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Identificación", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Cliente", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Teléfono", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Dirección", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Barrio", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("F.Inicio", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("F.Ultimo Pago", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("F.Pagos S-Q-M", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("VLR.Cuota", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Cantidad CTAS", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("CTAS Pagadas", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("CTAS Pendientes", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("CTAS Mora", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("VLR CTAS Adicional", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("VLR Pagado", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    cell = new PdfPCell(new Paragraph("Saldo", fuente2));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(color2);
                    tab.addCell(cell);

                    //Llenamos la tabla con los datos del jtables

                    for (int i = 0; i < this.obj1.tabla.getRowCount(); i++) {
                        tab.addCell(model.getValueAt(i, 0).toString());
                        tab.addCell(model.getValueAt(i, 1).toString());
                        tab.addCell(model.getValueAt(i, 2).toString());
                        tab.addCell(model.getValueAt(i, 3).toString());
                        tab.addCell(model.getValueAt(i, 4).toString());
                        tab.addCell(model.getValueAt(i, 5).toString());
                        tab.addCell(model.getValueAt(i, 6).toString());
                        tab.addCell(model.getValueAt(i, 7).toString());
                        tab.addCell(model.getValueAt(i, 8).toString());
                        tab.addCell(model.getValueAt(i, 9).toString());
                        tab.addCell(model.getValueAt(i, 10).toString());
                        tab.addCell(model.getValueAt(i, 11).toString());
                        tab.addCell(model.getValueAt(i, 12).toString());
                        tab.addCell(model.getValueAt(i, 13).toString());
                        tab.addCell(model.getValueAt(i, 14).toString());
                        tab.addCell(model.getValueAt(i, 15).toString());
                        
                        /*if ("2".equals(model.getValueAt(i, 17).toString())) {
                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 0).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 1).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 2).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 3).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 4).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 5).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 6).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 7).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 8).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 9).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 10).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 11).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 12).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 13).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 14).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 15).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);

                            cell = new PdfPCell(new Paragraph(model.getValueAt(i, 16).toString(), fuente2));
                            cell.setBackgroundColor(BaseColor.RED);
                            tab.addCell(cell);
                        } */
                    }
                    //////////////////////////////////////
                    document.add(tab);
                    document.close();
                } catch (DocumentException ex) {
                    Logger.getLogger(ControladorReporteCartera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ControladorReporteCartera.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ControladorReporteCartera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Funcion para limpiar los elementos
        if (e.getSource() == obj1.btn2) {
            obj1.cbx1.setSelectedItem("Seleccione");
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String anio = Integer.toString(c.get(Calendar.YEAR));
            
            if (mes.length() == 1) {
                mes = "0" + mes;
            }
            if (dia.length() == 1) {
                dia = "0" + dia;
            }

            String fecha = dia + "/" + mes + "/" + anio;
            String fecha2 = anio + "/" + mes + "/" + dia;
            
            this.obj1.jdc1.setDate(null);
            this.obj1.jdc2.setDate(null);
            this.obj1.txt17.setText(fecha);
            this.obj1.txt18.setText("—/—/———");
           
            this.obj1.txt17.requestFocus();
            this.obj1.txt18.requestFocus();
            cargarReporte(fecha2, null, 0);
        }
        /////////////
    }

    // item listener para el comboBox
    @Override
    public void itemStateChanged(ItemEvent e) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String valor = obj1.cbx1.getSelectedItem().toString();
        //System.out.print(valor);

        if (valor.equals("Seleccione")) {
            Calendar c = Calendar.getInstance();
            String dia = Integer.toString(c.get(Calendar.DATE));
            String mes = Integer.toString(c.get(Calendar.MONTH) + 1);
            String anio = Integer.toString(c.get(Calendar.YEAR));
            String fechaA = anio + "/" + mes + "/" + dia;

            Date fecha = obj1.jdc1.getDate();
            Date fecha2 = obj1.jdc2.getDate();
            String date;
            String date2;
            
            if (fecha2 == null) {
                date2 = null;
            } else {
                date2 = fmt.format(fecha2);
            }

            if (fecha == null) {
                date = fechaA;
            } else {
                date = fmt.format(fecha);
            }
            
            cargarReporte(date, date2, 0);
        } else {
            int idCartera = Integer.valueOf(String.valueOf(obj1.cbx1.getSelectedItem()).replace("Cartera ", ""));
            Date fecha = obj1.jdc1.getDate();
            Date fecha2 = obj1.jdc2.getDate();
            String date;
            String date2;
            if (fecha2 == null) {
                date2 = null;
            } else {
                date2 = fmt.format(fecha2);
            }

            if (fecha == null) {
                date = null;
            } else {
                date = fmt.format(fecha);
            }

            cargarReporte(date, date2, idCartera);
        }
    }
}