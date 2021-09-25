/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import persistencia.FundacionesDAO;


/**
 *
 * @author E5-473
 */
public class ColeccionFundaciones {
    
    private ArrayList<Fundaciones> lista;
    
    public ArrayList<Fundaciones> getLista() {
        return lista;
    }
    
    public boolean cargarTodasLasFundaciones() {
        FundacionesDAO dao = new FundacionesDAO();
        lista = dao.consultarFundaciones();
        if (lista.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    } 
    
    /**
     * Guarda la información de una Fundacion tomada desde el formulario
     * @param j un objeto con los datos de una Fundacion en específico
     * @return true si guarda la fundacion en la base de datos, o false si no lo guarda
     */
    public boolean guardarFundacion(Fundaciones j){

	FundacionesDAO dao= new FundacionesDAO();
	
   	
        if(j.getIdFundacion()==0){
            int id = dao.guardarNuevaFundacion(j);
        
            if (id > 0){

		return true;
            }else {
		return false;
	}
        }else{
            int filas = dao.guardarFundacionExistente(j);
            if (filas==1){
                return true;
            }else {
                return false;
            }
        }

}
   /**
     * Carga la información de ciertas Fundaciones de la base de datos mediante filtro
     * @return true si carga las Fundaciones, o false si no se logró cargar
     */
    public boolean cargarFundacionesPorFiltro(String filtro) {
        FundacionesDAO dao = new FundacionesDAO();
        lista = dao.consultarFundacionesPorFiltro(filtro);
        if (lista.size() > 0) {
            return true;
        }
        else {
            return false;
        }
    } 
       
    /**
     * Carga la información de una sola Fundacion de la base de datos
     * @return true si carga la Fundacion, o false si no se logró cargar
     */
    public Fundaciones cargarUnaFundacion(int id) {
        FundacionesDAO dao = new FundacionesDAO();
        Fundaciones j = dao.consultarFundacion(id);
        return j;
    }
    
    
}
