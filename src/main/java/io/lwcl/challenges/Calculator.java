package io.lwcl.challenges;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

public class Calculator {

    /* Desafío 1: Calculadora básica
        Crear una aplicación que permita realizar operaciones básicas (suma, resta, multiplicación,
        división) entre dos números.
        Para resolver este desafío, debemos crear un programa que:
        1. Pida al usuario que ingrese dos números.
        2. Pida al usuario que seleccione una operación básica (suma, resta, multiplicación,
        división).
        3. Realice la operación seleccionada con los dos números.
        4. Muestre el resultado al usuario.
     */

    enum OperatorType {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        REMAINDER("%"),
        POWER("^");

        private final String value;

        OperatorType(String value) {
            this.value = value;
        }

        public String getSymbol() {
            return switch (value) {
                case "*" -> "×";
                case "/" -> "÷";
                default -> value;
            };
        }

        public String getValue() {
            return value;
        }

        public static OperatorType fromString(String operator) {
            for (OperatorType button : OperatorType.values()) {
                if (button.getValue().equals(operator) || button.name().equalsIgnoreCase(operator)) {
                    return button;
                }
            }
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static Double perform(OperatorType operator, Double first, Double second) {
        return switch (operator) {
            case ADD -> first + second;
            case SUBTRACT -> first - second;
            case MULTIPLY -> first * second;
            case DIVIDE -> second == 0.0 ? Double.NaN : first / second;
            case REMAINDER -> first % second;
            case POWER -> Math.pow(first, second);
        };
    }

    private static double readNumber(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                out.println("Invalid number. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static OperatorType readOperator(Scanner scanner) {
        while (true) {
            out.println("(ADD(+), SUBTRACT(-), MULTIPLY(*), DIVIDE(/), REMAINDER(%), POWER(^))");
            out.print("Enter the operator: ");
            String operatorInput = scanner.next();
            try {
                return OperatorType.fromString(operatorInput);
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Try-with-resource para AutoClose.
        try (Scanner scanner = new Scanner(System.in)) {
            out.print("Enter the first number: ");
            double firstNumber = readNumber(scanner);

            out.print("Enter the second number: ");
            double secondNumber = readNumber(scanner);

            OperatorType operator = readOperator(scanner);

            double result = perform(operator, firstNumber, secondNumber);
            out.println("Input: " + firstNumber + " " + operator.getSymbol() + " " + secondNumber);
            out.println("Result: " + result);
        }
    }
}
