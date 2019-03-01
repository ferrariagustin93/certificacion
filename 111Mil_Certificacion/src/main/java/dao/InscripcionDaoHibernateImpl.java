/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Class.Aspirante;
import Class.Inscripcion;
import daoInterfaz.InscripcionDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Agustin
 */
public class InscripcionDaoHibernateImpl implements InscripcionDao{

    private final EntityManager gestor;
    private final EntityManagerFactory emf;
    
    public InscripcionDaoHibernateImpl(EntityManagerFactory emf,EntityManager gestor)
    {
        this.emf = emf;
        this.gestor = gestor;
    }
    
    
    /*@Override
    public void cargarInscripcioness(List<Aspirante> aspirantesNuevos) {
        try
        {
            Iterator iter = inscripciones.iterator();
            while(iter.hasNext())
            {
                Inscripcion i = (Inscripcion) iter.next();
                i.getAspirante().setInscripciones(inscripciones);
                i.getCategoria().setInscripciones(inscripciones);
                i.getCompetencia().setInscripciones(inscripciones);
                i.getEstado().setInscripciones(inscripciones);
                cargarAspirante(i.getAspirante());
                gestor.getTransaction().begin();
                if(gestor.find(Inscripcion.class, i.getIdInscripcion()) == null)
                {
                    System.out.println("Paso............................");
                    gestor.persist(i);
                    gestor.persist(i.getAspirante());
                    gestor.getTransaction().commit();
                }
                else
                {
                    System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOpaso................");
                    gestor.getTransaction().rollback();
                }   
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }*/
    
    public void cargarInscripciones(List<Aspirante> aspirantesNuevos)
    {
        try
        {
            Iterator iter = aspirantesNuevos.iterator();
            while(iter.hasNext())
            {
                Aspirante a = (Aspirante) iter.next();
                cargarAspirante(a);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void cargarAspirante(Aspirante nuevoAspirante)
    {
        try
        {
            gestor.getTransaction().begin();
            if(gestor.find(Aspirante.class, nuevoAspirante.getIdAspirante()) == null)
            {
                System.out.println("Paso............................");
                gestor.persist(nuevoAspirante);
                gestor.getTransaction().commit();
            }
            else
            {
                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOpaso................");
                gestor.getTransaction().rollback();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
