package ma.pharmacie.DAO;


import ma.pharmacie.ENTITIES.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface Fonction {

    public String saveMedicament () throws SQLException, ClassNotFoundException;
    public List<Medicament> searchMedicament (String mc);
    public Medicament getMedicament(Long id);
    public Medicament updateMedicament( Medicament p);
    public void deleteMedicament(Long id);



}
