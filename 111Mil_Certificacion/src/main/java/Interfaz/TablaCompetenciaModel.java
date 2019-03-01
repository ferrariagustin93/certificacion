/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Competencia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Agustin
 */
public final class TablaCompetenciaModel extends AbstractTableModel {

    // Nombre de las columnas de la tabla que contiene información de las disciplinas
    private static final String[] COLUMNAS = { "", "Disciplina" };
    
    // Listado de las competencias existentes.
    private List<Competencia> competencias;
    
    // Estado de la competencia según el aspirante.
    private List<Boolean> registro = new ArrayList();

    public TablaCompetenciaModel (List<Competencia> competencia) {
        super();
        this.competencias = competencia;
        cargarRegistro();
        
    }

    @Override
    public int getRowCount() {
        return this.competencias.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object retorno = null;
        Competencia competencia = competencias.get(fila);
        Boolean registro = this.registro.get(fila);
        // según la columna deseada obtenemos el valor a mostrar
        switch (columna) {
            case 0:
                retorno = registro;
                break;
            case 1:
                retorno = competencia.getNombre();
                break;            
        }
        
        return retorno;
    }
    public int getSize()
    {
        return this.getRowCount();
    }
    
    @Override
    public String getColumnName(int index) {
        return COLUMNAS[index];
    }
    
    
    
    public Competencia obtenerCompetenciaEn (int fila) {
        return competencias.get(fila);
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }
    
    public Boolean obtenerRegistroEn (int fila) {
        return registro.get(fila);
    }

    public void setRegistro(List<Boolean> registro) {
        this.registro = registro;
    }
    
    public boolean isCellEditable(int fila, int columna) {
        return (columna == 0);
    }
    
    public void setValueAt(Object value, int fila, int columna) {
        if(columna==0)
        {
            if(this.registro.get(fila))
            {
                this.registro.set(fila, Boolean.FALSE);
            }
            else
            {
                this.registro.set(fila, Boolean.TRUE);
            }
        }
    }
    
    public Class getColumnClass(int columna) {
        return (getValueAt(0, columna).getClass());
    }
    
    public void cargarRegistro()
    {
        for(int i = 0;i<this.competencias.size();i++)
        {
            this.registro.add(Boolean.FALSE);
        }
    }
    
    public int obtenerFilaPorCompetencia(Competencia competencia)
    {
        return this.competencias.indexOf(competencia);
    }
    
    public void modificarRegistroEn(int fila)
    {
        this.registro.set(fila, !this.registro.get(fila));
    }
    
    public void modificarRegistroEnTrue(int fila)
    {
        this.registro.set(fila,true);
    }
    
    public void clearTable()
    {
        int i = 0;
        while(i<this.registro.size())
        {
            this.registro.set(i,false);
            i++;
        }        
    }
    
}
