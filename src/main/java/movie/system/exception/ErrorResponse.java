package movie.system.exception;

import java.time.LocalDateTime;

public class ErrorResponse 
{

	 private String message;
	    private int status;
	    private String error;
	    private String path;
	    private String method;
	    private LocalDateTime timestamp;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		
		public LocalDateTime getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
		public ErrorResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ErrorResponse(String message, int status, String error, String path, String method,
				LocalDateTime timestamp) {
			super();
			this.message = message;
			this.status = status;
			this.error = error;
			this.path = path;
			this.method = method;
			this.timestamp = timestamp;
		}
		
	    
	    
	    
	    
    
    
}
