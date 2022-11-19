package ma.pharmacie.BEANS;
import ma.pharmacie.DAO.Fonction;
import ma.pharmacie.DAO.SingleConnxion;
import ma.pharmacie.ENTITIES.Medicament;
import ma.pharmacie.ENTITIES.Personnel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@Named
@ApplicationScoped
public class FonctionImplementation  extends Medicament implements Fonction {


    @Override
    public String saveMedicament() throws SQLException, ClassNotFoundException {

        Connection connection= ma.pharmacie.DAO.SingleConnxion.getConnection();



                PreparedStatement ps=connection.prepareStatement("INSERT INTO MEDICAMENT (idmedicament,DESIGNATION,PRIX,TVA,quantitemedicament) VALUES (?,?,?,?,?) ");
                ps.setInt(1, getIdMEDICAMENT());
                ps.setString(2,getDesignation());
                ps.setDouble(3, getPrix());
                ps.setDouble(4, getTVA());
                ps.setInt(5, getQuantiteMedicament());
                ps.executeUpdate();
                ps.close();



            return "page" ;
        }











    @Override
    public List<Medicament> searchMedicament(String mc) {
        return null;
    }

    @Override
    public Medicament getMedicament(Long id) {
        return null;
    }

    @Override
    public Medicament updateMedicament(Medicament p) {
        return null;
    }

    @Override
    public void deleteMedicament(Long id) {

    }
}
