/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagar;

import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author camilo
 */
@WebService(serviceName = "pagos")
public class pagos {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation(@WebParam(name = "Nombre") String Nombre, @WebParam(name = "Cuenta") String Cuenta, @WebParam(name = "Valor") int Valor) throws ClassNotFoundException {
       String mensaje = "";

        try {

            conexion conexion = new conexion();
            mensaje = conexion.Conexion(Nombre, Cuenta, Valor);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        return mensaje;
        
    }

}
