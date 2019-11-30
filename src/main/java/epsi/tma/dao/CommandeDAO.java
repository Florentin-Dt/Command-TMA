/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.dao;

import epsi.tma.database.DatabaseSpring;
import epsi.tma.entity.DatabaseVersioning;
import epsi.tma.factory.IFactoryCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author florentin
 */
public class CommandeDAO implements ICommandeDAO {
    
    private static final Logger LOG = LogManager.getLogger(CommandeDAO.class);
    
    @Autowired
    private DatabaseSpring databaseSpring;

    @Autowired
    private IFactoryCommande factoryCommande;

    @Override
    public void create(int idCommande, int idProduit, int idMagasin, int idEntrepot, int idEtat) {
        
        final String query = "INSERT INTO Commande(idCommande, idProduit, idMagasin, IdEntrepot) VALUES(?, ?, ?, ?)";  
        
        try {
            Connection connection = this.databaseSpring.connect();
            try {
                PreparedStatement preStat = connection.prepareStatement(query);
                try { 
                    preStat.setInt(1, idCommande);
                    preStat.setInt(2, idProduit);
                    preStat.setInt(3, idMagasin);
                    preStat.setInt(4, idEntrepot);
                    preStat.executeUpdate();
                } catch (SQLException exception) { 
                    LOG.warn("Unable to execute query : " + exception.toString());
                } finally {
                    preStat.close();
                }
            } catch (SQLException exception) {
                LOG.warn("Unable to execute query : " + exception.toString());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    LOG.warn(e.toString());
                }
            }
        } catch (Exception exception) {
            LOG.error("Failed to connect to database, catched Exception : ", exception);
        }
    }
}
