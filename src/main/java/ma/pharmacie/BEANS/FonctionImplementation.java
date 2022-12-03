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
    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
/***************************************************************************
 * CRUD MEDICAMENTS
 *
 * ***************************************************************************/
    public List<Medicament> getMedicaments () {
        return medicaments;
    }
    @Override
    public Medicament getMedicament(int id) {
        return null;
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

    public List<Medicament> getoneloader (int id) {
        medicaments.clear();
        try {
            dbPharmacie.getonemedicament(id);
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return medicaments;
    }



    @Override
    public String updateMedicament (Medicament  medicament) throws SQLException, ClassNotFoundException {

        medicaments.clear();
        try {
             dbPharmacie.updateMedicament(medicament);
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        if(dbPharmacie.loginState().equals("home_pharmacien"))
        return  "gestion_medicament_pharmacien";
        else return "gestion_medicament_employe";

    }

    @Override
    public String saveMedicament(Medicament medicament) throws SQLException, ClassNotFoundException {

            try {
                dbPharmacie.saveMedicament(medicament);
            }catch (Exception ex) {
                addErrorMessage (ex);
            }

        if(dbPharmacie.loginState().equals("home_pharmacien"))
            return  "gestion_medicament_pharmacien";
        else return "gestion_medicament_employe";
        }


    @Override
    public String deleteMedicament(Medicament medicament) throws SQLException, ClassNotFoundException {
        try {
            dbPharmacie.deleteMedicament(medicament);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(dbPharmacie.loginState().equals("home_pharmacien"))
            return  "gestion_medicament_pharmacien";
        else return "gestion_medicament_employe";
    }

    @Override
    public List<Medicament>  searchMedicament(String mc)   throws SQLException, ClassNotFoundException{
        medicaments.clear();
        try {
            medicaments= dbPharmacie.search(mc);
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return medicaments;
    }

    /***************************************************************************
     * CRUD COMMANDE
     *
     * ***************************************************************************/
    @Override
    public List<Commande> searchComande(String motcle) {
        return null;
    }


    }

