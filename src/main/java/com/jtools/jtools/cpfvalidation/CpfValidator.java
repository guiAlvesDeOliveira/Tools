package com.jtools.jtools.cpfvalidation;

public class CpfValidator {
    public boolean isCpfValid(String cpf) {
        String formattedCpf = formatCpf(cpf);
        validCpf(formattedCpf);

        return areDigitsValid(
                calculateDigits(formattedCpf, 9, 10),
                calculateDigits(formattedCpf, 10, 11),
                Character.getNumericValue(cpf.charAt(cpf.length() - 2)),
                Character.getNumericValue(cpf.charAt(cpf.length() - 1))
        );
    }

    private int calculateDigits(String cpf, int end, int multiplier){
        int sum = 0;
        for (int i = 0; i < end; i++) {
            sum += (multiplier - i) * Character.getNumericValue(cpf.charAt(i));
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
            throw new IllegalArgumentException("CPF cannot contain letters or symbols");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF must have 11 digits");
        }
    }
}
