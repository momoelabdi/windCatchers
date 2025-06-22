package org.env.windCatchers.responses;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // handle all api responses
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // Already wrapped ?
        if (body instanceof ApiResponse) {
            return body;
        }

        // Handle ResponseEntity
        if (body instanceof ResponseEntity<?>) {
            ResponseEntity<?> entity = (ResponseEntity<?>) body;
            Object actualBody = entity.getBody();

            if (actualBody instanceof ApiResponse) {
                return entity; // already wrapped
            }

            ApiResponse<?> wrapped = new ApiResponse<>(actualBody);
            return new ResponseEntity<>(wrapped, entity.getHeaders(), entity.getStatusCode());
        }

        // Otherwise just wrap normally
        return new ApiResponse<>(body);
    }
}
