package ma.pharmacie.DAO;


import ma.pharmacie.ENTITIES.Commande;
import ma.pharmacie.ENTITIES.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface Fonction {

    public String saveMedicament (Medicament medicament) throws SQLException, ClassNotFoundException;
    public List<Medicament> searchMedicament (String mc)throws SQLException, ClassNotFoundException;
    public Medicament getMedicament(int id);
    public String updateMedicament( Medicament medicament) throws SQLException, ClassNotFoundException;
    public String deleteMedicament(Medicament medicament) throws SQLException, ClassNotFoundException;



    public List<Commande> searchComande (String motcle);



}
