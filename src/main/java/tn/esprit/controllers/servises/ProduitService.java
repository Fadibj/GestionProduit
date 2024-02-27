package tn.esprit.controllers.servises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.controllers.entities.*;
import tn.esprit.controllers.utils.MyConnection;
public class ProduitService implements IService <Produit>  {

    Connection cn = MyConnection.getTest().getCnx();

    @Override
    public void Ajouter(Produit t) {
        try {
            String req = "INSERT INTO `Produit`(`nom`,`prix`,`description`,`categorie`)  VALUES (?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setFloat(2, t.getPrix());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getCategorie());
            pst.executeUpdate();
            System.out.println("Produit ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void Supprimer(int id) {
        System.out.println(id);
        try {
            String req = "DELETE FROM `Produit` WHERE `id` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Produit p) {
        try {
            String req = "UPDATE `Produit` SET `nom`='"+p.getNom()+"',`prix`='"+p.getPrix()+"',`description`='"+p.getDescription()+"',`categorie`='"+p.getCategorie()+"' WHERE `id`='"+p.getId()+"'";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public ResultSet Selectionner() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `Produit`";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            System.out.println(rs);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    @Override
    public ResultSet Getall() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `Produit`";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }

    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `produit` WHERE `id` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    @Override
    public List<Produit> afficher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
