/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.microservicios;

import co.edu.sena.microservicios.utils.JsonTransformer;
import java.util.Hashtable;
import spark.Spark;

/**
 *
 * @author LEONARDO
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Configuracion del puerto
        Spark.port(8181);
        //Servicio rest modo GET
        Spark.get("/", (request, response) -> {
            return "<h1> Bienvenido a la raiz del proyecto</h1>";
        });
        Spark.get("/saludar", (request, response) -> {
            return "Hola, bienvenido al mundo de los MS";
        });
        Spark.get("/saludar/:nombre", (request, response) -> {
            String nombre = request.params("nombre");
            return "Hola, bienvenido " + nombre;
        });

        Spark.get("/sumar/:n1/:n2", (request, response) -> {
            response.type("application/json");
            String n1 = request.params("n1");
            String n2 = request.params("n2");
            int num1 = 0;
            int num2 = 0;
            int suma = 0;
            Hashtable<String, Object> retorno = new Hashtable<>();
            try {
                num1 = Integer.parseInt(n1);
                num2 = Integer.parseInt(n2);
                suma = num1 + num2;
                System.out.println("Ejecutado con exito");
                response.status(200);
                retorno.put("status",200);
                retorno.put("message","Operacion suma ejecutada con exito !");
                retorno.put("data ",suma);
                
            } catch (NumberFormatException e) {
                System.out.println("No se ejecuto con exito");
                System.out.println(e.getMessage());
                response.status(400);
                retorno.put("Status ",400);
                retorno.put("message",e.getMessage());

            }

            return retorno;
        }, new JsonTransformer()
        );

    }

}
