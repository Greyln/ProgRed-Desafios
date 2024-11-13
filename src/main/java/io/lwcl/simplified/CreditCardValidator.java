package io.lwcl.simplified;

import java.util.Scanner;

import static java.lang.System.out;

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
        Scanner scanner = new Scanner(System.in);

        String cardNumber = getCardNumber(scanner);

        if (isCardNumberValid(cardNumber)) {
            validateCard(cardNumber);
        } else {
            out.println("El numero de tarjeta de credito debe contener solo digitos.");
        }
    }

    private static String getCardNumber(Scanner scanner) {
        out.print("Ingrese el numero de tarjeta de credito: ");
        return scanner.nextLine().replaceAll("\\s+", ""); // Eliminar espacios en blanco
    }

    private static boolean isCardNumberValid(String number) {
        return number.matches("\\d+");
    }

    private static void validateCard(String cardNumber) {
        if (isLuhnValid(cardNumber)) {
            out.println("El numero de tarjeta de credito es valido.");
        } else {
            out.println("El numero de tarjeta de credito no es valido.");
        }
    }

    public static boolean isLuhnValid(String number) {
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