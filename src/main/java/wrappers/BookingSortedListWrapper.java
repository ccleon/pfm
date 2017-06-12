package wrappers;

public class BookingSortedListWrapper {
	
	private String parameter; 
	
	public BookingSortedListWrapper() {	
		
	}
	
	public BookingSortedListWrapper(String parameter) {
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "BookingSortedListWrapper [parameter=" + parameter + "]";
	}

}
