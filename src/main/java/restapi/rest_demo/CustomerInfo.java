package restapi.rest_demo;

import java.util.ArrayList;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import models.CombineModels;
import models.CustomerModel;
import sql.SQLiteQueries;

@Path("customerinfo")
public class CustomerInfo{
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<CombineModels> getCustomerInfo(@FormParam("identifier") String identifier) {
		SQLiteQueries slq = new SQLiteQueries();
		CustomerModel cm = slq.getCustomer(identifier);
		ArrayList<CombineModels> tml = slq.getTasks(identifier);
		if(cm.getCustomerIdentifier() != null) {
			tml.add(0,cm);
		}
						
        return tml;
    }

}
