/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.dao;

/**
 *
 * @author florentin
 */
public interface ICommandeDAO {
    /**
     * Function to generate orders
     * 
     */
    public void create(int idProduit, int idMagasin, int idEntrepot, int idEtat);
    public void updateState(int newState);
}
