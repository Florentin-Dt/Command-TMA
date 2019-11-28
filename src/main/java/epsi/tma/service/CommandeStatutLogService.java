package epsi.tma.service;

import epsi.tma.dao.ICommandeStatutLogDAO;
import java.sql.Timestamp;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Service to read and create log from command handling
 * @author cDelage
 */
@Service
public class CommandeStatutLogService implements ICommandeStatutLogService{
    
    private static final Logger LOG = LogManager.getLogger(DatabaseVersioningService.class);

    @Autowired
    private ICommandeStatutLogDAO commandeStatutLogDAO;
    
    @Override
    public Map<String, Object> read(){
        return commandeStatutLogDAO.read();
    }
    
    @Override
    public String create(String emmeteur, String action, int idCommande, Timestamp horodatage, int idProduit, int idEtat){
        LOG.debug("CREEATE STATUT LOG IN STATLOG SERVICE");
        return commandeStatutLogDAO.create(emmeteur, action, idCommande, horodatage, idProduit, idEtat);
    }
}
