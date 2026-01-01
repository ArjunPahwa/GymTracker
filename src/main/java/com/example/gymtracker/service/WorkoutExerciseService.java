package com.example.gymtracker.service;

import com.example.gymtracker.model.Exercise;
import com.example.gymtracker.model.Workout;
import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.repository.ExerciseRepository;
import com.example.gymtracker.repository.UserRepository;
import com.example.gymtracker.repository.WorkoutExerciseRepository;
import com.example.gymtracker.repository.WorkoutRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final WorkoutRepository workoutRepository;


    public WorkoutExerciseService(ExerciseRepository exerciseRepository, WorkoutExerciseRepository workoutExerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.workoutRepository = workoutRepository;
    }


    public void addExerciseToUserWorkout(Long userid,Long workoutid, String exerciseName){
        Workout workout=workoutRepository.findByIdAndUserId(workoutid,userid)
                .orElseThrow(()-> new RuntimeException("Workout not found with UserId and WorkoutId"));

        Exercise newexercise=exerciseRepository.findByName(exerciseName)
                .orElseThrow(()-> new RuntimeException("Exercise not found"));

        WorkoutExercise workoutExercise= new WorkoutExercise(newexercise);

        workoutExercise.setWorkout(workout);

        workout.getExerciseList().add(workoutExercise);

        workoutExerciseRepository.save(workoutExercise);
    }


    public List<WorkoutExercise> getAllUserWorkoutExercises(Long userid,Long workoutid) {

        Workout workout = workoutRepository.findByIdAndUserId(workoutid,userid)
                .orElseThrow(()->new RuntimeException("Workout not found :("));
        return workout.getExerciseList();


    }

    public void deleteUserWorkoutExercise(Long userId, Long workoutId, String exercisename) {
        Workout workout=workoutRepository.findByIdAndUserId(workoutId,userId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        WorkoutExercise workoutExercise=workout.getExerciseList().stream()
                .filter(we -> we.getExercise().getName().equalsIgnoreCase(exercisename))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Exercise not found in workout"));

        workout.getExerciseList().remove(workoutExercise);
        workoutExerciseRepository.deleteById(workoutExercise.getId());

    }



}
