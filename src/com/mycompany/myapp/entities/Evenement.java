/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author joujo
 */
public class Evenement {
      private int id;
    String nom;
    private String lieu;
    private String date;
    private String description;

    private String datefin;
    private int nbrPersonnes;
    

    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(String nom, String lieu, String date, String description, String datefin) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
    }

    public Evenement(int id, String nom, String lieu, String date, String description, String datefin) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
    }

    public Evenement(String nom, String lieu, String description) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
    }
    

    public Evenement(int id, String nom, String lieu, String date, String description, String datefin, int nbrPersonnes) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
        this.nbrPersonnes = nbrPersonnes;
    }

    public Evenement(String nom, String lieu, String date, String description,String datefin, int nbrPersonnes) {
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.description = description;
        this.datefin = datefin;
        this.nbrPersonnes = nbrPersonnes;
    }

    public Evenement(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public int getNbrPersonnes() {
        return nbrPersonnes;
    }

    public void setNbrPersonnes(int nbrPersonnes) {
        this.nbrPersonnes = nbrPersonnes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", date=" + date + ", description=" + description +", datefin=" + datefin + ", nbrPersonnes=" + nbrPersonnes + '}';
    }
}
    



    
