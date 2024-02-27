package tn.esprit.controllers.servises;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import tn.esprit.controllers.entities.*;
public interface IService <T>{
    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(T t);
    public ResultSet Selectionner();
    public List<Produit> afficher(int id);
    public ResultSet Getall();
}
