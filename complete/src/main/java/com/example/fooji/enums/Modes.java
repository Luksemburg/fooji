package com.example.fooji.enums;

public enum Modes {
    TRANSLATE("translate"),
    MIXED("mixed"),
    HIRAGANA("hiragana");

    private final String description;

    Modes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
