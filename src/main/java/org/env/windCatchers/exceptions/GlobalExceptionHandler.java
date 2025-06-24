package org.env.windCatchers.exceptions;

import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.env.windCatchers.responses.ApiError;
import org.env.windCatchers.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.MDC;


@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        // Collect detailed validation error messages
        String errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError(
            "Validation failed: " + errors,
            "VALIDATION_ERROR",
            HttpStatus.BAD_REQUEST.value()
        );
        ApiResponse<ApiError> response = new ApiResponse<>(apiError);
        enrichResponse(response, request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ApiError>> handleAll(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
            "Internal server error: " + ex.getMessage(),
            "INTERNAL_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        ApiResponse<ApiError> response = new ApiResponse<>(apiError);
        enrichResponse(response, request);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
            ex.getMessage(),
            "NOT_FOUND",
            HttpStatus.NOT_FOUND.value()
        );

        ApiResponse<ApiError> response = new ApiResponse<>(apiError);
        enrichResponse(response, request);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    private void enrichResponse(ApiResponse<?> response, HttpServletRequest request) {
        response.setPath(request.getRequestURI());
        response.setMethod(request.getMethod());

        String traceId = MDC.get("traceId");
        if (traceId != null )
        response.setTraceId(traceId);
    }
}
