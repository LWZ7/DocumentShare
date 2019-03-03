package bean;

public class User {
	public int id;
	public String name;
	public String password;
	public String email_address;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	
	public String getEmail_address() {
		return email_address;
	}
}
