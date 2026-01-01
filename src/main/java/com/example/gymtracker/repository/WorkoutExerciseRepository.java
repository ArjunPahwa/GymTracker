package com.example.gymtracker.repository;

import com.example.gymtracker.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise,Long> {

    Optional<WorkoutExercise> findByIdAndWorkoutIdAndWorkoutUserId(Long exerciseId, Long workoutId, Long userId);

    @Query("SELECT we FROM WorkoutExercise we "+
            "WHERE we.id=:id AND we.workout.id=:workoutid AND we.workout.user.id=:userid")
    Optional<WorkoutExercise> findByIds(@Param("id") Long id,
                                        @Param("workoutid") Long workoutid,
                                        @Param("userid") Long userid);
}
