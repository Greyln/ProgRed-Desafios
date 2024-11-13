package io.lwcl.simplified;
import java.util.Scanner;

import static java.lang.System.out;

public class TicTacToe {

    /* Desafío 5: Juego de Ta-Te-Ti
        Crear un juego de Ta-Te-Ti para dos jugadores.
        Para resolver este desafío, debemos crear un programa que:
        1. Cree un tablero de juego 3x3.
        2. Pida a dos jugadores que ingresen sus movimientos.
        3. Verifique si un jugador ha ganado o si el juego es un empate.
        4. Muestre el resultado al final del juego.
     */

    private static char[][] board;
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        board = new char[3][3];
        currentPlayer = 'X'; // Jugador 1 empieza

        initializeBoard();

        while (true) {
            printBoard();
            playerMove(scanner);

            if (isWinner()) {
                printBoard();
                out.println("¡El jugador " + currentPlayer + " ha ganado!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                out.println("¡Es un empate!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        out.println("Tablero de juego:");
        for (int i = 0; i < 3; i++) {
            out.print(" | ");
            for (int j = 0; j < 3; j++) {
                out.print(board[i][j] + " | ");
            }
            out.println();
            out.println("-------------");
        }
    }

    private static void playerMove(Scanner scanner) {
        int row, col;

        while (true) {
            out.print("Jugador " + currentPlayer + ", ingrese su movimiento (fila y columna): ");
            row = scanner.nextInt() - 1; // Convertir a índice 0
            col = scanner.nextInt() - 1; // Convertir a índice 0

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                out.println("Movimiento inválido, intente de nuevo.");
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean isWinner() {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) || // Filas
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) { // Columnas
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) || // Diagonal principal
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer); // Diagonal secundaria
    }


    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Hay al menos una celda vacía
                }
            }
        }
        return true; // No hay celdas vacías
    }
}
