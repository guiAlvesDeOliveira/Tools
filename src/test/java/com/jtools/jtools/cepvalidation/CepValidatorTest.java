package com.jtools.jtools.cepvalidation;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CepValidatorTest {

    @Test
    void validCepShouldReturnTrue() throws IOException {
        CepValidator cepValidator = new CepValidator();
        assertTrue(cepValidator.isCepValid("01001-000"));
    }
}
