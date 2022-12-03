




package ma.pharmacie.ENTITIES;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Map;

@Named
@ApplicationScoped

public class Medicament {

    private int idMEDICAMENT;
    private String  designation;
    private double prix;
    private double TVA;
    private int quantiteMedicament;


    public Medicament() {
    }

    public int getIdMEDICAMENT() {
        return idMEDICAMENT;
    }

    public void setIdMEDICAMENT(int idMEDICAMENT) {
        this.idMEDICAMENT = idMEDICAMENT;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public int getQuantiteMedicament() {
        return quantiteMedicament;
    }

    public void setQuantiteMedicament(int quantiteMedicament) {
        this.quantiteMedicament = quantiteMedicament;
    }
}
