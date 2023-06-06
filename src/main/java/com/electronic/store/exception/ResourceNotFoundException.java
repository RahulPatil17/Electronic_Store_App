package com.electronic.store.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName) {
        super(resourceName);

    }
}
