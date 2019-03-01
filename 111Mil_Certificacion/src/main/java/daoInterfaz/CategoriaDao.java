/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoInterfaz;

import Class.Categoria;
import java.util.List;

/**
 *
 * @author Agustin
 */
public interface CategoriaDao {
    
    public List<Categoria> buscarCategorias();
}
