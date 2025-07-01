package Parkflow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	private String host = "localhost";
	private String port = "1433";
	private String dbName = "Parkflow";
	
	public Database() {
        String query = "IF EXISTS ("
            + "SELECT 1 "
            + "FROM INFORMATION_SCHEMA.TABLES "
            + "WHERE TABLE_SCHEMA = 'dbo' "
            + "AND TABLE_NAME = 'Maintenance' )"
            + " BEGIN "
            + " PRINT 'Table exists!' "
            + "END "
            + "ELSE"
            + " BEGIN "
            + "create table [maintenance]( "
            + "id int primary key identity(1,1), "
            + "maintence_kind varchar(25) not null, "
            + "schedule date not null, "
            + "observations varchar(max), "
            + "idCompany int not null foreign key references [company](id) ) "
            + "END";
        try(
				Connection conn = this.GetNewConnection();
				Statement stmt = conn.createStatement();
				){
        	stmt.executeUpdate(query);
			
		} catch (SQLException e){
			System.out.print("Erro ao criar verificar e criar tabela (DATABASE)");
			System.out.print(e);
		}
		
    }
	
	protected Connection GetConnection() throws SQLException {
		String strConnection = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true;encrypt=true;trustServerCertificate=true;", host, port, dbName);
		return DriverManager.getConnection(strConnection);
	}
	
	protected Connection GetNewConnection() throws SQLException {
		Connection conn = null;

        try {

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            String dbURL = "jdbc:sqlserver://localhost;encrypt=true;trustServerCertificate=true;databaseName=Parkflow;integratedSecurity=true;";
            
            conn = DriverManager.getConnection(dbURL);
            
            if (conn != null) {
                
                return conn;
            }
            return null;


        } catch (SQLException ex) {
            System.out.println("An error occurred while establishing the connection:");
            ex.printStackTrace();
            return null;
        }
	}
}
