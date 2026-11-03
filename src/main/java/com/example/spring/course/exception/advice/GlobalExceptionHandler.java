package com.example.spring.course.exception.advice;

import com.example.spring.course.exception.AppException;
import com.example.spring.course.exception.advice.model.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiErrorResponse> handleAppException(AppException e, HttpServletRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(e.getStatus());
        apiErrorResponse.setMessage(e.getMessage());
        apiErrorResponse.setError(e.getStatus().getReasonPhrase());
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(e.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        apiErrorResponse.setPath(request.getRequestURI());

        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
}
