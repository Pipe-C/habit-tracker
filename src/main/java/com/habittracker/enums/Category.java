package com.habittracker.enums;

// Constantes
public enum Category {
    HEALTH,
    STUDY,
    FINANCE,
    MINDFULNESS,
    OTHER;

    public static Category fromIndex(int index) {
        Category[] values = Category.values();
        if (index >= 1 && index <= values.length) {
            return values[index - 1];
        }
        return OTHER;
    }
}
