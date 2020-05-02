package sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class CreateSQLiteDatabase {
	private String url = "jdbc:sqlite:customerdb.db";
	
	private void initDb() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		File dbfile = new File("customerdb.db");		
		if(!dbfile.exists()) {			
			SQLiteDataSource ds = null;
			ds = new SQLiteDataSource();
			ds.setUrl(url);
		}
	}
	
	private void createCustomersTable() throws SQLException {
		String command = "CREATE TABLE IF NOT EXISTS customers ("
						+ "UniqueID integer PRIMARY KEY,"
						+ "CustomerIdentifier text NOT NULL UNIQUE,"
						+ "Name text"
						+ ");";
		
		Connection conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		stmt.execute(command);
		conn.close();
		
	}
	
	private void createCustomerTasksTable() throws SQLException {
		String command = "CREATE TABLE IF NOT EXISTS customer_tasks ("
						+ "UniqueID integer PRIMARY KEY,"
						+ "CustomerID integer,"
						+ "Description text,"
						+ "TaskDone BOOLEAN,"
						+ "FOREIGN KEY(CustomerID) REFERENCES customers(UniqueID)"
						+ ");";
		
		Connection conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		stmt.execute(command);
		conn.close();
	}
	
	private void fillInData() throws SQLException {
		String commands[] = {
		"INSERT INTO customers(UniqueID, CustomerIdentifier, Name) VALUES(1, 'customer1', 'Tifa')",
		"INSERT INTO customers(UniqueID, CustomerIdentifier, Name) VALUES(2, 'customer2', 'Cloud')",
		"INSERT INTO customers(UniqueID, CustomerIdentifier, Name) VALUES(3, 'customer3', 'Barret')",
		"INSERT INTO customers(UniqueID, CustomerIdentifier, Name) VALUES(4, 'customer4', 'Aerith')",
		"INSERT INTO customers(UniqueID, CustomerIdentifier, Name) VALUES(5, 'customer5', 'Red')",
		
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(1, 1, 'offer lunch', false)",
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(2, 1, 'consult', true)",
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(3, 1, 'buy offerings', true)",
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(4, 4, 'do something', false)",
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(5, 4, 'test code', false)",
		"INSERT INTO customer_tasks(UniqueID, CustomerID, Description, TaskDone) VALUES(6, 4, 'offer drinks', true)"
		};
		
		Connection conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		
		stmt.execute("DELETE FROM customer_tasks;");
		stmt.execute("DELETE FROM customers;");
		for (String command : commands) {
			stmt.execute(command);
		}	
		conn.close();		
	}
	
	public void createDb() {		
		try {
			initDb();
			createCustomersTable();
			createCustomerTasksTable();
			fillInData();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
