package ma.pharmacie.DAO;


import ma.pharmacie.ENTITIES.Commande;
import ma.pharmacie.ENTITIES.Employee;
import ma.pharmacie.ENTITIES.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface PharmacienFonction {

    /***************************************************************************
     * CRUD EMPLOYEES interfaces
     *
     * ***************************************************************************/
    public String saveEmploye (Employee employee) throws SQLException, ClassNotFoundException;
    public List<Employee> searchEmployee(String motcle);
    public Employee getEmployee(int id);
    public Employee updateEmployee( Employee e);
    public String deleteEmployee(Employee employee);


    /***************************************************************************
     * CRUD COMMANDE Interfaces
     *
     * ***************************************************************************/

    public String saveCommande (Commande commande) throws SQLException, ClassNotFoundException;
    public List<Commande> searchcommande(String motcle);
    public Commande getcommande(int id);
    public Commande updatecommande( Commande c);
    public String deletecommande(int id);


}
