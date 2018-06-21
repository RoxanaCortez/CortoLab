/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String numAfiliacion;
    private String nombres;
    private String apellidos;
    private int edad;
    private String profesion;
    private boolean existencia;

    public Filtro() {
    }

    public Filtro(int id, String numAfiliacion, String nombres, String apellidos, int edad, String profesion, boolean existencia) {
        this.id = id;
        this.numAfiliacion = numAfiliacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.profesion = profesion;
        this.existencia = existencia;
    }

    public Filtro(String numAfiliacion, String nombres, String apellidos, int edad, String profesion, boolean existencia) {
        this.numAfiliacion = numAfiliacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.profesion = profesion;
        this.existencia = existencia;
    }

    public Filtro(String nombres, String apellidos, int edad, String profesion, boolean existencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.profesion = profesion;
        this.existencia = existencia;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumAfiliacion() {
        return numAfiliacion;
    }

    public void setNumAfiliacion(String numAfiliacion) {
        this.numAfiliacion = numAfiliacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }
    
      
}
