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

    public static void main(String[] args) {
        // Try-with-resources para AutoClose.
        try (Scanner scanner = new Scanner(System.in)) {
            out.println("Bienvenido al generador de contraseñas.");

            PasswordConfig passCFG = getPassCFG(scanner);
            String chars = getChars(passCFG);

            for (int i = 0; i < passCFG.amount; i++) {
                String password = genPassword(passCFG.length, chars);
                out.println("Contraseña generada: " + password);
            }
        }
    }

    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        out.print(prompt);
        boolean answer = scanner.next().equalsIgnoreCase("S");
        out.println();
        return answer;
    }

    private static int getInputAmount(Scanner scanner, String prompt, int defaultValue) {
        out.print(prompt);

        try {
            return scanner.nextInt();
        } catch (NoSuchElementException | IllegalArgumentException e) {
            out.println("Utilizando cantidad predeterminada: " + defaultValue);
            scanner.nextLine(); // Clear the invalid input
            return defaultValue;
        }
    }

    public static PasswordConfig getPassCFG(Scanner scanner) {
        PasswordConfig cfg = new PasswordConfig();

        cfg.amount = getInputAmount(scanner, "Cuantas contraseñas desea generar? ", DEFAULT_AMOUNT);
        cfg.length = getInputAmount(scanner, "Que tamaño de caracteres desea? ", DEFAULT_LENGTH);

        out.print("Desea configurar los tipos de caracteres (S/N)? ");
        boolean toggleChars = scanner.next().equalsIgnoreCase("S");

        if (toggleChars) {
            cfg.useUpperCase = getYesNoInput(scanner, "Desea incluir mayusculas (S/N)? ");
            cfg.useLowerCase = getYesNoInput(scanner, "Desea incluir minusculas (S/N)? ");
            cfg.useNumbers = getYesNoInput(scanner, "Desea incluir numeros (S/N)? ");

            out.println("Especiales: " + CHARS_SYMBOLS + CHARS_AMBIGUOUS);
            cfg.useSymbols = getYesNoInput(scanner, "Desea incluir caracteres especiales (S/N)? ");
            out.println("Ambiguos: " + CHARS_AMBIGUOUS);
            cfg.notAmbiguous = getYesNoInput(scanner, "Desea excluir caracteres ambiguos (S/N)? ");
            out.println("Similares: " + CHARS_SIMILAR);
            cfg.notSimilar = getYesNoInput(scanner, "Desea excluir caracteres similares (S/N)? ");
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