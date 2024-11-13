package io.lwcl.challenges;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class GuessingGame {

    /* Desafío 2: Juego de adivinanza
        Crear un juego que genere un número aleatorio entre 1 y 100, y el usuario tenga que
        adivinar el número.
        Para resolver este desafío, debemos crear un programa que:
        1. Genere un número aleatorio entre 1 y 100.
        2. Pida al usuario que adivine el número.
        3. Verifique si el número ingresado es mayor o menor que el número aleatorio.
        4. Repita los pasos 2 y 3 hasta que el usuario adivine el número correctamente.
     */

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final Random random = new Random();

    public static void main(String[] args) {
        int randomNumber = generateRandomNumber();
        playGame(randomNumber);
    }

    private static int generateRandomNumber() {
        return random.nextInt(MAX_NUMBER) + MIN_NUMBER; // Genera un número entre 1 y 100
    }

    private static void playGame(int randomNumber) {
        boolean guessed = false;

        out.println("Bienvenido al juego de adivinanza!");
        out.println("Se ha generado un numero entre " + MIN_NUMBER + " y " + MAX_NUMBER + ". Intenta adivinarlo.");

        // Try-with-resource para AutoClose.
        try (Scanner scanner = new Scanner(System.in)) {
            while (!guessed) {
                int attempt = getUserInput(scanner);
                guessed = checkGuess(attempt, randomNumber);
            }
        }
    }

    private static int getUserInput(Scanner scanner) {
        int attempt = 0;
        boolean validInput = false;

        while (!validInput) {
            out.print("Introduce tu intento: ");
            try {
                attempt = scanner.nextInt();
                if (attempt < MIN_NUMBER || attempt > MAX_NUMBER) {
                    out.println("Por favor, introduce un numero entre " + MIN_NUMBER + " y " + MAX_NUMBER + ".");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                out.println("Entrada invalida. Por favor, introduce un numero.");
                scanner.next(); // Limpiar el buffer
            }
        }

        return attempt;
    }

    private static boolean checkGuess(int attempt, int randomNumber) {
        if (attempt < randomNumber) {
            out.println("El numero es mayor. Intenta de nuevo.");
            return false;
        } else if (attempt > randomNumber) {
            out.println("El numero es menor. Intenta de nuevo.");
            return false;
        } else {
            out.println("Felicidades! Adivinaste el numero: " + randomNumber);
            return true;
        }
    }
}
