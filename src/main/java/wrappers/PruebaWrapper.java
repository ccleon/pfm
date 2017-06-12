package wrappers;

public class PruebaWrapper {

	private int prueba;
	
	public PruebaWrapper(){
		
	}
	
	public PruebaWrapper(int prueba){
		this.prueba = prueba;
	}
	
	public int getPrueba(){
		return this.prueba;
	}
	
	public void setPrueba (int prueba){
		this.prueba = prueba;
	}

	@Override
	public String toString() {
		return "PruebaWrapper [prueba=" + prueba + "]";
	}
	
	
	
}
