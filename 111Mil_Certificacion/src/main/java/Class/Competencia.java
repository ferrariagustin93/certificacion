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
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Agustin
 */

@Entity(name="competencia")
@Table(name="competencia")
public class Competencia {
    @Id
    @Column(name="idCompetencia")
    private int idCompetencia;
    
    @Column(name="nombre")
    private String nombre;
    
    @OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    public Competencia(int idCompetencia, String nombre, List<Inscripcion> inscripciones) {
        this.idCompetencia = idCompetencia;
        this.nombre = nombre;
        this.inscripciones = inscripciones;
    }

    public Competencia() {
    }

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return "Competencia{" + "idCompetencia=" + idCompetencia + ", nombre=" + nombre + '}';
    }
    
    public boolean equals(Competencia c)
    {
        return c.getIdCompetencia()==this.idCompetencia;
    }
}
