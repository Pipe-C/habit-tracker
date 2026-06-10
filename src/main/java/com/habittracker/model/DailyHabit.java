package com.habittracker.model;

import com.habittracker.enums.Category;
import com.habittracker.enums.Frequency;
import java.time.LocalDate;

public class DailyHabit extends Habit {

    public DailyHabit(String name, String description, Category category) {
        super(name, description, category, Frequency.DAILY);
    }

    @Override
    public boolean isCompletedForCurrentPeriod() {
        return getCompletionDates().contains(LocalDate.now());
    }

    @Override
    public void display() {
        String status = isCompletedForCurrentPeriod() ? "✅" : "⬜";
        System.out.println(status + " [DIARIO] " + getName()
        + " | " + getCategory().name()
        + " | Racha: " + getCurrentStreak() + "días"
        + " | Total: " + getTotalCompletions() + "veces");
    }
}
