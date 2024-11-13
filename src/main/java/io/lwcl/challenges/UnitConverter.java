package io.lwcl.challenges;

import java.util.Scanner;

import static java.lang.System.out;

public class UnitConverter {

    /* Desafío 3: Conversión de unidades
        Crear una aplicación que convierta unidades de medida (por ejemplo, de Celsius a
        Fahrenheit, de euros a dólares, etc.).
        Para resolver este desafío, debemos crear un programa que:
        1. Pida al usuario que seleccione una unidad de medida (por ejemplo, Celsius a Fahrenheit).
        2. Pida al usuario que ingrese un valor en la unidad seleccionada.
        3. Realice la conversión de unidades utilizando una fórmula específica.
        4. Muestre el resultado al usuario.
     */

    private static final double EURO_TO_DOLLAR_RATE = 1.07;
    private static final double DOLLAR_TO_EURO_RATE = 0.94;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int option = getConversionOption(scanner);
            double value = getValue(scanner, option);
            double result = performConversion(option, value);

            displayResult(option, value, result);
        }
    }

    private static int getConversionOption(Scanner scanner) {
        out.println("Seleccione una conversion:");
        out.println(" [1]. Celsius a Fahrenheit");
        out.println(" [2]. Fahrenheit a Celsius");
        out.println(" [3]. Euros a Dolares");
        out.println(" [4]. Dolares a Euros");

        out.print("Ingrese el numero de la conversion que desea realizar: ");
        return scanner.nextInt();
    }

    private static double getValue(Scanner scanner, int option) {
        out.print("Ingrese el valor en " + getUnitName(option) + ": ");
        return scanner.nextDouble();
    }

    private static String getUnitName(int option) {
        return switch (option) {
            case 1 -> "Celsius";
            case 2 -> "Fahrenheit";
            case 3 -> "Euros";
            case 4 -> "Dolares";
            default -> "valor";
        };
    }

    private static double performConversion(int option, double value) {
        return switch (option) {
            case 1 -> celsiusToFahrenheit(value);
            case 2 -> fahrenheitToCelsius(value);
            case 3 -> eurosToDollars(value);
            case 4 -> dollarsToEuros(value);
            default -> throw new IllegalArgumentException("Opcion no valida.");
        };
    }

    private static void displayResult(int option, double value, double result) {
        switch (option) {
            case 1:
                out.printf("%.2f Celsius son %.2f Fahrenheit.%n", value, result);
                break;
            case 2:
                out.printf("%.2f Fahrenheit son %.2f Celsius.%n", value, result);
                break;
            case 3:
                out.printf("%.2f Euros son %.2f Dolares.%n", value, result);
                break;
            case 4:
                out.printf("%.2f Dolares son %.2f Euros.%n", value, result);
                break;
            default:
                out.println("Opcion no valida. Por favor, seleccione una opcion entre 1 y 4.");
        }
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double eurosToDollars(double euros) {
        return euros * EURO_TO_DOLLAR_RATE;
    }

    public static double dollarsToEuros(double dollars) {
        return dollars * DOLLAR_TO_EURO_RATE;
    }
}