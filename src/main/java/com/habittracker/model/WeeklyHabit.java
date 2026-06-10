package com.habittracker.model;

import com.habittracker.enums.Category;
import com.habittracker.enums.Frequency;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class WeeklyHabit extends Habit {

    private int timesPerWeek;

    public WeeklyHabit(String name, String description, Category category, int timesPerWeek) {
        super(name, description, category, Frequency.WEEKLY);
        this.timesPerWeek = timesPerWeek;
    }

    // Metodo para evaluar completadas de lunes a domingo.
    @Override
    public boolean isCompletedForCurrentPeriod() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        long completionsThisWeek = getCompletionDates().stream()
                .filter(date -> !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek))
                .count();

        return completionsThisWeek >= timesPerWeek;
    }

    @Override
    public void display() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        long thisWeek = getCompletionDates().stream()
                .filter(date -> !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek))
                .count();

        String status = isCompletedForCurrentPeriod() ? "✅" : "⬜";
        System.out.println(status + " [SEMANAL] " + getName()
        + " | " + getCategory().name()
        + " | Esta semana: " + thisWeek + "/" + timesPerWeek
        + " | Racha: " + getCurrentStreak() + " semanas");
    }

    public int getTimesPerWeek() { return timesPerWeek; }
}
