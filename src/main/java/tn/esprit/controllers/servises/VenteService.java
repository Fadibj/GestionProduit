package tn.esprit.controllers.servises;

import tn.esprit.controllers.entities.*;
import tn.esprit.controllers.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenteService implements IService <Vente>  {

    Connection cn = MyConnection.getTest().getCnx();

    @Override
    public void Ajouter(Vente t) {
        try {
            String req = "INSERT INTO `Vente`( `quantitevendu`, `montanttotal`, `idProduit`) VALUES  (?,?,?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, t.getQuantitevendu());
            pst.setInt(2, t.getMontanttotal());
            pst.setInt(3, t.getIdproduit());
            pst.executeUpdate();
            System.out.println("Vente ajouté !");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void Supprimer(int id) {
        System.out.println(id);
        try {
            String req = "DELETE FROM `Vente` WHERE `id` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Vente p) {

    }
    @Override
    public ResultSet Selectionner() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `Vente`";
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
            String req = "SELECT * FROM `Vente`";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    }



    @Override
    public List<Produit> afficher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
