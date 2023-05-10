/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author 21692
 */
public class Article {

    
    private int id;    
    private String nom;
    private String description;
    private String titre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article() {
    }

    public Article(int id, String nom, String descroption, String titre) {
        this.id = id;
        this.nom = nom;
        this.description = descroption;
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

           
}
