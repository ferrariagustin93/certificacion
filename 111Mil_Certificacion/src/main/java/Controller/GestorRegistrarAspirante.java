/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Class.Aspirante;
import Class.Categoria;
import Class.Competencia;
import Class.EntidadEducativa;
import Class.Estado;
import Class.Inscripcion;
import Interfaz.PantallaRegistrarAspirante;
import dao.AspiranteDaoHibernateImpl;
import dao.CategoriaDaoHibernateImpl;
import dao.CompetenciaDaoHibernateImpl;
import dao.EntidadEducativaDaoHibernateImpl;
import dao.EstadoDaoHibernateImpl;
import dao.InscripcionDaoHibernateImpl;
import daoInterfaz.InscripcionDao;
import daoInterfaz.AspiranteDao;
import daoInterfaz.CategoriaDao;
import daoInterfaz.CompetenciaDao;
import daoInterfaz.EntidadEducativaDao;
import daoInterfaz.EstadoDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Agustin
 */
public final class GestorRegistrarAspirante {
    private EntityManager gestor;
    private EntityManagerFactory emf;
    private final EntidadEducativaDao entidadEducativaDao;
    private final CompetenciaDao competenciaDao;
    private final AspiranteDao aspiranteDao;
    private final CategoriaDao categoriaDao;
    private final InscripcionDao inscripcionDao;
    private final EstadoDao estadoDao;
    
    public GestorRegistrarAspirante(EntityManagerFactory emf, EntityManager gestor)
    {
        this.crearGestor();
        this.entidadEducativaDao = new EntidadEducativaDaoHibernateImpl(this.emf,this.gestor);
        this.competenciaDao = new CompetenciaDaoHibernateImpl(this.emf,this.gestor);
        this.aspiranteDao = new AspiranteDaoHibernateImpl(this.emf,this.gestor);
        this.categoriaDao = new CategoriaDaoHibernateImpl(this.emf, this.gestor);
        this.inscripcionDao = new InscripcionDaoHibernateImpl(this.emf,this.gestor);
        this.estadoDao = new EstadoDaoHibernateImpl(this.emf,this.gestor);
    }
    
    public void run()
    {
        new PantallaRegistrarAspirante(this).setVisible(true);
    }
    
    public List<EntidadEducativa> buscarEntidadesEducativas()
    {
        return entidadEducativaDao.buscarEntidadesEducativas();
    }
    public List<Competencia> buscarCompetencias()
    {
        return competenciaDao.buscarCompetencias();
    }
    public List<Aspirante> buscarAspirantePorEntidadEducativa(EntidadEducativa entidadEducativa)
    {
        return aspiranteDao.buscarAspirantePorEntidadEducativa(entidadEducativa);
    }
    public List<Categoria> buscarCategorias()
    {
        return categoriaDao.buscarCategorias();
    }
    
    public boolean buscarAspiranteDNI(int dniNuevoAspirante)
    {
        return aspiranteDao.buscarAspiranteDNI(dniNuevoAspirante);
    }
    
    public void cargarInscripciones(List<Aspirante> aspirantesNuevos)
    {
        this.inscripcionDao.cargarInscripciones(aspirantesNuevos);
    }
    
    public Estado buscarEstadoPorId(int id)
    {
        return this.estadoDao.buscarEstadoPorId(id);
    }
    /**
     * Vincula el gestor con la base de datos a partir del archivo de persistencia. 
     */
    public void crearGestor()
    {
        this.emf = Persistence.createEntityManagerFactory("myConnectionBD");
        this.gestor = this.emf.createEntityManager();
    }
    
    public void cerrarGestor()
    {
        this.emf.close();
        this.gestor.close();
    }
}
