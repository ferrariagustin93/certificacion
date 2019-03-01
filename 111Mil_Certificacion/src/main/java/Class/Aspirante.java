/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Agustin
 */

@Entity(name="aspirante")
@Table(name="aspirante")
public class Aspirante {
    @Id
    @Column(name="idAspirante")
    private int idAspirante;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="fechaDeNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeNac;
    
    // 1 - Masculino // 2 - Femenino
    @Column(name="sexo")
    private int sexo;
    
    @Column(name="dni")
    private int dni;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "EE_idEntidadEducativa")
    private EntidadEducativa entidadEducativa;
    
    @OneToMany(mappedBy = "aspirante", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;
    
    public Aspirante(int idAspirante, String nombre, String apellido, String direccion, Date fechaDeNac, int sexo, int dni, EntidadEducativa entidadEducativa) {
        this.idAspirante = idAspirante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.fechaDeNac = fechaDeNac;
        this.sexo = sexo;
        this.dni = dni;
        this.entidadEducativa = entidadEducativa;
        this.inscripciones = new ArrayList();
    }

    public Aspirante() {
        this.inscripciones = new ArrayList();
    }

    public int getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(int idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaDeNac() {
        return fechaDeNac;
    }

    public void setFechaDeNac(Date fechaDeNac) {
        this.fechaDeNac = fechaDeNac;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public EntidadEducativa getEntidadEducativa() {
        return entidadEducativa;
    }

    public void setEntidadEducativa(EntidadEducativa entidadEducativa) {
        this.entidadEducativa = entidadEducativa;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

       
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellido;
    }
    
    public boolean equals(Aspirante a)
    {
        return a.getDni()==this.dni;
    }
    
        
}
