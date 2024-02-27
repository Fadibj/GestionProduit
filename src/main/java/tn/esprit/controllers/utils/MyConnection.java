package tn.esprit.controllers.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection Test;
    private static MyConnection data;

    /*public static PreparedStatement prepareStatement(String requete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public String url = "jdbc:mysql://127.0.0.1:3306/produit";
    public String login="root";
    public String pwd="";
    public static Connection cn;
    public static MyConnection cno;

    public MyConnection() {

        try {
            cn = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion");
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnection getInstance ()
    {
        if (cno == null)
        {
            cno = new MyConnection();
        }
        return cno;
    }

    public Connection getCnx() {
        return cn;
    }

    public static MyConnection getTest() {
        if (Test == null) {
            Test = new MyConnection();
        }
        return Test;
    }
    public void setCn(Connection cnx) {
        this.cn = cn;
    }
    public static MyConnection getConn() {
        return cno;
    }
}

