package ma.pharmacie.BEANS;

import ma.pharmacie.DAO.DbPharmacie;
import ma.pharmacie.DAO.PharmacienFonction;
import ma.pharmacie.ENTITIES.Commande;
import ma.pharmacie.ENTITIES.Employee;
import ma.pharmacie.ENTITIES.Medicament;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Named
@ApplicationScoped
public class FonctionpharmacienImplementation implements PharmacienFonction {



    private List<Commande> commandes;
    private List<Employee> employees;
    private DbPharmacie dbPharmacie;

    public FonctionpharmacienImplementation() throws Exception {
        commandes = new ArrayList<Commande>();
        employees = new ArrayList<Employee>();
        dbPharmacie = DbPharmacie.getInstance();
    }

    public List<Commande> getCommande () {
        return commandes;
    }
    public List<Employee> getEmployees () {return employees;}

    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /***************************************************************************
     * CRUD EMPLOYEES
     *
     * ***************************************************************************/
    public List<Employee> loadEmployees () {
        employees.clear();
        try {
            employees = dbPharmacie.getEmploye();
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return employees;
    }
    @Override
    public String saveEmploye(Employee employee) throws SQLException, ClassNotFoundException {

            try {
                dbPharmacie.saveEmployee(employee);
            }catch (Exception ex) {
                addErrorMessage (ex);
            }
            return  "gestion_employe_pharmacien";
        }


    @Override
    public List<Employee> searchEmployee(String motcle) {
        employees.clear();
        try {
            employees= dbPharmacie.searchEmployee(motcle);
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee p) {
        return null;
    }

    @Override
    public String deleteEmployee(Employee employee) {

            try {
                dbPharmacie.deleteEmployee(employee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "gestion_employe_pharmacien";
        }




    /***************************************************************************
     * CRUD COMMANDES
     *
     * ***************************************************************************/





    @Override
    public String saveCommande(Commande commande) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Commande> searchcommande(String motcle) {
        return null;
    }

    @Override
    public Commande getcommande(int id) {
        return null;
    }

    @Override
    public Commande updatecommande(Commande p) {
        return null;
    }

    @Override
    public String deletecommande(int id) {
        return null;
    }
}
