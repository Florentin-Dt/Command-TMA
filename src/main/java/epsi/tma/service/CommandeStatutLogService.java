package epsi.tma.service;

import epsi.tma.dao.IDatabaseVersioningDAO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author utilisateur
 */
@Service
public class CommandeStatutLogService implements ICommandeStatutLogService{
    
    @Autowired
    private ICommandeStatutLogService commandeStatutLogService;
    
    @Override
    public Map<String, Object> read(){
        Map<String, Object> response = commandeStatutLogService.read();
        return response;
    }
}
