package ma.pharmacie.DAO;


import ma.pharmacie.ENTITIES.Commande;
import ma.pharmacie.ENTITIES.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface Fonction {

    public String saveMedicament (Medicament medicament) throws SQLException, ClassNotFoundException;
    public List<Medicament> searchMedicament (String mc);
    public Medicament getMedicament(int id);
    public Medicament updateMedicament( Medicament p);
    public String deleteMedicament(int id);



    public List<Commande> searchComande (String motcle);



}
