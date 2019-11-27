/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.entity;

import java.sql.Timestamp;

/**
 *
 * @author cDelage
 */
public class CommandeStatutLog {

    private int idLog;
    private int idCommande;
    private int idEtat;
    private Timestamp horodatage;
    private String emmeteur;
    private String action;

    public int getIdCommande() {
        return idCommande;
    }
    
    public int getIdLog() {
        return idLog;
    }
    
    public int getIdEtat() {
        return idEtat;
    }

    public Timestamp getHorodatage() {
        return horodatage;
    }

    public String getEmmeteur() {
        return emmeteur;
    }

    public String getAction() {
        return action;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setIdEtat(int idEtat) {
        this.idEtat = idEtat;
    }

    public void setHorodatage(Timestamp horodatage) {
        this.horodatage = horodatage;
    }

    public void setEmmeteur(String emmeteur) {
        this.emmeteur = emmeteur;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setIdLog(int statutLog) {
        this.idLog = statutLog;
    }

}
