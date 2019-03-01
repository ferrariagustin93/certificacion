/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Aspirante;
import Class.EntidadEducativa;
import Class.Inscripcion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Agustin
 */
public class EntidadEducativaListModelListener implements ListSelectionListener {

    private final PantallaRegistrarAspirante pantallaRegistrarAspirante;

    public EntidadEducativaListModelListener(PantallaRegistrarAspirante pantallaRegistrarAspirante) {
        this.pantallaRegistrarAspirante = pantallaRegistrarAspirante;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // limpiar selecciones y tablas
        this.pantallaRegistrarAspirante.clearCompetenciaCategoria();
        this.pantallaRegistrarAspirante.getJListAspirante().clearSelection();
        // obtenemos la entidad educativa seleccionada
        EntidadEducativa seleccionado = this.pantallaRegistrarAspirante.obtenerEntidadEducativaSeleccionada();

        // actualizamos el aspirante asociado a la entidad educativa
        if (seleccionado != null) {
            List<Aspirante> aspirantesEntidadEducativa = this.pantallaRegistrarAspirante.getGestorRegistrarAspirante().buscarAspirantePorEntidadEducativa(seleccionado);
            aspirantesEntidadEducativa.addAll(cargarAspirantesPorInscripcion(seleccionado));
            this.pantallaRegistrarAspirante.getListAspiranteModel().setAspirante(aspirantesEntidadEducativa);
            this.pantallaRegistrarAspirante.getListAspiranteModel().fireListDataChanged();

        }
    }
    
    private List<Aspirante> cargarAspirantesPorInscripcion(EntidadEducativa seleccionado)
    {
        List<Aspirante> aspirantesInscripciones = new ArrayList();
        Iterator i = this.pantallaRegistrarAspirante.getRegistroAspirantesNuevos().iterator();
        while(i.hasNext())
        {
            Aspirante aux = (Aspirante) i.next();
            if(aux.getEntidadEducativa().equals(seleccionado))
                aspirantesInscripciones.add(aux);
        }
        return aspirantesInscripciones;
    }
    
    public void recargar()
    {
        // obtenemos la entidad educativa seleccionada
        EntidadEducativa seleccionado = this.pantallaRegistrarAspirante.obtenerEntidadEducativaSeleccionada();

        // actualizamos el aspirante asociado a la entidad educativa
        if (seleccionado != null) {
            List<Aspirante> aspirantesEntidadEducativa = this.pantallaRegistrarAspirante.getGestorRegistrarAspirante().buscarAspirantePorEntidadEducativa(seleccionado);
            aspirantesEntidadEducativa.addAll(cargarAspirantesPorInscripcion(seleccionado));
            this.pantallaRegistrarAspirante.getListAspiranteModel().setAspirante(aspirantesEntidadEducativa);
            this.pantallaRegistrarAspirante.getListAspiranteModel().fireListDataChanged();

        }
    }
}
