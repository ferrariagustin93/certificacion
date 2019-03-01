/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoInterfaz;

import Class.Aspirante;
import Class.EntidadEducativa;
import java.util.List;

/**
 *
 * @author Agustin
 */
public interface AspiranteDao {
    
    public List<Aspirante> buscarAspirantePorEntidadEducativa(EntidadEducativa entidadEducativa);
    public boolean buscarAspiranteDNI(int DniNuevoAspirante);
}
