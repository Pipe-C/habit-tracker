package com.habittracker.service;

import com.habittracker.enums.Category;
import com.habittracker.exceptions.HabitNotFoundException;
import com.habittracker.exceptions.InvalidHabitException;
import com.habittracker.model.DailyHabit;
import com.habittracker.model.Habit;
import com.habittracker.model.WeeklyHabit;
import com.habittracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository repository;

    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public DailyHabit createDailyHabit(String name, String description, Category category)
        throws InvalidHabitException {
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidHabitException("El nombre del hábito no puede estar vacío.");
            }
            DailyHabit habit = new DailyHabit(name.trim(), description.trim(), category);
            repository.save(habit);
            return habit;
        }

    public WeeklyHabit createWeeklyHabit(String name, String description, Category category, int timesPerWeek)
            throws InvalidHabitException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidHabitException("El nombre del hábito no puede estar vacío.");
        }
        if (timesPerWeek < 1 || timesPerWeek > 7) {
            throw new InvalidHabitException("Las veces por semana deben estar entre 1 y 7.");
        }
        WeeklyHabit habit = new WeeklyHabit(name.trim(), description.trim(), category, timesPerWeek);
        repository.save(habit);
        return habit;
    }

    public List<Habit> getAllActiveHabits() {
        return repository.findAllActive();
    }

    public List<Habit> getAllHabits() {
        return repository.findAll();
    }

    public void markHabitCompleted(int index) {
        List<Habit> habits = getAllActiveHabits();
        if (index < 1 || index > habits.size()) {
            throw new HabitNotFoundException("Número inválido.");
        }
        Habit habit = habits.get(index - 1);
        habit.markCompleted();
        repository.save(habit);
    }

    public void deleteHabit(int index) {
        List<Habit> habits = getAllActiveHabits();
        if (index < 1 || index > habits.size()) {
            throw new HabitNotFoundException("Número nválido.");
        }
        Habit habit = habits.get(index - 1);
        repository.delete(habit.getId());
        System.out.println("🗑️ Hábito '" + habit.getName() + "' eliminado");
    }

    public void showStats() {
        List<Habit> active = getAllActiveHabits();

        if (active.isEmpty()) {
            System.out.println(" No tienes hábitos activos aún.");
            return;
        }

        long completedToday = active.stream()
                .filter(Habit::isCompletedForCurrentPeriod)
                .count();

        int totalCompletions = active.stream()
                .mapToInt(Habit::getTotalCompletions)
                .sum();

        System.out.println(" 💡 Total de hábitos activos  : " + active.size());
        System.out.println(" ✅ Completados hoy/período   : " + completedToday + "/" + active.size());
        System.out.println(" ✍️ Completaciones totales    : " + totalCompletions);
    }
}
