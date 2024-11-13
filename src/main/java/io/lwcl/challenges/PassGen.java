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


        int amount = DEFAULT_AMOUNT;
        out.print("Cuantas contraseñas desea generar? ");
        try {
            amount = scanner.nextInt();
        } catch (NoSuchElementException e) {
            out.println("Utilizando predeterminado: " + DEFAULT_AMOUNT);
        }

        int length = DEFAULT_LENGTH;
        out.print("Que tamaño de caracteres desea? ");
        try {
            length = scanner.nextInt();
        } catch (NoSuchElementException e) {
            out.println("Utilizando predeterminado: " + DEFAULT_LENGTH);
        }

        boolean useMayus = true, useMinus = true;
        boolean useNumbers = true, useSymbols = true;
        boolean notAmbiguous = false, notSimilar = false;

        out.print("Desea configurar los tipos de caracteres (S/N)? ");
        boolean toggleChars = scanner.next().equalsIgnoreCase("S");

        if (toggleChars) {
            String fs = ":: %s ::%n";

            out.print("Desea incluir mayusculas (S/N)? ");
            useMayus = scanner.next().equalsIgnoreCase("S");

            out.print("Desea incluir minusculas (S/N)? ");
            useMinus = scanner.next().equalsIgnoreCase("S");

            out.print("Desea incluir numeros (S/N)? ");
            useNumbers = scanner.next().equalsIgnoreCase("S");

            out.printf(fs, CHARS_SYMBOLS+CHARS_AMBIGUOUS);
            out.print("Desea incluir caracteres especiales (S/N)? ");
            useSymbols = scanner.next().equalsIgnoreCase("S");

            out.printf(fs, CHARS_AMBIGUOUS);
            out.print("Desea excluir caracteres ambiguos (S/N)? ");
            notAmbiguous = scanner.next().equalsIgnoreCase("S");

            out.printf(fs, CHARS_SIMILAR);
            out.print("Desea excluir caracteres similares (S/N)? ");
            notSimilar = scanner.next().equalsIgnoreCase("S");
        }

        String chars = getChars(useMayus, useMinus, useNumbers, useSymbols, notAmbiguous, notSimilar);

        for (int i = 0; i < amount; i++) {
            String password = genPassword(length, chars);
            out.println("Contraseña generada: " + password);
        }

        scanner.close();
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

    private static String genPassword(int length, String chars) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }
}
