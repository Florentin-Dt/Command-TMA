/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.service;

import epsi.tma.entity.Commande;
import java.util.List;

/**
 *
 * @author florentin
 */
public interface ICommandeService {
    
    public void create(int pIdCommande, int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat);
    public void update(List<Commande> commandes);
    public String simulateMagasinCommande();
}
