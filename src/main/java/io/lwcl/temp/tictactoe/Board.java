package io.lwcl.temp.tictactoe;

import io.lwcl.utils.AnsiColor;

import static java.lang.System.out;

class Board {
    private final char[][] data;

    private static final int SIZE = 3;

    public static final char PLAYER_1 = 'X';
    private static final char PLAYER_2 = 'Y';
    private static final char EMPTY = ' ';

    public static boolean IS_DRAW = false;
    public static boolean IS_WIN = false;
    public static String RESULT = null;

    public Board() {
        this.data = new char[SIZE][SIZE];
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                data[i][j] = EMPTY;
            }
        }
    }

    private char getSus() {
        return EMPTY;
    }

    private char getStrikeChar(int row, int col, int decrement) {
        if (RESULT.equals("DIAG")) {
            if (row == col) {
                return '╲';
            }
        } else if (RESULT.equals("RDIAG")) {
            if (decrement == col) {
                return '╱';
            }
        } else if (RESULT.equals("ROW")) {
            if (0 == row) {
                return '—';
            }
        } else if (RESULT.equals("COL")) {
            if (0 == col) {
                return '|';
            }
        }
        return data[row][col];
    }

    private String DrawedChar(int row, int col) {
        AnsiColor color = switch (data[row][col]) {
            case PLAYER_1 -> AnsiColor.RED;
            case PLAYER_2 -> AnsiColor.BLUE;
            default -> AnsiColor.RESET;
        };

        char x = data[row][col];

        if (IS_WIN) {
            x = getStrikeChar();
        }

        return color.code + x + AnsiColor.RESET.code;
    }

    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(" C1  C2  C3  \n");
        sb.append("┏━━━┳━━━┳━━━┓\n");

        int x = SIZE - 1;

        for (int row = 0; row < SIZE; row++, x--) {
            sb.append("┃ ");
            for (int col = 0; col < SIZE; col++) {
                sb.append(DrawedChar(row, col));

                if (col < SIZE - 1) {
                    sb.append(" ┃ ");
                }
            }
            sb.append(" ┃ R").append(row + 1).append("\n");
            if (row < SIZE - 1) {
                sb.append("┣━━━╋━━━╋━━━┫\n");
            }
        }
        sb.append("┗━━━┻━━━┻━━━┛\n");
        return sb.toString();
    }

    public void print() {
        out.println();
        out.println();
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2) {
            out.println("Invalid row. it must be between 1 and 3. Try again.");
            return false;
        }
        if (col < 0 || col > 2) {
            out.println("Invalid column. it must be between 1 and 3. Try again.");
            return false;
        }
        if (data[row][col] != ' ') {
            out.println("The cell is already occupied. Try again.");
            return false;
        }
        return true;
    }

    public void placeMove(int row, int col, char player) {
        data[row][col] = player;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (data[i][j] == ' ') {
                    return false; // Hay al menos una celda vacía
                }
            }
        }
        return true; // No hay celdas vacías
    }

    public boolean checkWinner(char player) {
        // Verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((data[i][0] == player && data[i][1] == player && data[i][2] == player) || // Filas
                    (data[0][i] == player && data[1][i] == player && data[2][i] == player)) { // Columnas
                IS_WIN = true;
                return true;
            }
        }
        return (data[0][0] == player && data[1][1] == player && data[2][2] == player) || // Diagonal principal
                (data[0][2] == player && data[1][1] == player && data[2][0] == player); // Diagonal secundaria
    }
}
