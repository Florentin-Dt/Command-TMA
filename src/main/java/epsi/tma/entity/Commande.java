/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epsi.tma.entity;

/**
 * Class Commande
 * @author florentin
 */
public class Commande {
    private int idCommande;
    private int idProduit;
    private int idMagasin;
    private int idEntrepot;
    
    public int getIdCommande(){
        return this.idCommande;
    }
    
    public int getIdProduit(){
        return this.idProduit;
    }
    
    public int getIdMagasin(){
        return this.idMagasin;
    }
    
    public int getIdEntrepot(){
        return this.idEntrepot;
    }
    
    public void setIdCommande(int pIdCommande){
        this.idCommande = pIdCommande;
    }
    
    public void setIdProduit(int pIdProduit){
        this.idCommande = pIdProduit;
    }
    
    public void setIdMagasin(int pIdMagasin){
        this.idCommande = pIdMagasin;
    }
    
    public void setIdEntrepot(int pIdEntrepot){
        this.idCommande = pIdEntrepot;
    }
}
