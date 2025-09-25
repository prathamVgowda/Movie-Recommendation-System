package movie.system.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) 
	{
		logger.error("ResourceNotFoundException: {}", ex.getMessage());
	    ErrorResponse errorResponse = new ErrorResponse(
	        ex.getMessage(),
	        ex.getStatus(),
	        HttpStatus.NOT_FOUND.getReasonPhrase(),
	        request.getRequestURI(),
	        request.getMethod(),
	        ex.getTimestamp()
	    );
	    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
