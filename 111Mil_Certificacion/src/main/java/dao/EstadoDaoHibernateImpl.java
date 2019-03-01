/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.Estado;
import daoInterfaz.EstadoDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Agustin
 */
public class EstadoDaoHibernateImpl implements EstadoDao{
    private final EntityManager gestor;
    private final EntityManagerFactory emf;
    
    public EstadoDaoHibernateImpl(EntityManagerFactory emf,EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }
    @Override
    public Estado buscarEstadoPorId(int id) {

        Estado estado = null;
        
        // Cuerpo de busqueda de estado
        try
        {
            estado = this.gestor.find(Estado.class,id);
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        
        return estado;
     }
    
}
