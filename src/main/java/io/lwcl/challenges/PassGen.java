package io.lwcl.challenges;

import java.security.SecureRandom;
import java.util.Scanner;

import static io.lwcl.utils.Helper.getInputAmount;
import static io.lwcl.utils.Helper.getYesNoInput;
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
            out.println("Bienvenido al generador de passwords.");
            out.println();

            PasswordConfig passCFG = getPassCFG(scanner);
            String chars = getChars(passCFG);

            if (chars.isEmpty()) {
                out.println("No se han seleccionado caracteres validos para generar la password.");
                return;
            }

            out.println("Generando...");
            out.println();

            for (int i = 0; i < passCFG.amount; i++) {
                String password = genPassword(passCFG.length, chars);
                out.println("["+ i +"]: " + password);
            }
        }
    }

    public static PasswordConfig getPassCFG(Scanner scanner) {
        PasswordConfig cfg = new PasswordConfig();

        out.printf("How many passwords do you want to generate? (1-%d): ", MAX_AMOUNT);
        cfg.amount = getInputAmount(scanner, DEFAULT_AMOUNT, MAX_AMOUNT);
        out.println();

        out.printf("Character length? (1-%d): ", MAX_LENGTH);
        cfg.length = getInputAmount(scanner, DEFAULT_LENGTH, MAX_LENGTH);
        out.println();

        boolean toggleChars = getYesNoInput(scanner, "Do you want to select the character types (Y/N)? ");
        out.println();

        if (toggleChars) configCharTypes(scanner, cfg);

        return cfg;
    }

    private static void configCharTypes(Scanner scanner, PasswordConfig cfg) {
        cfg.useUpperCase = getYesNoInput(scanner, "Do you want to include capital letters? (Y/N)? ");
        cfg.useLowerCase = getYesNoInput(scanner, "Do you want to include lowercase letters (Y/N)? ");
        cfg.useNumbers = getYesNoInput(scanner, "Do you want to include numbers (Y/N)? ");
        out.println();

        out.println("Especiales: " + CHARS_SYMBOLS + CHARS_AMBIGUOUS);
        cfg.useSymbols = getYesNoInput(scanner, "Do you want to include special characters (Y/N)? ");
        out.println();

        if (cfg.useSymbols) {
            out.println("Ambiguos: " + CHARS_AMBIGUOUS);
            cfg.notAmbiguous = getYesNoInput(scanner, "Do you want to exclude ambiguous characters (Y/N)? ");
            out.println();
        }

        out.println("Similares: " + CHARS_SIMILAR);
        cfg.notSimilar = getYesNoInput(scanner, "Do you want to exclude similar characters (Y/N)? ");
        out.println();
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