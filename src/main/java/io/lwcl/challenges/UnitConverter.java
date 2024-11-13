package io.lwcl.challenges;

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
            Conversion conversionType = getConversionOption(scanner);
            if (conversionType == null) return;

            DoubleUnit input = getValue(scanner, conversionType.from);
            if (input.value == null) return;

            DoubleUnit result = performConversion(input.value, conversionType);
            if (result.value == null) return;

            out.printf("%s are %s", input, result);
            out.println();
        }
    }

    private static Conversion getConversionOption(Scanner scanner) {
        out.println("Select a conversion type:");
        out.println(" [1]. Celsius to Fahrenheit");
        out.println(" [2]. Fahrenheit to Celsius");
        out.println(" [3]. Euros to Dollars");
        out.println(" [4]. Dollars to Euros");

        out.print("Input the option you want to perform: ");
        Integer option = Helper.getInputInt(scanner, 1, 4);

        return switch (option) {
            case 1 -> Conversion.CELSIUS_TO_FAHRENHEIT;
            case 2 -> Conversion.FAHRENHEIT_TO_CELSIUS;
            case 3 -> Conversion.EURO_TO_DOLLAR;
            case 4 -> Conversion.DOLLAR_TO_EURO;
            case null, default -> null;
        };

    }

    private static DoubleUnit getValue(Scanner scanner, Unit unit) {
        out.printf("Enter the value in (%s): ", unit);
        Double result = Helper.getInputDouble(scanner, null, null);
        return new DoubleUnit(result, unit);
    }

    private static DoubleUnit performConversion(Double value, Conversion conversionType) {
        double result = conversionType.convert(value);
        return new DoubleUnit(result, conversionType.to);
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


    enum Unit {
        CELSIUS, FAHRENHEIT, EURO, DOLLAR
    }

    @FunctionalInterface
    interface ConversionFunction {
        double convert(double value);

    }

    public record DoubleUnit(Double value, Unit unit) {
        @Override
        public String toString() {
            return String.format("%.2f (%s)", value, unit);
        }
    }

    enum Conversion {
        CELSIUS_TO_FAHRENHEIT(Unit.CELSIUS, Unit.FAHRENHEIT, UnitConverter::celsiusToFahrenheit),
        FAHRENHEIT_TO_CELSIUS(Unit.FAHRENHEIT, Unit.CELSIUS, UnitConverter::fahrenheitToCelsius),
        EURO_TO_DOLLAR(Unit.EURO, Unit.DOLLAR, UnitConverter::eurosToDollars),
        DOLLAR_TO_EURO(Unit.DOLLAR, Unit.EURO, UnitConverter::dollarsToEuros);

        private final Unit from;
        private final Unit to;
        private final ConversionFunction conversionFunction;

        Conversion(Unit from, Unit to, ConversionFunction conversionFunction) {
            this.from = from;
            this.to = to;
            this.conversionFunction = conversionFunction;
        }

        public double convert(double value) {
            return conversionFunction.convert(value);
        }
    }
}