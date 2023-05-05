/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Amoulette
 */
public class Annonce {
    private int id  ;
    private String nom,image , descreption,titre ,tag;
     private int tel ;
     private String email,local,etat,categorie;

    public Annonce() {
    }

    public Annonce(int id, String nom, String image, String descreption, String titre, String tag, int tel, String email, String local, String etat, String categorie) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tag = tag;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }

    public Annonce(String nom, String descreption, String titre, String etat, String categorie) {
        this.nom = nom;
        this.descreption = descreption;
        this.titre = titre;
        this.etat = etat;
        this.categorie = categorie;
    }

    public Annonce(int id, String nom, String descreption, String titre, String etat, String categorie) {
        this.id = id;
        this.nom = nom;
        this.descreption = descreption;
        this.titre = titre;
        this.etat = etat;
        this.categorie = categorie;
    }

    
 


    public Annonce(String nom, String image, String descreption, String titre, String tag, int tel, String email, String local, String etat, String categorie) {
        this.nom = nom;
        this.image = image;
        this.descreption = descreption;
        this.titre = titre;
        this.tag = tag;
        this.tel = tel;
        this.email = email;
        this.local = local;
        this.etat = etat;
        this.categorie = categorie;
    }

   /* public Annonce(int i, String toString, String toString0, String toString1, String toString2, String toString3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    
    /*public Annonce(String toString, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    public int getId() {
        return id;
    }

    public int getTel() {
        return tel;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDescreption() {
        return descreption;
    }

    public String getTitre() {
        return titre;
    }

    public String getTag() {
        return tag;
    }

    public String getEmail() {
        return email;
    }

    public String getLocal() {
        return local;
    }

    public String getEtat() {
        return etat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Annonce{" + "nom=" + nom + ", image=" + image + ", descreption=" + descreption + ", titre=" + titre + ", tag=" + tag + ", tel=" + tel + ", email=" + email + ", local=" + local + ", etat=" + etat + ", categorie=" + categorie + '}';
    }
    
    
    
    
}
