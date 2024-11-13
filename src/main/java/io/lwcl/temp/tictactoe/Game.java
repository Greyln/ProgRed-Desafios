package io.lwcl.temp.tictactoe;

import java.util.Scanner;

import static java.lang.System.out;

class Game {
    private final Board board;
    private char currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Board.PLAYER_1; // Jugador 1 empieza
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                board.print();
                playerMove(scanner);

                if (isWinner()) {
                    board.print();
                    out.printf("The player %s won!", currentPlayer);
                    out.println();
                    break;
                }
                if (board.isFull()) {
                    board.print();
                    out.println("It's a draw!");
                    break;
                }
                switchPlayer();
            }
        }
    }

    private void playerMove(Scanner scanner) {
        int row, col;

        while (true) {
            out.printf("Player %s, enter your movements: ", currentPlayer);
            out.println();

            out.print("Row: ");
            row = scanner.nextInt() - 1; // Convertir a índice 0
            out.print("Column: ");
            col = scanner.nextInt() - 1; // Convertir a índice 0

            if (board.isValidMove(row, col)) {
                board.placeMove(row, col, currentPlayer);
                break;
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
