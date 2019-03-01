/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Categoria;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Agustin
 */
public class ListaCategoriaModel extends AbstractListModel{

    // Listado de las entidades educativas existentes
    private List <Categoria> categorias;
    
    public ListaCategoriaModel(List <Categoria> categorias)
    {
        super();
        this.categorias = categorias;
    }
    public int getSize() {
        return this.categorias.size(); 
    }

    public Object getElementAt(int index) {
        return this.categorias.get(index);
    }
    
     public Categoria obtenerCategoriaEn (int fila) {
        return categorias.get(fila);
    }

    public void setCategoria(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    public int obtenerFilaPorCategoria(Categoria categoria)
    {
        return this.categorias.indexOf(categoria);
    }
}
