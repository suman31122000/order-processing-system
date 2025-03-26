package com.Food_Ordering_system.Food_Ordering_system.Exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CustomErrorResponse {
    private String message;
    private Boolean success;
    private HttpStatus status;
}
