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

@Entity(name="categoria")
@Table(name="categoria")
public class Categoria {
    
    @Id
    @Column(name="idCategoria")
    private int idCategoria;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="limiteInferiorEdad")
    private int limiteInferiorEdad;
    
    @Column(name="limiteSuperiorEdad")
    private int limiteSuperiorEdad;
    
    @Column(name="sexo")
    private int sexo;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List <Inscripcion> inscripciones;

    public Categoria(int idCategoria, String nombre, int limiteInferiorEdad, int limiteSuperiorEdad, int sexo, List<Inscripcion> inscripciones) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.limiteInferiorEdad = limiteInferiorEdad;
        this.limiteSuperiorEdad = limiteSuperiorEdad;
        this.sexo = sexo;
        this.inscripciones = inscripciones;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLimiteInferiorEdad() {
        return limiteInferiorEdad;
    }

    public void setLimiteInferiorEdad(int limiteInferiorEdad) {
        this.limiteInferiorEdad = limiteInferiorEdad;
    }

    public int getLimiteSuperiorEdad() {
        return limiteSuperiorEdad;
    }

    public void setLimiteSuperiorEdad(int limiteSuperiorEdad) {
        this.limiteSuperiorEdad = limiteSuperiorEdad;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
    
    public String toString()
    {
        return this.nombre;
    }
    
    public boolean equals(Categoria c)
    {
        return c.getIdCategoria()==this.idCategoria;
    }
    
}
