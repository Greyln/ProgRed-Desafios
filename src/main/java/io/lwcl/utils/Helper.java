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
        String answer = scanner.next().trim().toUpperCase();
        return answer.matches("(Y|S|YES|SI)");
    }

    public static Integer getInputInt(Scanner scanner, Integer minValue, Integer maxValue) {
        try {
            int value = scanner.nextInt();
            if (minValue != null && maxValue != null && (value < minValue || value > maxValue)) {
                out.printf("Value out of range. Enter a number between %d and %d.", minValue, maxValue);
                out.println();
                return null;
            }

            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Invalid entry. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return null;
        }
    }

    public static Integer getInputAmount(Scanner scanner, Integer defaultValue, Integer maxValue) {
        try {
            int value = scanner.nextInt();
            if (maxValue != null && (value < 1 || value > maxValue)) {
                out.println("Value out of range. Using default amount: " + defaultValue);
                return defaultValue;
            }

            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Invalid entry. Using default amount: " + defaultValue);
            scanner.nextLine(); // Clear invalid input
            return defaultValue;
        }
    }

    public static Double getInputDouble(Scanner scanner, Double minValue, Double maxValue) {
        try {
            double value = scanner.nextDouble();
            if (minValue != null && maxValue != null && (value < minValue || value > maxValue)) {
                    out.printf("Value out of range. Enter a number between %f and %f.", minValue, maxValue);
                    out.println();
                    return null;
                }

            return value;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Invalid entry. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return null;
        }

    }
}
