package com.example.gymtracker.repository;

import com.example.gymtracker.model.Exercise;
import com.example.gymtracker.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    Optional<Exercise> findByName(String name);

}
