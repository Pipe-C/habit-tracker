package com.habittracker.dto;

import com.habittracker.enums.Category;

public class HabitRequest {

    private String name;
    private String description;
    private Category category;
    private int timesPerWeek;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public int getTimesPerWeek() { return timesPerWeek; }
    public void setTimesPerWeek(int timesPerWeek) { this.timesPerWeek = timesPerWeek; }
}
