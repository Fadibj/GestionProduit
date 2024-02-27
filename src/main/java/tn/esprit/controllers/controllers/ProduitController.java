package tn.esprit.controllers.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.controllers.entities.Produit;
import tn.esprit.controllers.entities.Vente;
import tn.esprit.controllers.servises.MyListener;
import tn.esprit.controllers.servises.VenteService;

import java.awt.event.MouseEvent;

public class ProduitController {

    private Produit Produit;
    private MyListener myListener;
    VenteService vs = new VenteService();
    @FXML
    private Label categorie;

    @FXML
    private Label desc;

    @FXML
    private Label id;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    @FXML
    private TextField tf_qte;

    public void setData(Produit p, MyListener myListener) {
        this.Produit = p;
        this.myListener = myListener;
        nom.setText(p.getNom());
        desc.setText(p.getDescription());
        prix.setText(String.valueOf(p.getPrix()));
        categorie.setText(p.getCategorie());
        id.setText(String.valueOf(p.getId()));
    }
    private void singleprod(MouseEvent mouseEvent) {
        myListener.onClickListener(Produit);
    }
    @FXML
    void ach(ActionEvent event) {
        if (tf_qte.getText().isEmpty() || Integer.parseInt(tf_qte.getText())==0) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        int qte =Integer.parseInt(tf_qte.getText());
        int total = qte*7;
        Vente v = new Vente(Integer.parseInt(tf_qte.getText()),total,Integer.parseInt(id.getText()));
        vs.Ajouter(v);
        tf_qte.clear();
    }

}
