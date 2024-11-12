package io.lwcl.utils;

enum AnsiColor {

    // Reset
    RESET("\033[0m"),

    // Text Colors
    BLACK("\033[30m"),
    RED("\033[31m"),
    GREEN("\033[32m"),
    YELLOW("\033[33m"),
    BLUE("\033[34m"),
    MAGENTA("\033[35m"),
    CYAN("\033[36m"),
    WHITE("\033[37m"),

    // Light Text Colors
    LI_BLACK("\033[90m"),
    LI_RED("\033[91m"),
    LI_GREEN("\033[92m"),
    LI_YELLOW("\033[93m"),
    LI_BLUE("\033[94m"),
    LI_MAGENTA("\033[95m"),
    LI_CYAN("\033[96m"),
    LI_WHITE("\033[97m"),

    // Background Colors
    BLACK_BG("\033[40m"),
    RED_BG("\033[41m"),
    GREEN_BG("\033[42m"),
    YELLOW_BG("\033[43m"),
    BLUE_BG("\033[44m"),
    MAGENTA_BG("\033[45m"),
    CYAN_BG("\033[46m"),
    WHITE_BG("\033[47m"),

    // Light Background Colors
    LI_BLACK_BG("\033[100m"),
    LI_RED_BG("\033[101m"),
    LI_GREEN_BG("\033[102m"),
    LI_YELLOW_BG("\033[103m"),
    LI_BLUE_BG("\033[104m"),
    LI_MAGENTA_BG("\033[105m"),
    LI_CYAN_BG("\033[106m"),
    LI_WHITE_BG("\033[107m"),

    // Styles
    BOLD("\033[1m"),
    UNDERLINE("\033[4m"),
    REVERSED("\033[7m");

    private final String code;

    AnsiColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
