package ch.heigvd.pro.b04.auth.exceptions;

import ch.heigvd.pro.b04.error.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LoginControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UnknownUserCredentialsException.class)
  public ResponseEntity<ErrorResponse> unknownUserCredentials() {
    return new ResponseEntity<>(ErrorResponse.from("Invalid credentials."), HttpStatus.BAD_REQUEST);
  }

  /**
   * Returns an {@link ErrorResponse} if some invalid credentials are provided.
   */
  @ExceptionHandler(WrongCredentialsException.class)
  public ResponseEntity<ErrorResponse> wrongCredentials() {
    return new ResponseEntity<>(
            ErrorResponse.from("Wrong credentials (username / password)."),
            HttpStatus.FORBIDDEN
    );
  }

  /**
   * Returns an {@link ErrorResponse} if some invalid credentials are provided.
   */
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> invalidCredentials() {
    return new ResponseEntity<>(
        ErrorResponse.from("Invalid credentials (username / password)."),
        HttpStatus.BAD_REQUEST
    );
  }

  /**
   * Returns an {@link ErrorResponse} if a duplicate username exception is triggered.
   */
  @ExceptionHandler(DuplicateUsernameException.class)
  public ResponseEntity<ErrorResponse> duplicateUsername() {
    return new ResponseEntity<>(
        ErrorResponse.from("Username already registered !"),
        HttpStatus.FORBIDDEN
    );
  }
}
