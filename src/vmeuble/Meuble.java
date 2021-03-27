/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

/**
 *
 * @author itashi_
 */
public class Meuble {
    int id;
     int qte;
    public Meuble(int id,int qte , String categorie, String descriptif, String couleur, float prix) {
        this.id = id;
        this.categorie = categorie;
        this.descriptif = descriptif;
        this.couleur = couleur;
        this.prix = prix;
        this.qte = qte;
    }
    String categorie,descriptif,couleur;
    float prix;

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getQte() {
        return qte;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    

    @Override
    public String toString() {
        return "Meuble{" + "id=" + id + ", qte=" + qte + ", categorie=" + categorie + ", descriptif=" + descriptif + ", couleur=" + couleur + ", prix=" + prix + '}';
    }
    
}
