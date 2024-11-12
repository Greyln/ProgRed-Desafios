package io.lwcl;

import java.util.Scanner;

public class CreditCardValidator {

    /* Desafío 4: Validación de datos
        Crear una aplicación que valide si un número de tarjeta de crédito es válido según el
        algoritmo de Luhn.
        Para resolver este desafío, debemos crear un programa que:

        1. Pida al usuario que ingrese un número de tarjeta de crédito.
        2. Verifique si el número es válido utilizando el algoritmo de Luhn.
        3. Muestre un mensaje indicando si el número es válido o no.
     */

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String cardNumber = getCardNumber(scanner);

            if (isCardNumberValid(cardNumber)) {
                validateCard(cardNumber);
            } else {
                System.out.println("El número de tarjeta de crédito debe contener solo dígitos.");
            }
        }
    }

    private static String getCardNumber(Scanner scanner) {
        System.out.print("Ingrese el número de tarjeta de crédito: ");
        return scanner.nextLine().replaceAll("\\s+", ""); // Eliminar espacios en blanco
    }

    private static boolean isCardNumberValid(String number) {
        return number.matches("\\d+");
    }

    private static void validateCard(String cardNumber) {
        if (LuhnAlgorithm.isValid(cardNumber)) {
            System.out.println("El número de tarjeta de crédito es válido.");
        } else {
            System.out.println("El número de tarjeta de crédito no es válido.");
        }
    }
}

class LuhnAlgorithm {

    public static boolean isValid(String number) {
        int sum = 0;
        boolean alter = false;

        // Recorrer el número de derecha a izquierda
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alter) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9; // Sumar los dígitos del resultado
                }
            }

            sum += digit;
            alter = !alter; // Alternar el estado
        }

        // El número es válido si la suma es un múltiplo de 10
        return sum % 10 == 0;
    }
}