package com.example.habit_tracker.controller;

import com.example.habit_tracker.entity.Habit;
import com.example.habit_tracker.entity.User;
import com.example.habit_tracker.repository.HabitRepository;
import com.example.habit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Get all habits for logged-in user
    @GetMapping
    public ResponseEntity<List<Habit>> getHabits(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(habitRepository.findByUser(user));
    }

    // ✅ Add a new habit
    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        habit.setUser(user);
        return ResponseEntity.ok(habitRepository.save(habit));
    }

    // ✅ Mark habit as completed
    @PutMapping("/{id}/complete")
    public ResponseEntity<Habit> markCompleted(@PathVariable Long id) {
        Habit habit = habitRepository.findById(id).orElseThrow();
        habit.setCompleted(true);
        return ResponseEntity.ok(habitRepository.save(habit));
    }

    // ✅ Delete habit
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
        return ResponseEntity.ok("Habit deleted successfully!");
    }
}