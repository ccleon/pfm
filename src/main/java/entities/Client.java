package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private String dni;
	
	public Client (){
	}
	
	public Client (String dni){
		this.dni = dni;
	}
	
	public int getId() {
        return id;
    }
	
	public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Client) obj).id;
    }

	@Override
	public String toString() {
		return "Client [id=" + id + ", dni=" + dni + "]";
	}
  
}
