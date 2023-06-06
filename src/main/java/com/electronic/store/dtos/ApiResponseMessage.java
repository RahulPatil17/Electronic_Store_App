package com.electronic.store.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseMessage {

    private HttpStatus status;
    private boolean success;
    private String message;
}
