package com.habittracker.repository;

import com.habittracker.model.Habit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HabitRepository implements Repository<Habit> {

    private final Map<String, Habit> habits;

    public HabitRepository() {
        this.habits = new LinkedHashMap<>();
    }

    @Override
    public void save(Habit entity) {
        habits.put(entity.getId(), entity);
    }

    @Override
    public Optional<Habit> findById(String id) {
        return Optional.ofNullable(habits.get(id));
    }

    @Override
    public List<Habit> findAll() {
        return new ArrayList<>(habits.values());
    }

    @Override
    public void delete(String id) {
        habits.remove(id);
    }

    @Override
    public boolean exists(String id) {
        return habits.containsKey(id);
    }

    public List<Habit> findAllActive() {
        return habits.values().stream()
                .filter(Habit::isActive)
                .collect(java.util.stream.Collectors.toList());
    }
}
