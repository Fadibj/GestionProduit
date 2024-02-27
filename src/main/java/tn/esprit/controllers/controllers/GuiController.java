package tn.esprit.controllers.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;
import tn.esprit.controllers.servises.*;
import tn.esprit.controllers.entities.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiController {

    @FXML
    private TableColumn<Produit,String> ccategorie;

    @FXML
    private TableColumn<Produit,String> cdate;

    @FXML
    private TableColumn<Produit,String> cdescription;

    @FXML
    private TableColumn<Vente,String> cfk;

    @FXML
    private TableColumn<Produit,String> cid;

    @FXML
    private TableColumn<Vente,String> cid1;

    @FXML
    private TableColumn<Vente,String> cmont;

    @FXML
    private TableColumn<Produit,String> cnom;

    @FXML
    private TableColumn<Produit,String> coption;

    @FXML
    private TableColumn<Vente,String> coption1;

    @FXML
    private TableColumn<Produit,String> cprix;

    @FXML
    private TableColumn<Vente,String> cqte;

    @FXML
    private TableView<Produit> display;

    @FXML
    private TableView<Vente> displayv;

    @FXML
    private GridPane grid;

    @FXML
    private Pane pn_addproduct;

    @FXML
    private Pane pn_admin;

    @FXML
    private Pane pn_allprodback;

    @FXML
    private Pane pn_allproduct;

    @FXML
    private Pane pn_allvente;

    @FXML
    private Pane pn_front;

    @FXML
    private Pane pn_login;

    @FXML
    private Pane pn_modifierPro;

    @FXML
    private ScrollPane scrolee;

    @FXML
    private TextField tf_categorie;

    @FXML
    private TextField tf_categorie1;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_description1;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_nom1;

    @FXML
    private TextField tf_prix;

    @FXML
    private TextField tf_prix1;
    ProduitService ps = new ProduitService();
    VenteService vs = new VenteService();
    Produit tmpp = new Produit();
    Vente tmppp = new Vente();
    private MyListener myListener;


    @FXML
    void btn_addproduct(ActionEvent event) {
        pn_addproduct.toFront();
    }

    @FXML
    void btn_admin(ActionEvent event) {
        pn_admin.toFront();
    }

    @FXML
    void btn_allproductfront(ActionEvent event) {
        pn_allproduct.toFront();
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void btn_allvente(ActionEvent event) {
        pn_allvente.toFront();
        displayv();
    }

    @FXML
    void btn_allproductsback(ActionEvent event) {
        pn_allprodback.toFront();
        display();
    }

    @FXML
    void btn_client(ActionEvent event) {
        pn_front.toFront();
    }

    @FXML
    void logout(ActionEvent event) {
        pn_login.toFront();
    }

    @FXML
    void validerajoutprod(ActionEvent event) {
        if (tf_nom.getText().isEmpty() || tf_description.getText().isEmpty() ||tf_prix.getText().isEmpty() ||tf_categorie.getText().isEmpty() || Integer.parseInt(tf_prix.getText())==0) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        String nom = tf_nom.getText();
        int prix = Integer.parseInt(tf_prix.getText());
        String description = tf_description.getText();
        String categorie = tf_categorie.getText();
        Produit p = new Produit(nom,prix,description,categorie);
        ps.Ajouter(p);
        tf_nom.clear();
        tf_prix.clear();
        tf_description.clear();
        tf_categorie.clear();
        pn_allprodback.toFront();
        display();
    }

    @FXML
    void validermodifprod(ActionEvent event) {
        if (tf_nom1.getText().isEmpty() || tf_description1.getText().isEmpty() ||tf_prix1.getText().isEmpty() ||tf_categorie1.getText().isEmpty()) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        String nom = tf_nom1.getText();
        int prix = Integer.parseInt(tf_prix1.getText());
        String description = tf_description1.getText();
        String categorie = tf_categorie1.getText();
        Produit p = new Produit(Integer.parseInt(tf_id.getText()),nom,prix,description,categorie);
        System.out.println(tf_prix1.getText());
        ps.Modifier(p);
        pn_allprodback.toFront();
        display();
    }

    private void display() {
        display.getItems().clear();
        ObservableList<Produit> l = FXCollections.observableArrayList();
        ResultSet resultSet = ps.Selectionner();
        l.clear();
        try {
            while (resultSet.next()) {
                l.add(new Produit(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("prix"),
                        resultSet.getString("description"),
                        resultSet.getString("categorie"),
                        resultSet.getString("dateajout")));
                display.setItems(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        cdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ccategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("dateajout"));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //add cell of button edit
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            //// make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:balck;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:balck;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            ps.Supprimer(tmpp.getId());
                        });

                        editIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            int id = tmpp.getId();
                            ResultSet resultSet1 = ps.SelectionnerSingle(id);
                            try {
                                while (resultSet.next()) {
                                    tmpp = new Produit(
                                            resultSet.getInt("id"),
                                            resultSet.getString("nom"),
                                            resultSet.getInt("prix"),
                                            resultSet.getString("description"),
                                            resultSet.getString("categorie"),
                                            resultSet.getString("dateajout")
                                    );
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            pn_modifierPro.toFront();
                            tf_id.setText(String.valueOf(tmpp.getId()));
                            tf_nom1.setText(tmpp.getNom());
                            tf_prix1.setText(String.valueOf(tmpp.getPrix()));
                            tf_description1.setText(tmpp.getDescription());
                            tf_categorie1.setText(tmpp.getCategorie());
                        });

                        HBox managebtn = new HBox(deleteIcon, editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(6, 6, 0, 7));
                        HBox.setMargin(editIcon, new Insets(6, 6, 0, 7));

                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };

            return cell;
        };
        coption.setCellFactory(cellFoctory);
    }

    private void setChosen(Produit Produit) {

    }

    private void displayg() {
        ///////////////////////////////////////////////////////////////
        ObservableList<Produit> l2 = FXCollections.observableArrayList();
        ResultSet resultSet2 = ps.Getall();
        l2.clear();
        Produit pppp = new Produit();
        l2.add(pppp);
        int column = 0;
        int row = 2;
        if (l2.size() > 0) {
            setChosen(l2.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit Produit) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Produit.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    ProduitController itemController = fxmlLoader.getController();
                    String nom = resultSet2.getString("nom");
                    String description = resultSet2.getString("description");
                    String categorie = resultSet2.getString("categorie");
                    String dateajout = resultSet2.getString("dateajout");
                    int prix = resultSet2.getInt("prix");
                    String id = resultSet2.getString("id");
                    int newid = Integer.parseInt(id);
                    Produit ppppp = new Produit(newid, nom, prix,description,categorie,dateajout);
                    itemController.setData(ppppp, myListener);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayv() {
        displayv.getItems().clear();
        ObservableList<Vente> l = FXCollections.observableArrayList();
        ResultSet resultSet = vs.Selectionner();
        l.clear();
        try {
            while (resultSet.next()) {
                l.add(new Vente(
                        resultSet.getInt("id"),
                        resultSet.getInt("quantitevendu"),
                        resultSet.getString("dateVente"),
                        resultSet.getInt("montanttotal"),
                        resultSet.getInt("idProduit")));
                displayv.setItems(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cid1.setCellValueFactory(new PropertyValueFactory<>("id"));
        cqte.setCellValueFactory(new PropertyValueFactory<>("quantitevendu"));
        cmont.setCellValueFactory(new PropertyValueFactory<>("montanttotal"));
        cfk.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        cdate.setCellValueFactory(new PropertyValueFactory<>("dateVente"));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //add cell of button edit 
        Callback<TableColumn<Vente, String>, TableCell<Vente, String>> cellFoctory = (TableColumn<Vente, String> param) -> {
            //// make cell containing buttons
            final TableCell<Vente, String> cell = new TableCell<Vente, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:balck;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmppp = displayv.getSelectionModel().getSelectedItem();
                            vs.Supprimer(tmppp.getId());
                        });
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(6, 6, 0, 7));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        coption1.setCellFactory(cellFoctory);
    }
}
