package com.habittracker.model;

import com.habittracker.enums.Category;
import com.habittracker.enums.Frequency;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;

// Atributos
public abstract class Habit {
    private String id;
    private String name;
    private String description;
    private Category category;
    private Frequency frequency;
    private boolean active;
    private List<LocalDate> completionDates;

    // Constructor
    public Habit(String name, String description, Category category, Frequency frequency) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.category = category;
        this.frequency = frequency;
        this.active = true;
        this.completionDates = new ArrayList<>();
    }

    // Metodo para obtener la racha actual
    public int getCurrentStreak() {
        int streak = 0;
        LocalDate date = LocalDate.now();
        while (completionDates.contains(date)) {
            streak++;
            date = date.minusDays(1);
        }
        return streak;
    }

    // Metodo para contar las veces completadas
    public int getTotalCompletions() {
        return completionDates.size();
    }

    // Metodo para agregar fechas a la lista.
    public void markCompleted() {
        LocalDate today = LocalDate.now();
        if (!completionDates.contains(today)) {
            completionDates.add(today);
            System.out.println("✅ ¡Hábito '" + getName() + "' completado hoy!");
        } else {
            System.out.println("ℹ️ Ya marcaste este hábito hoy.");
        }
    }

    // Métodos abstractos
    public abstract boolean isCompletedForCurrentPeriod();

    public abstract void display();

    // Getters y Setters
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Frequency getFrequency() { return frequency; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<LocalDate> getCompletionDates() { return completionDates; }
}