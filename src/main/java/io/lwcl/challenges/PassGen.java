package io.lwcl.challenges;

import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.out;

public class PassGen {

    /* Desafío 6: Generador de contraseñas
        Crear una aplicación que genere contraseñas aleatorias seguras.
        Para resolver este desafío, debemos crear un programa que:
        1. Genere una contraseña aleatoria utilizando caracteres específicos.
        2. Muestre la contraseña al usuario.
     */

    public static class PasswordConfig {
        boolean useUpperCase = true;
        boolean useLowerCase = true;
        boolean useNumbers = true;
        boolean useSymbols = true;
        boolean notAmbiguous = false;
        boolean notSimilar = false;

        int length = 12;
        int amount = 3;
    }

    private static final String CHARS_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHARS_NUMBERS = "0123456789";
    private static final String CHARS_SYMBOLS = "!@#$%&^*~-_=+?";
    private static final String CHARS_AMBIGUOUS = "(){}[]/\\~;:,.<>'\"";
    private static final String CHARS_SIMILAR = "0OoLli1";

    private static final int DEFAULT_LENGTH = 12;
    private static final int DEFAULT_AMOUNT = 3;
    private static final int MAX_LENGTH = 128;
    private static final int MAX_AMOUNT = 100;

    public static void main(String[] args) {
        // Try-with-resources para AutoClose.
        try (Scanner scanner = new Scanner(System.in)) {
            out.println("Bienvenido al generador de contraseñas.");
            out.println();

            PasswordConfig passCFG = getPassCFG(scanner);
            String chars = getChars(passCFG);

            if (chars.isEmpty()) {
                out.println("No se han seleccionado caracteres validos para generar la contraseña.");
                return;
            }

            out.println("Generando...");

            for (int i = 0; i < passCFG.amount; i++) {
                String password = genPassword(passCFG.length, chars);
                out.println("["+ i +"]: " + password);
            }
        }
    }

    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        out.print(prompt);
        return scanner.next().trim().equalsIgnoreCase("S");
    }

    private static int getInputAmount(Scanner scanner, int defaultValue, int maxValue) {
        out.print(": ");
        try {
            int value = scanner.nextInt();
            if (value < 1 || value > maxValue) {
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

    public static PasswordConfig getPassCFG(Scanner scanner) {
        PasswordConfig cfg = new PasswordConfig();

        out.printf("Cuantas contraseñas desea generar? (1-%d)", MAX_AMOUNT);
        cfg.amount = getInputAmount(scanner, DEFAULT_AMOUNT, MAX_AMOUNT);
        out.println();

        out.printf("Longitud de los caracteres? (1-%d)", MAX_LENGTH);
        cfg.length = getInputAmount(scanner, DEFAULT_LENGTH, MAX_LENGTH);
        out.println();

        out.print("Desea configurar los tipos de caracteres (S/N)? ");
        boolean toggleChars = scanner.next().equalsIgnoreCase("S");
        out.println();

        if (toggleChars) {
            cfg.useUpperCase = getYesNoInput(scanner, "Desea incluir mayusculas (S/N)? ");
            cfg.useLowerCase = getYesNoInput(scanner, "Desea incluir minusculas (S/N)? ");
            cfg.useNumbers = getYesNoInput(scanner, "Desea incluir numeros (S/N)? ");
            out.println();
            out.println("Especiales: " + CHARS_SYMBOLS + CHARS_AMBIGUOUS);
            cfg.useSymbols = getYesNoInput(scanner, "Desea incluir caracteres especiales (S/N)? ");
            if (cfg.useSymbols) {
                out.println("Ambiguos: " + CHARS_AMBIGUOUS);
                cfg.notAmbiguous = getYesNoInput(scanner, "Desea excluir caracteres ambiguos (S/N)? ");
                out.println();
            }
            out.println("Similares: " + CHARS_SIMILAR);
            cfg.notSimilar = getYesNoInput(scanner, "Desea excluir caracteres similares (S/N)? ");
            out.println();
        }
        return cfg;
    }

    public static String getChars(PasswordConfig passCFG) {
        StringBuilder chars = new StringBuilder();

        if (passCFG.useUpperCase) chars.append(CHARS_UPPERCASE);
        if (passCFG.useLowerCase) chars.append(CHARS_LOWERCASE);
        if (passCFG.useNumbers) chars.append(CHARS_NUMBERS);
        if (passCFG.useSymbols) {
            chars.append(CHARS_SYMBOLS);
            if (!passCFG.notAmbiguous) chars.append(CHARS_AMBIGUOUS);
        }

        String result = chars.toString();

        if (passCFG.notSimilar) {
            result = removeSimilarChars(result);
        }
        return result;
    }

    private static String removeSimilarChars(String input) {
        return input.replaceAll("[" + CHARS_SIMILAR + "]", "");
    }

    public static String genPassword(int length, String chars) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0 ; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();

    }
}