/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interfaz.PantallaRegistrarAspirante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Agustin
 */
public class Main {
    public static void main(String []args)
    {
        EntityManagerFactory emf = null;
        EntityManager gestor = null;
        try
        {
            new GestorRegistrarAspirante(emf,gestor).run();   
        }
        catch(Exception error)
        {
            System.exit(0);
        }
        
    }
}
