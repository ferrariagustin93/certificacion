/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.Competencia;
import daoInterfaz.CompetenciaDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Agustin
 */
public class CompetenciaDaoHibernateImpl implements CompetenciaDao{
    private final EntityManagerFactory emf;
    private final EntityManager gestor;
    
    
    public CompetenciaDaoHibernateImpl(EntityManagerFactory emf, EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }

    public List<Competencia> buscarCompetencias() {
        List<Competencia> competencias = new ArrayList();
        
        // buscar las competencias;
        try
        {
            Query q = this.gestor.createQuery("FROM competencia");
            competencias = (List<Competencia>) q.getResultList();
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
        return competencias;
    }

}
