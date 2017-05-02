package wrappers;

public class ClientCreateWrapper {
	
	private String name, surname, address, dni;
	
	public ClientCreateWrapper() {
	
	}

	public ClientCreateWrapper(String name, String surname, String address, String dni) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "ClientWrapper [name=" + name + ", surname=" + surname + ", address=" + address + ", dni=" + dni + "]";
	}

}
