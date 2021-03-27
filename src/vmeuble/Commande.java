/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmeuble;

/**
 *
 * @author admin
 */
public class Commande {
    int id ;
    String username;
    public Commande(int id , String username){
        this.id=id;
        this.username=username;    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", username=" + username + '}';
    }
    
}
