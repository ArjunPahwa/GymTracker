package com.example.gymtracker.controller;


import com.example.gymtracker.model.Exercise;
import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.service.WorkoutExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users/{userId}/workouts/{workoutId}/exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }


    @PostMapping
    public ResponseEntity<String> addUserWorkoutExercise(@PathVariable Long userId,
                                                            @PathVariable Long workoutId,
                                                            @RequestParam String exercisename){
        workoutExerciseService.addExerciseToUserWorkout(userId,workoutId,exercisename);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exercise added succesfully to workout :)");
    }

    @GetMapping("/all")
    public List<WorkoutExercise> getAllUserWorkoutExercises(@PathVariable Long userId, @PathVariable Long workoutId){

        return workoutExerciseService.getAllUserWorkoutExercises(userId,workoutId);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserWorkoutExercise(@PathVariable Long userId,
                                                            @PathVariable Long workoutId,
                                                            @RequestParam String exercisename){
        workoutExerciseService.deleteUserWorkoutExercise(userId,workoutId,exercisename);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exercise deleted successfully for userid: " +userId + " and workoutID: "+workoutId);




    }



}
