/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

import java.util.Date;

/**
 *
 * @author itashi_
 */
public class Personnel {
    
    private String nom,prenom,genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    private int id;
    float salaire;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personnel( int id,String nom, String prenom, float salaire,String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.salaire = salaire;
        this.genre=genre;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    
   

    
}
