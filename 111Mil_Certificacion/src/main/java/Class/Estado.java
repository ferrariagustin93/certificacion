/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Agustin
 */

@Entity(name="estado")
@Table(name="estado")
public class Estado {
    
    @Id
    @Column(name="idEstado")
    private int idEstado;
    
    @Column(name="denominacion")
    private String denominacion;
    
    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    public Estado(int idEstado, String denominacion, List<Inscripcion> inscripciones) {
        this.idEstado = idEstado;
        this.denominacion = denominacion;
        this.inscripciones = inscripciones;
    }

    public Estado() {
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
    
}
