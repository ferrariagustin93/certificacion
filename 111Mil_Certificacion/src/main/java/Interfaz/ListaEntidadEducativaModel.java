/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.EntidadEducativa;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Agustin
 */
public class ListaEntidadEducativaModel extends AbstractListModel{

    // Listado de las entidades educativas existentes
    private List <EntidadEducativa> entidadesEducativas;
    
    public ListaEntidadEducativaModel(List <EntidadEducativa> entidadesEducativas)
    {
        super();
        this.entidadesEducativas = entidadesEducativas;
    }
    public int getSize() {
        return this.entidadesEducativas.size(); 
    }

    public Object getElementAt(int index) {
        return this.entidadesEducativas.get(index);
    }
    
     public EntidadEducativa obtenerEntidadEducativaEn (int fila) {
        return entidadesEducativas.get(fila);
    }

    public void setEntidadEducativa(List<EntidadEducativa> entidadesEducativas) {
        this.entidadesEducativas = entidadesEducativas;
    }
    
}
