package com.bp.cbe.configuration;

import com.bp.cbe.domain.error.ApiError;
import com.bp.cbe.domain.error.ApiSubError;
import com.bp.cbe.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> notFoundException(Exception e) {
    log.error(e.toString());
    return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e));
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> noSuchElementException(NoSuchElementException e) {
    log.error(e.toString());
    return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException e) {
    log.error(e.toString());
    return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e) {
    log.error(e.toString());
    ApiError apiError =
        new ApiError(HttpStatus.BAD_REQUEST, "An error occurred with parameter validation.", e);
    e.getConstraintViolations()
        .forEach(
            element ->
                apiError
                    .getSubErrors()
                    .add(new ApiSubError(element.getInvalidValue(), element.getMessage())));
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> bindException(BindException e) {
    log.error(e.toString());
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error in DTO validation.", e);
    e.getBindingResult()
        .getAllErrors()
        .forEach(
            element ->
                apiError
                    .getSubErrors()
                    .add(new ApiSubError(element.getArguments(), element.getDefaultMessage())));
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error(e.toString());
    ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error in DTO validation.", e);
    e.getBindingResult()
        .getAllErrors()
        .forEach(
            element ->
                apiError
                    .getSubErrors()
                    .add(new ApiSubError(element.getArguments(), element.getDefaultMessage())));
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
