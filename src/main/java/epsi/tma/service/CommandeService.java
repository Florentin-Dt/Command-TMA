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
    public void create(int pIdCommande, int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat) {
        this.commandeDAO.create(pIdCommande, pIdProduit, pIdMagasin, pIdEntrepot, pIdEtat);
    }

    @Override
    public void update(List<Commande> commandes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String simulateMagasinCommande() {
        String response = new String();

        for (int i=1; i<=10; i++) {
           create(i,i,i,i,i);
           response += "Requête "+ i +"\n";
        }

        response += "Simulation create with success ";
        return response;
    }
    
}
