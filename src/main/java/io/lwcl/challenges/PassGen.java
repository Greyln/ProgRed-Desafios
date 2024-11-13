package io.lwcl.challenges;

import java.security.SecureRandom;
import java.util.Scanner;

public class PassGen {

    /* Desafío 6: Generador de contraseñas
        Crear una aplicación que genere contraseñas aleatorias seguras.
        Para resolver este desafío, debemos crear un programa que:
        1. Genere una contraseña aleatoria utilizando caracteres específicos.
        2. Muestre la contraseña al usuario.
     */

    private static final int DEFAULT_LENGTH = 12;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al generador de contraseñas.");
        System.out.print("Cuantas contraseñas desea generar? ");
        int cantidad = scanner.nextInt();

        boolean useMayus = true, useMinus = true;
        boolean useNumbers = true, useSymbols = true;

        System.out.print("Desea configurar los tipos de caracteres (S/N)? ");
        boolean toggleChars = scanner.next().equalsIgnoreCase("S");

        if (toggleChars) {
            System.out.print("Desea incluir mayusculas (S/N)? ");
            useMayus = scanner.next().equalsIgnoreCase("S");

            System.out.print("Desea incluir minusculas (S/N)? ");
            useMinus = scanner.next().equalsIgnoreCase("S");

            System.out.print("Desea incluir numeros (S/N)? ");
            useNumbers = scanner.next().equalsIgnoreCase("S");

            System.out.print("Desea incluir caracteres especiales (S/N)? ");
            useSymbols = scanner.next().equalsIgnoreCase("S");
        }

        String chars = getChars(useMayus, useMinus, useNumbers, useSymbols)

        for (int i = 0; i < cantidad; i++) {
            String contrasena = genPassword(length, chars);
            System.out.println("Contraseña generada: " + contrasena);
        }

        scanner.close();
    }

    public static String getChars(boolean upperCase, boolean lowerCase, boolean numbers, boolean symbols) {
        StringBuilder chars = new StringBuilder();

        if (upperCase) chars.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (lowerCase) chars.append("abcdefghijklmnopqrstuvwxyz");
        if (numbers) chars.append("0123456789");
        if (symbols) chars.append("!@#$%^&*()-_=+[]{};:,.<>?");

        return chars.toString();
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
