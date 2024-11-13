package io.lwcl.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.out;

public class Helper {

    private Helper() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean getYesNoInput(Scanner scanner, String prompt) {
        out.print(prompt);
        return scanner.next().trim().equalsIgnoreCase("S");
    }

    public static Integer getInputInt(Scanner scanner, Integer maxValue) {
        out.print(": ");
        try {
            int value = scanner.nextInt();
            if (maxValue != null && (value < 1 || value > maxValue)) {
                out.println("Valor fuera de rango. Ingrese un numero entre 1 y " + maxValue);
                return null;
            }

            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Entrada invalida. Por favor, ingrese un numero.");
            scanner.nextLine(); // Clear invalid input
            return null;
        }
    }

    public static Integer getInputAmount(Scanner scanner, Integer defaultValue, Integer maxValue) {
        out.print(": ");
        try {
            int value = scanner.nextInt();
            if (maxValue != null && (value < 1 || value > maxValue)) {
                out.println("Valor fuera de rango. Utilizando cantidad predeterminada: " + defaultValue);
                return defaultValue;
            }

            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Entrada invalida. Utilizando cantidad predeterminada: " + defaultValue);
            scanner.nextLine(); // Clear invalid input
            return defaultValue;
        }
    }

    public static Double getInputDouble(Scanner scanner, Double maxValue) {
        out.print(": ");
        try {
            double value = scanner.nextDouble();
            if (maxValue != null && (value < 1 || value > maxValue)) {
                out.println("Valor fuera de rango. Ingrese un numero entre 1 y " + maxValue);
                return null;
            }
            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Entrada invalida. Por favor, ingrese un numero.");
            scanner.nextLine(); // Clear invalid input
            return null;
        }

    }
}
