
package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michell
 * @author Kleber
 */
public class ConectaDB {
    
    //DAO = acrônimo de Data Access Object
    public static Connection conecta() {
        String prefixo = "jdbc:mysql://";
        String host = "localhost/"; //"127.0.0.1";
        String banco = "cadprod";
        String usuario = "root";     //root
        String pass = "";    //root
        String url = prefixo + host + banco;
        
        try {
            return DriverManager.getConnection(url, usuario, pass);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados\n"
                    + "Verifique se o MySQL está ativo","Falha na conexão",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        //somente em caso de erro na conexão
        return null;
    }
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConectaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
