package models;

public class CustomerModel implements CombineModels{
	private int uniqueID;
	private String customerIdentifier;
	private String name;
	
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getCustomerIdentifier() {
		return customerIdentifier;
	}
	public void setCustomerIdentifier(String customerIdentifier) {
		this.customerIdentifier = customerIdentifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
