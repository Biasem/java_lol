package utilidades;

import modelos.Personaje;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class UtilidadesBBDD {
    private  UtilidadesFicheros uf = new UtilidadesFicheros() ;
    private static final String url = "jdbc:mariadb://localhost:3306/lolete";
    private static final String usuario = "root";
    private static final String password = "1234";


    private static Connection conectarConBD() {
        Connection conexion;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error en la conexión:" + e.toString());
            return null;
        }
        return conexion;
    }
    public void crearTablas() throws IOException {
        List<Personaje> Listapersonajes = uf.leerPersonajes();


    }

   /* public static void mostrarPalas(){

        Connection con = conectarConBD();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM pala");
            ResultSet rs = stmt.executeQuery();

            //Recorremos los datos
            while (rs.next())
                System.out.println (rs.getString("nombre"));

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            try {
                // Cerramos conexiones
                if (con!=null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: "
                        + e.toString());
            }
        }
    }*/



}
