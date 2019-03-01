/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.Categoria;
import daoInterfaz.CategoriaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Agustin
 */
public class CategoriaDaoHibernateImpl implements CategoriaDao{

    private final EntityManagerFactory emf;
    private final EntityManager gestor;
    
    
    public CategoriaDaoHibernateImpl(EntityManagerFactory emf, EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }
    
    
    public List<Categoria> buscarCategorias() {
        List<Categoria> categorias = null;
        
        // Cuerpo de busqueda
        // buscar las competencias;
        try
        {
            Query q = this.gestor.createQuery("FROM categoria");
            categorias = (List<Categoria>) q.getResultList();
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        return categorias;
    }
    
}
