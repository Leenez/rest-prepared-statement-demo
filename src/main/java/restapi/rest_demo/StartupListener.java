package restapi.rest_demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import sql.CreateSQLiteDatabase;

@WebListener
public class StartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub	
	}

	//On server start create the demo database or if database already exists empty it's tables.
	@Override
	public void contextInitialized(ServletContextEvent arg0) {		
		CreateSQLiteDatabase csdb = new CreateSQLiteDatabase();
		csdb.createDb();
	}

}
