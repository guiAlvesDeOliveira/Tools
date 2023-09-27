package com.jtools.jtools.CpfValidation;

public class CpfValidator {
    public boolean isCpfValid(String cpf) {
        int firstDigit = calculateFirstDigit(cpf);
        int secondDigit = calculateSecondDigit(cpf);

        if (firstDigit == Character.getNumericValue(cpf.charAt(cpf.length() - 2)) &&
                secondDigit == Character.getNumericValue(cpf.charAt(cpf.length() - 1))
        ) return true;

        return false;
    }

    private int calculateFirstDigit(String cpf) {
        int sum = 0;

        for (int i = 1; i <= 9; i++) {
            sum = sum + (11 - i) * Character.getNumericValue(cpf.charAt(i + 1));
        }
        int resto = sum % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }

    private int calculateSecondDigit(String cpf) {
        int sum = 0;

        for (int i = 0; i <= 9; i++) {
            sum = sum + (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int resto = sum % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }

}
