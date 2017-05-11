package wrappers;

public class ClientCreateWrapper {
	
	private String name; 
	
	private String surname;
	
	private String phone;
	
	private String dni;
	
	public ClientCreateWrapper() {
	
	}

	public ClientCreateWrapper(String name, String surname, String phone, String dni) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "ClientWrapper [name=" + name + ", surname=" + surname + ", phone=" + phone + ", dni=" + dni + "]";
	}

}
