/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.Aspirante;
import Class.EntidadEducativa;
import daoInterfaz.AspiranteDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Agustin
 */
public class AspiranteDaoHibernateImpl implements AspiranteDao{
    private final EntityManagerFactory emf;
    private final EntityManager gestor;
    
    public AspiranteDaoHibernateImpl(EntityManagerFactory emf,EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }
    
    /**
     * Devuelve todos los aspirantes por una entidad educativa seleccionada.
     * @param entidadEducativa entidad educativa de las que se buscara los aspirantes.
     * @return devuele una lista de aspirantes que corresponde a la entidad
     * educativa pasada por parametro.
     */
    public List<Aspirante> buscarAspirantePorEntidadEducativa(EntidadEducativa entidadEducativa)
    {
        List <Aspirante> aspirantes = new ArrayList();
        try
        {
            Query q = this.gestor.createQuery("FROM aspirante");
            List <Aspirante> aux = (List<Aspirante>) q.getResultList();
            Iterator i = aux.iterator();
            while(i.hasNext())
            {
                Aspirante a = (Aspirante)i.next();
                if(a.getEntidadEducativa().equals(entidadEducativa))
                {
                    aspirantes.add(a);
                }
            }
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        
        return aspirantes;
    }
    
    public boolean buscarAspiranteDNI(int DniNuevoAspirante)
    {
        try
        {
            Query q = this.gestor.createQuery("FROM aspirante a WHERE a.dni="+DniNuevoAspirante);
            List<Aspirante> resultList = (List<Aspirante>) q.getResultList();
            if(resultList.size() == 0)
                return false;
            else
                return true;
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        return false;
    }
    
}
