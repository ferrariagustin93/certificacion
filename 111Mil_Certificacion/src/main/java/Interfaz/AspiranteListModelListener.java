/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Aspirante;
import Class.Categoria;
import Class.Competencia;
import Class.EntidadEducativa;
import Class.Inscripcion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Agustin
 */
public class AspiranteListModelListener implements ListSelectionListener {
    
    private final PantallaRegistrarAspirante pantallaRegistrarAspirante;

    public AspiranteListModelListener(PantallaRegistrarAspirante pantallaRegistrarAspirante)
    {
        this.pantallaRegistrarAspirante = pantallaRegistrarAspirante;
    }
    
    
    /**
     * Cuando se selecciona un aspirante en la lista de aspirantes, se buscan las competencias
     * asociadas a ese aspirante y se actualizan en la tabla de competencia.
     * @param e 
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // limpiar selecciones y tablas
        this.pantallaRegistrarAspirante.clearCompetenciaCategoria();
        
        // Buscar aspirante seleccionado
        Aspirante aspiranteSeleccionado = this.pantallaRegistrarAspirante.obtenerAspiranteSeleccionado();
        
        // Buscar Entidad educativa seleccionada
        EntidadEducativa entidadEducativaSeleccionada = this.pantallaRegistrarAspirante.obtenerEntidadEducativaSeleccionada();
        
        // Debe tener una entidad educativa seleccionada
        if(entidadEducativaSeleccionada == null)
        {
            JOptionPane.showMessageDialog(pantallaRegistrarAspirante,"Debe seleccionar una entidad educativa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            if(aspiranteSeleccionado != null && entidadEducativaSeleccionada!= null)
            {
                try
                {
                    // actualizar la tabla competencia 
                    actualizarDatosCompetencia(aspiranteSeleccionado);
                    actualizarDatosCategoria(aspiranteSeleccionado);
                    this.pantallaRegistrarAspirante.getTablaCompetenciaModel().fireTableStructureChanged();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(pantallaRegistrarAspirante, "Falló en la carga de aspirantes", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    
    private List<Inscripcion> cargarInscripcionesPorAspirante(Aspirante seleccionado)
    {
        List<Inscripcion> inscripcionesPorAspirante = new ArrayList();
        Iterator i = this.pantallaRegistrarAspirante.getInscripciones().iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            if(aux.getAspirante().equals(seleccionado))
                inscripcionesPorAspirante.add(aux);
        }
        return inscripcionesPorAspirante;
    }
    
    // Ver que pasa cuando cambia la competencia, cargar en la lista de inscripciones!!!!!!!!
    
    
    public void actualizarDatosCompetencia(Aspirante seleccionado)
    {
        List<Inscripcion> inscripcionesPorAspirante = cargarInscripcionesPorAspirante(seleccionado);
        Iterator i = inscripcionesPorAspirante.iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            Competencia competenciaAux = aux.getCompetencia();
            //Categoria categoriaAux = aux.getCategoria();
            if(competenciaAux != null)
            {
                // Busca la fila que debe actualizar en la tabla de competencias, para actualizar el estado de registro 
                int fila = this.pantallaRegistrarAspirante.getTablaCompetenciaModel().obtenerFilaPorCompetencia(competenciaAux);
                this.pantallaRegistrarAspirante.getTablaCompetenciaModel().modificarRegistroEnTrue(fila);
                this.pantallaRegistrarAspirante.getTablaCompetenciaModel().fireTableStructureChanged();
            }
        }
        
    }
    
    public void actualizarDatosCategoria(Aspirante seleccionado)
    {
        Competencia competencia = this.pantallaRegistrarAspirante.obtenerCompetenciaSeleccionada();
        if(competencia!= null)
        {
            List <Inscripcion> listaInscripciones = this.pantallaRegistrarAspirante.getInscripciones();
            Iterator i = listaInscripciones.iterator();
            while(i.hasNext())
            {
                Inscripcion aux = (Inscripcion) i.next();
                if(aux.getCategoria().equals(competencia) && aux.getAspirante().equals(seleccionado))
                {
                    this.pantallaRegistrarAspirante.getJListCategoria().clearSelection();
                    int fila = this.pantallaRegistrarAspirante.getListaCategoriaModel().obtenerFilaPorCategoria(aux.getCategoria());
                    this.pantallaRegistrarAspirante.getJListCategoria().setSelectedIndex(fila);
                }
            }    
        }
    }
}
