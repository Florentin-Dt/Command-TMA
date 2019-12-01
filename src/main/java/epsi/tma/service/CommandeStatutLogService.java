package epsi.tma.service;

import epsi.tma.dao.ICommandeStatutLogDAO;
import epsi.tma.entity.CommandeStatutLog;
import epsi.tma.enumClass.ActionEnum;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author cDelage
 */
@Service
public class CommandeStatutLogService implements ICommandeStatutLogService {

    private static final Logger LOG = LogManager.getLogger(CommandeStatutLogService.class);

    @Autowired
    private ICommandeStatutLogDAO commandeStatutLogDAO;

   /*
    * method to parse log from object to string like 'HH:MM:DD [TYPE] USER make ACTION for Order...'
    */
    @Override
    public List<String> logParser() {
        List<String> response = new ArrayList();
        List<CommandeStatutLog> logs = read();
        String logger;
        for (CommandeStatutLog log : logs) {
            if (log.getType().compareTo("DELETE") == 0) {
                logger = log.getHorodatage().toString()+ " [" + log.getType() + "] " + log.getEmmeteur() + " "+ log.getAction();
            } else {
                logger = log.getHorodatage().toString() + " [" + log.getType() + "] " + log.getEmmeteur() + " " + log.getType() + " order " + log.getIdCommande() + " to status " + log.getIdEtat()+ " " +ActionEnum.getActionLibelle(log.getIdEtat());
            }
            response.add(logger);
        }
        return response;
    }

    @Override
    public List<CommandeStatutLog> read() {
        return commandeStatutLogDAO.read();
    }

    @Override
    public String create(String emmeteur, String action, int idCommande, Timestamp horodatage, int idProduit, int idEtat, String type) {
        return commandeStatutLogDAO.create(emmeteur, action, idCommande, horodatage, idProduit, idEtat, type);
    }
}
