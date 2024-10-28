package com.tauseef.app.enums;

public enum Color {

    RESET("\033[0m"),

    // Regular Colors
    CYAN("\033[0;36m"),     // CYAN

    // Bold
    BLACK_BOLD("\033[1;30m"),
    RED_BOLD("\033[1;31m"),
    GREEN_BOLD("\033[1;32m"),
    YELLOW_BOLD("\033[1;33m"),
    BLUE_BOLD("\033[1;34m"),

    // Underline
    WHITE_UNDERLINED("\033[4;37m"),     // WHITE

    // Background
    YELLOW_BACKGROUND("\033[43m"),  // YELLOW
    WHITE_BACKGROUND("\033[47m");     // WHITE

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
