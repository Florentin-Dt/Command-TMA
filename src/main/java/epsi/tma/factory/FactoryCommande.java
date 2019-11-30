/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.factory;

import epsi.tma.entity.Commande;

/**
 * Factory to create Commande instance
 * @author florentin
 */
public class FactoryCommande implements IFactoryCommande {

    @Override
    public Commande create(int pIdCommande, int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat) {
        Commande commande = new Commande();
        commande.setIdCommande(pIdCommande);
        commande.setIdProduit(pIdProduit);
        commande.setIdMagasin(pIdMagasin);
        commande.setIdEntrepot(pIdEntrepot);
        commande.setIdEtat(pIdEtat);
        return commande;
    }
}
