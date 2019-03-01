/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Agustin
 */
public class PrincipalTest {
    public static void main(String []args)
    {
        EntityManagerFactory emf;
        EntityManager manager;
        
        /*  Crear gestor de persistencia (EM)   */
        emf = Persistence.createEntityManagerFactory("myConnectionBD");
        manager = emf.createEntityManager();
        
        try
        {
            Aspirante a = manager.find(Aspirante.class, 1);
            System.out.println(a.toString());
            
            
            
            manager.close();
            emf.close();    
        }
        catch(Exception error)
        {
            error.printStackTrace();
        }
    }
    
}
