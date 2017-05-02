package wrappers;

public class ClientWrapper {
	
	private String name, surname, address, dni;
	
	private int id;
	
	public ClientWrapper() {
	
	}

	public ClientWrapper(int id, String name, String surname, String address, String dni) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "ClientWrapper [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", dni=" + dni + "]";
	}

}
