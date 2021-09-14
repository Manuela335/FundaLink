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
 * @author E5-473
 */
public class FundacionesDAO {
     public ArrayList<Fundaciones> consultarFundaciones() {
        ArrayList<Fundaciones> lista = new ArrayList<>();
        ConexionBD con = new ConexionBD();
         System.out.println("Conectado");
        ResultSet rs = con.ejecutarQuery("SELECT idFundacion, Nombre, Direccion, Email,Representante,URL,Telefono, Username, Password FROM Fundaciones ");
        try {
            while (rs.next()) {
                int idFundacion = rs.getInt("idFundacion");
                String Nombre = rs.getString("Nombre");
                String Direccion = rs.getString("Direccion");
                String Email = rs.getString("Email");
                String Representante = rs.getString("Representante");
                String URL = rs.getString("URL");
                String Telefono = rs.getString("Telefono");
                //String Fundacionescol = rs.getString("Fundacionescol");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                
                Fundaciones j = new Fundaciones(idFundacion, Nombre, Direccion, Email, Representante, URL, Telefono, Username, Password);
                lista.add(j);
            }
        } catch (SQLException ex) {
            return null;
        }
        return lista;
    }
    
    
    
}
