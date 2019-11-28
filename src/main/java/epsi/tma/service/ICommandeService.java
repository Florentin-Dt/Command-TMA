/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.service;

/**
 *
 * @author florentin
 */
public interface ICommandeService {
    
    /**
     * Function to generate n orders
     * @param n number of order that you want to generate
     */
    public void generateCommande(int n);
}
