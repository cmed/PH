package ma.pharmacie.ENTITIES;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.util.Date;

@Named
@ApplicationScoped
public class Employee {

    private int Idemploye;

    private String nomemploye;

    private String prenomemploye;
    private Date date_naissance;
    private Date date_recrutement;
    private double salaire;
    private String cin;
    public Employee() {
    }

    public int getIdemploye() {
        return Idemploye;
    }

    public void setIdemploye(int idemploye) {
        Idemploye = idemploye;
    }

    public String getNomemploye() {
        return nomemploye;
    }

    public void setNomemploye(String nomemploye) {
        this.nomemploye = nomemploye;
    }

    public String getPrenomemploye() {
        return prenomemploye;
    }

    public void setPrenomemploye(String prenomemploye) {
        this.prenomemploye = prenomemploye;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_recrutement() {
        return date_recrutement;
    }

    public void setDate_recrutement(Date date_recrutement) {
        this.date_recrutement = date_recrutement;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }



}