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
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Agustin
 */
public class CategoriaListaModelListener implements ListSelectionListener {
    
    private final PantallaRegistrarAspirante pantallaRegistrarAspirante;

    public CategoriaListaModelListener(PantallaRegistrarAspirante pantallaRegistrarAspirante)
    {
        this.pantallaRegistrarAspirante = pantallaRegistrarAspirante;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()==false)
        {
            try
            {
                Competencia competenciaSeleccionada = this.pantallaRegistrarAspirante.obtenerCompetenciaSeleccionada();
                Aspirante aspiranteSeleccionado = this.pantallaRegistrarAspirante.obtenerAspiranteSeleccionado();
                EntidadEducativa entidadEducativaSeleccionada = this.pantallaRegistrarAspirante.obtenerEntidadEducativaSeleccionada();
                Categoria categoriaSeleccionada = this.pantallaRegistrarAspirante.obtenerCategoriaSeleccionada();
                if((aspiranteSeleccionado == null || entidadEducativaSeleccionada==null || competenciaSeleccionada==null) && categoriaSeleccionada!=null)
                {
                    JOptionPane.showMessageDialog(pantallaRegistrarAspirante, "Debe seleccionar previamente un aspirante, una entidad educativa y un categoría", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    pantallaRegistrarAspirante.getJListCategoria().clearSelection();
                }
                else
                {
                    if(aspiranteSeleccionado != null && entidadEducativaSeleccionada!=null && competenciaSeleccionada != null && categoriaSeleccionada!=null && pantallaRegistrarAspirante.esAspiranteNuevo(aspiranteSeleccionado))
                    {
                        int filaComp = this.pantallaRegistrarAspirante.getJTableCompetencia().getSelectedRow();
                        if(this.pantallaRegistrarAspirante.getTablaCompetenciaModel().obtenerRegistroEn(filaComp))
                        {
                            modificarCategoriaDeInscripcion(aspiranteSeleccionado, competenciaSeleccionada, categoriaSeleccionada);
                        }
                    }
                }   
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(pantallaRegistrarAspirante, "Fallo la seleccion de categoria", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void modificarCategoriaDeInscripcion(Aspirante aspiranteSeleccionado, Competencia competenciaSeleccionada, Categoria categoriaSeleccionada)
    {
        Iterator i = this.pantallaRegistrarAspirante.getInscripciones().iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            if(aux.getAspirante().equals(aspiranteSeleccionado) && aux.getCompetencia().equals(competenciaSeleccionada))
            {
                aux.setCategoria(categoriaSeleccionada);
            }
        }
    }
}
