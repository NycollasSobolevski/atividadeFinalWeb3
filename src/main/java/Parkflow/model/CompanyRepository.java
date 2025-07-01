package Parkflow.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class CompanyRepository extends Database{

	public static String tableName = "[company]";
	
	public CompanyRepository() {
		super();
	}
	
	public void GetAll() {
				
	}
	public Company GetByCnpj(String cnpj) {
		
		String querry = String.format("SELECT * FROM %s WHERE [cnpj] = '%s'", tableName, cnpj);
	
		try(
				Connection conn = this.GetConnection();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery(querry)
				){
			
	        if (res.next()) {
	            Company data = new Company(
	                res.getInt("id"),      // Coluna 1
	                res.getString("name"),   // Coluna 2
	                res.getString("cnpj"),   // Coluna 3
	                res.getInt("id_address"),      // Coluna 4
	                res.getInt("id_plan"),      // Coluna 5
	                res.getInt("id_settings")       // Coluna 6
	            );
	            return data;
	        } else {
	            return null;
	        }
		    } catch (SQLException e)  {
		    	System.out.println(e);
		    	return null;
		    }
			
		}

}
