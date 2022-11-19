package ma.pharmacie.BEANS;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.*;
import java.sql.Connection;


@Named
@ApplicationScoped

public class LoginBean {


    String UserName;
    private String Password;
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String LoginState() throws SQLException, ClassNotFoundException {
        Connection connection= ma.pharmacie.DAO.SingleConnxion.getConnection();
        String sql="SELECT Username, Password FROM users WHERE Username='"+UserName+"'AND Password='"+Password+"'";
        int i=0;
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                i++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(i==0){
            return "Login";
        }
        else{
            return "index" ;
        }
    }

}
