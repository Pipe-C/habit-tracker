package com.habittracker.controller;

import com.habittracker.exceptions.InvalidHabitException;
import com.habittracker.model.Habit;
import com.habittracker.service.HabitService;
import com.habittracker.dto.HabitRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @GetMapping
    public List<Habit> getAllHabits() {
        return service.getAllActiveHabits();
    }

    @PostMapping
    public Habit createHabit(@RequestBody HabitRequest request) throws InvalidHabitException {
        if (request.getTimesPerWeek() > 0) {
            return service.createWeeklyHabit(
                    request.getName(),
                    request.getDescription(),
                    request.getCategory(),
                    request.getTimesPerWeek()
            );
        }
        return service.createDailyHabit(
                request.getName(),
                request.getDescription(),
                request.getCategory()
        );
    }

    @PostMapping("/{index}/complete")
    public String completeHabit(@PathVariable int index) {
        service.markHabitCompleted(index);
        return "Hábito marcado como completado";
    }

    @DeleteMapping("/{index}")
    public String deleteHabit(@PathVariable int index) {
        service.deleteHabit(index);
        return "Hábito eliminado";
    }

}
