package wrappers;

public class ClientIdWrapper {
   
	private int ide;
	private int id2=2;

	public ClientIdWrapper() {

	}
	
	public ClientIdWrapper(int ide) {
		this.ide = id2;
	}

	public void setId(int ide) {
		this.ide = ide;
	}
	
	public int getId() {
		return ide;
	}

	@Override
	public String toString() {
		return "ClientIdWrapper [id=" + ide + id2 + this.ide+ " ]";
	}
}
