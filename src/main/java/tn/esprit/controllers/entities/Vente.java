package tn.esprit.controllers.entities;

public class Vente {
    private int id;
    private int quantitevendu ;
    private String dateVente ;
    private int montanttotal;
    private int idproduit ;

    public Vente() {
    }

    public Vente(int id, int quantitevendu, String dateVente, int montanttotal, int idproduit) {
        this.id = id;
        this.quantitevendu = quantitevendu;
        this.dateVente = dateVente;
        this.montanttotal = montanttotal;
        this.idproduit = idproduit;
    }

    public Vente(int quantitevendu, int montanttotal, int idproduit) {
        this.quantitevendu = quantitevendu;
        this.montanttotal = montanttotal;
        this.idproduit = idproduit;
    }

    public Vente(int quantitevendu, String dateVente, int montanttotal, int idproduit) {
        this.quantitevendu = quantitevendu;
        this.dateVente = dateVente;
        this.montanttotal = montanttotal;
        this.idproduit = idproduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantitevendu() {
        return quantitevendu;
    }

    public void setQuantitevendu(int quantitevendu) {
        this.quantitevendu = quantitevendu;
    }

    public String getDateVente() {
        return dateVente;
    }

    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
    }

    public int getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(int montanttotal) {
        this.montanttotal = montanttotal;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    @Override
    public String toString() {
        return "Vente{" + "id=" + id + ", quantitevendu=" + quantitevendu + ", dateVente=" + dateVente + ", montanttotal=" + montanttotal + ", idproduit=" + idproduit + '}';
    }

}
