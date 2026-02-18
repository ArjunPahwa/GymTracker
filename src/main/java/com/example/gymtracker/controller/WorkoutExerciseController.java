package com.example.gymtracker.controller;


import com.example.gymtracker.model.User;
import com.example.gymtracker.model.WorkoutExercise;
import com.example.gymtracker.service.WorkoutExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/users/workouts/{workoutId}/exercises")
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    public WorkoutExerciseController(WorkoutExerciseService workoutExerciseService) {
        this.workoutExerciseService = workoutExerciseService;
    }


    @PostMapping
    public ResponseEntity<String> addUserWorkoutExercise(Authentication authentication,
                                                         @PathVariable Long workoutId,
                                                         @RequestParam String exercisename){
        User user= (User) authentication.getPrincipal();

        workoutExerciseService.addExerciseToUserWorkout(user.getId(),workoutId,exercisename);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exercise added succesfully to workout :)");
    }

    @GetMapping("/all")
    public List<WorkoutExercise> getAllUserWorkoutExercises(Authentication authentication, @PathVariable Long workoutId){

        User user = (User) authentication.getPrincipal();

        return workoutExerciseService.getAllUserWorkoutExercises(user.getId(), workoutId);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserWorkoutExercise(Authentication authentication,
                                                            @PathVariable Long workoutId,
                                                            @RequestParam String exercisename){
        User user = (User) authentication.getPrincipal();
        final Long userid = user.getId();

        workoutExerciseService.deleteUserWorkoutExercise(userid,workoutId,exercisename);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Exercise deleted successfully for userid: " +userid+ " and workoutID: "+workoutId);




    }



}
