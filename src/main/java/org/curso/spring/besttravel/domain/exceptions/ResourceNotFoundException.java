package org.curso.spring.besttravel.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(String.format("%s with id %s was not found", resourceName, resourceId));
    }
}
