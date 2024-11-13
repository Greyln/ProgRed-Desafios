package io.lwcl.challenges;

import java.util.Scanner;

import static java.lang.System.out;

public class TicTacToe {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private final Board board;
    private char currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = 'X'; // Jugador 1 empieza
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                board.print();
                playerMove(scanner);

                if (isWinner()) {
                    board.print();
                    out.println("¡El jugador " + currentPlayer + " ha ganado!");
                    break;
                }
                if (board.isFull()) {
                    board.print();
                    out.println("¡Es un empate!");
                    break;
                }
                switchPlayer();
            }
        }
    }

    private void playerMove(Scanner scanner) {
        int row, col;

        while (true) {
            out.print("Jugador " + currentPlayer + ", ingrese su movimiento (fila y columna): ");
            row = scanner.nextInt() - 1; // Convertir a índice 0
            col = scanner.nextInt() - 1; // Convertir a índice 0

            if (board.isValidMove(row, col)) {
                board.placeMove(row, col, currentPlayer);
                break;
            } else {
                out.println("Movimiento invalido, intente de nuevo.");
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isWinner() {
        return board.checkWinner(currentPlayer);
    }
}

class Board {
    private char[][] board;

    public Board() {
        this.board = new char[3][3];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void print() {
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

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public void placeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Hay al menos una celda vacía
                }
            }
        }
        return true; // No hay celdas vacías
    }

    public boolean checkWinner(char player) {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || // Filas
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { // Columnas
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || // Diagonal principal
                (board[0][2] == player && board[1][1] == player && board[2][0] == player); // Diagonal secundaria
    }
}