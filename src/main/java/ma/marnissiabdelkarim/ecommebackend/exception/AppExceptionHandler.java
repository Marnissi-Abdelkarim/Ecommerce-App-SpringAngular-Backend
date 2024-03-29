package ma.marnissiabdelkarim.ecommebackend.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ma.marnissiabdelkarim.ecommebackend.responses.ErrorMessage;


@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value= {UserException.class})
	public ResponseEntity<Object> HandleUserException(UserException ex, WebRequest request) {
		
		ErrorMessage errorMessage= new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(value= Exception.class)
	public ResponseEntity<Object> HandleOthersException(Exception ex, WebRequest request) {
		
		ErrorMessage errorMessage= new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	
	@ExceptionHandler(value  =MethodArgumentNotValidException.class)
	public ResponseEntity<Object> HandleMethodeArgumentNotValid(MethodArgumentNotValidException ex , WebRequest request )
	{
		Map<String, String> errors = new HashMap<>();
		// ex :est un table des exception qui s'affiche par defaut
		ex.getBindingResult().getFieldErrors().forEach(error ->
		errors.put(error.getField(),error.getDefaultMessage())
				);
		

		
		return new ResponseEntity<>(errors,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}

}
