package ma.pharmacie.BEANS;
import ma.pharmacie.DAO.DbPharmacie;
import ma.pharmacie.DAO.Fonction;

import ma.pharmacie.ENTITIES.*;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Named
@ApplicationScoped
public class FonctionImplementation  extends Medicament implements Fonction {


    private List<Medicament> medicaments;
    private DbPharmacie dbPharmacie;

    public FonctionImplementation() throws Exception {
        medicaments = new ArrayList<Medicament>();
        dbPharmacie = DbPharmacie.getInstance();
    }

    public List<Medicament> getMedicaments () {
        return medicaments;
    }

    public List<Medicament> loadMedicament () {
        medicaments.clear();
        try {
            medicaments = dbPharmacie.getMedicament();
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return medicaments;
    }

    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }




    @Override
    public String saveMedicament(Medicament medicament) throws SQLException, ClassNotFoundException {


            try {
                dbPharmacie.saveMedicament(medicament);
            }catch (Exception ex) {
                addErrorMessage (ex);
            }
            return "home";
        }


    @Override
    public String deleteMedicament(int id) {
        try {
            dbPharmacie.deleteMedicament(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dashboard";
    }

    @Override
    public List<Commande> searchComande(String motcle) {
        return null;
    }


    @Override
    public List<Medicament> searchMedicament(String mc) {
        return null;
    }

    @Override
    public Medicament getMedicament(int id) {
        return null;
    }

    @Override
    public Medicament updateMedicament(Medicament medicament) {
        return null;
    }




    }

