/* db di test

CREATE SCHEMA db_squirreltest;

USE db_squirreltest;

CREATE TABLE tbl_nominativi (
	matricola INT AUTO_INCREMENT,
	cognome VARCHAR(30),
	PRIMARY KEY (matricola)
);

INSERT INTO tbl_nominativi (cognome) VALUES ('Rossi');
INSERT INTO tbl_nominativi (cognome) VALUES ('Bianchi');

*/
//COMMENTO EROS2

import java.sql.*;

public class MariaDBTest {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    
    //scelta database
    static final String DB_URL = "jdbc:mariadb://localhost:3306/db_squirreltest";

    //credenziali database 
    static final String USER = "root";
    static final String PASSWORD = "<password>";
    
	public static void main(String[] args) {
		        Connection conn = null;
		        Statement stmt = null;
		        
		        try {
		            //registrazione driver
		            Class.forName(JDBC_DRIVER);

		            //apertura connessione
		            System.out.println("Connettendo al database scelto...");
		            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		            System.out.println("Connesso con successo al database.");

		            //invio query
		            System.out.println("Inviando query...");
		            stmt = conn.createStatement();
		            String sql = "SELECT * "
		            		+ "FROM db_squirreltest.tbl_nominativi "
		            		+ "ORDER BY cognome ASC;";
		            ResultSet rs = stmt.executeQuery(sql);
		            System.out.println("Query inviata con successo.");
		            
		            //recupero dati
		            System.out.println("Risultato query:\n");
		            int id;
		            String cognome;
		            while (rs.next()) {  
		                id = rs.getInt("matricola");
		                cognome = rs.getString("cognome");
		                System.out.println("id: " + id + "\t" + "cognome: " + cognome);
		            }
		            
		        } catch (Exception e) {
		            //Handle errors for Class.forName
		            e.printStackTrace();
		        } finally {
		            //finally block used to close resources
		            try {
		                if (stmt != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		            	se.printStackTrace();
		            }
		            try {
		                if (conn != null) {
		                    conn.close();
		                }
		            } catch (SQLException se) {
		                se.printStackTrace();
		            }//end finally try
		        }//end try
	}
}
