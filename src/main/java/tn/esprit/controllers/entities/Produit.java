package tn.esprit.controllers.entities;

import java.util.Date;
import java.util.Objects;

public class Produit {
    private int id;
    private String nom ;
    private int prix ;
    private String description ;
    private String categorie ;
    private String dateajout ;

    public Produit() {
    }

    public Produit(String nom, int  prix, String description, String categorie) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
    }

    public Produit(int id, String nom, int prix, String description, String categorie, String dateajout) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
        this.dateajout = dateajout;
    }

    public Produit(int id, String nom, int prix, String description, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie = categorie;
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDateajout() {
        return dateajout;
    }

    public void setDateajout(String dateajout) {
        this.dateajout = dateajout;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", categorie=" + categorie + ", dateajout=" + dateajout + '}';
    }

}

