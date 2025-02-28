package movie.system.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int status;

    public ResourceNotFoundException(String message, int status) {
        super(message);  
        this.status = status;
    }

    @Override
    public String getMessage() {
        return super.getMessage(); 
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
