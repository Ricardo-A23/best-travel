package org.curso.spring.besttravel.domain.exceptions;

import java.math.BigDecimal;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException(BigDecimal price) {
        super("Price must be greater than or equal to zero. Value received: " + price);
    }
}
