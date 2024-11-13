package io.lwcl.temp.tictactoe;

public enum WIN_TYPE {
    VERTICAL,
    HORIZONTAL,
    DIAGONAL,
    REVERSE_DIAGONAL,
    DRAW;

    private Integer index = null;

    WIN_TYPE() {}

    WIN_TYPE(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
