package org.curso.spring.besttravel.domain.exceptions;

public class InvalidRangeException extends RuntimeException {
    public InvalidRangeException(Integer num) {
        super("Out of range" + num);
    }
}
