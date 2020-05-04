/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adbd_conmysq;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hilde
 */
public class Conexion_BD_Hiuta {

    Connection conectar = null;
    Statement stm;
    public boolean a;
    static String usuario;
    static String contraseña;
    static boolean otrosuser = false;
    public static String nombreDataBase;

    public Conexion_BD_Hiuta() {

    }

    public void Conexion() {
        System.out.println("estoy imprimiendo el usuario y contraseña de CONEXION");
        System.out.println(usuario);
        System.out.println(contraseña);

        try {
            Class.forName("com.mysql.jdbc.Driver");
//conectar =DriverManager.getConnection("jdbc:mysql://localhost/restauranteB?user=root&password=1234");
            if (otrosuser) {
                conectar = DriverManager.getConnection("jdbc:mysql://localhost/" + nombreDataBase + "?user=" + usuario + "&password=" + contraseña + "&useSSL=false");
                otrosuser = false;
            } else {
                conectar = DriverManager.getConnection("jdbc:mysql://localhost?user=" + usuario + "&password=" + contraseña + "&useSSL=false");
            }
            Statement stm = conectar.createStatement();
//JOptionPane.showMessageDialog(null,"Conectado");
            System.out.println("OK");
            a = true;
        } catch (Exception e) {
            a = false;

            JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");
        }

    }

    public boolean estado() {

        return a;
    }

    public Connection getConexion() {
        return conectar;
    }

    public void puente(String a) throws SQLException {

        System.out.println("estoy en el puente");
        System.out.println(a);
        Conexion();
        Statement stmn = conectar.createStatement();
        stmn.executeUpdate(a);

        System.out.println("OK QUERY");
    }

    public void closeConnection() {
        try {

            Conexion();
            conectar.close();
            System.out.println("close");
            //JOptionPane.showMessageDialog(null, "close" );

        } catch (SQLException ex) {
            Logger.getLogger(Conexion_BD_Hiuta.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
