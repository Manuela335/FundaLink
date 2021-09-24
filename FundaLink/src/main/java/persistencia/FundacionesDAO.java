/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.Fundaciones;

/**
 *
 * @author Ariel Fundalink
 * Contiene todas las operaciones CRUD para las fundaciones
 */
public class FundacionesDAO {
     
    /**
     * Envía la sentencia SQL para obtener la información de todas las Fundaciones y estructura
     * la respuesta en una estructura de datos.
     * @return una estructura de datos  con los objetos de tipo Fundaciones
     */
    
    public ArrayList<Fundaciones> consultarFundaciones() {
        ArrayList<Fundaciones> lista = new ArrayList<>();
        ConexionBD con = new ConexionBD();
        String sql = "SELECT idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Tipo,Username, Password " +
                     "FROM fundaciones ";
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            while (rs.next()) {
                int idFundacion = rs.getInt("idFundacion");
                String Nombre = rs.getString("Nombre");
                String Direccion = rs.getString("Direccion");
                String Email = rs.getString("Email");
                String Representante = rs.getString("Representante");
                String URL = rs.getString("URL");
                String Telefono = rs.getString("Telefono");
                String Tipo = rs.getString("Tipo");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                
                Fundaciones j = new Fundaciones(idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password);
                lista.add(j);
            }
        } catch (SQLException ex) {
           
            con.desconectar();
            return null;
        }
        con.desconectar();
        return lista;
    }
    
    public Fundaciones  consultarFundacion(int idAConsultar) {
        Fundaciones j = null;
        ConexionBD con = new ConexionBD();
        String sql = "SELECT idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password " +
                     "FROM fundaciones " +
                     "WHERE idFundacion = "+idAConsultar +" ";
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            while (rs.next()) {
                int idFundacion = rs.getInt("idFundacion");
                String Nombre = rs.getString("Nombre");
                String Direccion = rs.getString("Direccion");
                String Email = rs.getString("Email");
                String Representante = rs.getString("Representante");
                String URL = rs.getString("URL");
                String Telefono = rs.getString("Telefono");
                String Tipo = rs.getString("Tipo");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                
                j = new Fundaciones(idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password);
                
            }
        } catch (SQLException ex) {
           
            con.desconectar();
            return j;
        }
        con.desconectar();
        return j;
    }
     
    
    /**
     * Envía la sentencia SQL para obtener la información de ciertas Fundaciones mediante filtro y estructura
     * la respuesta en una lista de tipo Fundacion
     * @param filtro el filtro para buscar datos en la lista de Fundaciones a  consultar
     * @return un arraylist de tipo Fundacion con la información cargada
     */
    
    public ArrayList<Fundaciones> consultarFundacionesPorFiltro(String filtro) {
        ArrayList<Fundaciones> lista = new ArrayList<>();
        ConexionBD con = new ConexionBD();
        String sql = "SELECT idFundacion, Nombre,Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password " +
                     "FROM fundaciones" +
                     "WHERE Nombre LIKE '%" + filtro + "%' " +
                     "OR Representante LIKE '%" + filtro + "%' " +
                     "OR URL LIKE '%" + filtro + "%' ";
                
        ResultSet rs = con.ejecutarQuery(sql);
        try {
            while (rs.next()) {
                int idFundacion = rs.getInt("idFundacion");
                String Nombre = rs.getString("Nombre");
                String Direccion = rs.getString("Direccion");
                String Email = rs.getString("Email");
                String Representante = rs.getString("Representante");
                String URL = rs.getString("URL");
                String Telefono = rs.getString("Telefono");
                String Tipo = rs.getString("Tipo");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                
                Fundaciones j = new Fundaciones(idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password);
                lista.add(j);
            }
        } catch (SQLException ex) {
           
            con.desconectar();
            return null;
        }
        con.desconectar();
        return lista;
    }
    
    
      /**
     * Envía la sentencia SQL para almacenar los datos de una  fundación  
     * @param j de un objeto de tipo Fundacion
     * @return un numero indicando el id generado por la base de datos.
     */
      public int guardarNuevaFundacion(Fundaciones j) {
       
        ConexionBD con = new ConexionBD();
        
        String Nombre = j.getNombre();
        String Direccion = j.getDireccion();
        String Email = j.getEmail();
        String Representante = j.getRepresentante();
        String URL = j.getURL();
        String Telefono = j.getTelefono();
        String Tipo = j.getTipo();
        String Username = j.getUsername();
        String Password = j.getPassword();
                        
                            
        String sql = "INSERT INTO fundaciones (Nombre, Direccion, Email, Representante, URL, Telefono, Tipo, Username, Password) "+ 
                     "VALUES ('"+Nombre+ "', '"+Direccion+ "', '"+Email+ "', '"+Representante+ "', '"+URL+ "', '"+Telefono+ "', '"+Tipo+ "', '"+Username+ "', '"+Password+ "') ";
        //System.out.println(sql);
        ResultSet rs = con.ejecutarInsert(sql);
        int id = 0;
        try {
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            con.desconectar();
            return 0; 
        }
        con.desconectar();
        return id;
    }   



/**
     * Envía la sentencia SQL para actualizar el dato de una Fundacion existente
     * @param j un objeto de tipo Fundacion
     * @return un número indicando la cantidad de registros afectados
     */
    public int guardarFundacionExistente(Fundaciones j) {
        
        int filas = 0;
                                        
        String sql = "UPDATE fundaciones "+
                     "SET Nombre='" + j.getNombre() + "', Direccion = '" + j.getDireccion() + "',Email = '" + j.getEmail() + "', Representante = '" + j.getRepresentante() + "',  URL = '" + j.getURL() + "', Telefono = '" + j.getTelefono() + "', Tipo = '" + j.getTipo() + "', Username= '" + j.getUsername() + "', Password= '" + j.getPassword() + "' " +
                     "WHERE idFundacion ='" + j.getIdFundacion() + "'" ;
        ConexionBD con = new ConexionBD();
        filas = con.ejecutarUpdate(sql);
        
        return filas;
    }
}