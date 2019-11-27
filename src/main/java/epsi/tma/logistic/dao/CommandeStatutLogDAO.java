package epsi.tma.logistic.dao;

import epsi.tma.database.DatabaseSpring;
import epsi.tma.entity.CommandeStatutLog;
import epsi.tma.logistic.factory.IFactoryCommandeStatutLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cDelage
 */
@Repository
public class CommandeStatutLogDAO implements ICommandeStatutLogDAO {

    private static final Logger LOG = LogManager.getLogger(CommandeStatutLogDAO.class);

    @Autowired
    private DatabaseSpring databaseSpring;

    @Autowired
    private IFactoryCommandeStatutLog factoryCommandeStatutLog;

    public Map<String, Object> read() {
        Map<String, Object> response = new HashMap();
        List<CommandeStatutLog> listeLog = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CommandeStatutLog");
        Connection connection = this.databaseSpring.connect();

        try {
            PreparedStatement prestat = connection.prepareStatement(query.toString());
            ResultSet resultSet = prestat.executeQuery();
            while (resultSet.next()) {
                System.out.println("start while dao");
                CommandeStatutLog commandeStatutLog = factoryCommandeStatutLog.create(resultSet.findColumn("idLog"),resultSet.findColumn("idCommande"), resultSet.findColumn("idEtat"), resultSet.getTimestamp("horodatage"), resultSet.getString("emmeteur"), resultSet.getString("Action"));
                listeLog.add(commandeStatutLog);
                System.out.println("End while dao");
            }
            System.out.println("response dao");
            response.put("Log-list", listeLog);
        } catch (SQLException exception) {
            response.put("Fail log-list loading", exception.getMessage());
            LOG.error("Fail log-list loading, exception : ", exception);
            System.out.println(exception);
        }
        return response;
    }

    //public String create(String emmeteur, String action, int idCommande, Timestamp horodatage, int idProduit)
}
