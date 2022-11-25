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


    public List<Employee> loadEmployees () {
        employees.clear();
        try {
            employees = dbPharmacie.getEmploye();
        }catch (Exception ex) {
            addErrorMessage (ex);
        }
        return employees;
    }





    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public String saveEmploye(Employee employee) throws SQLException, ClassNotFoundException {

            try {
                dbPharmacie.saveEmployee(employee);
            }catch (Exception ex) {
                addErrorMessage (ex);
            }
            return  "home";
        }


    @Override
    public List<Employee> searchEmployee(String motcle) {
        return null;
    }

    @Override
    public Medicament getEmployee(int id) {
        return null;
    }

    @Override
    public Medicament updateEmployee(Medicament p) {
        return null;
    }

    @Override
    public String deleteEmployee(int id) {

            try {
                dbPharmacie.deleteEmployee(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "home";
        }










    @Override
    public String saveCommande(Commande commande) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Commande> searchcommande(String motcle) {
        return null;
    }

    @Override
    public Medicament getcommande(int id) {
        return null;
    }

    @Override
    public Medicament updatecommande(Medicament p) {
        return null;
    }

    @Override
    public String deletecommande(int id) {
        return null;
    }
}
