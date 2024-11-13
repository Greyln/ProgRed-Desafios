package io.lwcl.challenges;

import java.util.Random;
import java.util.Scanner;

import static io.lwcl.utils.Helper.getInputInt;
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
        return random.nextInt(MAX_NUMBER) + MIN_NUMBER;
    }

    private static void playGame(int randomNumber) {
        out.println("Welcome to the guessing game!");
        out.printf("A number has been generated between (%d and %d). Try guessing it.", MIN_NUMBER, MAX_NUMBER);
        out.println();

        // Try-with-resource para AutoClose.
        try (Scanner scanner = new Scanner(System.in)) {
            Integer attempt;
            do {
                out.print("Input your guess: ");
                attempt = getInputInt(scanner, MIN_NUMBER, MAX_NUMBER);
            } while (!checkGuess(attempt, randomNumber));
        }
    }

    private static boolean checkGuess(Integer attempt, int randomNumber) {
        if (attempt == null) return false;

        if (attempt < randomNumber) {
            out.println("Too high! Try again.");
            return false;
        } else if (attempt > randomNumber) {
            out.println("Too low! Try again.");
            return false;
        } else {
            out.println("Correct! Congratulations!");
            return true;
        }
    }
}
