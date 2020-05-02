package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.CombineModels;
import models.CustomerModel;
import models.TaskModel;

public class SQLiteQueries {
		
	private Connection connect() {
        String url = "jdbc:sqlite:customerdb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public CustomerModel getCustomer(String customerIdentifier) {
		CustomerModel customerModel = new CustomerModel();
		String sql = "SELECT * FROM customers WHERE CustomerIdentifier = ?";
		
		try (Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customerIdentifier);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customerModel.setUniqueID(rs.getInt("UniqueID"));
				customerModel.setCustomerIdentifier(rs.getString("CustomerIdentifier"));
				customerModel.setName(rs.getString("Name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return customerModel;
	}
	
	public ArrayList<CombineModels> getTasks(String customerIdentifier) {
		ArrayList<CombineModels> taskModels = new ArrayList<CombineModels>();
		String sql = "SELECT * FROM customer_tasks, customers WHERE customers.CustomerIdentifier = ? "
					+ "AND customers.UniqueID = customer_tasks.CustomerID";
		
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, customerIdentifier);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					TaskModel tm = new TaskModel();
					tm.setTaskID(rs.getInt("UniqueID"));
					tm.setDescription(rs.getString("Description"));
					tm.setTaskDone(rs.getBoolean("TaskDone"));
					taskModels.add(tm);
				}
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
		return taskModels;
	}
}
