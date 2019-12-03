/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.service;

import epsi.tma.dao.ICommandeDAO;
import epsi.tma.entity.Commande;
import epsi.tma.enumClass.ActionEnum;
import epsi.tma.enumClass.EntrepotEnum;
import epsi.tma.enumClass.MagasinEnum;
import epsi.tma.enumClass.ProduitEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        try {
            for (int i = 0; i < 10; i++) {
                int idCommande = create(idProduit, idMagasin, idEntrepot, 1);
                LOG.debug("SIMULATE magasin command : {}  - for product : {}", idMagasin, idProduit);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String action = " create order " + idCommande;
                commandeStatutLogService.create(MagasinEnum.getMagasinName(idMagasin), action, idCommande, timestamp, idProduit, 1, "CREATE");
            }

            response = "SIMULATE SUCCESSFULLY";
        } catch (Exception exception) {
            LOG.debug("CATCH EXCEPTION DURING SIMULATE MAGASIN COMMANDE :", exception);
            response = "ERROR DURING SIMULATE MAGASIN COMMANDE";
        }
        return response;
    }

    @Override
    public String updateCommand(int newState, int idCommande) {
        String response = new String();
        try {
            response = update(newState, idCommande);
            if (response.compareTo("UPDATE SUCCESSFULLY") == 0) {
                LOG.debug("UPDATE {} order to {} status : {} ", idCommande, newState, response);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String action = "update " + idCommande + " order";
                LOG.debug("TRY TO LOG ACTION : {}", action);
                commandeStatutLogService.create("ENTREPOT 1", action, idCommande, timestamp, 1, newState, "UPDATE");
            } else {
                String action = "fail to update " + idCommande + " order";
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                commandeStatutLogService.create("ENTREPOT 1", action, idCommande, timestamp, 1, newState, "ERROR");
            }
        } catch (Exception exception) {
            response = exception.getLocalizedMessage();
        }

        return response;
    }
    
    @Override
    public String updateAllCommand(int oldState, int newState) {
        String response = new String();
        List<Commande> commandes = new ArrayList();
        
        try {
            commandes = commandeDAO.readByStatus(oldState);
            try {
                response = updateAll(oldState, newState);
                if (response.compareTo("UPDATE SUCCESSFULLY") == 0) {
                    LOG.debug("UPDATE order status {} to {} status : {} ", oldState, newState, response);
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String action = "update " + oldState + " order";
                    LOG.debug("TRY TO LOG ACTION : {}", action);
                    for (Commande cmd : commandes){
                        commandeStatutLogService.create("ENTREPOT 1", action, cmd.getIdCommande(), timestamp, 1, newState, "UPDATE");
                    }
                } else {
                    String action = "fail to update order with "+ oldState +"status to "+ newState;
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    for (Commande cmd : commandes){
                        commandeStatutLogService.create("ENTREPOT 1", action, cmd.getIdCommande(), timestamp, 1, newState, "ERROR");
                    }
                }
            } catch (Exception e) {
                response = e.getLocalizedMessage();
            }
        } catch (Exception e){

        }

        return response;
    }

    @Override
    public List<Map<String, Object>> readFormater() {
        List<Commande> result = read();
        List<Map<String, Object>> response = new ArrayList();
        for (Commande i : result) {
            Map<String, Object> resp = new HashMap();
            resp.put("MAGASIN", MagasinEnum.getMagasinName(i.getIdMagasin()));
            resp.put("PRODUIT", ProduitEnum.getProduitName(i.getIdProduit()));
            resp.put("ENTREPOT", EntrepotEnum.getEntrepotName(i.getIdProduit()));
            resp.put("IDETAT", i.getIdEtat());
            resp.put("IDCOMMANDE", i.getIdCommande());
            response.add(resp);
        }
        return response;
    }
    
    @Override
    public List<Map<String, Object>> readStatusFormater(int status) {
        List<Commande> result = readByStatus(status);
        List<Map<String, Object>> response = new ArrayList();
        for (Commande i : result) {
            Map<String, Object> resp = new HashMap();
            resp.put("MAGASIN", MagasinEnum.getMagasinName(i.getIdMagasin()));
            resp.put("PRODUIT", ProduitEnum.getProduitName(i.getIdProduit()));
            resp.put("ENTREPOT", EntrepotEnum.getEntrepotName(i.getIdProduit()));
            resp.put("IDETAT", i.getIdEtat());
            resp.put("IDCOMMANDE", i.getIdCommande());
            response.add(resp);
        }
        return response;
    }

    @Override
    public int create(int pIdProduit, int pIdMagasin, int pIdEntrepot, int pIdEtat) {
        return commandeDAO.create(pIdProduit, pIdMagasin, pIdEntrepot, pIdEtat);
    }

    @Override
    public String update(int newState, int idCommand) {
        return commandeDAO.update(newState, idCommand);
    }
    
    @Override
    public String updateAll(int oldState, int newState) {
        return commandeDAO.updateAll(oldState, newState);
    }

    @Override
    public List<Commande> read() {
        return commandeDAO.read();
    }

    @Override
    public void clear() {
        commandeDAO.clear();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        commandeStatutLogService.create("ADMIN", "Clear all order", 0, timestamp, 0, 0, "DELETE");
    }

    @Override
    public void clearTodayStatus() {
        commandeDAO.clearTodayStatus();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        commandeStatutLogService.create("ADMIN", "Clear status 4 order", 0, timestamp, 0, 0, "DELETE");

    }
    
    @Override
    public List<Commande> readByStatus(int status){
        return commandeDAO.readByStatus(status);
    }
}
