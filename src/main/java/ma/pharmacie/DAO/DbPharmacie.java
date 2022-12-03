package ma.pharmacie.DAO;
import ma.pharmacie.ENTITIES.Employee;
import ma.pharmacie.ENTITIES.Medicament;
import ma.pharmacie.ENTITIES.User;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Named
@ApplicationScoped
public class DbPharmacie {


    private String username;
    private String password;
    private String role;

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private static DbPharmacie instance;
    private static DataSource datasource;
    private String jndiName = "java:comp/env/jdbc/pharma";

    public static DbPharmacie getInstance() throws Exception {
        if (instance == null) {
            instance = new DbPharmacie();
        }
        return instance;
    }

    DbPharmacie() throws Exception {
        datasource = getDataSource();
    }

    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        DataSource datasource = (DataSource) context.lookup(jndiName);
        return datasource;
    }

    /*********************************************************************
     GESTION DES AUTHENTIFICATIONS
     *********************************************************************/
    public String loginState() throws SQLException, ClassNotFoundException {
        String rol="" ;
        Connection connection = datasource.getConnection();
        int i = 0;
        String sql="SELECT Username, Password , role FROM users WHERE Username='"+username+"'AND Password='"+password+"'";
        try(

                Statement stmt = connection.createStatement()){
                ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                rol=(rs.getString(3));
                i++;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        connection.close();
        if (i==0)
        {
            return "login";}
        else if (i!=0 && rol.equals("pharmacien")){

            return "home_pharmacien";}
        else if ( i!=0 && rol.equals("employe"))
        {

            return "home_employe";}
        else
            return "login";
    }
    public String logout() throws SQLException, ClassNotFoundException {
        return "login";
    }

    /*********************************************************************
GESTION DES EMPLOYES
*********************************************************************/
public List<Employee> getEmploye() throws Exception {
    List<Employee> employees = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
        connection = datasource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from employe");
        while (resultSet.next()) {
            Employee employ = new Employee();
            employ.setIdemploye(resultSet.getInt("Idemploye"));
            employ.setNomemploye(resultSet.getString("nomemploye"));
            employ.setPrenomemploye(resultSet.getString("prenomemploye"));
            employ.setSalaire(resultSet.getDouble("salaire"));
            employ.setCin(resultSet.getString("cin"));

            employees.add(employ);

        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    connection.close();
    return employees;
}


    public void saveEmployee(Employee employee) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = datasource.getConnection();
            String sql = "insert into employe(nomemploye,prenomemploye,cin,salaire) values (?,?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, employee.getNomemploye());
            statement.setString(2, employee.getPrenomemploye());
            statement.setString(3, employee.getCin());
            statement.setDouble(4, employee.getSalaire());

            statement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
    }

        public List<Employee> searchEmployee(String id) throws SQLException {
            Connection connection = null;

            Statement statement = null;
            List<Employee> employerecherche = new ArrayList<>();
            try {
                connection = datasource.getConnection();
                statement = connection.createStatement();
                String sql = "select * from employe where nomemploye='" + id + "' ";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Employee employ = new Employee();
                    employ.setIdemploye(resultSet.getInt("Idemploye"));
                    employ.setNomemploye(resultSet.getString("nomemploye"));
                    employ.setPrenomemploye(resultSet.getString("prenomemploye"));
                    employ.setSalaire(resultSet.getDouble("salaire"));
                    employ.setCin(resultSet.getString("CIN"));
                    employ.setDate_naissance(resultSet.getDate("date_naissance"));
                    employ.setDate_recrutement(resultSet.getDate("daterecrutementemploye"));
                    employerecherche.add(employ);


                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            connection.close();
            return employerecherche;
        }


    public void deleteEmployee(Employee employee) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String sql = "DELETE FROM employe WHERE Idemploye=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,employee.getIdemploye()  );
            statement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }
    /*********************************************************************
     GESTION DES MEDICAMENTS
     *********************************************************************/
    public List<Medicament> getMedicament() throws Exception {
        List<Medicament> medicaments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = datasource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from medicament");
            while (resultSet.next()) {
                Medicament medicament = new Medicament();
                medicament.setIdMEDICAMENT(resultSet.getInt("idMEDICAMENT"));
                medicament.setDesignation(resultSet.getString("designation"));
                medicament.setPrix(resultSet.getDouble("prix"));
                medicament.setQuantiteMedicament(resultSet.getInt("quantitemedicament"));

                medicaments.add(medicament);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
        return medicaments;
    }


    public void saveMedicament(Medicament medicament) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = datasource.getConnection();
            String sql = "insert into medicament(designation, prix, quantitemedicament) values (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, medicament.getDesignation());
            statement.setDouble(2, medicament.getPrix());
            statement.setInt(3, medicament.getQuantiteMedicament());

            statement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
    }


    public void deleteMedicament(Medicament medicament) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String sql = "DELETE FROM medicament WHERE idMEDICAMENT=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, medicament.getIdMEDICAMENT());
            statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }


    public List<Medicament> search(String id) throws SQLException {
        Connection connection = null;

        Statement statement = null;
        List<Medicament> medicamentrecherche = new ArrayList<>();
        try {
            connection = datasource.getConnection();
            statement = connection.createStatement();
            String sql = "select * from medicament where designation='" + id + "' ";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Medicament medoc = new Medicament();
                medoc.setIdMEDICAMENT(resultSet.getInt("IdMEDICAMENT"));
                medoc.setDesignation(resultSet.getString("designation"));
                medoc.setPrix(resultSet.getDouble("prix"));
                medoc.setQuantiteMedicament(resultSet.getInt("quantitemedicament"));
                medoc.setTVA(resultSet.getDouble("TVA"));
                medicamentrecherche.add(medoc);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        connection.close();
   return medicamentrecherche;
    }


    public String   getonemedicament(int id) throws SQLException {
        Medicament medicament = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = datasource.getConnection();
            statement = (PreparedStatement) connection.createStatement();
            String sql = "select * from medicament where designation='" + id + "' ";
            ResultSet resultSet = statement.executeQuery(sql);


            resultSet.next();

            medicament.setIdMEDICAMENT(resultSet.getInt("IdMEDICAMENT"));
            medicament.setDesignation(resultSet.getString("designation"));
            medicament.setPrix(resultSet.getDouble("prix"));
            medicament.setQuantiteMedicament(resultSet.getInt("quantitemedicament"));
            medicament.setTVA(resultSet.getDouble("TVA"));

            sessionMap.put("editMedicament", medicament);


        }catch(Exception e){
            System.out.println(e);
        }
        connection.close();
        return "edit" ;
    }


    public void updateMedicament(Medicament medicament) throws SQLException {


        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String query = "update medicament set designation = ?,prix = ?, quantitemedicament=? where idMEDICAMENT = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1,medicament.getDesignation() );
            statement.setDouble(2,medicament.getPrix() );
            statement.setInt(3,medicament.getQuantiteMedicament() );
            statement.setInt(4, medicament.getIdMEDICAMENT());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
    }

}