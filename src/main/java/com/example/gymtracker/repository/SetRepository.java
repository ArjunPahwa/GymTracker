package com.example.gymtracker.repository;

import com.example.gymtracker.model.WorkoutSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetRepository extends JpaRepository<WorkoutSet,Long> {

    Optional<WorkoutSet> findByIdAndExercise_IdAndExercise_Workout_IdAndExercise_Workout_User_Id(
            Long id,
            Long workoutExerciseId,
            Long workoutId,
            Long userId);

}
