package io.lwcl.simplified;

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

    private static Double perform(String operator, Double first, Double second) {
        return switch (operator) {
            case "+", "ADD" -> first + second;
            case "-", "SUBSTRACT" -> first - second;
            case "*", "MULTIPLY" -> first * second;
            case "/", "DIVIDE" -> second == 0.0 ? Double.NaN : first / second;
            case "%", "REMAINDER" -> first % second;
            case "^", "POWER" -> Math.pow(first, second);
            default -> throw new IllegalStateException("Unexpected value: " + operator);
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

    private static String readOperator(Scanner scanner) {
        while (true) {
            out.println("(ADD(+), SUBTRACT(-), MULTIPLY(*), DIVIDE(/), REMAINDER(%), POWER(^))");
            out.print("Enter the operator: ");
            String operatorInput = scanner.next();
            try {
                perform(operatorInput, 1.0, 1.0);
                return operatorInput;
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.print("Enter the first number: ");
        double firstNumber = readNumber(scanner);

        out.print("Enter the second number: ");
        double secondNumber = readNumber(scanner);

        String operator = readOperator(scanner);

        double result = perform(operator, firstNumber, secondNumber);
        out.println("Input: " + firstNumber + " " + operator + " " + secondNumber);
        out.println("Result: " + result);

        scanner.close();
    }
}
