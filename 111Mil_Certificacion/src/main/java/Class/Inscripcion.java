/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Agustin
 */

@Entity(name="inscripcion")
@Table(name="inscripcion")
public class Inscripcion {
    
    @Id
    @Column(name="idInscripcion")
    private int idInscripcion;
    
    @Column(name="fecha")
    private Date fecha;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "A_idAspirante")
    private Aspirante aspirante;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "CA_idCategoria")
    private Categoria categoria;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_idCompetencia")
    private Competencia competencia;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "E_idEstado")
    private Estado estado;

    public Inscripcion(int idInscripcion, Date fecha, Aspirante aspirante, Categoria categoria, Competencia competencia, Estado estado) {
        this.idInscripcion = idInscripcion;
        this.fecha = fecha;
        this.aspirante = aspirante;
        this.categoria = categoria;
        this.competencia = competencia;
        this.estado = estado;
    }

    public Inscripcion() {
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Aspirante getAspirante() {
        return aspirante;
    }

    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripcion=" + idInscripcion + ", fecha=" + fecha + ", aspirante=" + aspirante + ", categoria=" + categoria + ", competencia=" + competencia + ", estado=" + estado + '}';
    }
    
    public boolean equals(Inscripcion i)
    {
        return this.idInscripcion==i.getIdInscripcion();
    }
    
}
