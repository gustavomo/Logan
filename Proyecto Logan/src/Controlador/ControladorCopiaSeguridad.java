
package Controlador;

import Modelo.ModeloCopiaSeguridad;
import Vista.CopiaSeguridad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorCopiaSeguridad implements ActionListener{
    
    private CopiaSeguridad obj1;
    private ModeloCopiaSeguridad modelo = new ModeloCopiaSeguridad();
    public ControladorCopiaSeguridad(CopiaSeguridad obj1) {
        this.obj1=obj1;
        this.obj1.btn1.addActionListener(this);
        
        this.obj1.txt1.setEditable(false);
        this.obj1.txt1.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.obj1.btn1){
            JFileChooser chooser = new JFileChooser(); 
            chooser.setDialogTitle("Elegir Ruta");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("SQL file", "sql"));
            chooser.setApproveButtonText("Guardar");
                
            if (chooser.showOpenDialog(this.obj1) == JFileChooser.APPROVE_OPTION) {               
                this.obj1.txt1.setText(String.valueOf(chooser.getSelectedFile()));
                JOptionPane.showMessageDialog(obj1,"Se ha Creado la Copia de Seguridad con Exito!");
            }
            else {
                System.out.println("No Selection ");
            }
            
            if (this.obj1.txt1.getText().equals("")){
                
            }
            String ruta= this.obj1.txt1.getText();
            ruta= ruta+"\\"+"Copia Seguridad"+"."+"sql";
            
            new ModeloCopiaSeguridad().CrearBackup("localhost", "3306", "root", "", "bd_logan",ruta);
        }
    }
}