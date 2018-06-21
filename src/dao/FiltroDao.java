/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Filtro>{
    private static final String SQL_INSERT = "INSERT INTO personas (numAfiliacion,nombres,apellidos,edad,profesion,estado) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE personas SET numAfiliacion = ?, nombres = ?, apellidos=?, edad=?, profesion=?, estado = ? WHERE codFiltro = ?";
    private static final String SQL_DELETE = "DELETE FROM personas WHERE numAfiliacion=?";
    private static final String SQL_READ = "SLECT * FROM personas WHERE numAfiliacion = ?";
    private static final String SQL_READALL = "SELECT * FROM personas";
    
    Conexion con = new Conexion();
    
    @Override
    public boolean create(Filtro g) {
        
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNumAfiliacion());
            ps.setString(2, g.getNombres());
            ps.setString(3, g.getProfesion());
            ps.setBoolean(4, true);
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            if(ps.executeUpdate() >0){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getNumAfiliacion());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombres());
            ps.setString(2,c.getApellidos());
            ps.setString(3, c.getProfesion());
            ps.setString(4, c.getNumAfiliacion());
            ps.setBoolean(5, c.isExistencia());
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps =con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            while(rs.next()){
                f = new Filtro (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Filtro(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getBoolean(7)));
            }
            rs.close();
        }catch(SQLException ex){
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return all;
    }
}
