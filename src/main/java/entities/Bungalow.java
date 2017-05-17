package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Bungalow {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private int number;
	
	private BigDecimal pricePerNight;
	
	public Bungalow (){
	}
	
	public Bungalow (int number, BigDecimal pricePerNight){
		this.number = number;
		this.pricePerNight = pricePerNight;
	}
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
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
        return id == ((Bungalow) obj).id;
    }

	@Override
	public String toString() {
		return "Bungalow [id=" + id + ", number=" + number + ", pricePerNight=" + pricePerNight + "]";
	}
	
}
