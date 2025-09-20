package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.Habit;
import com.example.habit_tracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    // Create or Update Habit
    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    // Get All Habits
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }
    // Get Habit by ID
    public Habit getHabitById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }

    // Delete Habit
    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
}
