package Parkflow.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class MaintenanceRepository extends Database {
	
	public static String tableName = "[maintenance]";
	
	public MaintenanceRepository() {
		super();
	}
	
	public List<Maintenance> GetAll() {
		List<Maintenance> results = new ArrayList<Maintenance>();
		
		String query = String.format("Select * from %s", tableName);
		
		try(
				Connection conn = this.GetNewConnection();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery(query)
				){
			
			while (res.next()) {
				results.add(new Maintenance(
						res.getInt("id"),
						res.getString("maintence_kind"),
						res.getString("observations"), 
						res.getObject("schedule", LocalDate.class),
						res.getInt("idCompany")
						));
			}
			
		} catch (SQLException e){
			System.out.print("Erro ao executar comando get (Company)");
		}
		
		return results;
	}
	
	public List<Maintenance>  GetNextMaintenances() {
		List<Maintenance> results = new ArrayList<Maintenance>();
		
		String query = String.format("SELECT "
				+ "*"
				+ "FROM %s m JOIN %s c on m.idCompany = c.id "
				+ "WHERE [schedule] > CAST(GETDATE() as DATE) "
					+ "ORDER BY [schedule] ASC", 
						tableName, CompanyRepository.tableName);
		
		try(
				Connection conn = this.GetNewConnection();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery(query)
				){

			while (res.next()) {
				Maintenance add = new Maintenance(res.getInt(1),
						res.getString(2),
						res.getString(4), 
						res.getObject(3, LocalDate.class),
						res.getInt(5));
				add.setCompany(new Company(
						res.getInt(6),
						res.getString(8),
						res.getString(9),
						res.getInt(10),
						res.getInt(11),
						res.getInt(12)
						));
				results.add(add);
			}
			
		} catch (SQLException e){
			System.out.print("Erro ao executar comando get (Maintenance Repository)");
			System.out.print(e);
		}

		
		return results;
	}
	
	public void Add(Maintenance value) {
		String query = String.format("INSERT %s VALUES ('%s', '%s', '%s', %d )", tableName, value.getMaintenceKind(), value.getSchedule().toString(), value.getObservations(), value.getIdCompany());
		System.out.println(query);
		
		
		try(
				Connection conn = this.GetConnection();
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery(query)
				){
					
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void Update(Maintenance value) {
		String query = String.format("update %s set "
				+ "maintence_kind = '%s', "
				+ "schedule = '%s', "
				+ "observations = '%s', "
				+ "idCompany = %d "
				+ " Where [id] = %d", 
				tableName, 
				value.getMaintenceKind(), 
				value.getSchedule().toString(), 
				value.getObservations(), 
				value.getIdCompany(),
				value.getId());
		System.out.println(query);
		
		
		try(
				Connection conn = this.GetConnection();
				Statement stmt = conn.createStatement()
				){
					stmt.executeUpdate(query);
				} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public void Delete(int id ) {
				String query = String.format("DELETE %s WHERE [id] = %d", 
				tableName, id);
		System.out.println(query);
		try(
				Connection conn = this.GetConnection();
				Statement stmt = conn.createStatement()
				){
					stmt.executeUpdate(query);
				} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
