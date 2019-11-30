/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.service;

import epsi.tma.dao.ICommandeDAO;
import epsi.tma.entity.Commande;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author florentin
 */
@Service
public class CommandeService implements ICommandeService{
    
    @Autowired
    ICommandeDAO commandeDAO;
    
    @Override
    public void create(int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat) {
        this.commandeDAO.create(pIdProduit, pIdMagasin, pIdEntrepot, pIdEtat);
    }

    @Override
    public void updateState(int newState) {
        this.commandeDAO.updateState(newState);
    }

    @Override
    public String simulateMagasinCommande() {
        String response = new String();

        for (int i=1; i<=10; i++) {
           this.create(i,i,i,i);
           response += "Requête "+ i +"\n";
        }

        response += "Simulation create with success ";
        return response;
    }
    
}
