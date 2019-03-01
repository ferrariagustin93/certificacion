/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.ArrayList;
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
@Entity(name="entidadeducativa")
@Table(name="entidadeducativa")
public class EntidadEducativa {
    @Id
    @Column(name="idEntidadEducativa")
    private int idEntidadEducativa;
    
    @Column(name="nombre")
    private String nombre;
    
    @OneToMany( mappedBy = "entidadEducativa", cascade = CascadeType.ALL )
    private List <Aspirante> aspirantes;
    
    public EntidadEducativa()
    {
        this.aspirantes = new ArrayList<Aspirante>();
    }
    
    public EntidadEducativa(int idEntidadEducativa, String nombre, List aspirantes )
    {
        this.idEntidadEducativa = idEntidadEducativa;
        this.nombre = nombre;
        this.aspirantes = aspirantes;
    }

    public int getIdEntidadEducativa() {
        return idEntidadEducativa;
    }

    public void setIdEntidadEducativa(int idEntidadEducativa) {
        this.idEntidadEducativa = idEntidadEducativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List getAspirantes() {
        return aspirantes;
    }

    public void setAspirantes(List aspirantes) {
        this.aspirantes = aspirantes;
    }

    @Override
    public String toString() {
        return this.nombre ;
    }
    
    
    
}
