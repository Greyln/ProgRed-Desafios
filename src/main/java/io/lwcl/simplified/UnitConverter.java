package io.lwcl.simplified;

import io.lwcl.utils.Helper;

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
            Integer option = getConversionOption(scanner);
            if (option == null) return;

            Double value = getValue(scanner, option);
            if (value == null) return;

            Double result = performConversion(option, value);
            if (result == null) return;

            String from = getFromUnit(option);
            String to = getToUnit(option);

            out.printf("%.2f (%s) are %.2f (%s)", value, from, result, to);
        }
    }

    private static Integer getConversionOption(Scanner scanner) {
        out.println("Select a conversion type:");
        out.println(" [1]. Celsius to Fahrenheit");
        out.println(" [2]. Fahrenheit to Celsius");
        out.println(" [3]. Euros to Dollars");
        out.println(" [4]. Dollars to Euros");

        out.print("Input the option you want to perform: ");
        return Helper.getInputInt(scanner, 1, 4);
    }

    private static Double getValue(Scanner scanner, int option) {
        out.printf("Enter the value in (%s): ", getFromUnit(option));
        return Helper.getInputDouble(scanner, null, null);
    }

    private static String getFromUnit(int option) {
        return switch (option) {
            case 1 -> "Celsius";
            case 2 -> "Fahrenheit";
            case 3 -> "Euros";
            case 4 -> "Dollars";
            default -> "None";
        };
    }
    private static String getToUnit(int option) {
        return switch (option) {
            case 1 -> "Fahrenheit";
            case 2 -> "Celsius";
            case 3 -> "Dollars";
            case 4 -> "Euros";
            default -> "None";
        };
    }


    private static double performConversion(int option, double value) {
        return switch (option) {
            case 1 -> celsiusToFahrenheit(value);
            case 2 -> fahrenheitToCelsius(value);
            case 3 -> eurosToDollars(value);
            case 4 -> dollarsToEuros(value);
            default -> throw new IllegalArgumentException("Invalid Option.");
        };
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