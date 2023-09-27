package com.jtools.jtools.cpfvalidation;

public class CpfValidator {
    public boolean isCpfValid(String cpf) {
        cpf = formatCpf(cpf);
        validCpf(cpf);

        return areDigitsValid(
                calculateFirstDigit(cpf),
                calculateSecondDigit(cpf),
                Character.getNumericValue(cpf.charAt(cpf.length() - 2)),
                Character.getNumericValue(cpf.charAt(cpf.length() - 1)));
    }

    private int calculateFirstDigit(String cpf) {
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum = sum + (10 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int remainder = sum % 11;

        return remainder < 2 ? 0 : 11 - remainder;
    }

    private int calculateSecondDigit(String cpf) {
        int sum = 0;

        for (int i = 0; i <= 9; i++) {
            sum = sum + (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int remainder = sum % 11;

        return remainder < 2 ? 0 : 11 - remainder;
    }

    private String formatCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF cannot be empty or null");
        }
        return cpf.replace(".", "").replace("-", "");
    }

    private boolean areDigitsValid(int firstDigit, int secondDigit, int firstValidator, int secondValidator) {
        return firstDigit == firstValidator && secondDigit == secondValidator;
    }

    private boolean containsOnlyDigits(String cpf) {
        for (char c : cpf.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void validCpf(String cpf) {
        if (!containsOnlyDigits(cpf)) {
            throw new IllegalArgumentException("CPF cannot contain letters");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF must have 11 digits");
        }
    }
}
