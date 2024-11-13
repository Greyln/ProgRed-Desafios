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

    private static final String CHARS_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHARS_NUMBERS = "0123456789";
    private static final String CHARS_SYMBOLS = "!@#$%&^*~-_=+?";
    private static final String CHARS_AMBIGUOUS = "(){}[]/\\~;:,.<>'\"";
    private static final String CHARS_SIMILAR = "0OoLli1";

    private static final int DEFAULT_LENGTH = 12;
    private static final int DEFAULT_AMOUNT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Bienvenido al generador de contraseñas.");


        int amount = getInputAmount(scanner, "Cuantas contraseñas desea generar? ", DEFAULT_AMOUNT);
        int length = getInputAmount(scanner, "Que tamaño de caracteres desea? ", DEFAULT_LENGTH);

        boolean[] charOptions = getCharacterOptions(scanner);
        String chars = getChars(charOptions[0], charOptions[1], charOptions[2], charOptions[3], charOptions[4], charOptions[5]);

        for (int i = 0; i < amount; i++) {
            String password = genPassword(length, chars);
            out.println("Contraseña generada: " + password);
        }

        scanner.close();
    }

    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        out.print(prompt);
        return scanner.next().equalsIgnoreCase("S");
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

    private static boolean[] getCharacterOptions(Scanner scanner) {
        boolean[] options = new boolean[6];
        
        options[0] = true;
        options[1] = true;
        options[2] = true;
        options[3] = true;
        options[4] = false;
        options[5] = false;

        out.print("Desea configurar los tipos de caracteres (S/N)? ");
        boolean toggleChars = scanner.next().equalsIgnoreCase("S");

        if (toggleChars) {
            options[0] = getYesNoInput(scanner, "Desea incluir mayusculas (S/N)? ");
            options[1] = getYesNoInput(scanner, "Desea incluir minusculas (S/N)? ");
            options[2] = getYesNoInput(scanner, "Desea incluir numeros (S/N)? ");
            out.println("Especiales: " + CHARS_SYMBOLS + CHARS_AMBIGUOUS);
            options[3] = getYesNoInput(scanner, "Desea incluir caracteres especiales (S/N)? ");
            out.println("Ambiguos: " + CHARS_AMBIGUOUS);
            options[4] = getYesNoInput(scanner, "Desea excluir caracteres ambiguos (S/N)? ");
            out.println("Similares: " + CHARS_SIMILAR);
            options[5] = getYesNoInput(scanner, "Desea excluir caracteres similares (S/N)? ");
        }
        return options;
    }

    public static String getChars(boolean upperCase, boolean lowerCase, boolean numbers, boolean symbols, boolean notAmbiguous, boolean notSimilar) {
        StringBuilder chars = new StringBuilder();

        if (upperCase) chars.append(CHARS_UPPERCASE);
        if (lowerCase) chars.append(CHARS_LOWERCASE);
        if (numbers) chars.append(CHARS_NUMBERS);
        if (symbols) {
            chars.append(CHARS_SYMBOLS);
            if (!notAmbiguous) chars.append(CHARS_AMBIGUOUS);
        }

        String result = chars.toString();

        if (notSimilar) {
            result = result.replaceAll("[" + CHARS_SIMILAR + "]", "");
        }
        return result;
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
