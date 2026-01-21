package org.curso.spring.besttravel.domain.exceptions;

import java.math.BigDecimal;

public class InvalidPriceRangeException extends RuntimeException {
    public InvalidPriceRangeException(BigDecimal min, BigDecimal max) {
        super("""
                Invalid price range.
                Prices must be >= 0 and min price must be less than or equal to max price.
                Received: min=%s, max=%s""".formatted(min, max));
    }
}
