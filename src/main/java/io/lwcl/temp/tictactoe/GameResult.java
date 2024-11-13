package io.lwcl.temp.tictactoe;

public record GameResult(WIN_TYPE winType) {
    boolean isDraw() {
        return winType.equals(WIN_TYPE.DRAW);
    }

    boolean isScore() {
        return !winType.equals(WIN_TYPE.DRAW);
    }

    boolean isPlayer1Win();
    boolean isPlayer2Win();

}
