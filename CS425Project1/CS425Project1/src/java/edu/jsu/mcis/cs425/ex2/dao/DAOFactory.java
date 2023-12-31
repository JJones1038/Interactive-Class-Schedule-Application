package edu.jsu.mcis.cs425.ex2.dao;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAOFactory {

    private DataSource ds = null;

    public DAOFactory() {

        try {

            Context envContext = new InitialContext();
            Context initContext = (Context) envContext.lookup("java:/comp/env");
            ds = (DataSource) initContext.lookup("jdbc/db_pool");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    Connection getConnection() {

        Connection c = null;

        try {

            if (ds != null) {
                c = ds.getConnection();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return c;

    }
    
    public RegistrationDAO getRegistrationDAO() {
        return new RegistrationDAO(this);
    }
    
    public SearchDAO getSearchDAO() {
        return new SearchDAO(this);
    }
    
    public FormDAO getFormDAO() {
        return new FormDAO(this);
    }
    
}