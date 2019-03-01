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
import Class.Estado;
import Class.Inscripcion;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Agustin
 */
public class CompetenciaTablaModelListener implements ListSelectionListener {

    private final PantallaRegistrarAspirante pantallaRegistrarAspirante;
    
    public CompetenciaTablaModelListener(PantallaRegistrarAspirante pantallaRegistrarAspirante) {
        this.pantallaRegistrarAspirante = pantallaRegistrarAspirante;
    }
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
    
        
        try
        {
            Competencia competenciaSeleccionada = this.pantallaRegistrarAspirante.obtenerCompetenciaSeleccionada();
            Aspirante aspiranteSeleccionado = this.pantallaRegistrarAspirante.obtenerAspiranteSeleccionado();
            EntidadEducativa entidadEducativaSeleccionada = this.pantallaRegistrarAspirante.obtenerEntidadEducativaSeleccionada();
            if((aspiranteSeleccionado == null || entidadEducativaSeleccionada==null) && competenciaSeleccionada!=null)
            {
                JOptionPane.showMessageDialog(pantallaRegistrarAspirante, "Debe seleccionar previamente un aspirante y una entidad educativa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                //pantallaRegistrarAspirante.getJTableCompetencia().clearSelection();
                pantallaRegistrarAspirante.getJListCategoria().clearSelection();
            }
            else
            {
                if(aspiranteSeleccionado != null && pantallaRegistrarAspirante.esAspiranteNuevo(aspiranteSeleccionado) && competenciaSeleccionada!=null && entidadEducativaSeleccionada!=null)
                {
                    
                    // Diferenciar clik para modifcar estado de registro de seleccionar columna!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    //int fila = this.pantallaRegistrarAspirante.getTablaCompetenciaModel().obtenerFilaPorCompetencia(competenciaSeleccionada);
                    int fila = this.pantallaRegistrarAspirante.getJTableCompetencia().getSelectedRow();
                    int column = this.pantallaRegistrarAspirante.getJTableCompetencia().getSelectedColumn();
                    this.pantallaRegistrarAspirante.getJListCategoria().clearSelection();
                    if(column==0)
                    {
                        if((Boolean)this.pantallaRegistrarAspirante.getTablaCompetenciaModel().getValueAt(fila,0)== false )
                        {
                            Categoria categoria = sugerirCategoria(aspiranteSeleccionado);
                            cargarInscripcion(aspiranteSeleccionado,competenciaSeleccionada, categoria);
                            this.pantallaRegistrarAspirante.getTablaCompetenciaModel().modificarRegistroEn(fila);
                            seleccionarListaCategoria(categoria);
                        }
                        else
                        {
                            eliminarInscripcion(aspiranteSeleccionado,competenciaSeleccionada);
                            this.pantallaRegistrarAspirante.getTablaCompetenciaModel().modificarRegistroEn(fila);
                        }    
                    }
                    else
                    {
                        if((Boolean)this.pantallaRegistrarAspirante.getTablaCompetenciaModel().getValueAt(fila, 0)==true)
                        {
                            Categoria categoria = buscarCategoria(aspiranteSeleccionado,competenciaSeleccionada);
                            if(categoria != null)
                            {
                                seleccionarListaCategoria(categoria);
                            }
                        }
                    }
                    
                    
                }
            }   
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(pantallaRegistrarAspirante, "Debe Seleccionar un aspirante", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            //pantallaRegistrarAspirante.getJTableCompetencia().clearSelection();
            pantallaRegistrarAspirante.getJListCategoria().clearSelection();
        }        
    }
    
    
    private void cargarInscripcion(Aspirante aspiranteSeleccionado,Competencia competenciaSeleccionada, Categoria categoria)
    {
        Inscripcion i = new Inscripcion();
        i.setAspirante(aspiranteSeleccionado);
        i.setFecha(new Date());
        i.setCompetencia(competenciaSeleccionada);
        i.setCategoria(categoria);
        i.setIdInscripcion(new Date().hashCode());
        Estado e = this.pantallaRegistrarAspirante.getGestorRegistrarAspirante().buscarEstadoPorId(1);
        i.setEstado(e);
        this.pantallaRegistrarAspirante.agregarInscripcion(i);
    }
    
    private Categoria sugerirCategoria(Aspirante aspiranteSeleccionado)
    {
        List<Categoria> categorias = this.pantallaRegistrarAspirante.getGestorRegistrarAspirante().buscarCategorias();
        int edad = calcularEdad(aspiranteSeleccionado.getFechaDeNac());
        Iterator i = categorias.iterator();
        while(i.hasNext())
        {
            Categoria aux = (Categoria) i.next();
            if(edad>=aux.getLimiteInferiorEdad() && edad<=aux.getLimiteSuperiorEdad() && aux.getSexo()==aspiranteSeleccionado.getSexo())
                return aux;
        }
        return categorias.get(0);
    }
    
    private void eliminarInscripcion(Aspirante aspiranteSeleccionado, Competencia competenciaSeleccionada)
    {
        List <Inscripcion> inscripciones = this.pantallaRegistrarAspirante.getInscripciones();
        Iterator i = inscripciones.iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            if(aux.getAspirante().equals(aspiranteSeleccionado) && aux.getCompetencia().equals(competenciaSeleccionada))
            {
                this.pantallaRegistrarAspirante.getInscripciones().remove(aux);
                break;
            }
        }
    }
    
    private int calcularEdad(Date date)
    {
        Date now = new Date();
        int diaNow = now.getDate(), mesNow = now.getMonth()+1, anioNow =now.getYear()+1900;
        int diaEdad = date.getDate(), mesEdad = date.getMonth()+1, anioEdad = date.getYear()+1900;
        int result =anioNow-anioEdad-1;
        if(mesNow > mesEdad)
        {
            result++;
        }
        else
        {
            if(mesNow==mesEdad && diaNow>=diaEdad)
                result++;
        }
        System.out.println("Result tiene:"+result+"------------------------------------------");
        return result;
    }
    
    private Categoria buscarCategoria(Aspirante aspiranteSeleccionado, Competencia competenciaSeleccionada)
    {        
        Iterator i = this.pantallaRegistrarAspirante.getInscripciones().iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            if(aux.getAspirante().equals(aspiranteSeleccionado) && aux.getCompetencia().equals(competenciaSeleccionada))
            {
                return aux.getCategoria();
            }
        }
        
        return null;
    }
    
    private void seleccionarListaCategoria(Categoria categoria)
    {
        this.pantallaRegistrarAspirante.getJListCategoria().setSelectedIndex(this.pantallaRegistrarAspirante.getListaCategoriaModel().obtenerFilaPorCategoria(categoria));
    }
    
    
    
}
