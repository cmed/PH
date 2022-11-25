package ma.pharmacie.BEANS;
import ma.pharmacie.DAO.DbPharmacie;
import ma.pharmacie.ENTITIES.Medicament;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped

public class LoginBean {


    private DbPharmacie dbPharmacie;

    public LoginBean() throws Exception {

        dbPharmacie = DbPharmacie.getInstance();
    }





    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }







}
