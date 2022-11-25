package ma.pharmacie.DAO;
import ma.pharmacie.ENTITIES.Employee;
import ma.pharmacie.ENTITIES.Medicament;
import ma.pharmacie.ENTITIES.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.primefaces.component.keyboard.KeyboardBase.PropertyKeys.password;

@Named
@ApplicationScoped
public class DbPharmacie {


    private String username;
    private String password;
    private String role;

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
    private DataSource datasource;
    private String jndiName = "java:comp/env/jdbc/pharma";

    public static DbPharmacie getInstance() throws Exception {
        if (instance == null) {
            instance = new DbPharmacie();
        }
        return instance;
    }

    private DbPharmacie() throws Exception {
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

        Connection connection = datasource.getConnection();
        int i = 0;
        String sql="SELECT Username, Password FROM users WHERE Username='"+username+"'AND Password='"+password+"'";

        try(

                Statement stmt = connection.createStatement()){
                ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                i++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        if (i==0)
            return "login";
        else
            return "home";
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
            employ.setIdemploye(resultSet.getInt("Idemlopye"));
            employ.setNomemploye(resultSet.getString("nomemploye"));
            employ.setPrenomemploye(resultSet.getString("prenomemploye"));
            employ.setSalaire(resultSet.getDouble("salaire"));

            employees.add(employ);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
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

    }

    public void deleteEmployee(int id) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String sql = "DELETE FROM employe WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    }

    public void deleteMedicament(int id) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = datasource.getConnection();
            String sql = "DELETE FROM medicament WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}