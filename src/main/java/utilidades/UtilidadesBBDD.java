package utilidades;

import java.sql.*;

public class UtilidadesBBDD {

    private static final String url = "jdbc:mariadb://localhost:3306/padel";
    private static final String usuario = "root";
    private static final String password = "patata123";


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

    public static void mostrarPalas(){

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
    }



}
