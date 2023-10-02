package com.jtools.jtools.cpfvalidation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidatorTest {

    @Test
    void formattedCpfShouldReturnTrue() {
        CpfValidator cpfValidator = new CpfValidator();
        assertTrue(cpfValidator.isCpfValid("41135505810"));
    }

    @Test
    void notFormattedCpfShouldReturnTrue() {
        CpfValidator cpfValidator = new CpfValidator();
        assertTrue(cpfValidator.isCpfValid("111.444.777-35"));
    }

    @Test
    void formattedCpfShouldReturnFalse() {
        CpfValidator cpfValidator = new CpfValidator();
        assertFalse(cpfValidator.isCpfValid("11122233363"));
    }

    @Test
    void notFormattedCpfShouldReturnFalse() {
        CpfValidator cpfValidator = new CpfValidator();
        assertFalse(cpfValidator.isCpfValid("111.222.333-63"));
    }

    @Test
    void nullCpfShouldReturnIllegalArgumentException() {
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid(null);
                });
    }

    @Test
    void emptyCpfShouldReturnIllegalArgumentException() {
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("");
                });
    }

    @Test
    void cpfContainsLetterShouldReturnIllegalArgumentException() {
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("123a2145670");
                });
    }

    @Test
    void twelveDigitsCpfShouldReturnIllegalArgumentException(){
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("123456789123");
                });
    }
    @Test
    void tenDigitsCpfShouldReturnIllegalArgumentException(){
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("1234567891");
                });
    }
    @Test
    void onlyLettersCpfShouldReturnIllegalArgumentException(){
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("abcasduiona");
                });
    }
    @Test
    void onlyDotsAndDashesCpfShouldReturnIllegalArgumentException(){
        CpfValidator cpfValidator = new CpfValidator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    cpfValidator.isCpfValid("...---...--");
                });
    }
    @Test
    void invalidCpfWithTypoShouldReturnFalse(){
        CpfValidator cpfValidator = new CpfValidator();
        assertFalse(cpfValidator.isCpfValid("123.456.789--10"));
    }
    @Test
    void validCpfWithTypoShouldReturnTrue(){
        CpfValidator cpfValidator = new CpfValidator();
        assertTrue(cpfValidator.isCpfValid("111.444.777--35"));
    }


}