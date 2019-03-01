/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.EntidadEducativa;
import daoInterfaz.EntidadEducativaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Agustin
 */
public class EntidadEducativaDaoHibernateImpl implements EntidadEducativaDao{
    private final EntityManager gestor;
    private final EntityManagerFactory emf;
    
    public EntidadEducativaDaoHibernateImpl(EntityManagerFactory emf,EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }
    
    public List<EntidadEducativa> buscarEntidadesEducativas()
    {
        List <EntidadEducativa> entidadesEducativas = null;
        
        // Cuerpo de busqueda de Entidades educativas
        try
        {
            Query q = this.gestor.createQuery("FROM entidadeducativa");
            entidadesEducativas = (List<EntidadEducativa>) q.getResultList();
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        
        return entidadesEducativas;
    }
    
}
