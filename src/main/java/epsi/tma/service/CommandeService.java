/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.service;

import epsi.tma.dao.ICommandeDAO;
import epsi.tma.entity.Commande;
import epsi.tma.enumClass.ActionEnum;
import epsi.tma.enumClass.MagasinEnum;
import java.sql.Timestamp;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author florentin
 */
@Service
public class CommandeService implements ICommandeService {

    private static final Logger LOG = LogManager.getLogger(CommandeService.class);

    @Autowired
    ICommandeDAO commandeDAO;
    
    @Autowired
    ICommandeStatutLogService commandeStatutLogService;

    @Override
    public int create(int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat) {
        return commandeDAO.create(pIdProduit, pIdMagasin, pIdEntrepot, pIdEtat);
    }

    @Override
    public void update(int newState, int idCommand) {
        commandeDAO.update(newState, idCommand);
    }

    /*
    * simulate command from magasin
    * @param idProduit
    * @param idMagasin
    * @param idEntrepot
    * @param idEtat
    */
    @Override
    public String simulateMagasinCommande(int idProduit, int idMagasin, int idEntrepot) {
        String response = new String();

        try{
        for (int i = 0; i < 10; i++) {
            int idCommande = create(idProduit, idMagasin, idEntrepot, 1);
            LOG.debug("SIMULATE magasin command : {}  - for product : {}",idMagasin,idProduit);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            commandeStatutLogService.create(MagasinEnum.getMagasinName(idMagasin), ActionEnum.getActionLibelle(1),idCommande ,timestamp ,idProduit, 1);
        }

        response = "SIMULATE SUCCESSFULLY";
        }catch (Exception exception){
        LOG.debug("CATCH EXCEPTION DURING SIMULATE MAGASIN COMMANDE :", exception);
        response = "ERROR DURING SIMULATE MAGASIN COMMANDE";
        }
        return response;
    }

    @Override
    public List<Commande> read() {
        return commandeDAO.read();
    }

    @Override
    public void clear() {
        commandeDAO.clear();
    }

    @Override
    public void clearTodayStatus() {
        commandeDAO.clearTodayStatus();
    }
}
