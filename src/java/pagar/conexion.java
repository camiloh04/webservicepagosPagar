 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bryan
 */
public class conexion {

    public String Conexion(String Nombre, String Cuenta, int Valor) throws SQLException, ClassNotFoundException {

        String resp = "";
        try {
            Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpago", "root", "");

            Statement st = con.createStatement();
            String Consulta = "select * from tblCuentas where Nombre like '" + Nombre + "' and Cuenta like '" + Cuenta + "'";
            ResultSet resulta = st.executeQuery(Consulta);
            if (resulta.next()) {
                int val = resulta.getInt("Valor");
                String cue = resulta.getString("Cuenta");
                if (Valor <= val) {
                    int res = val - Valor;
                    String modificar = "UPDATE tblCuentas SET Valor = " + res + " WHERE Cuenta = '" + cue + "'";
                    st.executeUpdate(modificar);
                    resp = "Transacción exitosa";
                } else {
                    resp = "Fondos Insuficientes";
                }
            } else {
                resp = "Cuenta no encontrada";
            }

            System.out.println("Conexión exitosa");

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return resp;
    }

}

