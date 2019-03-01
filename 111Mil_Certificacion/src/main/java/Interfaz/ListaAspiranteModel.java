/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Aspirante;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Agustin
 */
public class ListaAspiranteModel extends AbstractListModel{

    // Listado de los aspirantes asociados a una entidad educativa.
    private List <Aspirante> aspirantes;
    
    public ListaAspiranteModel()
    {
        super();
        this.aspirantes = new ArrayList();
    }
    
    public ListaAspiranteModel(List <Aspirante> aspirantes)
    {
        super();
        this.aspirantes = aspirantes;
    }
    public int getSize() {
        return this.aspirantes.size(); 
    }

    public Object getElementAt(int index) {
        return this.aspirantes.get(index);
    }
    
     public Aspirante obtenerAspiranteEn (int fila) {
        return aspirantes.get(fila);
    }

    public void setAspirante(List<Aspirante> aspirantes) {
        this.aspirantes = aspirantes;
    }
    
    public void fireListDataChanged() { 
        fireContentsChanged(this, 0, Math.max(this.getSize() - 1, 0)); 
    } 
}
