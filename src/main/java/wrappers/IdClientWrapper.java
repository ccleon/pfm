package wrappers;


public class IdClientWrapper {
   
    private long id;
    
    public IdClientWrapper(){
        
    }
    
    public IdClientWrapper(long id){
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TicketWrapper [id=" + id + "]";
    }
       
}
