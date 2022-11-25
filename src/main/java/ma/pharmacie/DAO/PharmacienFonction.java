package ma.pharmacie.DAO;


import ma.pharmacie.ENTITIES.Commande;
import ma.pharmacie.ENTITIES.Employee;
import ma.pharmacie.ENTITIES.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface PharmacienFonction {


    public String saveEmploye (Employee employee) throws SQLException, ClassNotFoundException;
    public List<Employee> searchEmployee(String motcle);
    public Medicament getEmployee(int id);
    public Medicament updateEmployee( Medicament p);
    public String deleteEmployee(int id);




    public String saveCommande (Commande commande) throws SQLException, ClassNotFoundException;
    public List<Commande> searchcommande(String motcle);
    public Medicament getcommande(int id);
    public Medicament updatecommande( Medicament p);
    public String deletecommande(int id);






}
