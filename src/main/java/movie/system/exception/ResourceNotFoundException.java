package movie.system.exception;

import java.time.LocalDateTime;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final LocalDateTime timestamp;

    public ResourceNotFoundException(String message, int status, LocalDateTime timestamp) {
        super(message); // Pass the message to RuntimeException
        this.status = status;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
