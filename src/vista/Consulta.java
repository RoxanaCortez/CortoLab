/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel lblnumAfiliado, lblNombre, lblApellidos, lblProfesion, lblEdad, lblExistencia;
    
    public JTextField numAfiliado, nombre, edad, apellidos;
    public JComboBox profesion;
    
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    public JButton buscar, eliminar, insertar, limpiar, actualizar;
    
    private static final int ANCHOC =130, ALTOC=30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblnumAfiliado);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblProfesion);
        container.add(lblEdad);
        container.add(lblExistencia);
        container.add(numAfiliado);
        container.add(nombre);
        container.add(edad);
        container.add(apellidos);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        container.add(profesion);
        setSize (600,600);
        eventos();
        
    }
    public final void agregarLabels(){
        lblnumAfiliado = new JLabel("N°AFP");
        lblNombre = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos");
        lblEdad = new JLabel("Edad");
        lblProfesion = new JLabel("Profesion");
        lblExistencia = new JLabel("Estado");
        //Arreglar coordenadas
        lblnumAfiliado.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellidos.setBounds(280, 60, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, ANCHOC, ALTOC);
        lblProfesion.setBounds(10, 140, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 180, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        numAfiliado = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();
        profesion = new JComboBox();
        nombre = new JTextField();
        si = new JRadioButton("si",true);
        no = new JRadioButton("no", false);
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        profesion.addItem("Ingeniero");
        profesion.addItem("Mecanico");
        profesion.addItem("Profesor");
        profesion.addItem("Arquitecto");
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        numAfiliado.setBounds(140,10,ANCHOC,ALTOC);
        nombre.setBounds(140, 60,ANCHOC,ALTOC);
        apellidos.setBounds(350,60,ANCHOC,ALTOC);
        edad.setBounds(140,100,ANCHOC,ALTOC);
        profesion.setBounds(140,150,ANCHOC,ALTOC);
        si.setBounds(180,180,50,ALTOC);
        no.setBounds(230,180,50,ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){
            public Class <?> getColumnClass(int column){
                switch (column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("N°AFP");
        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Pofesion");
        tm.addColumn("Estado");
        
        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();
        
        for(Filtro fi: filtros){
            tm.addRow(new Object[]{fi.getNumAfiliacion(), fi.getNombres(), fi.getApellidos(), fi.getEdad(), fi.getProfesion(), fi.isExistencia()});
        }
        resultados.setModel(tm);
    }
    public void eventos(){
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numAfiliado.getText(), nombre.getText(), apellidos.getText(),profesion.getSelectedItem().toString(),Integer.parseInt(edad.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crear el filtro");
                }
            }
        });
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numAfiliado.getText(), nombre.getText(), apellidos.getText(),profesion.getSelectedItem().toString(),Integer.parseInt(edad.getText()),true);
                
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.update(f)){
                    JOptionPane.showMessageDialog(null, "Filtro Modificado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");
                }
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if(fd.delete(numAfiliado.getText())){
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(numAfiliado.getText());
                if(f== null){
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                }else{
                    numAfiliado.setText(f.getNumAfiliacion());
                    nombre.setText(f.getNombres());
                    apellidos.setText(f.getApellidos());
                    profesion.setSelectedItem(f.getProfesion());
                    edad.setText(Integer.toString(f.getEdad()));
                    
                    if(f.isExistencia()){
                        si.setSelected(true);
                    }else{
                        no.setSelected(true);
                    }
                }
            }
        });
        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    public void limpiarCampos(){
        numAfiliado.setText("");
        nombre.setText("");
        apellidos.setText("");
        profesion.setSelectedItem("FRAM");
        edad.setText("");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
    
}
