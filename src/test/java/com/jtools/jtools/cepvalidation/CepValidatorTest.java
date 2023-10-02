package com.jtools.jtools.cepvalidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CepValidatorTest {

    @Test
    void validCepWithoutDashShouldReturnTrue() throws IOException {
        CepValidator cepValidator = new CepValidator();
        assertTrue(cepValidator.isCepValid("01001000"));
    }
    @Test
    void validCepWithDashShouldReturnTrue() throws IOException {
        CepValidator cepValidator = new CepValidator();
        assertTrue(cepValidator.isCepValid("01001-000"));
    }

    @Test
    void validCepWithoutDashShouldReturnFalse() throws IOException {
        CepValidator cepValidator = new CepValidator();
        assertFalse(cepValidator.isCepValid("12345678"));
    }
    @Test
    void validCepWithDashShouldReturnFalse() throws IOException {
        CepValidator cepValidator = new CepValidator();
        assertFalse(cepValidator.isCepValid("12345-678"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void invalidCepShouldReturnApiRequestException(String cep) {
        CepValidator cepValidator = new CepValidator();
        assertThrows(CepValidator.ApiRequestException.class, () -> cepValidator.isCepValid(cep));
    }
}
